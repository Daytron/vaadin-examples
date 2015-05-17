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
package com.github.daytron.containersdatasource;

import com.github.daytron.containersdatasource.model.Person;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Ryan Gilera
 */
public class BeanSampleCombo extends VerticalLayout {
    private static final long serialVersionUID = 1L;
    private final BeanItemContainer<Person> container;
    
    public BeanSampleCombo() {
        Person person1 = new Person(1L, "John","DOE",new Date(70, 0, 1));
        Person person2 = new Person(2L, "Jane","doe",new Date(70, 0, 1));
        Person person3 = new Person(3L, "jules","winnf",new Date(48, 11, 21));
        Person person4 = new Person(4L, "vincent","Vega",new Date(54, 1, 17));
        
        container = new BeanItemContainer<>(Person.class);
        
        container.addAll(Arrays.asList(
            person1,person2,person3,person4));
        
        ComboBox comboBox = new ComboBox("List Of Persons", container);
        comboBox.setImmediate(true);
        comboBox.setNullSelectionAllowed(true);
        comboBox.setItemCaptionPropertyId("fullname");
        
        comboBox.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                AbstractSelect combo = (AbstractSelect) event.getProperty();
                
                Person selected = (Person) combo.getValue();
                
                if (selected == null) {
                    Notification.show("Null selected");
                } else {
                    Notification.show(selected.getId() + 
                            ": " + selected.getFullname(), 
                            Notification.Type.HUMANIZED_MESSAGE);
                }
            }
        });
        
        addComponent(comboBox);
        
        
        
    }
    
    public void addNewPerson(Person person) {
        container.addItem(person);
    }
    
}
