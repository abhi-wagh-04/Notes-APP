package com.example.notes.controllers;

import com.example.notes.dto.UserDTO;
import com.example.notes.models.Role;
import com.example.notes.models.User;
import com.example.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    @Autowired
    UserService userService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update-role")
    public ResponseEntity<String> updateUserRole(@RequestParam Long userId,
                                                 @RequestParam String roleName) {
        userService.updateUserRole(userId, roleName);
        return ResponseEntity.ok("User role updated");
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')") --> We can use this Method-Lvl security annotaions in both controller and security and we can use it at class level as well
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/update-lock-status")
    public ResponseEntity<String> updateAccountLockStatus(@RequestParam Long userId, @RequestParam Boolean lock){
        userService.updateAccountLockStatus(userId, lock);
        return ResponseEntity.ok("Account lock status updated");
    }

    @GetMapping("/roles")
    public List<Role> getRoles(){
        return userService.getAllRoles();
    }

    @PutMapping("/update-expiry-status")
    public ResponseEntity<String> updateAccountExpiryStatus(@RequestParam Long userId, @RequestParam Boolean expire){
        userService.updateAccountExpiryStatus(userId, expire);
        return ResponseEntity.ok("Account Expiry Status updated");
    }

    @PutMapping("/update-enabled-status")
    public ResponseEntity<String> updateAccountEnabled(@RequestParam Long userId, @RequestParam Boolean enabled){
        userService.updateAccountEnabledStatus(userId, enabled);
        return ResponseEntity.ok("Account Enabled Status updated");
    }
    
    @PutMapping("/update-credentials-expiry-status")
    public ResponseEntity<String> updateCredentailsExpiryStatus(@RequestParam Long userId, @RequestParam Boolean expire){
        userService.updateCredientialsExpiryStatus(userId, expire);
        return ResponseEntity.ok("Credentials updated successfully");
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam Long userId, @RequestParam String password){
        try{
            userService.updatePassword(userId, password);
            return ResponseEntity.ok("Password Updated Successfully");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}

