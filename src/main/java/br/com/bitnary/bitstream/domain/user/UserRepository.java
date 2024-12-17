package br.com.bitnary.bitstream.domain.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    Optional<User> authenticate(String email, String password);

    User save(User user);
}
