package br.com.bitnary.bitstream.domain.user;

import br.com.bitnary.bitstream.domain.core.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User> {
    Optional<User> findByEmail(String email);

    User save(User user);
}
