package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long> {

}
