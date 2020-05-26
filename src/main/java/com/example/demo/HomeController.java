package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String addCourses(Model model){
        model.addAttribute("course", new Course());
        return "addCourse";
    }
    @PostMapping("/add")
    public String checkCourse(@Valid Course course, BindingResult result) {
        if(result.hasErrors()) {
            return "addCourse";
        }
        else {
            courseRepository.save(course);
            return "redirect:/";
        }
    }

    @RequestMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        return "addCourse";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        courseRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/details/{id}")
    public String detail(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findById(id).get());
        Course course = courseRepository.findById(id).get();

        return "detailCourse";
    }
}
