package br.com.bitnary.bitstream.infrastructure.entities;

import br.com.bitnary.bitstream.domain.media.MediaType;
import br.com.bitnary.bitstream.infrastructure.entities.base.TimestampedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users_medias")
public class UserMediaEntity extends TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_media_id")
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfileEntity userProfile;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "user_media_type")
    @Enumerated(EnumType.STRING)
    private MediaType type;

    @Column(name = "user_media_name")
    private String name;

    @Column(name = "user_media_path")
    private String path;
}
