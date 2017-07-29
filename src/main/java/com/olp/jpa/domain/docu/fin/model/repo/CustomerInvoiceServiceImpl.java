package com.olp.jpa.domain.docu.fin.model.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.common.AbstractServiceImpl.Outcome;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceEntity;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;
import com.olp.jpa.domain.docu.fin.model.FinEnums;
import com.olp.jpa.domain.docu.om.model.OrderEnums;

/**
 * @author Jayesh
 *
 */
public class CustomerInvoiceServiceImpl extends AbstractServiceImpl<CustomerInvoiceEntity,Long> implements CustomerInvoiceService {

	@Autowired
	@Qualifier("customerInvoiceRepository")
	private CustomerInvoiceRepository custInvRepo;
	
	@Override
	public CustomerInvoiceEntity findbyInvoiceNumber(String invoiceNumber) {
		CustomerInvoiceEntity bean = custInvRepo.findbyInvoiceNumber(invoiceNumber);
		return bean;
	}

	@Override
	protected JpaRepository<CustomerInvoiceEntity, Long> getRepository() {
		return custInvRepo;
	}

	@Override
	protected ITextRepository<CustomerInvoiceEntity, Long> getTextRepository() {
		return custInvRepo;
	}

	@Override
	protected String getAlternateKeyAsString(CustomerInvoiceEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"invoiceNumber\" : \"").append(entity.getInvoiceNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<CustomerInvoiceEntity, Long>.Outcome preProcess(int opCode,
			CustomerInvoiceEntity entity) throws EntityValidationException {
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

	private void preProcessAdd(CustomerInvoiceEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity); 
		
	}

	@Override
	protected AbstractServiceImpl<CustomerInvoiceEntity, Long>.Outcome postProcess(int opCode,
			CustomerInvoiceEntity paramT) throws EntityValidationException {
		AbstractServiceImpl<CustomerInvoiceEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		switch (opCode) {
		}
		return result;
	}
	
	@Override
	public void validate(CustomerInvoiceEntity entity) throws EntityValidationException {
		entity.setTaxGroupCode(entity.getTaxGroupRef().getGroupCode());
		entity.setPromoGroupCode(entity.getPromoGroupRef().getGroupCode());
		entity.setCustomerCode(entity.getCustomerRef().getCustomerCode());
		
		List<CustomerInvoiceLineEntity> invoiceLines = entity.getInvoiceLines();
		float baseTotalAmount = 0;
		float taxTotalAmount = 0;
		float discountTotalAmount = 0;
		for (CustomerInvoiceLineEntity customerInvoiceLineEntity : invoiceLines) {
			baseTotalAmount = baseTotalAmount + customerInvoiceLineEntity.getBaseAmount();
			taxTotalAmount = taxTotalAmount + customerInvoiceLineEntity.getTaxAmount();
			discountTotalAmount = discountTotalAmount + customerInvoiceLineEntity.getDiscountAmount();
		}
		entity.setBaseTotalAmount(baseTotalAmount);
		entity.setTaxTotalAmount(taxTotalAmount);
		entity.setDiscTotalAmount(discountTotalAmount);

		if (FinEnums.CustInvoiceType.STANDARD.equals(entity.getInvoiceType())) {
			if (!FinEnums.PaymentTerm.COD.equals(entity.getPaymentTerm())
					|| !FinEnums.PaymentTerm.IMMEDIATE.equals(entity.getPaymentTerm())) {
				throw new EntityValidationException(
						"Payment Term for Standard Invoice Type can be either COD or IMMEDIATE");
			}
		}

		if (FinEnums.CustInvoiceType.CREDITINV.equals(entity.getInvoiceType())) {
			if (!FinEnums.PaymentTerm.REFUND.equals(entity.getPaymentTerm())
					|| !FinEnums.PaymentTerm.CREDIT.equals(entity.getPaymentTerm())) {
				throw new EntityValidationException(
						"Payment Term for Standard Invoice Type can be either REFUND or CREDIT");
			}
		}

		if (null == entity.getInvoiceDate()) {
			throw new EntityValidationException("Invoice Date cannot be null");
		} else if ((entity.getOrderRef().getOrderDate().compareTo(entity.getInvoiceDate()) > 0)) {
			throw new EntityValidationException("Invoice Date cannot be before Order Date");
		}

	}

}
