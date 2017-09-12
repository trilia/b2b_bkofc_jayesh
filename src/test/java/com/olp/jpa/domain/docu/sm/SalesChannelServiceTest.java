package com.olp.jpa.domain.docu.sm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.ChannelInventoryService;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuService;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;
import com.olp.jpa.domain.docu.sm.model.SmEnums.ChannelType;
import com.olp.jpa.domain.docu.sm.repo.SalesChannelService;

/**
 * @author Jayesh
 *
 */
public class SalesChannelServiceTest extends BaseSpringAwareTest {

	@Autowired
    @Qualifier("prodSkuService")
    private ProductSkuService skuSvc;
    
    @Autowired
    @Qualifier("salesChannelService")
    private SalesChannelService salesChSvc;
    
    @Autowired
    @Qualifier("channelInventoryService")
    private ChannelInventoryService chInvSvc;
    
	/**
	 * 
	 */
	public SalesChannelServiceTest() {

	}

	@Before
	public void before() {
		salesChSvc.deleteAll(false);
		chInvSvc.deleteAll(false);
		skuSvc.deleteAll(false);
		setupData();
	}

	private void setupData() {
		ProductSkuEntity skuEntity = CommonSM.makeProduct();
        skuSvc.add(skuEntity);
        
		List<ChannelInventoryEntity> channelInvList = new ArrayList<>();
		ChannelInventoryEntity channelInvEntity = new ChannelInventoryEntity();
		channelInvEntity.setChannelCode("CH-INV-01");
		channelInvEntity.setPricingRule("PR_RULE");
		channelInvEntity.setAvailableQuantity(100);
		channelInvEntity.setSkuRef(skuEntity);
		channelInvEntity.setUom("Metre");
		
		chInvSvc.add(channelInvEntity);
		
		channelInvList.add(channelInvEntity);
		
		SalesChannelEntity entity = new SalesChannelEntity();
		entity.setChannelCode("CH-01");
		entity.setChannelName("Distributor");
		entity.setChannelType(ChannelType.RETAIL_OUTLET);
		entity.setChannelInvRef(channelInvList);
		
		salesChSvc.add(entity);
	}

	@Test
	public void test1_Add() {
		List<SalesChannelEntity> salesChList = salesChSvc.findAll();
		assertNotNull("Sales Channel should not be null !", salesChList);
        assertEquals("1 Sales Channel", 1, salesChList.size());
	}

	//@Test
	public void test2_Add() {
		ProductSkuEntity skuEntity = CommonSM.makeProduct();
        skuSvc.add(skuEntity);
        
		List<ChannelInventoryEntity> channelInvList = new ArrayList<>();
		ChannelInventoryEntity channelInvEntity = new ChannelInventoryEntity();
		channelInvEntity.setChannelCode("CH-INV-01");
		channelInvEntity.setPricingRule("PR_RULE");
		channelInvEntity.setAvailableQuantity(100);
		channelInvEntity.setSkuRef(skuEntity);
		channelInvEntity.setUom("Metre");
		
		chInvSvc.add(channelInvEntity);
		
		channelInvList.add(channelInvEntity);
		
		SalesChannelEntity entity = new SalesChannelEntity();
		entity.setChannelCode("CH-02");
		entity.setChannelName("Franchisee");
		entity.setChannelType(ChannelType.RETAIL_OUTLET);
		entity.setChannelInvRef(channelInvList);
		
		salesChSvc.add(entity);
		
		List<SalesChannelEntity> salesChList = salesChSvc.findAll();
		assertNotNull("Sales Channel should not be null !", salesChList);
        assertEquals("2 Sales Channel", 2, salesChList.size());
	}

	@Test
	public void test3_Update() {
		List<SalesChannelEntity> salesChList = salesChSvc.findAll();
		SalesChannelEntity entity = salesChList.get(0);
		entity.setChannelName("E-Com Site");
		salesChSvc.update(entity);
		
		assertNotNull("Sales Channel should not be null !", salesChList);
        assertEquals("Sales Channel", "E-Com Site", salesChSvc.findAll().get(0).getChannelName());
	}

	@Test
	public void test4_Delete() {
		List<SalesChannelEntity> salesChList = salesChSvc.findAll();
		salesChSvc.delete(salesChSvc.findAll().get(0).getId());
		
		assertNotNull("Sales Channel should not be null !", salesChList);
        assertEquals("0 Sales Channel", 0, salesChSvc.findAll().size());
	}
}
