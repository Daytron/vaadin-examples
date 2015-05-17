/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.daytron.generalerrorhandling;

import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;

/**
 *
 * @author Ryan Gilera
 */
public class CustomErrorHandler implements ErrorHandler {
    private final ErrorBar errorbar;

    public CustomErrorHandler(ErrorBar errorBar) {
        this.errorbar = errorBar;
    }
    

    @Override
    public void error(ErrorEvent event) {
        Throwable throwable = event.getThrowable();
        errorbar.displayyError(throwable);
    }
    
}
