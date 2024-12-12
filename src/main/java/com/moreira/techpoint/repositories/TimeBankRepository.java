package com.moreira.techpoint.repositories;

import com.moreira.techpoint.entities.TimeBank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeBankRepository extends JpaRepository<TimeBank, Long> {

    @Query("""
            SELECT obj FROM TimeBank obj
            WHERE obj.employee.employeeId = :employeeId
            """)
    Page<TimeBank> searchByEmployeeId(String employeeId, Pageable pageable);
}
