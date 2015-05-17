/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.daytron.generalerrorhandling;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Ryan Gilera
 */
public class ErrorBar extends VerticalLayout {
    private final Label staticLabel = new Label("An error has occured:");
    private final Label errorLabel = new Label();
    private final TextArea stackTextArea = new TextArea();
    private final Button collapseButton = new Button("Show details");

    public ErrorBar() {
        HorizontalLayout upperBar = new HorizontalLayout(staticLabel,errorLabel,
        collapseButton);
        
        upperBar.setSpacing(true); 
        addComponent(upperBar);
        addComponent(stackTextArea);
        
        stackTextArea.setVisible(false);
        stackTextArea.setWidth("100%");
        
        addStyleName("error");
        
        setVisible(false);
        
        collapseButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                boolean visible = stackTextArea.isVisible();
                
                stackTextArea.setVisible(!visible);
                collapseButton.setCaption(visible ? "Shpw details" : 
                        "Hide details");
            }
        });
    }
    
    public void displayyError(Throwable throwable) {
        errorLabel.setValue(throwable.getMessage());
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        
        throwable.printStackTrace(printWriter);
        
        stackTextArea.setReadOnly(false);
        stackTextArea.setValue(
            stringWriter.getBuffer().toString());
        stackTextArea.setRows(throwable.getStackTrace().length);
        stackTextArea.setReadOnly(true);
        
        setVisible(true);
    }
}
