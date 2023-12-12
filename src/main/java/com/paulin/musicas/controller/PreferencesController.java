package com.paulin.musicas.controller;


import com.paulin.musicas.model.Preferences;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PreferencesController {
    @PostMapping("/preferences")
    public ModelAndView gravaTema(@ModelAttribute Preferences pref, HttpServletResponse response){
        Cookie cookieTema = new Cookie("pref-tema", pref.getTema());
        cookieTema.setDomain("localhost");
        cookieTema.setHttpOnly(true);
        cookieTema.setMaxAge(86400);
        
        response.addCookie(cookieTema);
        
        return new ModelAndView("redirect:/");
    }
}
