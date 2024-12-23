package com.moreira.techpoint.services;

import com.moreira.techpoint.config.PasswordEncoderConfig;
import com.moreira.techpoint.dtos.EmployeeDTO;
import com.moreira.techpoint.dtos.EmployeeMinDTO;
import com.moreira.techpoint.entities.Employee;
import com.moreira.techpoint.entities.Role;
import com.moreira.techpoint.projections.UserDetailsProjection;
import com.moreira.techpoint.repositories.EmployeeRepository;
import com.moreira.techpoint.services.exceptions.DuplicateResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    @Transactional(readOnly = true)
    public List<EmployeeMinDTO> findAll() {
        List<Employee> result = repository.findAll();
        return result.stream().map(x -> new EmployeeMinDTO(x)).toList();
    }

    @Transactional
    public EmployeeDTO insert(EmployeeDTO dto) {
        if (repository.findByCpf(dto.getCpf()).isPresent())
            throw new DuplicateResourceException("Usuário já existe");

        Employee entity = new Employee();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setPassword(passwordEncoder.passwordEncoder().encode(dto.getCpf()));

        entity = repository.save(entity);
        return new EmployeeDTO(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchEmployeeAndRolesByEmployeeCode(username);

        if (result.isEmpty())
            throw new UsernameNotFoundException("Usuário não encontrado");

        Employee employee = new Employee();
        employee.setEmployeeCode(username);
        employee.setPassword(result.getFirst().getPassword());

        for (UserDetailsProjection proj: result) {
            employee.addRole(new Role(proj.getRoleId(), proj.getAuthority()));
        }

        return employee;
    }

    protected Employee authenticated() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return repository.findByEmployeeCode(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getMe() {
        Employee employee = authenticated();
        return new EmployeeDTO(employee);
    }
}
