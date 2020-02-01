package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.user.UserImp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserImp, UUID> {

    @Nonnull
    List<UserImp> findAll();

    @Nonnull
    Page<UserImp> findByUsernameContaining(String userName, @Nonnull Pageable pageable);

    UserImp findById(String id);

    UserImp findByUsername(String username);

    UserImp findByEmail(String email);

}
