package com.olp.jpa.domain.docu.om.repo;

import javax.jws.WebMethod;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.om.model.SalesOrderLineEntity;

/**
 * @author Jayesh
 *
 */
public interface SalesOrderLineService extends IJpaService<SalesOrderLineEntity, Long> {

	@WebMethod(operationName="findByOrderNumber")
	public SalesOrderLineEntity findbyOrderNumber(String orderNumber, int partNumber, int lineNumber);
	
	public void validate(SalesOrderLineEntity entity) throws EntityValidationException;
	
}
