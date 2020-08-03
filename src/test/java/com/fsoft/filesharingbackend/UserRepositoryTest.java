package com.fsoft.filesharingbackend;

import com.fsoft.filesharingbackend.entity.Users;
import com.fsoft.filesharingbackend.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    /*
     * Login with correct username and password
     */
    public void findByUsernameAndPassword1() {
        Optional<Users> user = this.userRepository.findByUsernameAndPassword("triuhn", "123456");
        Assert.assertEquals(true, user.isPresent());
    }

    @Test
    /*
     * Login with incorrect username and password
     */
    public void findByUsernameAndPassword2() {
        Optional<Users> user = this.userRepository.findByUsernameAndPassword("triuhn", "1234567");
        Assert.assertEquals(false, user.isPresent());
    }

    @Test
    /*
     * Login with null username and password
     */
    public void findByUsernameAndPassword3() {
        Optional<Users> user = this.userRepository.findByUsernameAndPassword(null, null);
        Assert.assertEquals(false, user.isPresent());
    }
}
