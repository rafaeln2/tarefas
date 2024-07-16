package com.tarefas.tarefas.controllers;

import com.tarefas.tarefas.models.User;
import com.tarefas.tarefas.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    //localhost:8080/user/1
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}") // exemplo de get usando path variable na url
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user); //ResponseEntity.ok(user)
    }

    @GetMapping("/get-mapping") // exemplo de get usando request param na url
    public ResponseEntity<User> findByIdThroughRequestParam(@RequestParam("id") Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user); //ResponseEntity.ok(user)
    }

    @PostMapping()
    @Validated(User.CreateUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(User.UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
