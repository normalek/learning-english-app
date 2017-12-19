package com.learning.utils;

import com.learning.models.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
    @Query("select d from Dictionary d order by word")
    List<Dictionary> findWithOrder();
}
