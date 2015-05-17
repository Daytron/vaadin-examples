/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.daytron.tablebean;

import com.github.daytron.tablebean.model.Person;
import com.github.daytron.tablebean.tableColumn.DisplayNameColumnGenerator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Ryan Gilera
 */
public class TableScreenSample extends HorizontalLayout {
    private static final long serialVersionUID = 1L;
    private final BeanItemContainer<Person> container;
    private final Table table;
    
    public TableScreenSample() {
        Person person1 = new Person(1L, "John","DOE",new Date(70, 0, 1));
        Person person2 = new Person(2L, "Jane","doe",new Date(70, 0, 1));
        Person person3 = new Person(3L, "jules","winnf",new Date(48, 11, 21));
        Person person4 = new Person(4L, "vincent","Vega",new Date(54, 1, 17));
        
        container = new BeanItemContainer<>(Person.class);
        
        container.addAll(Arrays.asList(
            person1,person2,person3,person4));
        
        table = new Table("Sample Table",container);
        
        table.addGeneratedColumn("fullname", new DisplayNameColumnGenerator());
        table.setColumnHeader("fullname", "Full Name");
        table.setColumnHeader("id", "ID");
        table.setColumnHeader("firstname", "First Name");
        table.setColumnHeader("lastname", "Last Name");
        table.setColumnHeader("birthdate", "Birth Date");
        
       
        
        table.setVisibleColumns((Object[]) new String[]{"id", 
            "fullname","firstname","lastname", "birthdate"});
        
        
        table.setColumnAlignments(new Table.Align[] {Table.ALIGN_LEFT, 
            Table.Align.CENTER, Table.Align.CENTER, Table.Align.CENTER, 
            Table.Align.CENTER});
        
        table.setSortEnabled(false);
        table.sort(new String[]{"firstname","lastname"},
                new boolean[]{true,true});
        table.setColumnWidth("id", 50);
        table.setColumnWidth("fullname", 160);
        table.setColumnWidth("firstname", 150);
        table.setColumnWidth("lastname", 150);
        table.setColumnWidth("birthdate", 150);
        
        
        table.setSelectable(true);
        table.setPageLength(4);
        table.setCurrentPageFirstItemIndex(0);
        
       
        addComponent(table);
        setMargin(true);
        
    }
    
}
