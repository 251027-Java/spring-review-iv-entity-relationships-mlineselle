package com.example.library.service;

import com.example.library.controller.PatronDTO;
import com.example.library.exception.PatronNotFoundException;
import com.example.library.model.Patron;
import com.example.library.repository.PatronRepository;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * PatronService
 */
@Service
public class PatronService {

    private final PatronRepository patronRepository;

    public PatronService(
            PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<PatronDTO> getAllPatrons() {
        return patronRepository.findAll().stream().map(this::PatronToDTO).toList();
    }

    public PatronDTO getPatronById(Long patronId) {
        return PatronToDTO(patronRepository.findById(patronId)
                .orElseThrow(() -> new PatronNotFoundException("Patron not found with id " + patronId)));
    }

    public PatronDTO createPatron(PatronDTO patronDTO) {
        return PatronToDTO(patronRepository.save(new Patron(patronDTO.name(), patronDTO.email())));
    }

    private PatronDTO PatronToDTO(Patron patron) {
        return new PatronDTO(
                patron.getLoans(),
                patron.getId(),
                patron.getName(),
                patron.getEmail(),
                patron.getMemberSince());
    }

}
