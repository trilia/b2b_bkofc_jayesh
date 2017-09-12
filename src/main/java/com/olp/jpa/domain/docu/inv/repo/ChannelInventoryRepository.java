package com.olp.jpa.domain.docu.inv.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
public interface ChannelInventoryRepository extends JpaRepository<ChannelInventoryEntity, Long>, ITextRepository<ChannelInventoryEntity, Long> { 
	
	public ChannelInventoryEntity findChannelInventoryBySku(String skuCode,String channelCode);

}
