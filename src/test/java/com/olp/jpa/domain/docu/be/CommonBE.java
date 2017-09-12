/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.be;

import com.olp.fwk.common.BaseTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.CommonEnums.LifeCycleStatus;
import com.olp.jpa.domain.docu.be.model.BankAccountEntity;
import com.olp.jpa.domain.docu.be.model.BeEnums.BankAccountType;
import com.olp.jpa.domain.docu.be.model.BeEnums.PartyType;
import com.olp.jpa.domain.docu.be.model.MerchantEntity;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;
import com.olp.jpa.domain.docu.cs.model.CustomerEntity;
import com.olp.jpa.domain.docu.org.model.LocationEntity;
import com.olp.jpa.domain.docu.org.model.Source;

/**
 *
 * @author raghosh
 */
class CommonBE extends BaseTest {
    
    static MerchantEntity makeMerchant() {
        
        MerchantEntity m = new MerchantEntity();
        
        String str = getRandom();
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        m.setTenantId(tid);
        m.setMerchantCode("MRCHNT_" + str);
        m.setMerchantName("Merchant " + str);
        
        return(m);
    }
    
    static SupplierEntity makeSupplier() {
        
        SupplierEntity s = new SupplierEntity();
        
        String str = getRandom();
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        s.setSupplierCode("SUPPLIER_" + str);
        s.setSupplierName("Supplier " + str);
        //List<SupplierLocationEntity> locList = new ArrayList<>();
        //locList.add(makeSuppLoc());
        //s.setSupplierLocations(locList);
        
        return(s);
    }
    
    static SupplierLocationEntity makeSuppLoc() {
        
        SupplierLocationEntity loc = new SupplierLocationEntity();
        
        String str = getRandom();
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        loc.setLocationCode(str);
        //List<BankAccountEntity> beList = new ArrayList<>();
        //beList.add(makeBankAccount());
        //loc.setBankAccounts(beList);
        loc.setBillingLocation(true);
        loc.setShippingLocation(true);
        
        return(loc);
    }
    
    static BankAccountEntity makeBankAccount() {
        
        BankAccountEntity b = new BankAccountEntity();
        
        String str = getRandom();
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        b.setAccountAlias("BANK_" + str);
        b.setBankAcctNum(str);
        //b.setTenantId(tid);
        
        b.setBankName("ABC Bank");
        b.setBranchName("XYZ Branch");
        b.setAcctType(BankAccountType.CURRENT);
        b.setLifecycleStatus(LifeCycleStatus.ACTIVE);
        /*b.setOwningEntity(PartyType.SUPPLIER);*/
        
        return(b);
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
    
}
