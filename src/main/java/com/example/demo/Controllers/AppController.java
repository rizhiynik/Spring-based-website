package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Entities.Task;
import com.example.demo.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired; // Определяем класс или поле в качестве главного контейнера, связывающего остальные зависимости между друг другом
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller; // Задаем класс в качестве контроллера в рамках концепции MVC
import org.springframework.ui.Model; // Интерфейс, предоставляющий функционал для реализации модели в рамках концепции MVC
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView; // Хранит функционал для реализации как модели, так и образа в рамках концепции MVC

@Controller
public class AppController {

    @Autowired
    private TaskService service;

    @RequestMapping("/")
    public String viewGuestHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword,
                                    @RequestParam(value = "title", defaultValue = "false") boolean titleCheckbox,
                                    @RequestParam(value = "description", defaultValue = "false") boolean descriptionCheckbox,
                                    @RequestParam(value = "start_date", defaultValue = "false") boolean startDateCheckbox,
                                    @RequestParam(value = "end_date", defaultValue = "false") boolean endDateCheckbox,
                                    @RequestParam(value = "reward", defaultValue = "false") boolean rewardCheckbox) {
        List<Task> listTasks = service.search(keyword, titleCheckbox, descriptionCheckbox, startDateCheckbox,
                endDateCheckbox, rewardCheckbox);
        model.addAttribute("listTasks", listTasks);
        model.addAttribute("keyword", keyword);
        return "task/main";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/new")
    public String showNewCargoForm(Model model) {
        Task task = new Task();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        task.setUserName(userName);
        model.addAttribute("task", task);
        return "task/new_task";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCargo(@ModelAttribute("task") Task task) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        task.setUserName(userName);
        service.save(task);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditCargoForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("task/edit_task");
        Task task = service.get(id);
        mav.addObject("task", task);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteCargo(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }
}

