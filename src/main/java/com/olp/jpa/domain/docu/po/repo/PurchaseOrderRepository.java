package com.olp.jpa.domain.docu.po.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.po.model.PurchaseOrderEntity;

/**
 * @author Jayesh
 *
 */
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long>, ITextRepository<PurchaseOrderEntity, Long> {	
	
	public PurchaseOrderEntity findByPurchaseOrder(String purchaseOrder);
	
	
}
