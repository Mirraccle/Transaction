package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.LoginDto;
import com.task.transaction.dto.UserDto;

public interface UserService {

    CommonResponse signUp(UserDto userDto);

    CommonResponse login(LoginDto loginDto);
}
