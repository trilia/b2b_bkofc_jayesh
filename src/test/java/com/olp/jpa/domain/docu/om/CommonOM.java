package com.olp.jpa.domain.docu.om;

import com.olp.fwk.common.BaseTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.model.SkuBean;

class CommonOM extends BaseTest {

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

}
