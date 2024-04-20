package br.com.fundatec.CosItems.service;

import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.model.UserRole;
import br.com.fundatec.CosItems.model.DTO.UserDTO;
import br.com.fundatec.CosItems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserModel createUser(UserDTO userRegisterDTO) throws Exception {

        if (userRepository.findByEmail(userRegisterDTO.email()) != null) {
            throw new Exception("Usuario com o email " + userRegisterDTO.email() + " já existe.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());

        UserModel newUser = new UserModel(userRegisterDTO.email(), encryptedPassword, UserRole.USER);

        userRepository.save(newUser);

        return newUser;
    }

    public UserModel update(String id, UserModel user) {
        UserModel existingUser = userRepository.findById(id)
                .orElseThrow(() -> new Error("User not found with id: " + id));

        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public UserModel authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
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
