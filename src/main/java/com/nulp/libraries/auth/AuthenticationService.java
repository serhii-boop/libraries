package com.nulp.libraries.auth;

import com.nulp.libraries.entity.auth.AuthRequest;
import com.nulp.libraries.entity.auth.AuthResponse;
import com.nulp.libraries.entity.auth.RegisterRequest;
import com.nulp.libraries.entity.library.Role;
import com.nulp.libraries.entity.library.Worker;
import com.nulp.libraries.repository.library.WorkerRepository;
import com.nulp.libraries.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final WorkerRepository workerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var worker = Worker.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.WORKER)
                .build();

        workerRepository.save(worker);
        var jwtToken = jwtService.generateToken(worker);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          request.getEmail(),
                          request.getPassword()
                  )
          );
          var worker = workerRepository.findByEmail(request.getEmail())
                  .orElseThrow();

        var jwtToken = jwtService.generateToken(worker);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
