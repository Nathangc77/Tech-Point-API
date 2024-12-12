package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.entities.TimeBank;
import com.moreira.techpoint.repositories.TimeBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
