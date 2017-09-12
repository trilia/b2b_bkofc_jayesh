package com.olp.jpa.domain.docu.po;

import java.util.ArrayList;
import java.util.List;

import com.olp.fwk.common.BaseTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.domain.docu.be.model.LegalInfoBean;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.model.SkuBean;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.model.Source;

public class CommonPO extends BaseTest {
	
	static SupplierEntity makeSupplier() {

		SupplierEntity s = new SupplierEntity();

		String str = getRandom();

		IContext ctx = ContextManager.getContext();
		String tid = ctx.getTenantId();

		s.setSupplierCode("SUPPLIER_" + str);
		s.setSupplierName("Supplier " + str);
		s.setLegalInfo(makeLegalInfo());
		// List<SupplierLocationEntity> locList = new ArrayList<>();
		// locList.add(makeSuppLoc());
		// s.setSupplierLocations(locList);

		return (s);
	}
	
	static List<LegalInfoBean> makeLegalInfo() {

		LegalInfoBean le1 = new LegalInfoBean();
        le1.setAttrName("PAN Number");
        le1.setAttrValue("AABBCCDD");
        le1.setAttrSequence(1);
        le1.setDescription("Pan Number of Merchant");
        
        List<LegalInfoBean> leList = new ArrayList<>();
        leList.add(le1);

		return (leList);
	}

	static SupplierLocationEntity makeSuppLoc() {

		SupplierLocationEntity loc = new SupplierLocationEntity();

		String str = getRandom();

		IContext ctx = ContextManager.getContext();
		String tid = ctx.getTenantId();

		loc.setLocationCode(str);
		// List<BankAccountEntity> beList = new ArrayList<>();
		// beList.add(makeBankAccount());
		// loc.setBankAccounts(beList);
		loc.setBillingLocation(true);
		loc.setShippingLocation(true);

		return (loc);
	}
	
	static LocationEntity makeLoc() {

		LocationEntity loc = new LocationEntity();

		String str = getRandom();

		IContext ctx = ContextManager.getContext();
		String tid = ctx.getTenantId();

		loc.setLocationAlias(str);
		loc.setAddressLine1("Walnut Road");
		loc.setAddressLine2("Peepalwadi");
		loc.setCity("Mumbai");
		loc.setCountry("India");
		loc.setZipCode("400063");
		loc.setLocationSource(Source.MRCNHT_ORG);
		loc.setLocationType("Corporate");
		loc.setStateOrProvince("State");

		return (loc);
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
