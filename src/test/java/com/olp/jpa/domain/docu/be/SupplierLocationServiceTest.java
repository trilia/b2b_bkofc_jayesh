package com.olp.jpa.domain.docu.be;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.jpa.domain.docu.be.model.BankAccountEntity;
import com.olp.jpa.domain.docu.be.model.BeEnums;
import com.olp.jpa.domain.docu.be.model.BeEnums.PartyType;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.be.repo.SupplierLocationService;
import com.olp.jpa.domain.docu.be.repo.SupplierService;
import com.olp.jpa.domain.docu.be.service.BankAccountService;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.repo.LocationService;

/**
 * @author Jayesh
 *
 */
public class SupplierLocationServiceTest extends BaseSpringAwareTest {

	/**
	 * 
	 */
	public SupplierLocationServiceTest() {

	}

	@Autowired
    @Qualifier("supplierService")
    private SupplierService suppSvc;
	
	@Autowired
    @Qualifier("supplierLocationService")
    private SupplierLocationService suppLocSvc;
	
	@Autowired
    @Qualifier("locationService")
    private LocationService locSvc;
	
	@Autowired
    @Qualifier("bankAcctService")
    private BankAccountService bankSvc;
	
	@Before
	public void before() {
		suppLocSvc.deleteAll(false);
		locSvc.deleteAll(false);
		bankSvc.deleteAll(false);
		setupData();
	}

	private void setupData() {
		
		SupplierLocationEntity suppLocEntity = new SupplierLocationEntity();
		
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar cal0 = builder.setDate(2017, 11, 16).build();
		
		BankAccountEntity be1 = CommonBE.makeBankAccount();
		be1.setOwningEntity(PartyType.LOGISTIC_PARTNER);
		be1.setSupplierLocRef(suppLocEntity);
        
        BankAccountEntity be2 = CommonBE.makeBankAccount();
        be2.setOwningEntity(PartyType.SUPPLIER);
        be2.setSupplierLocRef(suppLocEntity);
        
        List<BankAccountEntity> bankList = new ArrayList<BankAccountEntity>();
        bankList.add(be1);
        bankList.add(be2);
		
        LocationEntity locEntity = CommonBE.makeLoc();
        locEntity.setStartDate(cal0.getTime());
        locEntity.setEndDate(cal0.getTime());
        locSvc.add(locEntity);
        
		suppLocEntity.setLocation(locEntity);
		suppLocEntity.setLocationCode(locEntity.getLocationAlias());
		
		suppLocSvc.add(suppLocEntity);
		
	}

	@Test
	public void test1_Add() {
		List<SupplierLocationEntity> list = suppLocSvc.findAll();
        
        assertNotNull("Supplier Location list should not be null !", list);
        assertEquals("1 Supplier Location entity", 1, list.size());

	}
	
	@Test
	public void test2_Add() {
		
		SupplierLocationEntity suppLocEntity = new SupplierLocationEntity();
		
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar cal0 = builder.setDate(2017, 11, 16).build();
		
		BankAccountEntity be1 = CommonBE.makeBankAccount();
		be1.setOwningEntity(PartyType.LOGISTIC_PARTNER);
		be1.setSupplierLocRef(suppLocEntity);
        
        BankAccountEntity be2 = CommonBE.makeBankAccount();
        be2.setOwningEntity(PartyType.SUPPLIER);
        be2.setSupplierLocRef(suppLocEntity);
        
        List<BankAccountEntity> bankList = new ArrayList<BankAccountEntity>();
        bankList.add(be1);
        bankList.add(be2);
		
        LocationEntity locEntity = CommonBE.makeLoc();
        locEntity.setStartDate(cal0.getTime());
        locEntity.setEndDate(cal0.getTime());
        locSvc.add(locEntity);
        
		suppLocEntity.setLocation(locEntity);
		suppLocEntity.setLocationCode(locEntity.getLocationAlias());
		
		suppLocSvc.add(suppLocEntity);
		
		List<SupplierLocationEntity> list = suppLocSvc.findAll();
		
		assertNotNull("Supplier Location list should not be null !", list);
        assertEquals("2 Supplier Location entity", 2, list.size());

	}

	@Test
	public void test3_Update() {

		List<SupplierLocationEntity> list = suppLocSvc.findAll();
		
		assertNotNull("Supplier Location list should not be null !", list);
		SupplierLocationEntity suppLocEntity = list.get(0);

		List<BankAccountEntity> accts = suppLocEntity.getBankAccounts();
        assertNotNull("Accounts list should not be null !", accts);
      
        BankAccountEntity bank2 = CommonBE.makeBankAccount();
        bank2.setSupplierLocRef(suppLocEntity);
        bank2.setOwningEntity(BeEnums.PartyType.SUPPLIER);
        
        accts.add(bank2);
		
		suppLocSvc.update(suppLocEntity);
		
		list = suppLocSvc.findAll();
        assertEquals(" Supplier Location Bank Account entity", 1,list.get(0).getBankAccounts().size() );
	}

	@Test
	public void test4_Delete() {

		List<SupplierLocationEntity> list = suppLocSvc.findAll();
		
		assertNotNull("Supplier Location list should not be null !", list);
		SupplierLocationEntity suppLocEntity = list.get(0);
		
		suppLocSvc.delete(suppLocEntity.getId());
		list = suppLocSvc.findAll();
        assertEquals("Supplier Location entity", "0", list.size());
	}
}
