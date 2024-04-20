package br.com.fundatec.CosItems.service;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.model.UserRole;
import br.com.fundatec.CosItems.model.DTO.UserDTO;
import br.com.fundatec.CosItems.repository.ProductRepository;
import br.com.fundatec.CosItems.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminHomeService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public UserModel createAdminUser(UserDTO userRegisterDTO) throws Exception {

        if (userRepository.findByEmail(userRegisterDTO.email()) != null) {
            throw new Exception("Usuario com o email " + userRegisterDTO.email() + " já existe.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRegisterDTO.password());
        
        UserModel newUser = new UserModel(userRegisterDTO.email(), encryptedPassword, UserRole.ADMIN);

        userRepository.save(newUser);
    
        return newUser;
    }
    
    public ProductModel createProductAdmin(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public String saveImage(MultipartFile image) throws IOException {

        String originalFilename = image.getOriginalFilename();
        String extension = ".jpg";  // extension
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + extension;
        
        
        String directory = "src/main/resources/static/assets/productimages";
        String imagePath = directory + "/" + filename;
    
        byte[] bytes = image.getBytes();
        Path path = Paths.get(imagePath);
        Files.write(path, bytes);
    
        return imagePath;
    }
}
