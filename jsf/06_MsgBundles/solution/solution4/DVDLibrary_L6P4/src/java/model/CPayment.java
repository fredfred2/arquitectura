/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fredfred2
 */
@Entity
@Table(name = "C_PAYMENT")
@NamedQueries({
    @NamedQuery(name = "CPayment.findAll", query = "SELECT c FROM CPayment c"),
    @NamedQuery(name = "CPayment.findById", query = "SELECT c FROM CPayment c WHERE c.id = :id"),
    @NamedQuery(name = "CPayment.findByPaymentIdentifier", query = "SELECT c FROM CPayment c WHERE c.paymentIdentifier = :paymentIdentifier"),
    @NamedQuery(name = "CPayment.findByReceiptMethodId", query = "SELECT c FROM CPayment c WHERE c.receiptMethodId = :receiptMethodId"),
    @NamedQuery(name = "CPayment.findByPaymentDate", query = "SELECT c FROM CPayment c WHERE c.paymentDate = :paymentDate"),
    @NamedQuery(name = "CPayment.findByAmountApplied", query = "SELECT c FROM CPayment c WHERE c.amountApplied = :amountApplied"),
    @NamedQuery(name = "CPayment.findByCurrencyCodeApplied", query = "SELECT c FROM CPayment c WHERE c.currencyCodeApplied = :currencyCodeApplied")})
public class CPayment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    @Size(max = 40)
    @Column(name = "PAYMENT_IDENTIFIER")
    private String paymentIdentifier;
    @Column(name = "RECEIPT_METHOD_ID")
    private BigInteger receiptMethodId;
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT_APPLIED")
    private BigDecimal amountApplied;
    @Size(max = 3)
    @Column(name = "CURRENCY_CODE_APPLIED")
    private String currencyCodeApplied;

    public CPayment() {
    }

    public CPayment(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentIdentifier() {
        return paymentIdentifier;
    }

    public void setPaymentIdentifier(String paymentIdentifier) {
        this.paymentIdentifier = paymentIdentifier;
    }

    public BigInteger getReceiptMethodId() {
        return receiptMethodId;
    }

    public void setReceiptMethodId(BigInteger receiptMethodId) {
        this.receiptMethodId = receiptMethodId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmountApplied() {
        return amountApplied;
    }

    public void setAmountApplied(BigDecimal amountApplied) {
        this.amountApplied = amountApplied;
    }

    public String getCurrencyCodeApplied() {
        return currencyCodeApplied;
    }

    public void setCurrencyCodeApplied(String currencyCodeApplied) {
        this.currencyCodeApplied = currencyCodeApplied;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CPayment)) {
            return false;
        }
        CPayment other = (CPayment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CPayment[ id=" + id + " ]";
    }
    
}
