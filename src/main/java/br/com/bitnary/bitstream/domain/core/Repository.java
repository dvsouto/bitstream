package br.com.bitnary.bitstream.domain.core;

import br.com.bitnary.bitstream.infrastructure.entities.UserMediaEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface Repository<Repo> {
    List<Repo> findAll();
    Optional<Repo> findById(String id);

    default <DatabaseEntity, DomainEntity> List<DomainEntity> mapList(List<DatabaseEntity> list, Function<DatabaseEntity, DomainEntity> mapperMethod) {
        if (list == null) {
            return List.of();
        }

        return list
                .stream()
                .map(mapperMethod)
                .toList();
    }

    default <DatabaseEntity, DomainEntity> Optional<DomainEntity> mapObject(Optional<DatabaseEntity> rawEntity, Function<DatabaseEntity, DomainEntity> mapperMethod) {
        return rawEntity.map(mapperMethod);
    }
}
