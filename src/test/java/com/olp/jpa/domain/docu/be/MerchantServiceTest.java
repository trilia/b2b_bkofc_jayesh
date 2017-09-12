/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.be;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import com.olp.jpa.domain.docu.be.service.BankAccountService;
import com.olp.jpa.domain.docu.be.service.MerchantService;
import com.olp.jpa.domain.docu.comm.CommonSubscr;
import com.olp.jpa.domain.docu.comm.model.SubscriberEntity;
import com.olp.jpa.domain.docu.comm.model.SubscriptionPlanAsscnEntity;
import com.olp.jpa.domain.docu.comm.model.SubscriptionPlanEntity;
import com.olp.jpa.domain.docu.comm.repo.SubscriberService;
import com.olp.jpa.domain.docu.comm.repo.SubscriptionPlanAsscnService;
import com.olp.jpa.domain.docu.comm.repo.SubscriptionPlanService;

/**
 *
 * @author raghosh
 */
public class MerchantServiceTest extends BaseSpringAwareTest {
    
    @Autowired
    @Qualifier("merchantService")
    private MerchantService merchantSvc;
    
    @Autowired
    @Qualifier("bankAcctService")
    private BankAccountService bankSvc;
    
    @Autowired
    @Qualifier("planAsscnService")
    private SubscriptionPlanAsscnService planAsscnSvc;
    
    @Autowired
    @Qualifier("subscrPlanService")
    private SubscriptionPlanService planSvc;
    
    @Autowired
    @Qualifier("subscriberService")
    private SubscriberService subscSvc;
    
    @Before
    public void before() {
        
        
        merchantSvc.deleteAll(false); // deletes all referenced entities
        
        planSvc.deleteAll(false);
        
        setupData();
    }
    
    //@Test
    public void dummy() {
        //MerchantEntity me = merchantSvc.find(new Long(5184));
        //BankAccountEntity bae = bankSvc.find(new Long(5187));
        //SubscriberEntity se = subscSvc.find(new Long(5182));
    }
    
    @Test
    public void test_add1() {
        
        List<MerchantEntity> list = merchantSvc.findAll();
        
        assertNotNull("Merchant list should not be null !", list);
        assertEquals("1 merchant entity", 1, list.size());
    }
 
    @Test
    public void test_add2() {
        
        MerchantEntity me = CommonBE.makeMerchant();
        BankAccountEntity be1 = CommonBE.makeBankAccount();
        
        Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        SubscriptionPlanEntity spe = CommonSubscr.makeSubscrPlan();
        Calendar cal0 = builder.setDate(2014, 02, 01).build();
        spe.setStartDate(cal0.getTime());
        
        planSvc.add(spe);
        
        SubscriberEntity se = CommonSubscr.makeSubscriber();
        se.setSubscriberName(me.getMerchantName());
        //se.setPartitionCode(Objects.);
        
        SubscriptionPlanAsscnEntity spae = new SubscriptionPlanAsscnEntity();
        Calendar cal0_1 = builder.setDate(2015, 3, 16).build();
        spae.setStartDate(cal0_1.getTime());
        spae.setPlanRef(spe);
        
        
        List<SubscriptionPlanAsscnEntity> spaeList = new ArrayList<>();
        spaeList.add(spae);
        
        se.setPlanAsscnList(spaeList);
        
        me.setSubscriber(se);
        
        BankAccountEntity be2 = CommonBE.makeBankAccount();
        
        List<BankAccountEntity> mlist = new ArrayList<>();
        mlist.add(be1); mlist.add(be2);
        me.setBankAccounts(mlist);
        
        merchantSvc.add(me);
        
        List<MerchantEntity> list = merchantSvc.findAll();
        
        assertNotNull("Merchant list should not be null !", list);
        assertEquals("2 merchant entity", 2, list.size());
    }
    
    @Test
    public void test_update1() {
        
        List<MerchantEntity> list = merchantSvc.findAll();
        assertNotNull("Merchant list should not be null !", list);
        
        Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        MerchantEntity entity = list.get(0);
        List<BankAccountEntity> accts = entity.getBankAccounts();
        assertNotNull("Accounts list should not be null !", accts);
        //BankAccountEntity bank = accts.get(accts.size() - 1);
        //builder.setDate(2016, 2, 1);
      
        BankAccountEntity bank2 = CommonBE.makeBankAccount();
        bank2.setMerchantRef(entity);
        bank2.setOwningEntity(BeEnums.PartyType.MERCHANT);
        
        accts.add(bank2);
        
        merchantSvc.update(entity);
        
        List<MerchantEntity> list2 = merchantSvc.findAll();
        assertNotNull("Merchant list should not be null !", list2);
        MerchantEntity entity2 = list.get(0);
        List<BankAccountEntity> accts2 = entity2.getBankAccounts();
        assertNotNull("Accounts list should not be null !", accts2);
        assertEquals("3 bank account records", 3, accts2.size());
        
    }
    
    private void setupData() {
        
        MerchantEntity me = CommonBE.makeMerchant();
        
        Calendar.Builder builder = new Calendar.Builder();
        builder.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        SubscriptionPlanEntity spe = CommonSubscr.makeSubscrPlan();
        Calendar cal0 = builder.setDate(2014, 02, 10).build();
        spe.setStartDate(cal0.getTime());
        
        planSvc.add(spe);
        
        SubscriberEntity se = CommonSubscr.makeSubscriber();
        se.setSubscriberName(me.getMerchantName());
        //se.setPartitionCode(Objects.);
        
        SubscriptionPlanAsscnEntity spae = new SubscriptionPlanAsscnEntity();
        Calendar cal0_1 = builder.setDate(2015, 4, 22).build();
        spae.setStartDate(cal0_1.getTime());
        spae.setPlanRef(spe);
        
        
        List<SubscriptionPlanAsscnEntity> spaeList = new ArrayList<>();
        spaeList.add(spae);
        
        se.setPlanAsscnList(spaeList);
        
        me.setSubscriber(se);
        
        
        Calendar cal1 = builder.setDate(2015, 4, 22).build(), cal2 = builder.setDate(2016, 1, 11).build();
        
        BankAccountEntity be1 = CommonBE.makeBankAccount();
        be1.setAcctType(BeEnums.BankAccountType.CURRENT);
        be1.setMerchantRef(me);
        //be1.setStartDate(cal1.getTime()); be1.setEndDate(cal2.getTime());
        
        BankAccountEntity be2 = CommonBE.makeBankAccount();
        be2.setAcctType(BeEnums.BankAccountType.CURRENT);
        be2.setMerchantRef(me);
        //Calendar cal3 = builder.setDate(2016, 1, 11).build();
        //be2.setStartDate(cal3.getTime());
        
        List<BankAccountEntity> mlist = new ArrayList<>();
        mlist.add(be1); mlist.add(be2);
        me.setBankAccounts(mlist);
        
        me.setLegalInfoContext("IN");
        LegalInfoBean le1 = new LegalInfoBean();
        le1.setAttrName("PAN Number");
        le1.setAttrValue("AABBCCDD");
        le1.setAttrSequence(1);
        le1.setDescription("Pan Number of Merchant");
        
        Set<LegalInfoBean> leList = new HashSet<>();
        leList.add(le1);
        
        me.setLegalInfoList(leList);
        
        
        merchantSvc.add(me);
        
    }
    
}
