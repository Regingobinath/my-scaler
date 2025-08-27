package com.myscaler.bms.services;

import com.myscaler.bms.models.User;
import com.myscaler.bms.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User signupUser(String name, String email, String password) throws Exception {

        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new Exception("User already exist");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(newUser);

        return newUser;
    }

    @Override
    public boolean login(String email, String password) throws Exception {

        Optional<User> user = this.userRepository.findByEmail(email);
        user.orElseThrow(() -> new Exception("User not found"));

        return BCrypt.checkpw(password, user.get().getPassword());
    }
}
