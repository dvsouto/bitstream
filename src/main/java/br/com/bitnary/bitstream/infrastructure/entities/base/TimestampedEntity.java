package br.com.bitnary.bitstream.infrastructure.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TimestampedEntity extends EntityWithUuid {
    @Column(name = "user_created_at")
    protected LocalDateTime createdAt;

    @Column(name = "user_updated_at")
    protected LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime dateNow = LocalDateTime.now();

        createdAt = dateNow;
        updatedAt = dateNow;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
