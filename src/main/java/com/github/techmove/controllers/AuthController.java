package com.github.techmove.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.techmove.services.AuthManager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {
    private AuthenticationManager authManager;

    @GetMapping("/signin")
    public String show() {
        return "auth/signin";
    }    

    @PostMapping("/authenticate")
    public String signin(@RequestParam String email, @RequestParam String password) {
        log.info("hiii");
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/dashboard";
    }

    @PostMapping("/signout")
    public String signout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }  
}
