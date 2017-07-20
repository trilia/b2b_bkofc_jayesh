/**
 * 
 */
package com.olp.jpa.domain.docu.po.repo;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.common.SearchCriteriaBean;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderLineEntity;

/**
 * @author Jayesh
 *
 */
@Repository("purchaseOrderLineRepository")
public class PurchaseOrderLineRepositoryImpl extends AbstractRepositoryImpl<PurchaseOrderLineEntity,Long> implements PurchaseOrderLineRepository {

	@Override
	public String getLazyLoadElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrderLineEntity findByPurchaseOrderNumber(String purchaseOrderNum) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<PurchaseOrderLineEntity> query = getEntityManager().createNamedQuery("PurchaseOrderLine.findByPurchaseOrderNumber", PurchaseOrderLineEntity.class);
        query.setParameter("poNumber", purchaseOrderNum); 
        query.setParameter("tenant", tid);
        
        PurchaseOrderLineEntity bean = query.getSingleResult();
        
        return(bean);
	}

	

}
