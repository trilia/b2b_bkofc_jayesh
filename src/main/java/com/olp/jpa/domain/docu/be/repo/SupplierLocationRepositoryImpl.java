package com.olp.jpa.domain.docu.be.repo;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.be.model.SupplierLocationEntity;

/**
 *
 * @author Jayesh Jain
 */
@Repository("supplierRepository")
public class SupplierLocationRepositoryImpl extends AbstractRepositoryImpl<SupplierLocationEntity, Long> implements SupplierLocationRepository {
    
    @Override
    @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
    public SupplierLocationEntity findByLocationCode(String locationCode) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<SupplierLocationEntity> query = getEntityManager().createNamedQuery("Supplier.findByLocationCode", SupplierLocationEntity.class);
        query.setParameter("code", locationCode); 
        query.setParameter("tenant", tid);
        
        SupplierLocationEntity bean = query.getSingleResult();
        
        return(bean);
    }
    
    @Override
    public String getLazyLoadElements() {
        return(null);
    }
}
