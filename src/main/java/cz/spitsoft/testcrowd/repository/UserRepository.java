package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.RoleImp;
import cz.spitsoft.testcrowd.model.UserImp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserImp<RoleImp>, UUID> {
    UserImp<RoleImp> findByUsername(String username);

    UserImp<RoleImp> findByEmail(String email);

    Boolean existsByEmail(String email);

    UserImp<RoleImp> findById(String id);
}
