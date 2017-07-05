package com.olp.jpa.domain.docu.be.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.be.model.SupplierEntity;

/**
 *
 * @author Jayesh Jain
 */
@Repository("supplierRepository")
public class SupplierRepositoryImpl extends AbstractRepositoryImpl<SupplierEntity, Long> implements SupplierRepository {
    
    @Override
    @Transactional(readOnly=true)
    public SupplierEntity findBySupplierCode(String supplierCode) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<SupplierEntity> query = getEntityManager().createNamedQuery("Supplier.findBySupplierCode", SupplierEntity.class);
        query.setParameter("code", supplierCode); 
        query.setParameter("tenant", tid);
        
        SupplierEntity bean = query.getSingleResult();
        
        return(bean);
    }
    
    @Override
    @Transactional(readOnly=true)
    public SupplierEntity findBySupplierName(String supplierName) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<SupplierEntity> query = getEntityManager().createNamedQuery("Supplier.findBySupplierName", SupplierEntity.class);
        query.setParameter("name", supplierName); 
        query.setParameter("tenant", tid);
        
        SupplierEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return(null);
    }
}
