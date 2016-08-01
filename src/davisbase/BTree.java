/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swetha
 */
public class BTree {
    
    int pageSize = 512;
    
    public void initializeTable(String fileName){
        RandomAccessFile randFile = FileController.getFileControl().getFile(fileName);
        try {
            randFile.setLength(0);
            randFile.setLength(pageSize*3);
            //initialize interior page
            randFile.seek(0);
            randFile.writeByte(0x05);
            randFile.writeByte(0x00);
            //offset for start of content area 0x01F8 (copying from prof code)
            randFile.writeShort(0x01F8);
        } catch (IOException ex) {
            Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void insertRow(TableStructure tabStruct, String[] values){
        RandomAccessFile randFile = FileController.getFileControl().getFile(tabStruct.tableName);
    }
    
}
