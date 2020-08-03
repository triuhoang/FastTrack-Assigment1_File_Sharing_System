package com.fsoft.filesharingbackend;

import com.fsoft.filesharingbackend.entity.Files;
import com.fsoft.filesharingbackend.entity.Users;
import com.fsoft.filesharingbackend.repository.FileRepository;
import com.fsoft.filesharingbackend.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FileRepositoryTest {
    @Autowired
    private FileRepository fileRepository;

    @Test
    /*
     * Find File by null md5
     */
    public void findByMd5Null() {
        Optional<Files> file = this.fileRepository.findByMd5(null);
        Assert.assertEquals(false, file.isPresent());
    }

    @Test
    /*
     * Find File by incorrect md5
     */
    public void findByMd5Incorrect() {
        Optional<Files> file = this.fileRepository.findByMd5("a1sd12");
        Assert.assertEquals(false, file.isPresent());
    }

}
