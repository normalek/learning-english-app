package com.learning.service;

import com.learning.models.Users;
import com.learning.utils.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsersHelper {
    @Autowired
    private UsersRepository usersRepository;

    public Users getCurrentUser() {
        UserDetails current_user_details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersRepository.findByName(current_user_details.getUsername());
    }
}
