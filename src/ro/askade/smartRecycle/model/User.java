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
@Entity
@Table(name = "XXSRC_USERS")
public class User {
    private BigInteger userId;
    private String userName;
    private String userDescription;
    private String password;
    private String socialMediaId;
    private Date dataIn;
    private Date dataOut;
    private Date creationDate;
    private String createdBy;
    private Timestamp lastUpdateDate;
    private String lastUpdatedBy;

    @Column (name = "user_id", updatable = false)
    @Id
    public BigInteger getUserId() {
        return userId;
    }

    @Column (name = "user_name", updatable = false)
    public String getUserName() {
        return userName;
    }

    @Column (name = "user_description")
    public String getUserDescription() {
        return userDescription;
    }

    @Column (name = "password")
    public String getPassword() {
        return password;
    }

    @Column (name = "social_media_id")
    public String getSocialMediaId() {
        return socialMediaId;
    }

    @Column (name = "data_in", updatable = false)
    public Date getDataIn() {
        return dataIn;
    }

    @Column (name = "data_out")
    public Date getDataOut() {
        return dataOut;
    }
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column (name = "creation_date", updatable = false)
    public Date getCreationDate() {
        return creationDate;
    }

    @Column (name = "created_by", updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Version
    @Column (name = "last_update_date")
    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    @Column (name = "last_updated_by")
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public void setDataIn(Date dataIn) {
        this.dataIn = dataIn;
    }

    public void setDataOut(Date dataOut) {
        this.dataOut = dataOut;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
