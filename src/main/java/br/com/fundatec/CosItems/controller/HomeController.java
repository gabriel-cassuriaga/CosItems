package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @GetMapping
    public String index(Model model) {
        List<ProductModel> products = productService.findAll();
        
        model.addAttribute("products", products);
        return "home/index";
    }

    @PostMapping("/cart")
    public String cart() {
        return "redirect:/cart";
    }
}
