package com.learning.utils;

import com.learning.models.Timers;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called dictionaryRepository
// CRUD refers Create, Read, Update, Delete

public interface TimersRepository extends CrudRepository<Timers, Integer> {

}
