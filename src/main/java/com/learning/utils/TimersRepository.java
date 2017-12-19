package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimersRepository extends JpaRepository<Timers, Integer> {

    @Query("select t from Timers t where (id_gradation between 1 and 3) order by rand()")
    List<Timers> findFirstWorst(Pageable pageable);

}
