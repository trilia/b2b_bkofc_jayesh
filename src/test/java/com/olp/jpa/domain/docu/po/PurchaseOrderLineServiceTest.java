package com.olp.jpa.domain.docu.po;

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
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.be.repo.SupplierLocationService;
import com.olp.jpa.domain.docu.be.repo.SupplierService;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuService;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.repo.LocationService;
import com.olp.jpa.domain.docu.po.model.PoLineType;
import com.olp.jpa.domain.docu.po.model.PoType;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderLineEntity;
import com.olp.jpa.domain.docu.po.repo.PurchaseOrderLineService;
import com.olp.jpa.domain.docu.po.repo.PurchaseOrderService;

/**
 * @author Jayesh
 *
 */
public class PurchaseOrderLineServiceTest extends BaseSpringAwareTest {

	/**
	 * 
	 */
	public PurchaseOrderLineServiceTest() {

	}

	@Autowired
    @Qualifier("purchaseOrderService")
    private PurchaseOrderService poSvc;
	
	@Autowired
    @Qualifier("purchaseOrderLineService")
    private PurchaseOrderLineService poLineSvc;
	
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
    @Qualifier("prodSkuService")
    private ProductSkuService skuSvc;
	
	@Before
	public void before() {
		poSvc.deleteAll(false);
		poLineSvc.deleteAll(false);
		suppSvc.deleteAll(false);
		suppLocSvc.deleteAll(false);
		locSvc.deleteAll(false);
		skuSvc.deleteAll(false);
		setupData();
	}

