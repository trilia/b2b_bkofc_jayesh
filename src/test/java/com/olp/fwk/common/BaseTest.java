/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.fwk.common;

import com.olp.fwk.common.MultiTenantAccess.Level;
import com.olp.fwk.common.impl.OliveRootContext;
import com.olp.fwk.config.ConfigurationBuilder;
import com.olp.fwk.config.error.ConfigurationException;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author raghosh
 */

public abstract class BaseTest {
    
    @BeforeClass
    public static void beforeClass() throws ConfigurationException {
        OliveRootContext ctx = (OliveRootContext) ContextManager.getContext("1001");
        SecurityContext sctx = new SecurityContext();
        sctx.setMtAccessLevel(Level.ALL);
        sctx.setUserId(100);
        sctx.setUserName("UT");
        ctx.setSecCtx(sctx);
    }
    
    protected static String getRandom() {
        
        String s = Long.toHexString(Double.doubleToLongBits(Math.random()));
        
        return(s);
    }
    
    protected static String getTenantId() {
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        return(tid);
    }
    
}
