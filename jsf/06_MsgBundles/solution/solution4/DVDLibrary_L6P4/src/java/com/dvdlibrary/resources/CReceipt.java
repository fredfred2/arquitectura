/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.resources;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fredfred2
 */
@Entity
@Table(name = "C_RECEIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CReceipt.findAll", query = "SELECT c FROM CReceipt c"),
    @NamedQuery(name = "CReceipt.findById", query = "SELECT c FROM CReceipt c WHERE c.id = :id"),
    @NamedQuery(name = "CReceipt.findByAmount", query = "SELECT c FROM CReceipt c WHERE c.amount = :amount"),
    @NamedQuery(name = "CReceipt.findByCurrencyCode", query = "SELECT c FROM CReceipt c WHERE c.currencyCode = :currencyCode"),
    @NamedQuery(name = "CReceipt.findByGlDate", query = "SELECT c FROM CReceipt c WHERE c.glDate = :glDate"),
    @NamedQuery(name = "CReceipt.findByReceiptDate", query = "SELECT c FROM CReceipt c WHERE c.receiptDate = :receiptDate"),
    @NamedQuery(name = "CReceipt.findByReceiptMethodId", query = "SELECT c FROM CReceipt c WHERE c.receiptMethodId = :receiptMethodId"),
    @NamedQuery(name = "CReceipt.findByReceiptNumber", query = "SELECT c FROM CReceipt c WHERE c.receiptNumber = :receiptNumber"),
    @NamedQuery(name = "CReceipt.findByCaptureLine", query = "SELECT c FROM CReceipt c WHERE c.captureLine = :captureLine"),
    @NamedQuery(name = "CReceipt.findByReferenceNumber", query = "SELECT c FROM CReceipt c WHERE c.referenceNumber = :referenceNumber"),
    @NamedQuery(name = "CReceipt.findByAmountApplied", query = "SELECT c FROM CReceipt c WHERE c.amountApplied = :amountApplied"),
    @NamedQuery(name = "CReceipt.findByTransactionNumber", query = "SELECT c FROM CReceipt c WHERE c.transactionNumber = :transactionNumber"),
    @NamedQuery(name = "CReceipt.findByTransactionDate", query = "SELECT c FROM CReceipt c WHERE c.transactionDate = :transactionDate"),
    @NamedQuery(name = "CReceipt.findByApplicationDate", query = "SELECT c FROM CReceipt c WHERE c.applicationDate = :applicationDate"),
    @NamedQuery(name = "CReceipt.findByAccountingDate", query = "SELECT c FROM CReceipt c WHERE c.accountingDate = :accountingDate"),
    @NamedQuery(name = "CReceipt.findByCashReceiptId", query = "SELECT c FROM CReceipt c WHERE c.cashReceiptId = :cashReceiptId"),
    @NamedQuery(name = "CReceipt.findByStatus", query = "SELECT c FROM CReceipt c WHERE c.status = :status")})
public class CReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal amount;
    @Size(max = 5)
    @Column(name = "CURRENCY_CODE")
    private String currencyCode;
    @Column(name = "GL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date glDate;
    @Column(name = "RECEIPT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiptDate;
    @Column(name = "RECEIPT_METHOD_ID")
    private BigInteger receiptMethodId;
    @Size(max = 20)
    @Column(name = "RECEIPT_NUMBER")
    private String receiptNumber;
    @Size(max = 50)
    @Column(name = "CAPTURE_LINE")
    private String captureLine;
    @Size(max = 50)
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Column(name = "AMOUNT_APPLIED")
    private Integer amountApplied;
    @Size(max = 20)
    @Column(name = "TRANSACTION_NUMBER")
    private String transactionNumber;
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Column(name = "APPLICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date applicationDate;
    @Column(name = "ACCOUNTING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date accountingDate;
    @Column(name = "CASH_RECEIPT_ID")
    private BigInteger cashReceiptId;
    @Size(max = 20)
    private String status;
    @JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID")
    @ManyToOne
    private CInvoice invoiceId;

    public CReceipt() {
    }

    public CReceipt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public BigInteger getReceiptMethodId() {
        return receiptMethodId;
    }

    public void setReceiptMethodId(BigInteger receiptMethodId) {
        this.receiptMethodId = receiptMethodId;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getCaptureLine() {
        return captureLine;
    }

    public void setCaptureLine(String captureLine) {
        this.captureLine = captureLine;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getAmountApplied() {
        return amountApplied;
    }

    public void setAmountApplied(Integer amountApplied) {
        this.amountApplied = amountApplied;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public BigInteger getCashReceiptId() {
        return cashReceiptId;
    }

    public void setCashReceiptId(BigInteger cashReceiptId) {
        this.cashReceiptId = cashReceiptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CInvoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(CInvoice invoiceId) {
        this.invoiceId = invoiceId;
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
        if (!(object instanceof CReceipt)) {
            return false;
        }
        CReceipt other = (CReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvdlibrary.resources.CReceipt[ id=" + id + " ]";
    }
    
}
