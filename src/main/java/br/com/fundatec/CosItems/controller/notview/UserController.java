package br.com.fundatec.CosItems.controller.notview;

import br.com.fundatec.CosItems.model.UserModel;
import br.com.fundatec.CosItems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<UserModel> updateUser(@PathVariable String userId, @RequestBody UserModel userModel){
        UserModel updatedUser = userService.update(userId, userModel);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public UserModel getUserById(@PathVariable String userId) {
        return userService.getById(userId);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

}
