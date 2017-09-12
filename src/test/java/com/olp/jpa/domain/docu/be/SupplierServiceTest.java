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
import com.olp.jpa.domain.docu.be.model.LegalInfoBean;
import com.olp.jpa.domain.docu.be.model.MerchantEntity;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.be.model.BeEnums.PartyType;
import com.olp.jpa.domain.docu.be.repo.SupplierLocationService;
import com.olp.jpa.domain.docu.be.repo.SupplierService;
import com.olp.jpa.domain.docu.be.service.BankAccountService;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.repo.LocationService;

/**
 * @author Jayesh
 *
 */
public class SupplierServiceTest extends BaseSpringAwareTest {

	/**
	 * 
	 */
	public SupplierServiceTest() {

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
		suppSvc.deleteAll(false);
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
		
		MerchantEntity me = CommonBE.makeMerchant();
		 
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
		//suppLocEntity.setBankAccounts(bankList);
		
		List<SupplierLocationEntity> suppLocList = new ArrayList<SupplierLocationEntity>();
		suppLocList.add(suppLocEntity);
		
		LegalInfoBean le1 = new LegalInfoBean();
        le1.setAttrName("PAN Number");
        le1.setAttrValue("AABBCCDD");
        le1.setAttrSequence(1);
        le1.setDescription("Pan Number of Merchant");
        
        List<LegalInfoBean> leList = new ArrayList<LegalInfoBean>();
        leList.add(le1);
		
		SupplierEntity suppEntity = new SupplierEntity();
		suppEntity.setSupplierName("HM Media");
		suppEntity.setSupplierCode("HM");
		suppEntity.setSupplierLocations(suppLocList);
		suppEntity.setLegalInfo(leList);

		suppLocSvc.add(suppLocEntity);
		
		suppSvc.add(suppEntity);

	}

	@Test
	public void test1_Add() {
		List<SupplierEntity> list = suppSvc.findAll();
        
        assertNotNull("Supplier list should not be null !", list);
        assertEquals("1 Supplier entity", 1, list.size());

	}
	
	@Test
	public void test2_Add() {
		
SupplierLocationEntity suppLocEntity = new SupplierLocationEntity();
		
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        Calendar cal0 = builder.setDate(2017, 11, 16).build();
		
		MerchantEntity me = CommonBE.makeMerchant();
		 
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
		//suppLocEntity.setBankAccounts(bankList);
		
		List<SupplierLocationEntity> suppLocList = new ArrayList<SupplierLocationEntity>();
		suppLocList.add(suppLocEntity);
		
		LegalInfoBean le1 = new LegalInfoBean();
        le1.setAttrName("PAN Number");
        le1.setAttrValue("AABBCCDD");
        le1.setAttrSequence(1);
        le1.setDescription("Pan Number of Merchant");
        
        List<LegalInfoBean> leList = new ArrayList<LegalInfoBean>();
        leList.add(le1);
		
		SupplierEntity suppEntity = new SupplierEntity();
		suppEntity.setSupplierName("Sun Media");
		suppEntity.setSupplierCode("Sun");
		suppEntity.setSupplierLocations(suppLocList);
		suppEntity.setLegalInfo(leList);

		suppLocSvc.add(suppLocEntity);
		
		suppSvc.add(suppEntity);
		
		List<SupplierEntity> list = suppSvc.findAll();
		
		assertNotNull("Supplier  list should not be null !", list);
        assertEquals("2 Supplier  entity", 2, list.size());

	}

	@Test
	public void test3_Update() {

		List<SupplierEntity> list = suppSvc.findAll();
		
		assertNotNull("Supplier  list should not be null !", list);
		SupplierEntity suppEntity = list.get(0);
		suppEntity.setSupplierName("MD Media");
		
		suppSvc.update(suppEntity);
		
		list = suppSvc.findAll();
        assertEquals(" Supplier Location Bank Account entity", "MD Media",list.get(0).getSupplierName());
	}

	@Test
	public void test4_Delete() {

		List<SupplierEntity> list = suppSvc.findAll();
		
		assertNotNull("Supplier list should not be null !", list);
		SupplierEntity suppEntity = list.get(0);
		
		suppLocSvc.delete(suppEntity.getId());
		list = suppSvc.findAll();
        assertEquals("Supplier entity", "0", list.size());
	}
}
