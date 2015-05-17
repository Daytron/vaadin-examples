/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.daytron.tablebean.tableColumn;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;

/**
 *
 * @author Ryan Gilera
 */
public class DisplayNameColumnGenerator implements Table.ColumnGenerator {

    @Override
    @SuppressWarnings("Unchecked")
    public Object generateCell(Table source, Object itemId, Object columnId) {
        // item is a person or line in a table
        Item item = source.getItem(itemId);
        
        Property<String> firstname = item.getItemProperty("firstname");
        Property<String> lastname = item.getItemProperty("lastname");
        
        return firstname.getValue() + " " + lastname.getValue();
    }
    
}
