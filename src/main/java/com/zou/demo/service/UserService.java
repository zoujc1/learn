package com.zou.demo.service;

import com.zou.demo.model.User;
import com.zou.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(User user){
        return userRepository.save(user);

    }
    public User update(User user){
        return userRepository.save(user);
    }
    public User findById(Long id){
        return userRepository.getOne(id);

    }
    public void delete(Long id){
        userRepository.deleteById(id);

    }
    public List<User> findList(){
        return userRepository.findAll();
    }

}
