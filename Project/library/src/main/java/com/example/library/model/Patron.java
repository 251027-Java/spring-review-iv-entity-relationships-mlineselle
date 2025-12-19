package com.example.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Book Entity
 */
@Entity
@Table(name = "patrons")
public class Patron {

    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Loan> loans = new ArrayList<>();

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private LocalDate memberSince;

    // Default constructor required by JPA
    public Patron() {
        this.memberSince = LocalDate.now();
    }

    public Patron(String name, String email) {
        this.name = name;
        this.email = email;
        this.memberSince = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }
}
