package com.aduilio.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aduilio.med.dto.TokenDto;
import com.aduilio.med.dto.UserDto;
import com.aduilio.med.entity.User;
import com.aduilio.med.service.TokenService;

import jakarta.validation.Valid;

/**
 * A controller to receive the requests for the login.
 */
@RestController
@RequestMapping("login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<TokenDto> login(@RequestBody @Valid final UserDto userDto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUser(), userDto.getPassword());

        var authentication = authenticationManager.authenticate(authenticationToken);

        var token = tokenService.generate((User) authentication.getPrincipal());

        return ResponseEntity.ok(TokenDto.builder().token(token).build());
    }
}
