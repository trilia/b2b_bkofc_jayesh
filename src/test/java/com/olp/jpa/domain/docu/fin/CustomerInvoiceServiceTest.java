package com.olp.jpa.domain.docu.fin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;
import com.olp.jpa.domain.docu.fin.model.FinEnums.CustInvoiceType;
import com.olp.jpa.domain.docu.fin.model.FinEnums.PaymentTerm;
import com.olp.jpa.domain.docu.fin.model.TaxGroupEntity;
import com.olp.jpa.domain.docu.fin.repo.CustomerInvoiceLineService;
import com.olp.jpa.domain.docu.fin.repo.CustomerInvoiceService;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;
import com.olp.jpa.domain.docu.om.repo.SalesOrderLineService;
import com.olp.jpa.domain.docu.om.repo.SalesOrderService;

/**
 * @author Jayesh
 *
 */
public class CustomerInvoiceServiceTest extends BaseSpringAwareTest {

	@Autowired
    @Qualifier("customerInvoiceService")
    private CustomerInvoiceService custInvSvc;
	
	@Autowired
    @Qualifier("customerInvoiceLineService")
    private CustomerInvoiceLineService custInvLineSvc;
	
	@Autowired
    @Qualifier("salesOrderService")
    private SalesOrderService soSvc;
	
	@Autowired
    @Qualifier("salesOrderLineService")
    private SalesOrderLineService soLineSvc;
	
	/**
	 * 
	 */
	public CustomerInvoiceServiceTest() {

	}

	@Before
	public void before() {
		custInvSvc.deleteAll(false);
		custInvLineSvc.deleteAll(false);
		soLineSvc.deleteAll(false);
		soSvc.deleteAll(false);
		setupData();
	}

	private void setupData() {
		CustomerEntity custEntity = CommonFin.makeCustomer();
		
		ProductSkuEntity skuEntity = CommonFin.makeProduct();
		
		SalesOrderEntity saleOrderEntity = CommonFin.makeSaleOrder();
		saleOrderEntity.setCustomerRef(custEntity);
		saleOrderEntity.setCustomerCode(custEntity.getCustomerCode());
		
		soSvc.add(saleOrderEntity);
		
		SalesOrderLineEntity soLineEntity = CommonFin.makeSaleOrderLine();
		soLineEntity.setOrderRef(saleOrderEntity);
		soLineEntity.setOrderNumber(saleOrderEntity.getOrderNumber());
		soLineEntity.setProductSkuRef(skuEntity);
		soLineEntity.setProductSkuCode(skuEntity.getSkuCode());
		
		soLineSvc.add(soLineEntity);
		
		PromotionGroupEntity promoEntity = CommonFin.makePromoGroup();
		
		TaxGroupEntity taxEntity = CommonFin.makeTaxGroup();
		
		CustomerInvoiceLineEntity invLineEntity = new CustomerInvoiceLineEntity();
		invLineEntity.setInvoiceNumber("1");
		invLineEntity.setInvoiceLineNumber(1);
		invLineEntity.setLineDescription("Payment Recived");
		invLineEntity.setListPrice(15);
		invLineEntity.setOrderLineRef(soLineEntity);
		//invLineEntity.setTaxGroupRef(taxEntity);
		invLineEntity.setTaxGroupCode("Tax-1");
		//invLineEntity.setPromoGroupRef(promoEntity);
		invLineEntity.setPromoGroupCode("Promo-1");
		invLineEntity.setTaxAmount(10);
		invLineEntity.setBaseAmount(15);
		invLineEntity.setDiscountAmount(2);
		invLineEntity.setNetAmount(20);
		invLineEntity.setLineTotalAmount(40);
		invLineEntity.setQuantity(2);
		invLineEntity.setUom("Metre");
		
		List<CustomerInvoiceLineEntity> invLineList = new ArrayList();
		invLineList.add(invLineEntity);
		
		custInvLineSvc.addAll(invLineList, false);
		
		CustomerInvoiceEntity invEntity = new CustomerInvoiceEntity();
		invEntity.setCustomerRef(custEntity);
		invEntity.setInvoiceNumber("1");
		invEntity.setInvoiceDate(new Date());
		invEntity.setInvoiceType(CustInvoiceType.STANDARD);
		invEntity.setInvoiceTotalAmount(1000);
		invEntity.setInvoiceCurrency("INR");
		invEntity.setNetTotalAmount(900);
		invEntity.setBaseTotalAmount(1000);
		invEntity.setOrderRef(saleOrderEntity);
		invEntity.setPromoGroupRef(promoEntity);
		invEntity.setTaxGroupRef(taxEntity);
		invEntity.setInvoiceLines(invLineList);
		invEntity.setPaymentTerm(PaymentTerm.COD);
		invEntity.setBillingAddress("Mumbai");
		
		
		custInvSvc.add(invEntity);
	}

