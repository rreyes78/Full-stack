package com.rm.jwt.backend.cotrollers;

import com.rm.jwt.backend.config.UserAuthProvider;
import com.rm.jwt.backend.dto.CredentialDto;
import com.rm.jwt.backend.dto.SignUpDto;
import com.rm.jwt.backend.dto.UserDto;
import com.rm.jwt.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Signature;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login (@RequestBody CredentialDto credentialDto) {
        UserDto user =  userService.login(credentialDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);

    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/app_user/"+ user.getId())).body(user);
    }
}
