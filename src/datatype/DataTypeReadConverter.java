/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import davisbase.FileController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swetha
 */
public class DataTypeReadConverter implements DataTypeConverter{
    RandomAccessFile currentFile = null;

    public RandomAccessFile getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currFileName) {
        this.currentFile = FileController.getFileControl().getFile(currFileName);
    }
    
    @Override
    public void visit(DB_TINYINT data) {
        try {
            byte newData = currentFile.readByte();
            data.setTinyInt(newData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DATE data) {
        try {
            long newIdata = currentFile.readLong();
            data.setDate(newIdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DATETIME data) {
        try {
            long newIdata = currentFile.readLong();
            data.setDateTime(newIdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DOUBLE data) {
        try {
            double newIdata = currentFile.readDouble();
            data.setDoubleValue(newIdata);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_REAL data) {
        try {
            float newData = currentFile.readFloat();
            data.setFloatValue(newData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_INT data) {
        try {
            int newData = currentFile.readInt();
            data.setIntValue(newData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_BIGINT data) {
        long newData;
        try {
            newData = currentFile.readLong();
            data.setBigInt(newData);
        } catch (IOException ex) {
            Logger.getLogger(DataTypeReadConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visit(DB_SMALLINT data) {
        try {
            short newData = currentFile.readShort();
            data.setShortData(newData);
        } catch (IOException ex) {
            Logger.getLogger(DataTypeReadConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void visit(DB_TEXT data) {
        long ln = data.getTiny();
        byte[] b = new byte[(int)ln];
        char[] c = new char[(int)ln];
        try {
            currentFile.read(b);
            for(int i=0; i < ln; i++){
                c[i] = (char) b[i];
            }
            String textData = String.valueOf(c);
            data.setData(textData);
        } catch (IOException ex) {
            Logger.getLogger(DataTypeReadConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visit(DB_Record data) {
    }
    
    public void seek(long position){
        try{
            currentFile.seek(position);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
