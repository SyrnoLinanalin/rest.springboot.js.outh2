package com.akhtyamovfanil.springboot.demo.controller;

import com.akhtyamovfanil.springboot.demo.model.User;
import com.akhtyamovfanil.springboot.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminUserRestController {


    private final UserService userService;

    public AdminUserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> list(){
      return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> get(@PathVariable Long userid){
        return new ResponseEntity<>(userService.getById(userid),HttpStatus.OK);
        //check
    }

    @PostMapping("/add")
    public ResponseEntity<Void> add(@RequestBody User user){
        userService.add(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit/{userid}")
    public ResponseEntity<Void> update(
            @RequestBody User user){
       userService.update(user);
       return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<Void> delete(@PathVariable Long userid){
        userService.delete(userid);
        return ResponseEntity.ok().build();
    }



}
