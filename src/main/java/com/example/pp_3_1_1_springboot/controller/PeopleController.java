package com.example.pp_3_1_1_springboot.controller;

import com.example.pp_3_1_1_springboot.models.User;
import com.example.pp_3_1_1_springboot.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final UserService userService;

    public PeopleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index (Model model) {
        model.addAttribute("people", userService.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        return "people/new";
    }

    @PostMapping()
    public String create (@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit (@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") User user,
                          @PathVariable("id") int id) {
        userService.showUser(id);
        userService.update(user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteUser (@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/people";
    }
}
