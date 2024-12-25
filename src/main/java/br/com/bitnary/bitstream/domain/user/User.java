package br.com.bitnary.bitstream.domain.user;

import br.com.bitnary.bitstream.domain.core.Entity;
import br.com.bitnary.bitstream.domain.userProfile.UserProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString
public class User extends Entity implements UserDetails {
    private String name;
    private String email;
    private String password;
    private LocalDateTime birthdayDate;

    @Builder.Default
    private List<UserProfile> profiles = new ArrayList<>();

    private boolean active;
    private Collection<? extends GrantedAuthority> roles;

    public void setNewPassword(String password) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);

        this.password = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public void addProfile(UserProfile userProfile) {
        this.profiles.add(userProfile);
    }
}
