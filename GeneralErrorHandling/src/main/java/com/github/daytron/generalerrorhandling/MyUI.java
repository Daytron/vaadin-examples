package com.github.daytron.generalerrorhandling;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Property;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.github.daytron.generalerrorhandling.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final ErrorBar errorBar = new ErrorBar();
        Button throwButton = new Button("Throw Error");
        
        throwButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                throw new RuntimeException("A bad thing happened");
            }
        });
        
        CheckBox check = new CheckBox("Set custom error handler");
        check.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                boolean checked = (Boolean)event.getProperty().getValue();
                
                VaadinSession session = VaadinSession.getCurrent();
                
                ErrorHandler handler = checked ? new CustomErrorHandler(errorBar) 
                        : new DefaultErrorHandler();
                
                errorBar.setVisible(false);
                session.setErrorHandler(handler);
            }
        });
        
        VerticalLayout layout = new VerticalLayout(errorBar,check,throwButton);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
