package com.moreira.techpoint.repositories;

import com.moreira.techpoint.entities.TimeBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TimeBankRepository extends JpaRepository<TimeBank, Long> {

    @Query("""
            SELECT obj FROM TimeBank obj
            WHERE obj.employee.employeeCode = :employeeCode
            """)
    Page<TimeBank> searchByEmployeeCode(String employeeCode, Pageable pageable);

    @Query("""
            SELECT obj FROM TimeBank obj
            WHERE obj.date = :date AND obj.employee.id = :employeeId
            """)
    Optional<TimeBank> searchByEmployeeAndDate(Long employeeId, LocalDate date);
}
