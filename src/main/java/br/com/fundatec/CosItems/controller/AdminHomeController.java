package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.model.DTO.UserDTO;
import br.com.fundatec.CosItems.service.AdminHomeService;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {
    @Autowired
    AdminHomeService adminHomeService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("userModel", new UserModel());
        return "admin/index";
    }

    @PostMapping("/register")
    public String registerUser(Model model, UserDTO userDTO) {
        try{
            adminHomeService.createAdminUser(userDTO);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorUserExists", "Usuario já cadastrado!");
        }
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("userModel", new UserModel());
        return "admin/index";
    
    }

    @PostMapping("/product")
    public String createProduct(Model model, ProductModel productModel, MultipartFile image) {
    
        try {
    
            String uploadDir = "src/main/resources/static/assets/productimages/";
    
            // Gerar um nome de arquivo único
            String originalFilename = image.getOriginalFilename();
            @SuppressWarnings("null")
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;
    
            File file = new File(uploadDir + filename);
    
            Files.write(file.toPath(), image.getBytes());
    
            productModel.setImagePath("/assets/productimages/" + filename);
    
            adminHomeService.createProductAdmin(productModel);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("userModel", new UserModel());
        return "admin/index";
    }
    

}
