package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;
import cz.spitsoft.testcrowd.repository.UserRepository;
import cz.spitsoft.testcrowd.repository.testcases.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserImp user, Set<RoleImp> roles) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        userRepository.save(user);
    }

    @Override
    public UserImp findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserImp findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
