package br.com.bitnary.bitstream.domain.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@ToString
public class Entity {
    protected String id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Entity() {
        LocalDateTime dateNow = LocalDateTime.now();

        this.id = null;
        this.createdAt = dateNow;
        this.updatedAt = dateNow;
    }

    public UUID getUuid() {
        if (this.id != null) {
            return UUID.fromString(this.id);
        }

        return null;
    }

    public void updateTimestamps() {
        LocalDateTime dateNow = LocalDateTime.now();

        if (id == null || createdAt == null) {
            this.setCreatedAt(dateNow);
        }

        this.setUpdatedAt(dateNow);
    }
}
