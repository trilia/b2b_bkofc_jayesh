package com.olp.jpa.domain.docu.inv.repo;

import org.springframework.stereotype.Repository;

import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;

/**
 * @author Jayesh
 *
 */
@Repository("channelInventoryRepository")
public class ChannelInventoryRepositoryImpl  extends AbstractRepositoryImpl<ChannelInventoryEntity,Long> implements ChannelInventoryRepository {

	/*@Override
	public SalesChannelEntity findByChannelCode(String channelCode) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<SalesChannelEntity> query = getEntityManager().createNamedQuery("SalesChannel.findByChannelCode", SalesChannelEntity.class);
        query.setParameter("channelCode", channelCode);
        
        SalesChannelEntity bean = query.getSingleResult();
        
        return(bean);
	}*/

	@Override
	public String getLazyLoadElements() {
		return null;
	}

}
