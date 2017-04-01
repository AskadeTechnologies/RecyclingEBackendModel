package ro.askade.smartRecycle.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by AdrianIonita on 3/8/2017.
 */
public class Client {

    private BigInteger clientId;
    private String clientName;
    private BigInteger clientCategoryId;
    private Date creationDate;
    private String createdBy;
    private Timestamp lastUpdateDate;
    private String lastUpdatedBy;

    @Column(name = "client_id", updatable=false)
    @Id
    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }
    @Column (name = "client_name")
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    @Column (name = "client_category_id")
    public BigInteger getClientCategoryId() {
        return clientCategoryId;
    }

    public void setClientCategoryId(BigInteger clientCategoryId) {
        this.clientCategoryId = clientCategoryId;
    }
    @Column (name = "creation_date", updatable=false)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    @Column (name = "created_by", updatable=false)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    @Column (name = "last_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Version
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    @Column(name = "last_updated_by")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
