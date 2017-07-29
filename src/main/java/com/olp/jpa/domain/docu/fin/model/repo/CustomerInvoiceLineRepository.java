package com.olp.jpa.domain.docu.fin.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerInvoiceLineRepository extends JpaRepository<CustomerInvoiceLineEntity, Long>, ITextRepository<CustomerInvoiceLineEntity, Long> { 
	
	public CustomerInvoiceLineEntity findbyInvoiceLine(String invoiceNumber, int lineNumber);

}
