package com.test.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.api.model.User;
import com.test.api.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

	private final UserRepository userRepository;
	
	@GetMapping("/users/listUsers")
	public List<User> listUsers() { return userRepository.findAll(); }
	
	@PostMapping("/users")
	public User guardar(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<User>buscarporId(@PathVariable Integer id){
		return userRepository.findById(id);
	}
	
	@DeleteMapping("/users/{id}")
	public void eliminar(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
}
