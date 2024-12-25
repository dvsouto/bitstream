package br.com.bitnary.bitstream.domain.media;

import br.com.bitnary.bitstream.domain.core.Entity;
import br.com.bitnary.bitstream.domain.profile.UserProfile;
import br.com.bitnary.bitstream.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString
public class Media extends Entity {
    private String name;
    private MediaType type;
    private String path;

    private User user;
    private UserProfile userProfile;
}
