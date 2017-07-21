package com.olp.jpa.domain.docu.om.repo;

import javax.jws.WebMethod;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.om.model.SalesOrderEntity;

/**
 * @author Jayesh
 *
 */
public interface SalesOrderLineService extends IJpaService<SalesOrderEntity, Long> {

	@WebMethod(operationName="findByPurchaseOrderNumber")
	public SalesOrderEntity findbyOrderNumber(String orderNumber, int partNumber);
	
	public void validate(SalesOrderEntity entity) throws EntityValidationException;
	
}
