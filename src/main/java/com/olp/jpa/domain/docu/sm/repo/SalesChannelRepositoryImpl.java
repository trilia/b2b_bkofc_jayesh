package com.olp.jpa.domain.docu.sm.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.sm.model.SalesChannelEntity;

/**
 * @author Jayesh
 *
 */
@Repository("salesChannelRepository")
public class SalesChannelRepositoryImpl  extends AbstractRepositoryImpl<SalesChannelEntity,Long> implements SalesChannelRepository {

	@Override
	public SalesChannelEntity findByChannelCode(String channelCode) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<SalesChannelEntity> query = getEntityManager().createNamedQuery("SalesChannel.findByChannelCode", SalesChannelEntity.class);
        query.setParameter("channelCode", channelCode);
        
        SalesChannelEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		return null;
	}

}
