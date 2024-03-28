package br.com.fundatec.CosItems.service;

import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserModel create(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public UserModel update(String id, UserModel user) {
        ///////AJUSTAR EXCEÇÃO/////////////////////////////////////////////////
        UserModel existingUser = userRepository.findById(id).
                orElseThrow(() -> new Error("User not found with id: " + id));
        //////////////////////////////////////////////////////////////////////

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public UserModel getById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }
}

