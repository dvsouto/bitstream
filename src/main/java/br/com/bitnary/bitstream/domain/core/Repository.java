package br.com.bitnary.bitstream.domain.core;

import java.util.List;
import java.util.Optional;

public interface Repository<Repo> {
    List<Repo> findAll();
    Optional<Repo> findById(String id);
}
