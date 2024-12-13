package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.DeletionRequestDTO;
import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.dtos.UpdateTimeBankDTO;
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
        entity.setClockIn(LocalTime.now().withNano(0));

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

    @Transactional
    public TimeBankDTO update(Long id, UpdateTimeBankDTO dto) {
        TimeBank entity = repository.getReferenceById(id);

        if (dto.isUpdateLunchOut() && entity.getLunchOut() == null && LocalTime.now().isAfter(entity.getClockIn())) {
            entity.setLunchOut(LocalTime.now().withNano(0));
        } else if (dto.isUpdateLunchIn() && entity.getLunchIn() == null && LocalTime.now().isAfter(entity.getLunchOut())) {
            entity.setLunchIn(LocalTime.now().withNano(0));
        } else if (dto.isUpdateClockOut() && entity.getClockOut() == null && LocalTime.now().isAfter(entity.getLunchIn())) {
            entity.setClockOut(LocalTime.now().withNano(0));
        } else {
            throw new IllegalArgumentException();
        }

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

    @Transactional
    public TimeBankDTO updateManual(Long id, TimeBankDTO dto) {
        TimeBank entity = repository.getReferenceById(id);

        if ((dto.getLunchOut() != null && entity.getClockIn() != null &&
                (entity.getLunchOut() == null || entity.getClockIn().isBefore(dto.getLunchOut())))) {
            entity.setLunchOut(dto.getLunchOut());
        }

        if ((dto.getLunchIn() != null && entity.getLunchOut() != null &&
                (entity.getLunchIn() == null || entity.getLunchOut().isBefore(dto.getLunchIn())))) {
            entity.setLunchIn(dto.getLunchIn());
        }

        if ((dto.getClockOut() != null && entity.getLunchIn() != null &&
                (entity.getClockOut() == null || entity.getLunchIn().isBefore(dto.getClockOut())))) {
            entity.setClockOut(dto.getClockOut());
        }

        if ((dto.getClockIn() != null && (entity.getClockIn() == null || entity.getClockIn().isBefore(dto.getLunchOut())))) {
            entity.setClockIn(dto.getClockIn());
        }

        entity = repository.save(entity);
        return new TimeBankDTO(entity);
    }

    @Transactional
    public void softDelete(Long id, DeletionRequestDTO dto) {
        TimeBank entity = repository.getReferenceById(id);
        entity.setDeleted(true);
        entity.setDeleteReason(dto.getDeleteReason());
        repository.save(entity);
    }

    private void copyDtoForEntity(TimeBankDTO dto, TimeBank entity) {
        entity.setDate(dto.getDate());
        entity.setClockIn(dto.getClockIn());
        entity.setLunchOut(dto.getLunchOut());
        entity.setLunchIn(dto.getLunchIn());
        entity.setClockOut(dto.getClockOut());
    }
}
