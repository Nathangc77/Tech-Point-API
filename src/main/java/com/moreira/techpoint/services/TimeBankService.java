package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.entities.TimeBank;
import com.moreira.techpoint.repositories.EmployeeRepository;
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
    @Autowired
    private EmployeeRepository employeeRepository;

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
        Employee employee = employeeRepository.getReferenceById(employeeId);
        TimeBank entity = new TimeBank();
        entity.setEmployee(employee);
        entity.setDate(LocalDate.now());
        entity.setClockIn(LocalTime.now());

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

    @Transactional
    public TimeBankDTO insertManual(TimeBankDTO dto) {
        TimeBank entity = new TimeBank();
        copyDtoForEntity(dto, entity);

        Employee employee = employeeRepository.getReferenceById(dto.getEmployeeId());
        entity.setEmployee(employee);

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

    private void copyDtoForEntity(TimeBankDTO dto, TimeBank entity) {
        entity.setDate(dto.getDate());
        entity.setClockIn(dto.getClockIn());
        entity.setLunchOut(dto.getLunchOut());
        entity.setLunchIn(dto.getLunchIn());
        entity.setClockOut(dto.getClockOut());
    }

}
