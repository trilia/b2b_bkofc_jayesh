package com.olp.jpa.domain.docu.po.repo;

import javax.persistence.TypedQuery;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;

/**
 * @author Jayesh
 *
 */
public class PurchaseOrderRepositoryImpl extends AbstractRepositoryImpl<PurchaseOrderEntity, Long>  implements PurchaseOrderRepository {

	

	/* (non-Javadoc)
	 * @see com.olp.jpa.domain.docu.po.repo.PurchaseOrderRepository#findByPurchaseOrder(java.lang.String)
	 */
	@Override
	public PurchaseOrderEntity findByPurchaseOrder(String purchaseOrder) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<PurchaseOrderEntity> query = getEntityManager().createNamedQuery("PurchaseOrder.findByPurchaseOrder", PurchaseOrderEntity.class);
        query.setParameter("poNumber", purchaseOrder); 
        query.setParameter("tenant", tid);
        
        PurchaseOrderEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		return null;
	}

}
