package com.denisar5.perfumehub.service;

import com.denisar5.perfumehub.model.dto.UserLoginDto;
import com.denisar5.perfumehub.model.dto.UserRegisterDto;
import com.denisar5.perfumehub.model.entity.UserEntity;

public interface UserService {

    void register(UserRegisterDto userRegisterDto);

    UserEntity login(UserLoginDto userLoginDto);
}
