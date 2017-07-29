package com.olp.jpa.domain.docu.fin.model.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;

/**
 * @author Jayesh
 *
 */
@Repository("customerInvoiceLineRepository")
public class CustomerInvoiceLineRepositoryImpl  extends AbstractRepositoryImpl<CustomerInvoiceLineEntity,Long> implements CustomerInvoiceLineRepository {

	@Override
	public CustomerInvoiceLineEntity findbyInvoiceLine(String invoiceNumber, int lineNumber) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<CustomerInvoiceLineEntity> query = getEntityManager().createNamedQuery("CustomerInvoice.findbyInvoiceNumber", CustomerInvoiceLineEntity.class);
        query.setParameter("invoiceNumber", invoiceNumber);
        query.setParameter("invoiceLineNumber", lineNumber); 
        
        CustomerInvoiceLineEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		return null;
	}
}
