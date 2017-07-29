package com.olp.jpa.domain.docu.fin.model.repo;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerInvoiceService extends IJpaService<CustomerInvoiceEntity, Long> {

	public CustomerInvoiceEntity findbyInvoiceNumber(String invoiceNumber);
	
	public void validate(CustomerInvoiceEntity entity) throws EntityValidationException;
	
}
