package com.paulin.musicas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MusicController {
    
    @GetMapping("/")
    public ModelAndView getHomePage(@CookieValue(name="pref-tema", defaultValue="styleEscuro")String tema){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("css", tema);
        return mv;
    }
    
    @GetMapping("/music/list")
    public ModelAndView getList(@CookieValue(name="pref-tema", defaultValue="styleEscuro")String tema){
        ModelAndView mv = new  ModelAndView("listar");
        mv.addObject("css", tema);
        return mv;
    }
    @GetMapping("/music/form/add")
    public ModelAndView getFormAdd(@CookieValue(name="pref-tema", defaultValue="styleEscuro")String tema){
        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("css", tema);
        return mv;
    }
}
