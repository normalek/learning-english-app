package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimersRepository extends JpaRepository<Timers, Integer> {

    @Query("select t from Timers t where rownum <= :count")
    List<Timers> findFirstWorst(@Param("count") Integer count);
}
