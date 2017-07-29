package com.olp.jpa.domain.docu.fin.model;

/**
 *
 * @author Jayesh Jain
 */
public class FinEnums {
    
    public static enum CustInvoiceType {
    	STANDARD,
    	CREDITINV
    };
    
    public static enum PaymentTerm {
    	IMMEDIATE,
        COD,
        REFUND,
        CREDIT
    };
    
    public static enum PaymentMethod {
        COD,
        GATEWAY,
        WALLET
    };
}
