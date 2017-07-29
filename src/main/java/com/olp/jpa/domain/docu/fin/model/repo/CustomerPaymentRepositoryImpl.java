package com.olp.jpa.domain.docu.fin.model.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.fin.model.CustomerPaymentEntity;

/**
 * @author Jayesh
 *
 */
@Repository("customerPaymentRepository")
public class CustomerPaymentRepositoryImpl  extends AbstractRepositoryImpl<CustomerPaymentEntity,Long> implements CustomerPaymentRepository {

	@Override
	public CustomerPaymentEntity findbyPaymentNum(String paymentNumber) {
		IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<CustomerPaymentEntity> query = getEntityManager().createNamedQuery("CustomerPayment.findbyPaymentNum", CustomerPaymentEntity.class);
        query.setParameter("paymentNumber", paymentNumber);
        
        CustomerPaymentEntity bean = query.getSingleResult();
        
        return(bean);
	}

	@Override
	public String getLazyLoadElements() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
