package cz.spitsoft.testcrowd.service;

import cz.spitsoft.testcrowd.model.user.UserImp;
import cz.spitsoft.testcrowd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserImp> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<UserImp> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
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
