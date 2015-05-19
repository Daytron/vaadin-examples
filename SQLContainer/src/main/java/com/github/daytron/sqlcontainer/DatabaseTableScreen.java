/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.daytron.sqlcontainer;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan Gilera
 */
public class DatabaseTableScreen extends VerticalLayout {

    private SQLContainer container;
    private Table table;
    
    public DatabaseTableScreen() {
        setMargin(true);
        
        table = new Table();
        table.setPageLength(10);
        table.setEditable(true);
        table.setSizeFull();
        
        table.addGeneratedColumn("", new RemoveItemColumnGenerator());
        
        HorizontalLayout buttonBar = new HorizontalLayout();
        
        buttonBar.setMargin(true);
        buttonBar.setSpacing(true);
        
        Button commitButton = new Button("Commit");
        commitButton.addClickListener(new Button.ClickListener() {
            
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    container.commit();
                    Notification.show("Changes committed");
                } catch (UnsupportedOperationException | SQLException ex) {
                    Logger.getLogger(DatabaseTableScreen.class.getName()).log(Level.SEVERE, null, ex);
                    Notification.show("Unable to commit", Notification.Type.ERROR_MESSAGE);
                }
                
            }
        });
        
        buttonBar.addComponent(commitButton);
        Button rollbackButton = new Button("Rollback");
        
        rollbackButton.addClickListener(new Button.ClickListener() {
            
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    container.rollback();
                    Notification.show("Changes rollback");
                } catch (UnsupportedOperationException | SQLException ex) {
                    Logger.getLogger(DatabaseTableScreen.class.getName()).log(Level.SEVERE, null, ex);
                    Notification.show("Unable to rollback", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        
        buttonBar.addComponent(rollbackButton);
        addComponent(table);
        addComponent(buttonBar);
    }
    
    public class RemoveItemColumnGenerator implements Table.ColumnGenerator {
        
        @Override
        public Object generateCell(Table source, Object itemId, Object columnId) {
            Button button = new Button("Delete");
            button.setData(itemId);
            
            button.addClickListener(new Button.ClickListener() {
                
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Object itemId = event.getButton().getData();
                    container.removeItem(itemId);
                }
            });
            
            return button;
        }
        
    }
    
    public void populate(String tableName, JDBCConnectionPool connectionPool) {
        QueryDelegate query = new TableQuery(tableName, connectionPool);
        
        try {
            container = new SQLContainer(query);
            table.setContainerDataSource(container);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTableScreen.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
