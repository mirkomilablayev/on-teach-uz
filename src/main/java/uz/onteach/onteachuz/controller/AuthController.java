package uz.onteach.onteachuz.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import uz.onteach.onteachuz.domain.User;
import uz.onteach.onteachuz.dto.CommonResponse;
import uz.onteach.onteachuz.dto.LoginRequest;
import uz.onteach.onteachuz.dto.RegisterRequest;
import uz.onteach.onteachuz.repository.UserRepository;
import uz.onteach.onteachuz.utills.AuthConst;
import uz.onteach.onteachuz.utills.JWTUtils;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Mono<CommonResponse<?>> register(@RequestBody RegisterRequest registerRequest) {
        return userRepository.existsByUsername(registerRequest.getUsername())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new Exception());
                    }

                    User user = new User();
                    user.setFirstname(registerRequest.getFirstname());
                    user.setLastname(registerRequest.getLastname());
                    user.setUsername(registerRequest.getUsername());
                    user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                    user.setRoles(Set.of(AuthConst.USER_ROLE));
                    return userRepository.save(user)
                            .thenReturn(new CommonResponse<>("", "", 1));
                });
    }

    @PostMapping("/login")
    public Mono<CommonResponse<String>> login(@RequestBody LoginRequest loginRequest) {
          return userRepository.findByUsername(loginRequest.getUsername())
                .filter(user -> passwordEncoder.encode(loginRequest.getPassword()).equals(user.getPassword()))
                .map(user -> new CommonResponse<>(jwtUtils.generateToken(user), "", 1))
                .switchIfEmpty(Mono.error(new Exception()));
    }

}
