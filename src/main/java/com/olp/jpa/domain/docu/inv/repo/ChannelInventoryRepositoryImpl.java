package com.olp.jpa.domain.docu.inv.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.inv.model.ChannelInventoryEntity;

/**
 * @author Jayesh
 *
 */
@Repository("channelInventoryRepository")
public class ChannelInventoryRepositoryImpl  extends AbstractRepositoryImpl<ChannelInventoryEntity,Long> implements ChannelInventoryRepository {

	@Override
	public ChannelInventoryEntity findChannelInventoryBySku(String skuCode,String channelCode){
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<ChannelInventoryEntity> query = getEntityManager().createNamedQuery("ChannelInventory.findChannelInventoryBySku", ChannelInventoryEntity.class);
        query.setParameter("channelCode", channelCode);
        query.setParameter("skuCode", skuCode);
        
        ChannelInventoryEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		return null;
	}

}
