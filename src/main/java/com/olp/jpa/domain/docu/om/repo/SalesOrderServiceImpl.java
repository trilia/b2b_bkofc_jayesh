package com.olp.jpa.domain.docu.om.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.om.model.OrderEnums;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;

/**
 * @author Jayesh
 *
 */
@Service("salesOrderService")
public class SalesOrderServiceImpl extends AbstractServiceImpl<SalesOrderEntity,Long> implements SalesOrderService {

	@Autowired
	@Qualifier("salesOrderRepository")
	private SalesOrderRepository repo;
	
	@Override
	public SalesOrderEntity findbyOrderNumber(String orderNumber, int partNumber) {
		SalesOrderEntity bean = repo.findbyOrderNumber(orderNumber, partNumber);
		return bean;
	}

	@Override
	protected JpaRepository<SalesOrderEntity, Long> getRepository() {
		return repo;
	}

	@Override
	protected ITextRepository<SalesOrderEntity, Long> getTextRepository() {
		return repo;
	}

	@Override
	protected String getAlternateKeyAsString(SalesOrderEntity entity) {
		StringBuilder buff = new StringBuilder(50);
        buff.append("{ \"order_number\" : \"").append(entity.getOrderNumber()).append("\" }");
        
        return(buff.toString());
	}

	@Override
	protected AbstractServiceImpl<SalesOrderEntity, Long>.Outcome preProcess(int opCode, SalesOrderEntity entity)
			throws EntityValidationException {
		Outcome result = new Outcome();
        switch(opCode) {
            
            case ADD : {
                preProcessAdd(entity);
                result.setResult(true);
                break;
            }
            case ADD_BULK : {
                preProcessAdd(entity);
                result.setResult(true);
                break;
            }
            default: {
                result.setResult(true);
            }
            
        }
            
        return(result);
	}

	private void preProcessAdd(SalesOrderEntity entity) throws EntityValidationException {
		validate(entity);
		updateTenantWithRevision(entity);    
	}

	@Override
	protected AbstractServiceImpl<SalesOrderEntity, Long>.Outcome postProcess(int opCode, SalesOrderEntity paramT)
			throws EntityValidationException {
		AbstractServiceImpl<SalesOrderEntity, Long>.Outcome result = new AbstractServiceImpl.Outcome();
		result.setResult(true);

		switch (opCode) {
		}
		return result;
	}
	
	@Override
	public void validate(SalesOrderEntity entity) throws EntityValidationException {
		if(null == entity.getId() && !OrderEnums.OrderStatus.RECEIVED.equals(entity.getOrderStatus())){
			throw new EntityValidationException("New Order should have status “RECEIVED”");
		}
		if(OrderEnums.DeliveryType.COMMITTED.equals(entity.getDeliveryType())){
			if(null == entity.getDeliverByDate()){
				throw new EntityValidationException("Order Delivery Date cannot be null");
			}else if((entity.getOrderDate().compareTo(entity.getDeliverByDate()) > 0)){
				throw new EntityValidationException("Order Delivery Date cannot be before Order Date");
			}
		}
		if(null == entity.getOrderNumber() || "".equals(entity.getOrderNumber().trim())){
			throw new EntityValidationException("Order Number cannot be empty");
		}
		if(entity.getOrderPart() <= 0){
			throw new EntityValidationException("Order Part Number cannot be empty");
		}
	}

}
