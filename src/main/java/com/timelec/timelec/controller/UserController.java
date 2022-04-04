package com.timelec.timelec.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.timelec.timelec.exception.ResourceNotFoundException;
import com.timelec.timelec.models.ERole;
import com.timelec.timelec.models.Role;
import com.timelec.timelec.models.User;
import com.timelec.timelec.payload.request.SignupRequest;
import com.timelec.timelec.payload.response.MessageResponse;
import com.timelec.timelec.repository.RoleRepository;
import com.timelec.timelec.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
	private UserRepository userRepository;
	
    @Autowired
	private RoleRepository roleRepository;	
	
    @Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/all")
	public List<User>getAllUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable Long id) {
		
		User user = userRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("User not exist with id:" + id));
		return user;
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails){
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exit with id:" + id));
		user.setEmail(userDetails.getEmail());
		user.setUsername(userDetails.getUsername());
		
		User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User user = userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User not exit with id:" + id));
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/roles/all", method = RequestMethod.GET)
	public List<Role>getAllRoles(){
		return roleRepository.findAll();
	}	
	
}