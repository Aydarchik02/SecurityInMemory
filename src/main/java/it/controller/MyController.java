package it.controller;

import it.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {


    @GetMapping("/")
    public String hello() {
        return "menu";
    }

    @GetMapping("/admin")
    public String helloAdmin() {
        return "a";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "u";
    }
    @GetMapping("/time")
    public String time() {
        return "time";
    }




    @GetMapping("/hello")
    public String helo() {
        return "helloWorld";
    }



    @GetMapping("/findPerson")
    public String find(Model model) {
        Person person = new Person();
        person.setName("Aydar");
        person.setAge(19);
        model.addAttribute(person);
        return "findPerson";
    }
}