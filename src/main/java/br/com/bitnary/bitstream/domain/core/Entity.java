package br.com.bitnary.bitstream.domain.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Entity {
    protected String id = null;
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt = LocalDateTime.now();

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = Entity.this.updatedAt;
    }

    public UUID getUuid() {
        if (this.id != null) {
            return UUID.fromString(this.id);
        }

        return null;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void updateTimestamps() {
        LocalDateTime dateNow = LocalDateTime.now();

        if (id == null || createdAt == null) {
            this.setCreatedAt(dateNow);
        }

        this.setUpdatedAt(dateNow);
    }
}
