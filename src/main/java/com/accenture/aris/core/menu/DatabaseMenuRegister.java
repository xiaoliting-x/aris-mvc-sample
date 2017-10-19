package com.accenture.aris.core.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public class DatabaseMenuRegister implements MenuRegister {
    
    @Autowired
    private MenuRegisterDao menuRegisterDao;
    
    @Override
    public void regist(String id, ModelAndView modelAndView) {
        List<MenuRegisterData> menues = menuRegisterDao.selectByUserId(id);
        modelAndView.addObject("menues", menues);
    }
}