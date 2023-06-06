package com.bol.mancala.repositories.impl;

import com.bol.mancala.domain.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class InMemoryGameRepositoryTest {
    @Autowired
    private InMemoryGameRepository repository;

    @Test
    void testSaveOrUpdate() {
        var entity = new Game();
        var savedEntity = repository.saveOrUpdate(entity);

        assertEquals(entity, savedEntity);
    }

    @Test
    void testFindByIdWhenEntityExists() {
        var savedEntity = repository.saveOrUpdate(new Game());
        var queriedEntity = repository.findById(savedEntity.getId());

        assertNotEquals(queriedEntity, Optional.empty());
        assertEquals(savedEntity, queriedEntity.get());
    }

    @Test
    void testFindByIdWhenIdIsNotExists() {
        assertEquals(repository.findById(UUID.randomUUID()), Optional.empty());
    }
}
