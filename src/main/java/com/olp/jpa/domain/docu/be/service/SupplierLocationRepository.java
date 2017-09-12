package com.olp.jpa.domain.docu.be.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;

/**
 *
 * @author Jayesh Jain
 */
@NoRepositoryBean
public interface SupplierLocationRepository extends JpaRepository<SupplierLocationEntity, Long>, ITextRepository<SupplierLocationEntity, Long> {
    
    public SupplierLocationEntity findByLocationCode(String locationCode);
    
}
