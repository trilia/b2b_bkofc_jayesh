/**
 * 
 */
package com.olp.jpa.domain.docu.inv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.olp.jpa.domain.docu.fin.model.TaxGroupEntity;
import com.olp.jpa.domain.docu.mkt.model.PromotionGroupEntity;

/**
 * @author Jayesh
 *
 */

@Embeddable
public class ChannelInvLot {
	
	@Column(name="lot_sequence")
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private int lotSequence;

	@Column(name="quantity")
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private float quantity;

	@Column(name="base_price")
	@Fields({
		@Field(index=Index.NO, store=Store.YES, analyze=Analyze.NO)
	})
	private float basePrice;

	@ManyToOne
	@JoinColumn(name="tax_group_ref")
	@ContainedIn
	private TaxGroupEntity taxGroupRef;

	@Column(name="tax_group_code")
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String taxGroupCode ;

	@ManyToOne
	@JoinColumn(name="promo_group_ref")
	@ContainedIn
	private PromotionGroupEntity promoGroupRef;

	@Column(name="promo_group_code")
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private String promoGroupCode;

	@Column(name="txn_date")
	@Fields({
		@Field(index=Index.YES, store=Store.NO, analyze=Analyze.NO)
	})
	private Date txnDate;

	/**
	 * @return the lotSequence
	 */
	public int getLotSequence() {
		return lotSequence;
	}

	/**
	 * @param lotSequence the lotSequence to set
	 */
	public void setLotSequence(int lotSequence) {
		this.lotSequence = lotSequence;
	}

	/**
	 * @return the quantity
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the basePrice
	 */
	public float getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * @return the taxGroupRef
	 */
	public TaxGroupEntity getTaxGroupRef() {
		return taxGroupRef;
	}

	/**
	 * @param taxGroupRef the taxGroupRef to set
	 */
	public void setTaxGroupRef(TaxGroupEntity taxGroupRef) {
		this.taxGroupRef = taxGroupRef;
	}

	/**
	 * @return the taxGroupCode
	 */
	public String getTaxGroupCode() {
		return taxGroupCode;
	}

	/**
	 * @param taxGroupCode the taxGroupCode to set
	 */
	public void setTaxGroupCode(String taxGroupCode) {
		this.taxGroupCode = taxGroupCode;
	}

	/**
	 * @return the promoGroupRef
	 */
	public PromotionGroupEntity getPromoGroupRef() {
		return promoGroupRef;
	}

	/**
	 * @param promoGroupRef the promoGroupRef to set
	 */
	public void setPromoGroupRef(PromotionGroupEntity promoGroupRef) {
		this.promoGroupRef = promoGroupRef;
	}

	/**
	 * @return the promoGroupCode
	 */
	public String getPromoGroupCode() {
		return promoGroupCode;
	}

	/**
	 * @param promoGroupCode the promoGroupCode to set
	 */
	public void setPromoGroupCode(String promoGroupCode) {
		this.promoGroupCode = promoGroupCode;
	}

	/**
	 * @return the txnDate
	 */
	public Date getTxnDate() {
		return txnDate;
	}

	/**
	 * @param txnDate the txnDate to set
	 */
	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

}
