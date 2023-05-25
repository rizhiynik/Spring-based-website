package com.example.demo.Controllers;

import com.example.demo.Entities.Record;
import com.example.demo.Services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RecordController {

    @Autowired
    private RecordService aService;

    @RequestMapping("/record")
    public String ViewBlogMain (Model model, @Param("keyword") String keyword) {
        List<Record> listRecord = aService.listAll(keyword);
        System.out.println(listRecord);
        model.addAttribute("listRecord", listRecord);
        model.addAttribute("keyword", keyword);
        return "record/record_view";
    }
    @RequestMapping("/record/new")
    public String showNewArticleForm(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "record/new_record";
    }
    @RequestMapping("/record/edit/{id}")
    public ModelAndView showEditArticleForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("record/edit_record");
        Record record = aService.get(id);
        mav.addObject("record", record);
        return mav;
    }

    @RequestMapping("/record/delete/{id}")
    public String deleteArticle(@PathVariable(name = "id") Long id) {
        aService.delete(id);
        return "redirect:/record";
    }
    @RequestMapping(value = "/record/save", method = RequestMethod.POST)
    public String saveArticle(@ModelAttribute("record") Record record) {
        aService.save(record);
        return "redirect:/record";
    }
    @RequestMapping("/record/{id}")
    public String articleDetails(Model model, @PathVariable(name = "id") Long id) {
        List<Record> listRecord = aService.getList(id);
        model.addAttribute("listRecord", listRecord);
        return "record/record_view";
    }

}
