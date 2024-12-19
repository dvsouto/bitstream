package br.com.bitnary.bitstream.domain.userProfile;

import br.com.bitnary.bitstream.domain.core.Entity;
import br.com.bitnary.bitstream.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString
public class UserProfile extends Entity {
    private String name;
    private User user;
}
