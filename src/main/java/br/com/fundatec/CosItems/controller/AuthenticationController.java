package br.com.fundatec.CosItems.controller;
import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public String registerUser(Model model, UserModel userModel) {
        
        try{
            userService.createUser(userModel);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorUserExists", "Usuario já cadastrado!");
            return "signin/index";
        }
    
    }

    @PostMapping("/login")
    public String loginUser(Model model, UserModel userModel){
        UserModel userLogin = userService.authenticate(userModel.getEmail(), userModel.getPassword());
        if (userLogin != null){
            return "redirect:/";
            }
        model.addAttribute("errorUserNotFound", "Usuario ou senha inválidos!");
        return "signin/index";
    }

     @GetMapping
    public String signin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "signin/index";
    }


}
