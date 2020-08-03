package com.fsoft.filesharingbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author TriuHN
 */
@Entity
@Table(name = "USERS", catalog = "FILE_SHARING_DB", schema = "dbo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"USERNAME", "EMAIL", "MOBILE_NO"})})
@XmlRootElement
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;
    @Basic(optional = false)
    @Column(name = "GENDER", nullable = false, length = 10)
    private String gender;
    @Basic(optional = false)
    @Column(name = "FULL_NAME", nullable = false, length = 100)
    private String fullName;
    @Basic(optional = false)
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @Column(name = "DOB", nullable = false, length = 10)
    private String dob;
    @Basic(optional = false)
    @Column(name = "MOBILE_NO", nullable = false, length = 20)
    private String mobileNo;
    @Basic(optional = false)
    @Column(name = "STATUS", nullable = false)
    private int status;
    @Basic(optional = false)
    @Column(name = "CREATED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "MODIFIED_AT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersId")
    private List<Files> fileCollection;

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
