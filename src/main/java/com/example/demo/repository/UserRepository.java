package com.example.demo.repository;

import com.example.demo.entity.Contact;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository{

    Map<Long, User> userMap = new HashMap<>();

    //simple in-memory implementation
    public UserRepository() {
        Contact miller = new Contact("miller@miller.com", "123564");
        User user = new User(1211, "Mike Miller", miller);
        userMap.put(user.getUserId(), user);
        Contact michael = new Contact("michael@michael.com", "123456");
        user = new User(1311, "Michael",michael);
        userMap.put(user.getUserId(), user);
        Contact luis = new Contact("luis@luis.com", "111111");
        user = new User(1411, "Luis",luis);
        userMap.put(user.getUserId(), user);
        Contact natelli = new Contact("natelli@natelli.com", "333333");
        user = new User(1511, "Natelli",natelli);
        userMap.put(user.getUserId(), user);
    }

    public User getUserById(long id) {
        if (userMap.get(id) != null) {
            return userMap.get(id);
        } else
            throw new UserNotFoundException("User ID not found - " + id);
    }

    public List<User> getAllUsers() {
        return List.copyOf(userMap.values());
    }
}
