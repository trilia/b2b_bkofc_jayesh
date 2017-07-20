/**
 * 
 */
package com.olp.jpa.domain.docu.po.repo;

import javax.jws.WebMethod;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderLineEntity;

/**
 * @author Jayesh
 *
 */
public interface PurchaseOrderLineService extends IJpaService<PurchaseOrderLineEntity, Long>{
	
	@WebMethod(operationName="findByPurchaseOrderNumber")
	public PurchaseOrderLineEntity findByPurchaseOrderNumber(String purchaseOrderNum);
	
	public void validate(PurchaseOrderLineEntity entity) throws EntityValidationException;

}
