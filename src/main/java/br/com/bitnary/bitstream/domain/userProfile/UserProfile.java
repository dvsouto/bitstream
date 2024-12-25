package br.com.bitnary.bitstream.domain.userProfile;

import br.com.bitnary.bitstream.domain.core.Entity;
import br.com.bitnary.bitstream.domain.user.User;
import br.com.bitnary.bitstream.shared.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString
public class UserProfile extends Entity {
    private String name;
    private User user;

    public void setName(String name) {
        this.name = StringUtils.capitalizeFirst(name);
    }
}
