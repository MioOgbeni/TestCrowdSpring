package cz.spitsoft.testcrowd.repository;

import cz.spitsoft.testcrowd.model.user.RoleType;
import cz.spitsoft.testcrowd.model.user.UserImp;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private UserImp user;

    @Before
    public void setUp() {
        user = new UserImp();
        user.setEmail("testUser@testcrowd.cz");
        user.setUsername("testUser");
        user.setFirstName("Edward");
        user.setLastName("Grant");
        user.setPassword("testPassword");
        user.setPasswordConfirm("testPassword");
        user.setRoleType(RoleType.ADMIN);

        user = userRepository.saveAndFlush(user);
    }

    @Test
    public void saveFindUserByIdSuccess() {
        Assert.assertEquals(user, userRepository.findById(user.getId()));
    }

    @After
    public void tearDown() {
        userRepository.delete(user);
    }
}
