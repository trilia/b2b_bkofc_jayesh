package com.olp.jpa.domain.docu.fin.repo;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.fin.model.CustomerPaymentEntity;

/**
 * @author Jayesh
 *
 */
public interface CustomerPaymentService extends IJpaService<CustomerPaymentEntity, Long> {

	public CustomerPaymentEntity findbyPaymentNum(String paymentNumber);
	
	public void validate(CustomerPaymentEntity entity) throws EntityValidationException;
	
}
