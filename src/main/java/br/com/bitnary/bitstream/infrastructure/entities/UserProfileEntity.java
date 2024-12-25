package br.com.bitnary.bitstream.infrastructure.entities;

import br.com.bitnary.bitstream.infrastructure.entities.base.TimestampedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users_profiles")
public class UserProfileEntity extends TimestampedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_profile_id")
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "user_profile_name")
    private String name;

    @Column(name = "user_profile_default")
    private boolean defaultProfile;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserMediaEntity> medias;
}