	@Test
	public void test1_Add() {
		List<CustomerInvoiceEntity> list = custInvSvc.findAll();
        
        assertNotNull("Customer Invoice should not be null !", list);
        assertEquals("1 Customer Invoice", 1, list.size());
	}

	@Test
	public void test2_Add() {
		CustomerEntity custEntity = CommonFin.makeCustomer();
		
		ProductSkuEntity skuEntity = CommonFin.makeProduct();
		
		SalesOrderEntity saleOrderEntity = CommonFin.makeSaleOrder();
		saleOrderEntity.setCustomerRef(custEntity);
		saleOrderEntity.setCustomerCode(custEntity.getCustomerCode());
		
		soSvc.add(saleOrderEntity);
		
		SalesOrderLineEntity soLineEntity = CommonFin.makeSaleOrderLine();
		soLineEntity.setOrderRef(saleOrderEntity);
		soLineEntity.setOrderNumber(saleOrderEntity.getOrderNumber());
		soLineEntity.setProductSkuRef(skuEntity);
		soLineEntity.setProductSkuCode(skuEntity.getSkuCode());
		
		soLineSvc.add(soLineEntity);
		
		PromotionGroupEntity promoEntity = CommonFin.makePromoGroup();
		
		TaxGroupEntity taxEntity = CommonFin.makeTaxGroup();
		
		CustomerInvoiceLineEntity invLineEntity = new CustomerInvoiceLineEntity();
		invLineEntity.setInvoiceNumber("2");
		invLineEntity.setInvoiceLineNumber(1);
		invLineEntity.setLineDescription("Payment Recived");
		invLineEntity.setListPrice(15);
		invLineEntity.setOrderLineRef(soLineEntity);
		//invLineEntity.setTaxGroupRef(taxEntity);
		invLineEntity.setTaxGroupCode("Tax-1");
		//invLineEntity.setPromoGroupRef(promoEntity);
		invLineEntity.setPromoGroupCode("Promo-1");
		invLineEntity.setTaxAmount(10);
		invLineEntity.setBaseAmount(15);
		invLineEntity.setDiscountAmount(2);
		invLineEntity.setNetAmount(20);
		invLineEntity.setLineTotalAmount(40);
		invLineEntity.setQuantity(2);
		invLineEntity.setUom("Metre");
		
		List<CustomerInvoiceLineEntity> invLineList = new ArrayList();
		invLineList.add(invLineEntity);
		
		custInvLineSvc.addAll(invLineList, false);
		
		CustomerInvoiceEntity invEntity = new CustomerInvoiceEntity();
		invEntity.setCustomerRef(custEntity);
		invEntity.setInvoiceNumber("2");
		invEntity.setInvoiceDate(new Date());
		invEntity.setInvoiceType(CustInvoiceType.CREDITINV);
		invEntity.setInvoiceTotalAmount(1000);
		invEntity.setInvoiceCurrency("INR");
		invEntity.setNetTotalAmount(900);
		invEntity.setBaseTotalAmount(1000);
		invEntity.setOrderRef(saleOrderEntity);
		invEntity.setPromoGroupRef(promoEntity);
		invEntity.setTaxGroupRef(taxEntity);
		invEntity.setInvoiceLines(invLineList);
		invEntity.setPaymentTerm(PaymentTerm.CREDIT);
		invEntity.setBillingAddress("Mumbai");
		
		
		custInvSvc.add(invEntity);
		List<CustomerInvoiceEntity> list = custInvSvc.findAll();
        
        assertNotNull("Customer Invoice should not be null !", list);
        assertEquals("2 Customer Invoice", 2, list.size());
	}

	@Test
	public void test3_Update() {

		List<CustomerInvoiceEntity> list = custInvSvc.findAll();
		CustomerInvoiceEntity entity = list.get(0);
		entity.setInvoiceType(CustInvoiceType.STANDARD);
		entity.setPaymentTerm(PaymentTerm.IMMEDIATE);
		
		custInvSvc.update(entity);
        
        assertNotNull("Customer Invoice should not be null !", list);
        assertEquals("Customer Invoice Type: Standard", PaymentTerm.IMMEDIATE, entity.getPaymentTerm());
	}

	@Test
	public void test4_Delete() {
		List<CustomerInvoiceEntity> list = custInvSvc.findAll();
		
		custInvSvc.delete(list.get(0).getId());
        
        assertNotNull("Customer Invoice should not be null !", list);
        assertEquals("Customer Invoice ", 0,custInvSvc.findAll().size());
	}
}
