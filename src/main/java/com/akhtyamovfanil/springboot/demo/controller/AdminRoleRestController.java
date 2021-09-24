package com.akhtyamovfanil.springboot.demo.controller;

import com.akhtyamovfanil.springboot.demo.model.Role;
import com.akhtyamovfanil.springboot.demo.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class AdminRoleRestController {

    private final RoleService roleService;

    public AdminRoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return new ResponseEntity<>(roleService.getRoleList(), HttpStatus.OK);
    }


}
