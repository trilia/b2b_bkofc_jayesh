package com.olp.jpa.domain.docu.fin.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.fin.model.CustomerPaymentEntity;

/**
 * @author Jayesh
 *
 */
@Service("customerPaymentService")
public class CustomerPaymentServiceImpl extends AbstractServiceImpl<CustomerPaymentEntity,Long> implements CustomerPaymentService {

	@Autowired
	@Qualifier("customerPaymentRepository")
	private CustomerPaymentRepository custPayRepo;
	
	@Override
	public CustomerPaymentEntity findbyPaymentNum(String paymentNumber) {
		CustomerPaymentEntity bean = custPayRepo.findbyPaymentNum(paymentNumber);
		return bean;
	}

	@Override
	protected JpaRepository<CustomerPaymentEntity, Long> getRepository() {
		return custPayRepo;
	}

	@Override
	protected ITextRepository<CustomerPaymentEntity, Long> getTextRepository() {
		return custPayRepo;
	}

	@Override
	protected String getAlternateKeyAsString(CustomerPaymentEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"payment_number\" : \"").append(entity.getPaymentNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<CustomerPaymentEntity, Long>.Outcome preProcess(int opCode,
			CustomerPaymentEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
        switch(opCode) {
            
            case ADD : {
                preProcessAdd(entity);
                break;
            }
            case ADD_BULK : {
                preProcessAdd(entity);
                break;
            }
            default: {
                result.setResult(true);
            }
            
        }
            
        return(result);
	}

	private void preProcessAdd(CustomerPaymentEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity); 
	}

	@Override
	protected AbstractServiceImpl<CustomerPaymentEntity, Long>.Outcome postProcess(int opCode,
			CustomerPaymentEntity entity) throws EntityValidationException {
		AbstractServiceImpl<CustomerPaymentEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		switch (opCode) {
		}
		return result;
	}

	@Override
	public void validate(CustomerPaymentEntity entity) throws EntityValidationException {
		entity.setInvoiceNumber(entity.getInvoiceRef().getInvoiceNumber());
		entity.setCustomerNumber(entity.getInvoiceRef().getCustomerCode());
		
		if (null == entity.getPaymentDate()) {
			throw new EntityValidationException("Payment Date cannot be null");
		} else if ((entity.getInvoiceRef().getOrderRef().getOrderDate().compareTo(entity.getPaymentDate()) > 0)) {
			throw new EntityValidationException("Payment Date cannot be before Order Date");
		}
		
	}
}
