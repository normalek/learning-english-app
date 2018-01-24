package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TimersRepositoryImpl implements CustomTimersRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public String findTopByUser(Integer words_count) {
        return null;
    }
}
