/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.utilidades;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Igor
 */
public class JTableRenderer extends DefaultTableCellRenderer{

    @Override
    public void setValue(Object value) {
       if(value instanceof ImageIcon){
           if(value != null){
               ImageIcon d = (ImageIcon) value;
               setIcon(d);
               
           }else{
               setText("");
               setIcon(null);
           }
       }else{
           super.setValue(value);
       }
    }
    
    
}
