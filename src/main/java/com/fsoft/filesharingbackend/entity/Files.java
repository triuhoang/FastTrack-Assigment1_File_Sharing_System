package com.fsoft.filesharingbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author TriuHN
 */
@Entity
@Table(name = "FILES", catalog = "FILE_SHARING_DB", schema = "dbo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"MD5"})})
@XmlRootElement
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "FILENAME", nullable = false, length = 200)
    private String filename;
    @Basic(optional = false)
    @Column(name = "MD5", nullable = false, length = 1500)
    private String md5;
    @Basic(optional = false)
    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "MODIFIED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @JoinColumn(name = "USERS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Users usersId;

    @PrePersist
    void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = new Date();
        }
        if (this.modifiedAt == null) {
            this.modifiedAt = new Date();
        }
    }

    @PreUpdate
    void preUpdate() {
        this.modifiedAt = new Date();
    }
}
