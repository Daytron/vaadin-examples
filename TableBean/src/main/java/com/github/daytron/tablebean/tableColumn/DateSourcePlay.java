/*
 * The MIT License
 *
 * Copyright 2015 Ryan Gilera.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.daytron.tablebean.tableColumn;

import com.vaadin.data.Property;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

/**
 *
 * @author Ryan Gilera
 */
public class DateSourcePlay extends HorizontalLayout{
    private static final long serialVersionUID = 1L;
    
    public DateSourcePlay() {
        
        
        Property<String> property = new ObjectProperty<>("ABC");
        final TextField tf = new TextField(property);
        tf.setBuffered(true);
        
        tf.addTextChangeListener(new FieldEvents.TextChangeListener() {

            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                Notification.show(
                        "Text change (event): " + event.getText(), 
                        Notification.Type.TRAY_NOTIFICATION);
            }
        });
        
        Button commitButton = new Button("Save");
        commitButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show(
                        "Before commit (property): " 
                         + tf.getPropertyDataSource().getValue(), 
                        Notification.Type.TRAY_NOTIFICATION);
                
                tf.commit();
                
                Notification.show(
                        "After commit (property): " 
                        + tf.getPropertyDataSource().getValue(), 
                        Notification.Type.TRAY_NOTIFICATION);
            }
        });
        
        Button discardButton = new Button("Cancel");
        discardButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show(
                        "Before discard (property): " 
                         + tf.getPropertyDataSource().getValue(), 
                        Notification.Type.TRAY_NOTIFICATION);
                
                tf.discard();
                
                Notification.show(
                        "After discard (property): " 
                        + tf.getPropertyDataSource().getValue(), 
                        Notification.Type.TRAY_NOTIFICATION);
            }
        });
        
        addComponent(tf);
        addComponent(commitButton);
        addComponent(discardButton);
    }
    
    
    
}
