/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author satyam
 */
@Entity
@Table(name = "BAMA_ACCOUNTS")
@NamedQueries({})
public class BamaAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "DOMAIN_NAME", nullable = false)
    private String domainName;
    @Column(name = "SERIAL_KEY")
    private String serialKey;
    @Column(name = "SCHEMA_VERSION", nullable = false)
    private int schemaVersion = -1;
    @Column(name = "PAYMENT_TYPE")
    private Integer paymentType;
    @Column(name = "TYPE", nullable = false)
    private int type = -1;
    @Column(name = "ZONE_ID", nullable = false)
    private int zoneId;
    @Column(name = "STATUS", nullable = false)
    private String status;
    @Column(name = "DATE_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "DATE_MODIFIED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;
    @Column(name = "DATE_EXPIRED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpired;
    @Column(name = "DISABLED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabled;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "TEMPLATE_ID")
    private Integer templateId;
    @Column(name = "IN_SYNC")
    private String inSync;

    public BamaAccount() {
    }

    public BamaAccount(Integer accountId) {
        this.accountId = accountId;
    }

    public BamaAccount(Integer accountId, String name, String domainName, int schemaVersion, int type, int zoneId, String status, Date dateCreated, Date dateModified, Date dateExpired) {
        this.accountId = accountId;
        this.name = name;
        this.domainName = domainName;
        this.schemaVersion = schemaVersion;
        this.type = type;
        this.zoneId = zoneId;
        this.status = status;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.dateExpired = dateExpired;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getSerialKey() {
        return serialKey;
    }

    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    public int getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(int schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }    

    public String getInSync() {
        return inSync;
    }

    public void setInSync(String isSync) {
        this.inSync = isSync;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Date getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    public Date getDisabled() {
        return disabled;
    }

    public void setDisabled(Date disabled) {
        this.disabled = disabled;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BamaAccount)) {
            return false;
        }
        BamaAccount other = (BamaAccount) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BamaAccounts[accountId=" + accountId + "]";
    }

}
