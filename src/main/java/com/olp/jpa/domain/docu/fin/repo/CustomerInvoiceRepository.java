package com.olp.jpa.domain.docu.fin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerInvoiceRepository extends JpaRepository<CustomerInvoiceEntity, Long>, ITextRepository<CustomerInvoiceEntity, Long> { 
	
	public CustomerInvoiceEntity findbyInvoiceNumber(String invoiceNumber);

}
