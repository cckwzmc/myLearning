package com.toney.publish.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 *  首页
 */
@Controller
@RequestMapping("/sys")
public class HomeController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String home(Locale locale, Model model) {
    	
        return "sys/login";
    }
    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String home1(Locale locale, Model model) {
    	
    	return "sys/login";
    }
    
    @RequestMapping(value="login",method=RequestMethod.GET)
    public  String  login(Model model){
    	return "sys/login";
    }
    
    
}
