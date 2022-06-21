package com.grupoagoc.test.service;

import com.grupoagoc.test.controller.model.UserRequest;
import com.grupoagoc.test.exception.UserException;
import com.grupoagoc.test.persist.User;

public interface LoginService {
    User registerOrUpdateUserDetails(UserRequest user) throws UserException;
}
