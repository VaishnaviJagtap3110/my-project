package com.wheelshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheelshare.entity.User;
import com.wheelshare.service.CustomerServiceImpl;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
	@Autowired
    private CustomerServiceImpl customerService;
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getCustomer(@PathVariable Long id) {
        User user = customerService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/profile")
    public ResponseEntity<User> updateProfile(@RequestBody User user) {
        User updatedUser = customerService.updateProfile(user);
        return ResponseEntity.ok(updatedUser);
    }

}
