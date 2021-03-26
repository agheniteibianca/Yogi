package com.PS.demo.gui;

import com.PS.demo.service.UserService;

import javax.swing.*;

public class UserForm {
    private final UserService userService;
    private JFrame jFrame;

    public UserForm(UserService userService) {
        this.userService = userService;
    }
}
