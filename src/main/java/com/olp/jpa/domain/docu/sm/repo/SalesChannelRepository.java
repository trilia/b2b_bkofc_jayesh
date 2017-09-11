package com.olp.jpa.domain.docu.sm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
public interface SalesChannelRepository extends JpaRepository<SalesChannelEntity, Long>, ITextRepository<SalesChannelEntity, Long> { 
	
	public SalesChannelEntity findByChannelCode(String channelCode);

}
