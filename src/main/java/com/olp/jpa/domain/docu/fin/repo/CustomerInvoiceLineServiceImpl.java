package com.olp.jpa.domain.docu.fin.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.fin.model.CustomerInvoiceLineEntity;

/**
 * @author Jayesh
 *
 */
public class CustomerInvoiceLineServiceImpl extends AbstractServiceImpl<CustomerInvoiceLineEntity,Long> implements CustomerInvoiceLineService {

	@Autowired
	@Qualifier("bankAcctRepository")
	private CustomerInvoiceLineRepository custInvLineRepo;
	 
	@Override
	public CustomerInvoiceLineEntity findbyInvoiceLine(String invoiceNumber, int lineNumber) {
		CustomerInvoiceLineEntity bean = custInvLineRepo.findbyInvoiceLine(invoiceNumber, lineNumber);
        return(bean);
	}

	@Override
	protected JpaRepository<CustomerInvoiceLineEntity, Long> getRepository() {
		return custInvLineRepo;
	}

	@Override
	protected ITextRepository<CustomerInvoiceLineEntity, Long> getTextRepository() {
		return custInvLineRepo;
	}

	@Override
	protected String getAlternateKeyAsString(CustomerInvoiceLineEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"invoice_line_number\" : \"").append(entity.getInvoiceLineNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<CustomerInvoiceLineEntity, Long>.Outcome preProcess(int opCode,
			CustomerInvoiceLineEntity entity) throws EntityValidationException {
		Outcome result = new Outcome();
		result.setResult(Boolean.TRUE);
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

	private void preProcessAdd(CustomerInvoiceLineEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity); 
	}

	@Override
	protected AbstractServiceImpl<CustomerInvoiceLineEntity, Long>.Outcome postProcess(int opCode,
			CustomerInvoiceLineEntity entity) throws EntityValidationException {
		AbstractServiceImpl<CustomerInvoiceLineEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		return result;
	}

	@Override
	public void validate(CustomerInvoiceLineEntity entity) throws EntityValidationException {
		entity.setTaxGroupCode(entity.getTaxGroupRef().getGroupCode());
		entity.setPromoGroupCode(entity.getPromoGroupRef().getGroupCode());
		entity.setInvoiceLineNumber(entity.getOrderLineRef().getLineNumber());
		//entity.setInvoiceNumber(entity.getInvoiceRef().getInvoiceNumber());
		
	}
}
