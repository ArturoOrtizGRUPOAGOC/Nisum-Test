package com.grupoagoc.test.service.impl;

import com.grupoagoc.test.controller.model.UserRequest;
import com.grupoagoc.test.exception.UserException;
import com.grupoagoc.test.persist.User;
import com.grupoagoc.test.persist.repository.UserRepository;
import com.grupoagoc.test.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository;

    @Override
    public User registerOrUpdateUserDetails(UserRequest input) throws UserException {
        User user = null;

        if(input.getId()==null || input.getId().equals(0)){
            if(input.getEmail()==null)
                throw new UserException("Email is required");
            List<User> sameEmail = userRepository.findUsersByEmail(input.getEmail());
            if(sameEmail.size()>0){
                String emailAlreadyExist = String.join("", "The email: ", input.getEmail(), " was previously registered.");
                throw new UserException(emailAlreadyExist);
            }

            user = input.newRegister();
        } else {
            user = input.updateRegister();
        }

        userRepository.save(user);
        return user;
    }
}
