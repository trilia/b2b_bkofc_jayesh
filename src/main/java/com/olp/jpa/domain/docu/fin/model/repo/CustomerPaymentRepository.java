package com.olp.jpa.domain.docu.fin.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerPaymentEntity;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerPaymentRepository extends JpaRepository<CustomerPaymentEntity, Long>, ITextRepository<CustomerPaymentEntity, Long> { 
	
	public CustomerPaymentEntity findbyPaymentNum(String paymentNumber);

}
