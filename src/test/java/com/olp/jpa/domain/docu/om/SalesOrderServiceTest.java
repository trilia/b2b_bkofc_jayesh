package com.olp.jpa.domain.docu.om;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesOrderServiceTest extends BaseSpringAwareTest {

	@Autowired
    @Qualifier("salesOrderService")
    private SalesOrderService soSvc;
	
	@Autowired
    @Qualifier("salesOrderLineService")
    private SalesOrderLineService soLineSvc;
	
	
	/**
	 * 
	 */
	public SalesOrderServiceTest() {

	}

	@Before
	public void before() {
		soSvc.deleteAll(Boolean.TRUE);
		setupData();
	}

	private void setupData() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2017, 02, 10).build();
        
        CustomerEntity custEntity = CommonOM.makeCustomer();
        
        List<SalesOrderLineEntity> soLines = new ArrayList<SalesOrderLineEntity>();
        SalesOrderLineEntity soLineEntity = new SalesOrderLineEntity();
        soLineEntity.setLineNumber(1);
        soLineEntity.setLineType(OrderLineType.ITEM);
        soLineEntity.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity.setOrderQuantity(10);
        soLineEntity.setUnitRate(100);
        soLineEntity.setPartNumber(123456);
        
        soLines.add(soLineEntity);
        
        SalesOrderEntity soEntity = new SalesOrderEntity();
        soEntity.setOrderDate(cal0.getTime());
        soEntity.setOrderNumber("100-XYZ");
        soEntity.setOrderSource(OrderSource.CUSTOMER);
        soEntity.setOrderStatus(OrderStatus.RECEIVED);
        soEntity.setOrderType(OrderType.FIXED_COST);
        soEntity.setOrderPart(123456);
        soEntity.setCustomerCode(custEntity.getCustomerCode());
        soEntity.setDeliverByDate(cal0.getTime());
        soEntity.setDeliveryType(DeliveryType.STANDARD);
        soEntity.setShippingAddress("Mumbai");
		
		soSvc.add(soEntity);

	}

	@Test
	public void test1_Add() {
		List<SalesOrderEntity> list = soSvc.findAll();
        
        assertNotNull("Sales Order should not be null !", list);
        assertEquals("1 Sales Order", 1, list.size());
	}

	@Test
	@Rollback(false)
	public void test2_Add() {
		Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Calendar cal0 = builder.setDate(2017, 02, 10).build();
        
        CustomerEntity custEntity = CommonOM.makeCustomer();
        
        List<SalesOrderLineEntity> soLines = new ArrayList<SalesOrderLineEntity>();
        SalesOrderLineEntity soLineEntity = new SalesOrderLineEntity();
        soLineEntity.setLineNumber(1);
        soLineEntity.setLineType(OrderLineType.ITEM);
        soLineEntity.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity.setOrderQuantity(10);
        soLineEntity.setUnitRate(100);
        soLineEntity.setPartNumber(123456);
        
        soLines.add(soLineEntity);
        
        SalesOrderEntity soEntity = new SalesOrderEntity();
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
		
		List<SalesOrderEntity> list = soSvc.findAll();
        
        assertNotNull("Sales Order should not be null !", list);
        assertEquals("2 Sales Order", 2, list.size());
	}

	@Test
	public void test3_Update() {
		List<SalesOrderEntity> list = soSvc.findAll();

        assertEquals("1 Sale Order ", 1, list.size());
        SalesOrderEntity soEntity = list.get(0);
        soEntity.setDeliveryType(DeliveryType.COMMITTED);
        
        soSvc.update(soEntity);

        assertEquals("Sales Order Delivery Type", DeliveryType.COMMITTED, soEntity.getDeliveryType());
	}

	//@Test
	public void test4_Update() {
		List<SalesOrderEntity> list = soSvc.findAll();

        assertEquals("2 Sale Order ", 2, list.size());
        SalesOrderEntity soEntity = list.get(list.size() - 1);
        soEntity.setOrderType(OrderType.VARIABLE_COST);
        
        soSvc.update(soEntity);

        assertEquals("Sales Order Delivery Type", OrderType.VARIABLE_COST, soEntity.getOrderType());
	}
	
	@Test
	public void test5_Delete() {
		List<SalesOrderEntity> list = soSvc.findAll();

        assertEquals("1 Sale Order ", 1, list.size());
        SalesOrderEntity soEntity = list.get(list.size() - 1);
        
        soSvc.delete(soEntity.getId());

		list = soSvc.findAll();
        assertEquals("0 Sale Order ", 0, list.size());
	}
}
