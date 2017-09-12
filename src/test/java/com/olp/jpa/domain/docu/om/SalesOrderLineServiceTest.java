package com.olp.jpa.domain.docu.om;

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
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuService;
import com.olp.jpa.domain.docu.om.model.OrderEnums.DeliveryType;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderLineStatus;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderLineType;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderSource;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderStatus;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderType;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;
import com.olp.jpa.domain.docu.om.repo.SalesOrderLineService;
import com.olp.jpa.domain.docu.om.repo.SalesOrderService;

/**
 * @author Jayesh
 *
 */
public class SalesOrderLineServiceTest extends BaseSpringAwareTest {

	@Autowired
    @Qualifier("salesOrderService")
    private SalesOrderService soSvc;
	
	@Autowired
    @Qualifier("salesOrderLineService")
    private SalesOrderLineService soLineSvc;
	
	@Autowired
    @Qualifier("prodSkuService")
    private ProductSkuService skuSvc;
	
	/**
	 * 
	 */
	public SalesOrderLineServiceTest() {

	}

	@Before
	public void before() {
		soSvc.deleteAll(Boolean.TRUE);
		soLineSvc.deleteAll(Boolean.TRUE);
		skuSvc.deleteAll(Boolean.TRUE);
		setupData();
	}

	private void setupData() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2017, 02, 10).build();
        
        CustomerEntity custEntity = CommonOM.makeCustomer();
        
        ProductSkuEntity skuEntity = CommonOM.makeProduct();
        skuSvc.add(skuEntity);
        
        IContext ctx = ContextManager.getContext();
        
        SalesOrderEntity soEntity = new SalesOrderEntity();
        soEntity.setTenantId(ctx.getTenantId());
        soEntity.setOrderDate(cal0.getTime());
        soEntity.setOrderNumber("102-XYZ");
        soEntity.setOrderSource(OrderSource.CUSTOMER);
        soEntity.setOrderStatus(OrderStatus.RECEIVED);
        soEntity.setOrderType(OrderType.FIXED_COST);
        soEntity.setOrderPart(123456);
        soEntity.setCustomerCode(custEntity.getCustomerCode());
        soEntity.setDeliverByDate(cal0.getTime());
        soEntity.setDeliveryType(DeliveryType.STANDARD);
        soEntity.setShippingAddress("Mumbai");
        
        soSvc.add(soEntity);
        
        List<SalesOrderLineEntity> soLines = new ArrayList<SalesOrderLineEntity>();
        SalesOrderLineEntity soLineEntity = new SalesOrderLineEntity();
        soLineEntity.setTenantId(ctx.getTenantId());
        soLineEntity.setOrderNumber(soEntity.getOrderNumber());
        soLineEntity.setOrderQuantity(15);
        soLineEntity.setPartNumber(123456);
        soLineEntity.setLineNumber(1);
        soLineEntity.setLineType(OrderLineType.ITEM);
        soLineEntity.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity.setOrderQuantity(10);
        soLineEntity.setUnitRate(100);
        soLineEntity.setOrderRef(soEntity);
        soLineEntity.setProductSkuRef(skuEntity);
        
        soLines.add(soLineEntity);
        
        SalesOrderLineEntity soLineEntity2 = new SalesOrderLineEntity();
        soLineEntity2.setTenantId(ctx.getTenantId());
        soLineEntity2.setOrderNumber(soEntity.getOrderNumber());
        soLineEntity2.setOrderQuantity(5);
        soLineEntity2.setPartNumber(98765);
        soLineEntity2.setLineNumber(2);
        soLineEntity2.setLineType(OrderLineType.ITEM);
        soLineEntity2.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity2.setOrderQuantity(10);
        soLineEntity2.setUnitRate(100);
        soLineEntity2.setOrderRef(soEntity);
        soLineEntity2.setProductSkuRef(skuEntity);
        soLines.add(soLineEntity2);
        
        soLineSvc.addAll(soLines,Boolean.FALSE);

	}

	@Test
	public void test1_Add() {
		List<SalesOrderLineEntity> list = soLineSvc.findAll();
        
        assertNotNull("Sales Order Line should not be null !", list);
        assertEquals("2 Sales Order Line", 2, list.size());
	}

	@Test
	public void test2_Add() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2017, 02, 10).build();
        
        CustomerEntity custEntity = CommonOM.makeCustomer();
        
        ProductSkuEntity skuEntity = CommonOM.makeProduct();
        skuSvc.add(skuEntity);
        
        IContext ctx = ContextManager.getContext();
        
        SalesOrderEntity soEntity = new SalesOrderEntity();
        soEntity.setTenantId(ctx.getTenantId());
        soEntity.setOrderDate(cal0.getTime());
        soEntity.setOrderNumber("101-ABC");
        soEntity.setOrderSource(OrderSource.CUSTOMER);
        soEntity.setOrderStatus(OrderStatus.RECEIVED);
        soEntity.setOrderType(OrderType.FIXED_COST);
        soEntity.setOrderPart(123456);
        soEntity.setCustomerCode(custEntity.getCustomerCode());
        soEntity.setDeliverByDate(cal0.getTime());
        soEntity.setDeliveryType(DeliveryType.STANDARD);
        soEntity.setShippingAddress("Mumbai");
        
        soSvc.add(soEntity);
        
        List<SalesOrderLineEntity> soLines = new ArrayList<SalesOrderLineEntity>();
        SalesOrderLineEntity soLineEntity = new SalesOrderLineEntity();
        soLineEntity.setTenantId(ctx.getTenantId());
        soLineEntity.setOrderNumber(soEntity.getOrderNumber());
        soLineEntity.setOrderQuantity(15);
        soLineEntity.setPartNumber(123456);
        soLineEntity.setLineNumber(1);
        soLineEntity.setLineType(OrderLineType.ITEM);
        soLineEntity.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity.setOrderQuantity(10);
        soLineEntity.setUnitRate(100);
        soLineEntity.setPartNumber(123456);
        soLineEntity.setOrderRef(soEntity);
        soLineEntity.setProductSkuRef(skuEntity);
        
        soLines.add(soLineEntity);

        SalesOrderLineEntity soLineEntity2 = new SalesOrderLineEntity();
        soLineEntity2.setTenantId(ctx.getTenantId());
        soLineEntity2.setOrderNumber(soEntity.getOrderNumber());
        soLineEntity2.setOrderQuantity(15);
        soLineEntity2.setPartNumber(98765);
        soLineEntity2.setLineNumber(2);
        soLineEntity2.setLineType(OrderLineType.ITEM);
        soLineEntity2.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity2.setOrderQuantity(10);
        soLineEntity2.setUnitRate(100);
        soLineEntity2.setOrderRef(soEntity);
        soLineEntity2.setProductSkuRef(skuEntity);
        soLines.add(soLineEntity2);
        
        soLineSvc.addAll(soLines,Boolean.TRUE);

        List<SalesOrderEntity> soList = soSvc.findAll();
        assertNotNull("Sales Order  should not be null !", soList);
        assertEquals("2 Sales Order ", 2, soSvc.findAll().size());
        
        List<SalesOrderLineEntity> list = soLineSvc.findAll();
        assertNotNull("Sales Order line should not be null !", list);
        assertEquals("4 Sales Order line", 4, soLineSvc.findAll().size());
	}

	@Test
	public void test3_Update() {
		List<SalesOrderLineEntity> list = soLineSvc.findAll();
        assertEquals("2 Sale Order Line ", 2, list.size());
        SalesOrderLineEntity soLineEntity= soLineSvc.findbyOrderNumber("102-XYZ",123456,1);
        soLineEntity.setLineStatus(OrderLineStatus.DELIVERED);
        
        soLineSvc.update(soLineEntity);

        assertEquals("Sales Order Line status", OrderLineStatus.DELIVERED, soLineEntity.getLineStatus());
	}

	//@Test
	public void test_Update2() {
		List<SalesOrderEntity> list = soSvc.findAll();

        assertEquals("2 Sale Order ", 2, list.size());
        SalesOrderEntity soEntity = list.get(list.size() - 1);
        soEntity.setOrderType(OrderType.VARIABLE_COST);
        
        soSvc.update(soEntity);

        assertEquals("Sales Order Delivery Type", OrderType.VARIABLE_COST, soEntity.getOrderType());
	}
	
	@Test
	public void test4_Delete() {
		List<SalesOrderLineEntity> list = soLineSvc.findAll();

        assertEquals("2 Sale Order Lines ", 2, list.size());
        SalesOrderLineEntity soEntity = list.get(list.size() - 1);
        
        soLineSvc.delete(soEntity.getId());

        list = soLineSvc.findAll();
        assertEquals("1 Sale Order Line", 1, list.size());
	}
}
