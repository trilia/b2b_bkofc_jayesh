package com.olp.jpa.domain.docu.fin.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;

/**
 * @author Jayesh
 *
 */
@Repository("customerInvoiceRepository")
public class CustomerInvoiceRepositoryImpl  extends AbstractRepositoryImpl<CustomerInvoiceEntity,Long> implements CustomerInvoiceRepository {

	@Override
	public CustomerInvoiceEntity findbyInvoiceNumber(String invoiceNumber) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<CustomerInvoiceEntity> query = getEntityManager().createNamedQuery("CustomerInvoice.findbyInvoiceNumber", CustomerInvoiceEntity.class);
        query.setParameter("invoiceNumber", invoiceNumber);
        
        CustomerInvoiceEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		return null;
	}
}
