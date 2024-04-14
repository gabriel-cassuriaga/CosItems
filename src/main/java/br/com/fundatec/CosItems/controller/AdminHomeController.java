package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.service.AdminHomeService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String createUser(Model model, UserModel userModel) {
        try{
            adminHomeService.createAdminUser(userModel);
        } catch (Exception e) {
            model.addAttribute("error", "Usuario já cadastrado!");
        }
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("userModel", new UserModel());
        return "admin/index";
    }

    // @PostMapping("/product")
    // public String createProduct(Model model, ProductModel productModel, MultipartFile image) {
    //     adminHomeService.createProductAdmin(productModel);
    //     model.addAttribute("productModel", new ProductModel());
    //     model.addAttribute("userModel", new UserModel());
    //     return "admin/index";
    // }

    @PostMapping("/product")
    public String createProduct(Model model, ProductModel productModel, MultipartFile image) {
        try {
            Path dir = Paths.get("/assets/productimages");

            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            
            Path destination = Paths.get(dir.toString() + "/" + image.getOriginalFilename());
            Files.copy(image.getInputStream(), destination);
            productModel.setImagePath(destination.toString());

            adminHomeService.createProductAdmin(productModel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("userModel", new UserModel());
        return "admin/index";
    }

}
