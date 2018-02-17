/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvdlibrary.resources;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "C_CAPTURE_LINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CCaptureLine.findAll", query = "SELECT c FROM CCaptureLine c"),
    @NamedQuery(name = "CCaptureLine.findById", query = "SELECT c FROM CCaptureLine c WHERE c.id = :id"),
    @NamedQuery(name = "CCaptureLine.findByCaptureLine", query = "SELECT c FROM CCaptureLine c WHERE c.captureLine = :captureLine"),
    @NamedQuery(name = "CCaptureLine.findByCreationDate", query = "SELECT c FROM CCaptureLine c WHERE c.creationDate = :creationDate"),
    @NamedQuery(name = "CCaptureLine.findByProjectId", query = "SELECT c FROM CCaptureLine c WHERE c.projectId = :projectId"),
    @NamedQuery(name = "CCaptureLine.findByDivisionId", query = "SELECT c FROM CCaptureLine c WHERE c.divisionId = :divisionId"),
    @NamedQuery(name = "CCaptureLine.findByStatus", query = "SELECT c FROM CCaptureLine c WHERE c.status = :status"),
    @NamedQuery(name = "CCaptureLine.findByBankPayment", query = "SELECT c FROM CCaptureLine c WHERE c.bankPayment = :bankPayment"),
    @NamedQuery(name = "CCaptureLine.findByAmount", query = "SELECT c FROM CCaptureLine c WHERE c.amount = :amount"),
    @NamedQuery(name = "CCaptureLine.findByContractId", query = "SELECT c FROM CCaptureLine c WHERE c.contractId = :contractId"),
    @NamedQuery(name = "CCaptureLine.findByDueDate", query = "SELECT c FROM CCaptureLine c WHERE c.dueDate = :dueDate"),
    @NamedQuery(name = "CCaptureLine.findByReferenceNumber", query = "SELECT c FROM CCaptureLine c WHERE c.referenceNumber = :referenceNumber"),
    @NamedQuery(name = "CCaptureLine.findByGenerationType", query = "SELECT c FROM CCaptureLine c WHERE c.generationType = :generationType"),
    @NamedQuery(name = "CCaptureLine.findByGrouping", query = "SELECT c FROM CCaptureLine c WHERE c.grouping = :grouping"),
    @NamedQuery(name = "CCaptureLine.findByMergeable", query = "SELECT c FROM CCaptureLine c WHERE c.mergeable = :mergeable")})
public class CCaptureLine implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "CAPTURE_LINE")
    private String captureLine;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Size(max = 10)
    @Column(name = "PROJECT_ID")
    private String projectId;
    @Size(max = 20)
    @Column(name = "DIVISION_ID")
    private String divisionId;
    @Size(max = 50)
    private String status;
    @Size(max = 20)
    @Column(name = "BANK_PAYMENT")
    private String bankPayment;
    private BigDecimal amount;
    @Size(max = 10)
    @Column(name = "CONTRACT_ID")
    private String contractId;
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Size(max = 100)
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Size(max = 50)
    @Column(name = "GENERATION_TYPE")
    private String generationType;
    @Size(max = 20)
    private String grouping;
    private Short mergeable;
    @OneToMany(mappedBy = "captureLineId")
    private List<CInvoice> cInvoiceList;

    public CCaptureLine() {
    }

    public CCaptureLine(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCaptureLine() {
        return captureLine;
    }

    public void setCaptureLine(String captureLine) {
        this.captureLine = captureLine;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBankPayment() {
        return bankPayment;
    }

    public void setBankPayment(String bankPayment) {
        this.bankPayment = bankPayment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getGenerationType() {
        return generationType;
    }

    public void setGenerationType(String generationType) {
        this.generationType = generationType;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public Short getMergeable() {
        return mergeable;
    }

    public void setMergeable(Short mergeable) {
        this.mergeable = mergeable;
    }

    @XmlTransient
    public List<CInvoice> getCInvoiceList() {
        return cInvoiceList;
    }

    public void setCInvoiceList(List<CInvoice> cInvoiceList) {
        this.cInvoiceList = cInvoiceList;
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
        if (!(object instanceof CCaptureLine)) {
            return false;
        }
        CCaptureLine other = (CCaptureLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dvdlibrary.resources.CCaptureLine[ id=" + id + " ]";
    }
    
}
