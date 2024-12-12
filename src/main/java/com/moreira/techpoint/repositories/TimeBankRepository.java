package com.moreira.techpoint.repositories;

import com.moreira.techpoint.entities.TimeBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeBankRepository extends JpaRepository<TimeBank, Long> {
}
