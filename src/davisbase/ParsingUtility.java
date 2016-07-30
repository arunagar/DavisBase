/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.io.File;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Swetha
 */
public class ParsingUtility {
    static String singleSpace = " ";
    static String doubleSpaces = "  ";
    
    public static String getParanthesisValue(String s){
        int firstIndex = s.indexOf("(");
        int lastIndex = s.lastIndexOf(")");
        String value = s.substring(firstIndex+1, lastIndex);
        return value.trim();
    }
    
    public static String getSingleQuoteValue(String s){
        int firstIndex = s.indexOf("'");
        int lastIndex = s.lastIndexOf("'");
        String value = null;
        if(firstIndex >= lastIndex){
            return null;
        }
        value = s.substring(firstIndex+1, lastIndex);
        return value.trim();
    }
    
    public static String getKeyWord(String s, String from){
        int firstIndex = s.indexOf(from);
        int lastIndex = s.length();
        String key = s.substring(firstIndex+from.length(), lastIndex);
        return key.trim();
    }
    
    public static String getPatternMatchKey(String pattern, String s){
        String key ="";
        Pattern pat = Pattern.compile(pattern);
        Matcher match = pat.matcher(s);
        while(match.find()){
            key = match.group(1);
        }
        return key;
    }
    
    public static void trim(String[] arr){
        for(int i=0;i<arr.length;i++){
            arr[i] = arr[i].trim();
        }
    }
    
    public static boolean validateDate(String date){
        String key = "2016-07-23";
        if(date.length() != key.length()){
            return false;
        }
        return true;
    }
    
    public static boolean validateDateTime(String dateTime){
        String key = "2016-07-23_17:02:23";
        if(dateTime.length() != key.length()){
            return false;
        }
        return true;
    }
    
    public static boolean checkIsEmpty(String s){
        if(s==null){
            return false;
        }
        else if(s.isEmpty()){
            return false;
        }
        else if (s.equals("")){
        return false;
    }
        return true;
    }
    
    public static boolean checkNonZeroValue(String s){
        if(s.isEmpty()){
            return false;
        }
        else if(s == null){
            return false;
        }
        else if(Integer.parseInt(s) < 1){
            return false;
        }
        return true;
    }
    
    public static boolean checkVectorIsEmpty(Vector<Vector<String>> values){
        if(values == null){
            return false;
        }
        else if(values.isEmpty()){
            return false;
        }
        else if(values.get(0) == null){
            return false;
        }
        else if(values.get(0).isEmpty()){
            return false;
        }
        else if(values.get(0).equals("")){
            return false;
        }
        return true;
    }
    
    public static String formatCommand(String cmd){
        cmd = cmd.replaceAll("\n", singleSpace);
        cmd = cmd.replaceAll("\t", singleSpace);
        cmd = cmd.trim();
        while(cmd.contains(doubleSpaces)){
            cmd= cmd.replaceAll(doubleSpaces, singleSpace);
        }
        return cmd;
    }
    
    public static boolean removeFiles(String filename){
        File file = new File(filename);
        if(file.isFile() && file.exists()){
            file.delete();
            System.out.println("File " +filename+ " deleted successfully");
            return true;
        }
        else{
            System.out.println("File "+filename+" deletion failed");
            return false;
        }
    }
}
