package com.rm.jwt.backend.services;

import com.rm.jwt.backend.dto.CredentialDto;
import com.rm.jwt.backend.dto.SignUpDto;
import com.rm.jwt.backend.dto.UserDto;
import com.rm.jwt.backend.entities.User;
import com.rm.jwt.backend.exception.AppException;
import com.rm.jwt.backend.mapper.UserMapper;
import com.rm.jwt.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;



    public UserDto login(CredentialDto credentialDto) {
       User user = userRepository.findByLogin(credentialDto.login())
                .orElseThrow(() -> new AppException("Uknown User", HttpStatus.NOT_FOUND));

       if(passwordEncoder.matches(CharBuffer.wrap(credentialDto.password()),
               user.getPassword())) {
           return userMapper.toDtoUser(user);
       }

       throw  new AppException("Invalid Password", HttpStatus.BAD_REQUEST);

    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<User> oUser = userRepository.findByLogin(signUpDto.login());
        if(oUser.isPresent()) {
            throw new AppException("Login already esist" , HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

        User savedUser = userRepository.save(user);
        return userMapper.toDtoUser(savedUser);
    }


}
