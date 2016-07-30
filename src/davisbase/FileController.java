/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.io.RandomAccessFile;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Swetha
 */
public class FileController {
    private static FileController fileControl = null;
    Map<String, RandomAccessFile> fileMap;
    private String rootPath = "./data/";
    
    private FileController(){
        fileMap = new TreeMap<>();
    }
    
    public RandomAccessFile getFile(String name){
        RandomAccessFile file = null;
        if(fileMap.containsKey(name)){
            return fileMap.get(name);
        }
        try{
            file = new RandomAccessFile(rootPath+name, "rw");
            fileMap.put(name, file);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return file;
    }
    
    public static FileController getFileControl(){
        if(fileControl == null){
            fileControl = new FileController();
        }
        return fileControl;
    }
}
