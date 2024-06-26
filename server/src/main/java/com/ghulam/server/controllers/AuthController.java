package com.ghulam.server.controllers;

import com.ghulam.server.dtos.RegisterApplicant;
import com.ghulam.server.dtos.RegisterRecruiter;
import com.ghulam.server.dtos.UserLogin;
import com.ghulam.server.models.Applicant;
import com.ghulam.server.models.Recruiter;
import com.ghulam.server.repositories.ApplicantRepository;
import com.ghulam.server.repositories.RecruiterRepository;
import com.ghulam.server.security.JwtService;
import com.ghulam.server.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "${api.endpoints.base-url}/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ApplicantRepository applicantRepository;
    private final RecruiterRepository recruiterRepository;


    public AuthController(
            AuthService authService,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            ApplicantRepository applicantRepository,
            RecruiterRepository recruiterRepository
    ) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.applicantRepository = applicantRepository;
        this.recruiterRepository = recruiterRepository;
    }

    @PostMapping("/register-applicant")
    public String registerApplicant(@RequestBody RegisterApplicant payload) {
        return authService.registerApplicant(payload);
    }

    @PostMapping("/register-recruiter")
    public String registerRecruiter(@RequestBody RegisterRecruiter payload) {
        return authService.registerRecruiter(payload);
    }

    @PostMapping("/login-applicant")
    public ResponseEntity<?> loginApplicant(@RequestBody UserLogin payload) {
        final String email = payload.email();
        final String password = payload.password();

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateTokenFromUsername((UserDetails) authentication.getPrincipal());

        Applicant applicant = applicantRepository.findByEmail(email).orElse(null);
        assert applicant != null;

        Map<String, Object> map = new HashMap<>();
        map.put("userId", applicant.getApplicantId());
        map.put("username", applicant.getUsername());
        map.put("role", applicant.getRole().toString());
        map.put("token", token);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/login-recruiter")
    public ResponseEntity<?> loginRecruiter(@RequestBody UserLogin payload) {
        final String email = payload.email();
        final String password = payload.password();

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateTokenFromUsername((UserDetails) authentication.getPrincipal());

        Recruiter recruiter = recruiterRepository.findByEmail(email).orElse(null);
        assert recruiter != null;

        Map<String, Object> map = new HashMap<>();
        map.put("userId", recruiter.getRecruiterId());
        map.put("username", recruiter.getUsername());
        map.put("role", recruiter.getRole().toString());
        map.put("token", token);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/userList")
    public Object userList() {
        List<Applicant> applicants = applicantRepository.findAll();
        List<Recruiter> recruiters = recruiterRepository.findAll();

        return new ResponseEntity<>(Map.of("applicants", applicants, "recruiters", recruiters), HttpStatus.OK);
    }

}
