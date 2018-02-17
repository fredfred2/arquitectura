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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fredfred2
 */
@Entity
@Table(name = "C_INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CInvoice.findAll", query = "SELECT c FROM CInvoice c"),
    @NamedQuery(name = "CInvoice.findById", query = "SELECT c FROM CInvoice c WHERE c.id = :id"),
    @NamedQuery(name = "CInvoice.findByBusinessUnit", query = "SELECT c FROM CInvoice c WHERE c.businessUnit = :businessUnit"),
    @NamedQuery(name = "CInvoice.findByTransactionSource", query = "SELECT c FROM CInvoice c WHERE c.transactionSource = :transactionSource"),
    @NamedQuery(name = "CInvoice.findByTransactionType", query = "SELECT c FROM CInvoice c WHERE c.transactionType = :transactionType"),
    @NamedQuery(name = "CInvoice.findByTrxDate", query = "SELECT c FROM CInvoice c WHERE c.trxDate = :trxDate"),
    @NamedQuery(name = "CInvoice.findByGlDate", query = "SELECT c FROM CInvoice c WHERE c.glDate = :glDate"),
    @NamedQuery(name = "CInvoice.findByBillToCustomerName", query = "SELECT c FROM CInvoice c WHERE c.billToCustomerName = :billToCustomerName"),
    @NamedQuery(name = "CInvoice.findByBillToAccountNumber", query = "SELECT c FROM CInvoice c WHERE c.billToAccountNumber = :billToAccountNumber"),
    @NamedQuery(name = "CInvoice.findByPaymentTermsName", query = "SELECT c FROM CInvoice c WHERE c.paymentTermsName = :paymentTermsName"),
    @NamedQuery(name = "CInvoice.findByInvoiceCurrencyCode", query = "SELECT c FROM CInvoice c WHERE c.invoiceCurrencyCode = :invoiceCurrencyCode"),
    @NamedQuery(name = "CInvoice.findByConversionDate", query = "SELECT c FROM CInvoice c WHERE c.conversionDate = :conversionDate"),
    @NamedQuery(name = "CInvoice.findByConversionRateType", query = "SELECT c FROM CInvoice c WHERE c.conversionRateType = :conversionRateType"),
    @NamedQuery(name = "CInvoice.findByConversionRate", query = "SELECT c FROM CInvoice c WHERE c.conversionRate = :conversionRate"),
    @NamedQuery(name = "CInvoice.findByPurchaseOrder", query = "SELECT c FROM CInvoice c WHERE c.purchaseOrder = :purchaseOrder"),
    @NamedQuery(name = "CInvoice.findBySoldToCustomerName", query = "SELECT c FROM CInvoice c WHERE c.soldToCustomerName = :soldToCustomerName"),
    @NamedQuery(name = "CInvoice.findByBillToContact", query = "SELECT c FROM CInvoice c WHERE c.billToContact = :billToContact"),
    @NamedQuery(name = "CInvoice.findByServiceStatus", query = "SELECT c FROM CInvoice c WHERE c.serviceStatus = :serviceStatus"),
    @NamedQuery(name = "CInvoice.findByTransactionNumber", query = "SELECT c FROM CInvoice c WHERE c.transactionNumber = :transactionNumber"),
    @NamedQuery(name = "CInvoice.findByCustomerTrxId", query = "SELECT c FROM CInvoice c WHERE c.customerTrxId = :customerTrxId"),
    @NamedQuery(name = "CInvoice.findByContractNumber", query = "SELECT c FROM CInvoice c WHERE c.contractNumber = :contractNumber"),
    @NamedQuery(name = "CInvoice.findByLocalNumber", query = "SELECT c FROM CInvoice c WHERE c.localNumber = :localNumber"),
    @NamedQuery(name = "CInvoice.findByContractCategory", query = "SELECT c FROM CInvoice c WHERE c.contractCategory = :contractCategory"),
    @NamedQuery(name = "CInvoice.findByLocalType", query = "SELECT c FROM CInvoice c WHERE c.localType = :localType"),
    @NamedQuery(name = "CInvoice.findByCustomerId", query = "SELECT c FROM CInvoice c WHERE c.customerId = :customerId"),
    @NamedQuery(name = "CInvoice.findByOrgId", query = "SELECT c FROM CInvoice c WHERE c.orgId = :orgId"),
    @NamedQuery(name = "CInvoice.findByStatus", query = "SELECT c FROM CInvoice c WHERE c.status = :status")})
