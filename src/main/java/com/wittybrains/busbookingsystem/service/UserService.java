package com.wittybrains.busbookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wittybrains.busbookingsystem.dto.UserDTO;
import com.wittybrains.busbookingsystem.model.User;
import com.wittybrains.busbookingsystem.repository.UserRepository;




@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());
        user.setAddress(userDTO.getAddress());
        user.setCountry(userDTO.getCountry());

        User createdUser = userRepository.save(user);
        return new UserDTO(createdUser);
    }

    public UserDTO getUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDTO(user);
        } else {
            return null;
        }
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setEmail(userDTO.getEmail());
            user.setCity(userDTO.getCity());
            user.setAddress(userDTO.getAddress());
            user.setCountry(userDTO.getCountry());

            User updatedUser = userRepository.save(user);
            return new UserDTO(updatedUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(new UserDTO(user));
        }
        return userDTOList;
    }
}
