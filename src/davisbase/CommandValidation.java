/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

/**
 *
 * @author Swetha
 */
public class CommandValidation {
    
    public static boolean validateShowCmd(String showCmd){
        if(showCmd.equalsIgnoreCase("show tables")){
            return true;
        }
        return false;
    }
    
    public static boolean validateDropCmd(String dropCmd, String[] cmd){
        if(dropCmd.startsWith("drop table") && cmd.length == 3){
            return true;
        }
        return false;
    }
    
    
}
