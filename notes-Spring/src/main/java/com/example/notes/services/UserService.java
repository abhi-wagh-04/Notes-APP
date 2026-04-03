package com.example.notes.services;

import com.example.notes.dto.UserDTO;
import com.example.notes.models.Role;
import com.example.notes.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUserName(String username);

    void updateAccountLockStatus(Long userId, Boolean lock);

    List<Role> getAllRoles();

    void updateAccountExpiryStatus(Long userId, Boolean expire);

    void updateAccountEnabledStatus(Long userId, Boolean enabled);

    void updatePassword(Long userId, String password);

    void updateCredientialsExpiryStatus(Long userId, Boolean expire);
}

