package com.olp.jpa.domain.docu.om.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.om.model.OrderEnums;
import com.olp.jpa.domain.docu.om.model.OrderEnums.OrderLineStatus;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */
@Service("salesOrderLineService")
public class SalesOrderLineServiceImpl extends AbstractServiceImpl<SalesOrderLineEntity,Long> implements SalesOrderLineService {

	@Autowired
	@Qualifier("salesOrderLineRepository")
	private SalesOrderLineRepository repo;
	
	@Override
	public SalesOrderLineEntity findbyOrderNumber(String orderNumber, int partNumber,int lineNumber) {
		SalesOrderLineEntity bean = repo.findbyOrderNumber(orderNumber, partNumber,lineNumber);
		return bean;
	}

	@Override
	protected JpaRepository<SalesOrderLineEntity, Long> getRepository() {
		return repo;
	}

	@Override
	protected ITextRepository<SalesOrderLineEntity, Long> getTextRepository() {
		return repo;
	}

	@Override
	protected String getAlternateKeyAsString(SalesOrderLineEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"order_number\" : \"").append(entity.getOrderNumber()).append("\" }")
        	.append("{ \"part_number\" : \"").append(entity.getPartNumber()).append("\" }")
        	.append("{ \"line_number\" : \"").append(entity.getLineNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<SalesOrderLineEntity, Long>.Outcome preProcess(int opCode, SalesOrderLineEntity entity)
			throws EntityValidationException {
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

	private void preProcessAdd(SalesOrderLineEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity);    
	}

	@Override
	protected AbstractServiceImpl<SalesOrderLineEntity, Long>.Outcome postProcess(int opCode, SalesOrderLineEntity entity)
			throws EntityValidationException {
		AbstractServiceImpl<SalesOrderLineEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		switch (opCode) {
			case UPDATE :{
				if(!OrderLineStatus.RECEIVED.equals(entity.getLineStatus().RECEIVED)){
					//if(entity.getOrderRef().get)
				}
			}
		}
		return result;
	}
	
	@Override
	public void validate(SalesOrderLineEntity entity) throws EntityValidationException {
		if(OrderEnums.OrderLineType.PROMOTION.equals(entity.getLineType()) && entity.getUnitRate() > 0){
			throw new EntityValidationException("For lineType PROMOTION , unitRate should be zero");
		}
		if(entity.getReturnQuantity() > entity.getOrderQuantity()){
			throw new EntityValidationException("ReturnQuantity cannot be greater than Order Quantity");
		}
		
	}

}