	private void setupData() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2014, 02, 10).build();
        
        SupplierEntity suppEntity = CommonPO.makeSupplier();
        suppSvc.add(suppEntity);
        
        SupplierLocationEntity suppLocEntity = CommonPO.makeSuppLoc();
        suppLocSvc.add(suppLocEntity);
        
        ProductSkuEntity skuEntity = CommonPO.makeProduct();
        skuSvc.add(skuEntity);
        
        LocationEntity shippingLocEntity = CommonPO.makeLoc();
        shippingLocEntity.setStartDate(cal0.getTime());
        shippingLocEntity.setEndDate(cal0.getTime());
        
        LocationEntity billingLocEntity = CommonPO.makeLoc();
        billingLocEntity.setStartDate(cal0.getTime());
        billingLocEntity.setEndDate(cal0.getTime());
        
        locSvc.add(shippingLocEntity);
        locSvc.add(billingLocEntity);
        
        List<PurchaseOrderLineEntity> poLines = new ArrayList<PurchaseOrderLineEntity>();
        PurchaseOrderLineEntity poLineEntity = new PurchaseOrderLineEntity();
        poLineEntity.setLineNumber("1");
        poLineEntity.setPoNumber("PO-1");
        poLineEntity.setLineType(PoLineType.STANDARD);
        poLineEntity.setQuantity(10);
        poLineEntity.setSkuRef(skuEntity);
        poLineEntity.setProdDesc("XYZ");
        poLineEntity.setRateVariance(10);
        poLineEntity.setUnitRate("15");
        poLineEntity.setUom("Metre");
        poLineEntity.setWarrantyTerms(3);
        poLineSvc.add(poLineEntity);
        
        poLines.add(poLineEntity);
        
        PurchaseOrderLineEntity poLineEntity2 = new PurchaseOrderLineEntity();
        poLineEntity2.setLineNumber("2");
        poLineEntity2.setPoNumber("PO-1");
        poLineEntity2.setLineType(PoLineType.NONSTANDARD);
        poLineEntity2.setQuantity(10);
        poLineEntity2.setSkuRef(skuEntity);
        poLineEntity2.setProdDesc("XYZ");
        poLineEntity2.setRateVariance(10);
        poLineEntity2.setUnitRate("15");
        poLineEntity2.setUom("Metre");
        poLineEntity2.setWarrantyTerms(3);
        poLineSvc.add(poLineEntity2);
        
        poLines.add(poLineEntity2);
        
		PurchaseOrderEntity poEntity = new PurchaseOrderEntity();
		poEntity.setPoDate(cal0.getTime());
		poEntity.setPoNumber("PO-1");
		poEntity.setDescription("Purchase Order");
		poEntity.setPoType(PoType.STANDARD);
		poEntity.setPoLines(poLines);
		poEntity.setSupplierLocRef(suppLocEntity);
		poEntity.setSupplierRef(suppEntity);
		poEntity.setShippingLocation(shippingLocEntity);
		poEntity.setBillingLocation(billingLocEntity);
		
		poSvc.add(poEntity);

	}

	@Test
	public void test1_Add() {
		List<PurchaseOrderEntity> list = poSvc.findAll();
        
        assertNotNull("Purchase Order should not be null !", list);
        assertEquals("1 Purchase Order", 1, list.size());
	}
	
	@Test
	public void test2_Add() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2014, 02, 10).build();
        
        SupplierEntity suppEntity = CommonPO.makeSupplier();
        suppSvc.add(suppEntity);
        
        SupplierLocationEntity suppLocEntity = CommonPO.makeSuppLoc();
        suppLocSvc.add(suppLocEntity);
        
        ProductSkuEntity skuEntity = CommonPO.makeProduct();
        skuSvc.add(skuEntity);
        
        LocationEntity shippingLocEntity = CommonPO.makeLoc();
        shippingLocEntity.setStartDate(cal0.getTime());
        shippingLocEntity.setEndDate(cal0.getTime());
        
        LocationEntity billingLocEntity = CommonPO.makeLoc();
        billingLocEntity.setStartDate(cal0.getTime());
        billingLocEntity.setEndDate(cal0.getTime());
        
        locSvc.add(shippingLocEntity);
        locSvc.add(billingLocEntity);
        
        List<PurchaseOrderLineEntity> poLines = new ArrayList<PurchaseOrderLineEntity>();
        PurchaseOrderLineEntity poLineEntity = new PurchaseOrderLineEntity();
        poLineEntity.setPoNumber("PO-2");
        poLineEntity.setLineNumber("1");
        poLineEntity.setLineType(PoLineType.STANDARD);
        poLineEntity.setQuantity(10);
        poLineEntity.setSkuRef(skuEntity);
        poLineEntity.setProdDesc("XYZ");
        poLineEntity.setRateVariance(10);
        poLineEntity.setUnitRate("15");
        poLineEntity.setUom("Metre");
        poLineEntity.setWarrantyTerms(3);
        poLineSvc.add(poLineEntity);
        
        poLines.add(poLineEntity);
        
		PurchaseOrderEntity poEntity = new PurchaseOrderEntity();
		poEntity.setPoDate(cal0.getTime());
		poEntity.setPoNumber("PO-2");
		poEntity.setDescription("Purchase Order");
		poEntity.setPoType(PoType.STANDARD);
		poEntity.setPoLines(poLines);
		poEntity.setSupplierLocRef(suppLocEntity);
		poEntity.setSupplierRef(suppEntity);
		poEntity.setShippingLocation(shippingLocEntity);
		poEntity.setBillingLocation(billingLocEntity);
		
		poSvc.add(poEntity);
		List<PurchaseOrderLineEntity> list = poLineSvc.findAll();
        
        assertNotNull("Purchase Order Lines should not be null !", list);
        assertEquals("3 Purchase Order Lines", 3, list.size());
	}

	@Test
	public void test3_Update() {
		List<PurchaseOrderLineEntity> poLines = poLineSvc.findAll();

        assertEquals("2 Purchase Order Lines", 2, poLines.size());

        PurchaseOrderLineEntity entity = poLines.get(0);
        entity.setLineNumber("3");
        
        poLineSvc.update(entity);

        assertEquals("Purchase Order Lines : Line Number ", "3",entity.getLineNumber());
	}

	@Test
	public void test4_Delete() {
		List<PurchaseOrderLineEntity> poLines = poLineSvc.findAll();
		assertEquals("2 Purchase Order Lines", 2, poLines.size());
		
		poLineSvc.delete(poLines.get(0).getId());
		
		assertEquals("1 Purchase Order Lines", 1, poLineSvc.findAll().size());
	}
}
