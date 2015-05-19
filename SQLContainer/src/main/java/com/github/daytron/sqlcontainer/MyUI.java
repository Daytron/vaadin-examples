package com.github.daytron.sqlcontainer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
@Theme("mytheme")
@SuppressWarnings("serial")
@Widgetset("com.github.daytron.sqlcontainer.MyAppWidgetset")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        DatabaseTableScreen screen = new DatabaseTableScreen();
        
        try {
            JDBCConnectionPool connectionPool = new SimpleJDBCConnectionPool(
                    "com.mysql.jdbc.Driver", 
                    "jdbc:mysql://localhost/persondb", "sqlusersample","sqluserpw");
            
            screen.populate("Person", connectionPool);
        } catch (SQLException ex) {
            Logger.getLogger(MyUI.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        
        setContent(screen);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
