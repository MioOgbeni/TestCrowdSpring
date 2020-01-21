package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.UserImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserImp, UUID> {
    UserImp findByUsername(String username);

    UserImp findByEmail(String email);

    Boolean existsByEmail(String email);

    UserImp findById(String id);
}
