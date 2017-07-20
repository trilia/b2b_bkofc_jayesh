package com.olp.jpa.domain.docu.po.repo;

import javax.jws.WebMethod;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;

/**
 * @author Jayesh
 *
 */
public interface PurchaseOrderService extends IJpaService<PurchaseOrderEntity, Long> {

	 @WebMethod(operationName="findByPurchaseOrder")
	 public PurchaseOrderEntity findByPurchaseOrder(String purchaseOrder);
	 
	 public void validate(PurchaseOrderEntity entity) throws EntityValidationException;
}
