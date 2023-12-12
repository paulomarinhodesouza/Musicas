package com.paulin.musicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnalysisController {

    
    @GetMapping("/analysis/form/add")
    public ModelAndView getFormAdd(@CookieValue(name="pref-tema", defaultValue="styleEscuro")String tema){
        ModelAndView mv = new ModelAndView("analisar");
        mv.addObject("css", tema);
        return mv;
    }
    
    @GetMapping("/analysis/list")
    public ModelAndView getList(@CookieValue(name="pref-tema", defaultValue="styleEscuro")String tema){
        ModelAndView mv = new ModelAndView("analises");
        mv.addObject("css", tema);
        return mv;
    }
}
