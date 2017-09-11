package com.olp.jpa.domain.docu.fin.repo;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerInvoiceLineService extends IJpaService<CustomerInvoiceLineEntity, Long> {

	public CustomerInvoiceLineEntity findbyInvoiceLine(String invoiceNumber, int lineNumber);
	
	public void validate(CustomerInvoiceLineEntity entity) throws EntityValidationException;
	
}
