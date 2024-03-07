package com.example.mywebapp;

import com.example.mywebapp.User.UserRepository;
import com.example.mywebapp.User.user;
import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Iterator;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddnew(){
        //User user=new User();
        user users=new user();
        users.setEmail("faiza@gmail.com");
        users.setPassword("214587");
        users.setFirstname("MD.");
        users.setLastname("WWW");

        user savedUser =repo.save(users);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);



    }

    @Test
    public void testListAll(){
      Iterable<user>users=  repo.findAll();
      Assertions.assertThat(users).hasSizeGreaterThan(0);

      for(user userss:users){
          System.out.println(userss);
      }
    }

    @Test
    public void testUpdate(){
        Integer userId=1;
        Optional<user>optionalUser=repo.findById(userId);
        user users=optionalUser.get();
        users.setPassword("111111");
        repo.save(users);

        user updateUser=repo.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("111111");

    }

    @Test
    public void testGet(){
        Integer userId=2;
        Optional<user>optionalUser=repo.findById(userId);


        //user updateUser=repo.findById(userId).get();
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId=2;
        repo.deleteById(userId);

        Optional<user>optionalUser=repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