public class CInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    private Integer id;
    @Size(max = 255)
    @Column(name = "BUSINESS_UNIT")
    private String businessUnit;
    @Size(max = 255)
    @Column(name = "TRANSACTION_SOURCE")
    private String transactionSource;
    @Size(max = 255)
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "TRX_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trxDate;
    @Column(name = "GL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date glDate;
    @Size(max = 255)
    @Column(name = "BILL_TO_CUSTOMER_NAME")
    private String billToCustomerName;
    @Size(max = 255)
    @Column(name = "BILL_TO_ACCOUNT_NUMBER")
    private String billToAccountNumber;
    @Size(max = 255)
    @Column(name = "PAYMENT_TERMS_NAME")
    private String paymentTermsName;
    @Size(max = 20)
    @Column(name = "INVOICE_CURRENCY_CODE")
    private String invoiceCurrencyCode;
    @Column(name = "CONVERSION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conversionDate;
    @Size(max = 20)
    @Column(name = "CONVERSION_RATE_TYPE")
    private String conversionRateType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONVERSION_RATE")
    private BigDecimal conversionRate;
    @Size(max = 255)
    @Column(name = "PURCHASE_ORDER")
    private String purchaseOrder;
    @Size(max = 255)
    @Column(name = "SOLD_TO_CUSTOMER_NAME")
    private String soldToCustomerName;
    @Size(max = 255)
    @Column(name = "BILL_TO_CONTACT")
    private String billToContact;
    @Size(max = 255)
    @Column(name = "SERVICE_STATUS")
    private String serviceStatus;
    @Size(max = 255)
    @Column(name = "TRANSACTION_NUMBER")
    private String transactionNumber;
    @Column(name = "CUSTOMER_TRX_ID")
    private BigInteger customerTrxId;
    @Size(max = 20)
    @Column(name = "CONTRACT_NUMBER")
    private String contractNumber;
    @Size(max = 20)
    @Column(name = "LOCAL_NUMBER")
    private String localNumber;
    @Size(max = 20)
    @Column(name = "CONTRACT_CATEGORY")
    private String contractCategory;
    @Size(max = 20)
    @Column(name = "LOCAL_TYPE")
    private String localType;
    @Column(name = "CUSTOMER_ID")
    private BigInteger customerId;
    @Column(name = "ORG_ID")
    private BigInteger orgId;
    @Size(max = 20)
    private String status;
    @OneToMany(mappedBy = "invoiceId")
    private List<CReceipt> cReceiptList;
    @JoinColumn(name = "CAPTURE_LINE_ID", referencedColumnName = "ID")
    @ManyToOne
    private CCaptureLine captureLineId;

    public CInvoice() {
    }

    public CInvoice(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTransactionSource() {
        return transactionSource;
    }

    public void setTransactionSource(String transactionSource) {
        this.transactionSource = transactionSource;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getBillToCustomerName() {
        return billToCustomerName;
    }

    public void setBillToCustomerName(String billToCustomerName) {
        this.billToCustomerName = billToCustomerName;
    }

    public String getBillToAccountNumber() {
        return billToAccountNumber;
    }

    public void setBillToAccountNumber(String billToAccountNumber) {
        this.billToAccountNumber = billToAccountNumber;
    }

    public String getPaymentTermsName() {
        return paymentTermsName;
    }

    public void setPaymentTermsName(String paymentTermsName) {
        this.paymentTermsName = paymentTermsName;
    }

    public String getInvoiceCurrencyCode() {
        return invoiceCurrencyCode;
    }

    public void setInvoiceCurrencyCode(String invoiceCurrencyCode) {
        this.invoiceCurrencyCode = invoiceCurrencyCode;
    }

    public Date getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(Date conversionDate) {
        this.conversionDate = conversionDate;
    }

    public String getConversionRateType() {
        return conversionRateType;
    }

    public void setConversionRateType(String conversionRateType) {
        this.conversionRateType = conversionRateType;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getSoldToCustomerName() {
        return soldToCustomerName;
    }

    public void setSoldToCustomerName(String soldToCustomerName) {
        this.soldToCustomerName = soldToCustomerName;
    }

    public String getBillToContact() {
        return billToContact;
    }

    public void setBillToContact(String billToContact) {
        this.billToContact = billToContact;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public BigInteger getCustomerTrxId() {
        return customerTrxId;
    }

    public void setCustomerTrxId(BigInteger customerTrxId) {
        this.customerTrxId = customerTrxId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getContractCategory() {
        return contractCategory;
    }

    public void setContractCategory(String contractCategory) {
        this.contractCategory = contractCategory;
    }

    public String getLocalType() {
        return localType;
    }

    public void setLocalType(String localType) {
        this.localType = localType;
    }

    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public BigInteger getOrgId() {
        return orgId;
    }

    public void setOrgId(BigInteger orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<CReceipt> getCReceiptList() {
        return cReceiptList;
    }

    public void setCReceiptList(List<CReceipt> cReceiptList) {
        this.cReceiptList = cReceiptList;
    }

    public CCaptureLine getCaptureLineId() {
        return captureLineId;
    }

    public void setCaptureLineId(CCaptureLine captureLineId) {
        this.captureLineId = captureLineId;
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
        if (!(object instanceof CInvoice)) {
            return false;
        }
        CInvoice other = (CInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvdlibrary.resources.CInvoice[ id=" + id + " ]";
    }
    
}
