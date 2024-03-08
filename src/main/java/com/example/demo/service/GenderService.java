package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Gender;
import com.example.demo.repository.GenderRepository;

@Service
public class GenderService {

	@Autowired
	private GenderRepository genderRepository;

	public GenderRepository getGenderRepository() {
		return genderRepository;
	}
	
	public List<Gender> getAllGender() {
		return genderRepository.findAll();
	}
	
	public Gender getGenderByID(Long id) {
		return genderRepository.findById(id).orElse(null);
	}
	
}
