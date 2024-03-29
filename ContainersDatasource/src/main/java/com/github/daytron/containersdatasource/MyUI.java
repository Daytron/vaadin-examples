package com.github.daytron.containersdatasource;

import com.github.daytron.containersdatasource.model.Person;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.github.daytron.containersdatasource.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        BeanSampleCombo bsc = new BeanSampleCombo();
        setContent(bsc);
        
        bsc.addNewPerson(new Person(5L, "Ryan", "Gilera", new Date()));

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
