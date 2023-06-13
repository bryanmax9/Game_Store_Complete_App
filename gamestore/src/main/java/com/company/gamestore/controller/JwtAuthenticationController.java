package com.company.gamestore.controller;

import com.company.gamestore.config.JwtTokenUtil;
import com.company.gamestore.model.JwtRequest;
import com.company.gamestore.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ModelAndView createAuthenticationToken(JwtRequest authenticationRequest, HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        try {
            Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication); // Add this line
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            request.getSession().setAttribute("token", token); // Save the token in the session
            modelAndView.setViewName("redirect:/admin/dashboard"); // Redirect to your desired endpoint
            return modelAndView;
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    private Authentication authenticate(String username, String password) throws Exception { // change return type to Authentication
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); // return the authentication object
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}

