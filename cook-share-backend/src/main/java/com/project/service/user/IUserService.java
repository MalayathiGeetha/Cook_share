package com.project.service.user;

import com.project.model.User;

public interface IUserService {

  User registerUser(User user);

  String findByUsername(String username);
}
