package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(UserImp user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UserImp user) {
        userRepository.delete(user);
    }

    @Override
    public UserImp findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserImp findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserImp findById(String id) {
        return userRepository.findById(id);
    }
}
