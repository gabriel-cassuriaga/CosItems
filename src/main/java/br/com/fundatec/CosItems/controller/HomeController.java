package br.com.fundatec.CosItems.controller;

import br.com.fundatec.CosItems.model.ProductModel;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.model.DTO.CartRequest;
import br.com.fundatec.CosItems.service.ProductService;
import br.com.fundatec.CosItems.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            String id = (String) inputFlashMap.get("userId");
            model.addAttribute("userId", id);

        }
        List<ProductModel> products = productService.findAll();
        model.addAttribute("products", products);
        return "home/index";

    }

    @PostMapping("/addCart/")
    public ResponseEntity<Void> addCart(@RequestBody CartRequest cartRequest) {
        ProductModel productModel = productService.findById(cartRequest.productId());

        UserModel userModel = userService.getById(cartRequest.userId());

        userModel.getShoppingCart().add(1, productModel);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cart/{id}")
    public RedirectView cart(RedirectAttributes redirectAttributes, @PathVariable("id") String userId) {
        redirectAttributes.addFlashAttribute("userId", userId);
        return new RedirectView("/shoppingcart", true);
    }

}
