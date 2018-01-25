package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TimersRepository extends JpaRepository<Timers, Integer>, CustomTimersRepository {

    @Query("select t from Timers t where DATEDIFF(now(), date_learned) >= :user_date order by rand()")
    List<Timers> findFirstWorst(Pageable pageable, @Param("user_date") Integer user_date);

//    @Transactional
//    @Procedure(name = "find_words")
//    List<Timers> selectTopByUser(@Param("words_amount") Integer words_amount);
//
//    @Procedure
//    List<Timers> plus1inout(Integer arg);
}
