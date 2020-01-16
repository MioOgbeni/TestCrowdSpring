package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User2, Long> {
    Optional<User2> findByEmail(String email);

    Boolean existsByEmail(String email);
}
