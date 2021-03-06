package com.my.application.black.jack.model;


import javax.persistence.*;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * This is a base class for all entities. It provides an equals and hashcode that will always work correctly in all
 * circumstances. This avoids frequent errors related to the implementation of those same methods.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    protected Long id;

    @Version
    private Long version;

    @Transient
    private UUID uuid;

    @Column(name = "UUID")
    private String uuidStr;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;

    public AbstractEntity() {
        syncCreatedOn();
    }

    @PrePersist
    protected void prePersist() {
        syncUuidString();
    }

    private void syncCreatedOn() {
        if (createdOn == null) {
            createdOn = LocalDateTime.now();
        }
    }

    protected void syncUuidString() {
        if (null == uuidStr) {
            // initial method call fills the uuid
            getUuid();
        }
    }

    public UUID getUuid() {
        if (uuidStr == null) {
            if (uuid == null) {
                uuid = UUID.randomUUID();
            }
            uuidStr = uuid.toString();
        }
        return uuid;
    }

    public Long getId() {
        return id;
    }

    /*
    *
    *   This method is mean for testing purposes only (create mock data), as we should not set Ids manually,
    *   Hibernate will do it automatically
    *
    **/
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (getUuid() != null ? !getUuid().equals(that.getUuid()) : that.getUuid() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getUuid() != null ? getUuid().hashCode() : 0;
    }

    public Long getVersion() {
        return version;
    }

    public String getUuidStr() {
        syncUuidString();
        return uuidStr;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }
}

