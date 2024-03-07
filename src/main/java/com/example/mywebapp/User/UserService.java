package com.example.mywebapp.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository repo;

    public List<user> listAll(){
        return (List<user>) repo.findAll();
    }

    public void save(user users) {

        repo.save(users);
    }

    public user get(Integer id) throws UserNotFoundException{
        Optional<user>result=repo.findById(id);
        if(result.isPresent()){
            return  result.get();
        }

        throw new UserNotFoundException("Could not find any users with ID" +id);
    }
}
