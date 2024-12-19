package com.moreira.techpoint.services;

import com.moreira.techpoint.dtos.DeletionRequestDTO;
import com.moreira.techpoint.dtos.TimeBankDTO;
import com.moreira.techpoint.dtos.UpdateTimeBankDTO;
import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.entities.TimeBank;
import com.moreira.techpoint.services.exceptions.DuplicateResourceException;
import com.moreira.techpoint.services.exceptions.InvalidTimeBankUpdateException;
import com.moreira.techpoint.services.exceptions.ResourceNotFoundException;
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
    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public Page<TimeBankDTO> findAll(Pageable pageable) {
        Page<TimeBank> result = repository.findAll(pageable);
        return result.map(x -> new TimeBankDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<TimeBankDTO> searchTimeBankByEmployee(String employeeCode, Pageable pageable) {
        Page<TimeBank> result = repository.searchByEmployeeCode(employeeCode, pageable);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        return result.map(x -> new TimeBankDTO(x));

    }

    @Transactional(readOnly = true)
    public TimeBankDTO findById(Long id) {
        TimeBank entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        authService.validateEmployeeOrAdmin(entity.getEmployee().getId());
        return new TimeBankDTO(entity);
    }

    @Transactional
    public TimeBankDTO insert(Long employeeId) {
        Employee employee = employeeRepository.getReferenceById(employeeId);
        authService.validateEmployeeOrAdmin(employeeId);

        TimeBank entity = new TimeBank();
        entity.setEmployee(employee);
        entity.setDate(LocalDate.now());
        entity.setClockIn(LocalTime.now().withNano(0));

        if (repository.searchByEmployeeAndDate(employee.getId(), entity.getDate()).isPresent())
            throw new DuplicateResourceException("Registro já existente");

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
        try {
            TimeBank entity = repository.getReferenceById(id);

            if (dto.isUpdateLunchOut() && entity.getLunchOut() == null && LocalTime.now().isAfter(entity.getClockIn())) {
                entity.setLunchOut(LocalTime.now().withNano(0));
            } else if (dto.isUpdateLunchIn() && entity.getLunchIn() == null && LocalTime.now().isAfter(entity.getLunchOut())) {
                entity.setLunchIn(LocalTime.now().withNano(0));
            } else if (dto.isUpdateClockOut() && entity.getClockOut() == null && LocalTime.now().isAfter(entity.getLunchIn())) {
                entity.setClockOut(LocalTime.now().withNano(0));
            } else {
                throw new InvalidTimeBankUpdateException("O horário de saída já foi registrado");
            }

            entity = repository.save(entity);
            return new TimeBankDTO(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public TimeBankDTO updateManual(Long id, TimeBankDTO dto) {
        try {
            TimeBank entity = repository.getReferenceById(id);

            if ((dto.getClockIn() != null && (entity.getClockIn() == null || entity.getClockIn().isAfter(dto.getLunchOut())))) {
                entity.setClockIn(dto.getClockIn());
            }

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

            entity = repository.save(entity);
            return new TimeBankDTO(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public void softDelete(Long id, DeletionRequestDTO dto) {
        try {
            TimeBank entity = repository.getReferenceById(id);
            entity.setDeleted(true);
            entity.setDeleteReason(dto.getDeleteReason());
            repository.save(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    private void copyDtoForEntity(TimeBankDTO dto, TimeBank entity) {
        entity.setDate(dto.getDate());
        entity.setClockIn(dto.getClockIn());
        entity.setLunchOut(dto.getLunchOut());
        entity.setLunchIn(dto.getLunchIn());
        entity.setClockOut(dto.getClockOut());
    }
}
