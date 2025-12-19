package com.example.library.controller;

import com.example.library.service.PatronService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PatronController
 */
@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping()
    public List<PatronDTO> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public PatronDTO getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    @PostMapping
    public PatronDTO createPatron(@RequestBody PatronDTO patronDTO) {
        return patronService.createPatron(patronDTO);
    }
}

