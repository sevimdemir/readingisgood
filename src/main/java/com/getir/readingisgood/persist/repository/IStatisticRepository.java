package com.getir.readingisgood.persist.repository;

import com.getir.readingisgood.persist.model.Statistic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IStatisticRepository extends MongoRepository<Statistic, String> {
    Optional<Statistic> findByYearAndMonth(Integer year, Integer month);
}
