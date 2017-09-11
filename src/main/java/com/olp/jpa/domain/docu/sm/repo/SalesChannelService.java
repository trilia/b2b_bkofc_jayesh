package com.olp.jpa.domain.docu.sm.repo;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
public interface SalesChannelService extends IJpaService<SalesChannelEntity, Long> {

	public SalesChannelEntity findByChannelCode(String channelCode);
	
	public void validate(SalesChannelEntity entity) throws EntityValidationException;
	
}
