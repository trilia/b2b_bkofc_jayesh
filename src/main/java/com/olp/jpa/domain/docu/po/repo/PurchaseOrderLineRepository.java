/**
 * 
 */
package com.olp.jpa.domain.docu.po.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderLineEntity;

/**
 * @author Jayesh
 *
 */
public interface PurchaseOrderLineRepository extends JpaRepository<PurchaseOrderLineEntity, Long>, ITextRepository<PurchaseOrderLineEntity, Long> {	

	public PurchaseOrderLineEntity findByPurchaseOrderNumber(String purchaseOrderNum);
}
