package com.olp.jpa.domain.docu.be.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;

/**
 *
 * @author Jayesh Jain
 */
@NoRepositoryBean
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>, ITextRepository<SupplierEntity, Long> {
    
    public SupplierEntity findBySupplierCode(String supplierCode);
    
    public SupplierEntity findBySupplierName(String supplierName);
    
}
