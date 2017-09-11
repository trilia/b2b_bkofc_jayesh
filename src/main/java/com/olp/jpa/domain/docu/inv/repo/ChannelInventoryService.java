package com.olp.jpa.domain.docu.inv.repo;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;

/**
 * @author Jayesh
 *
 */
public interface ChannelInventoryService extends IJpaService<ChannelInventoryEntity, Long> {

	//public ChannelInventoryEntity findByChannelCode(String channelCode);
	
	public void validate(ChannelInventoryEntity entity) throws EntityValidationException;
	
}
