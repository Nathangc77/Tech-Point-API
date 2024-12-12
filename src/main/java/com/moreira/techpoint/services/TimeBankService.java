package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.entities.TimeBank;
import com.moreira.techpoint.repositories.TimeBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class TimeBankService {

    @Autowired
    private TimeBankRepository repository;

    @Transactional(readOnly = true)
    public Page<TimeBankDTO> findAll(Pageable pageable) {
        Page<TimeBank> result = repository.findAll(pageable);
        return result.map(x -> new TimeBankDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<TimeBankDTO> searchTimeBankByEmployee(String employeeId, Pageable pageable) {
        System.out.println(employeeId);
        Page<TimeBank> result = repository.searchByEmployeeId(employeeId, pageable);
        return result.map(x -> new TimeBankDTO(x));
    }

    @Transactional
    public TimeBankDTO insert(Long id) {
        Employee employee = repository.getReferenceById(id).getEmployee();
        TimeBank entity = new TimeBank();
        entity.setEmployee(employee);
        entity.setDate(LocalDate.now());
        entity.setClockIn(LocalTime.now());

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

}
