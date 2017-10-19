package com.accenture.aris.common.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accenture.aris.core.support.message.Messages;

@Controller
@RequestMapping(value = "/top")
public class TopController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopController.class);

    @Autowired
    private Messages messages;

    @RequestMapping(value = "")
    public String top(Model uiModel) {
        return "top";
    }
}
