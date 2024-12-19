package br.com.bitnary.bitstream.infrastructure.entities;

import br.com.bitnary.bitstream.infrastructure.entities.base.TimestampedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity extends TimestampedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_birthday_date")
    private LocalDate birthdayDate;

    @Column(name = "user_active")
    private boolean active;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<UserProfileEntity> profiles;
}
