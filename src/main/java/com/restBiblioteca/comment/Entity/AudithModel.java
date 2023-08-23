package com.restBiblioteca.comment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"updated_at", "created_at"},
        allowGetters = true
)
public abstract class AudithModel implements Serializable {
    @Serial
    private static final long serialVersionUID= 1L;
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdAt= new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    public Date getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(Date created_at) {
        this.createdAt = created_at;
    }

    public Date getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(Date updated_at) {
        this.updatedAt = updated_at;
    }
}
