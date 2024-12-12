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
    public Page<TimeBankDTO> searchTimeBankByEmployee(String employeeCode, Pageable pageable) {
        System.out.println(employeeCode);
        Page<TimeBank> result = repository.searchByEmployeeCode(employeeCode, pageable);
        return result.map(x -> new TimeBankDTO(x));
    }

    @Transactional(readOnly = true)
    public TimeBankDTO findById(Long id) {
        TimeBank entity = repository.findById(id).get();
        return new TimeBankDTO(entity);
    }

    @Transactional
    public TimeBankDTO insert(Long employeeId) {
        Employee employee = repository.getReferenceById(employeeId).getEmployee();
        TimeBank entity = new TimeBank();
        entity.setEmployee(employee);
        entity.setDate(LocalDate.now());
        entity.setClockIn(LocalTime.now());

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

}
