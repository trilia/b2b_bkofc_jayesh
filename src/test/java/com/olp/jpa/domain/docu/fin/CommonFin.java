package com.olp.jpa.domain.docu.fin;

import java.util.Date;

import com.olp.fwk.common.BaseTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.fin.model.TaxGroupEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.model.SkuBean;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;
import com.olp.jpa.domain.docu.om.model.OrderEnums.DeliveryType;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderLineStatus;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderLineType;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderSource;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderStatus;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderType;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

class CommonFin extends BaseTest {

	static CustomerEntity makeCustomer() {

		CustomerEntity m = new CustomerEntity();

		String str = getRandom();

		IContext ctx = ContextManager.getContext();
		String tid = ctx.getTenantId();

		// m.setTenantId(tid);
		m.setCustomerCode("CUST_" + str);
		m.setFirstName("CUST_FIRST_ " + str);
		m.setLastName("CUST_LAST_" + str);

		return (m);
	}

	static ProductSkuEntity makeProduct() {

		ProductSkuEntity skuEntity = new ProductSkuEntity();
		SkuBean skuBean = new SkuBean();

		String str = getRandom();

		IContext ctx = ContextManager.getContext();
		String tid = ctx.getTenantId();

		skuBean.setProduct("Shirt");
		skuBean.setVariant("Color");
		skuBean.setFamily("Clothing");

		skuEntity.setSku(skuBean);
		skuEntity.setSkuCode("SH-1");
		skuEntity.setSource("Source");
		skuEntity.setStatus("In-Stock");
		skuEntity.setIsEnabled(Boolean.TRUE);

		return (skuEntity);
	}
	
	static SalesOrderEntity makeSaleOrder() {

		SalesOrderEntity soEntity = new SalesOrderEntity();
        soEntity.setOrderDate(new Date());
        soEntity.setOrderNumber("101-ABC");
        soEntity.setOrderSource(OrderSource.CUSTOMER);
        soEntity.setOrderStatus(OrderStatus.RECEIVED);
        soEntity.setOrderType(OrderType.FIXED_COST);
        soEntity.setOrderPart(123456);
        soEntity.setDeliverByDate(new Date());
        soEntity.setDeliveryType(DeliveryType.STANDARD);
        soEntity.setShippingAddress("Mumbai");

		return (soEntity);
	}
	
	static SalesOrderLineEntity makeSaleOrderLine() {

		SalesOrderLineEntity soLineEntity = new SalesOrderLineEntity();
        soLineEntity.setOrderQuantity(15);
        soLineEntity.setPartNumber(123456);
        soLineEntity.setLineNumber(1);
        soLineEntity.setLineType(OrderLineType.ITEM);
        soLineEntity.setLineStatus(OrderLineStatus.RECEIVED);
        soLineEntity.setOrderQuantity(10);
        soLineEntity.setUnitRate(100);

		return (soLineEntity);
	}
	
	static PromotionGroupEntity makePromoGroup() {

		PromotionGroupEntity promoEntity = new PromotionGroupEntity();
		promoEntity.setGroupCode("Promo=01");

		return (promoEntity);
	}
	
	static TaxGroupEntity makeTaxGroup() {

		TaxGroupEntity taxEntity = new TaxGroupEntity();
		taxEntity.setGroupCode("Tax-01");
		taxEntity.setApplication("Application");
		taxEntity.setDirection("Direction");

		return (taxEntity);
	}

}
