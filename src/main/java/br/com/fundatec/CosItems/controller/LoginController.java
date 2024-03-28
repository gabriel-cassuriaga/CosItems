package br.com.fundatec.CosItems.controller;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/signin")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser(UserModel userModel) {
        UserModel createdUser = userService.create(userModel);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginUser(){
        return "";
    }

    @GetMapping
    public String signin() {
        return "signin/signin";
    }

}
