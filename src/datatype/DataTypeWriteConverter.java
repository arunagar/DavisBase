/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import davisbase.FileController;

/**
 *
 * @author Swetha
 */
public class DataTypeWriteConverter implements DataTypeConverter{
    RandomAccessFile currentFile = null;
    public void setCurrentFile(String fileName) {
        currentFile = FileController.getFileControl().getFile(fileName);
    }

    @Override
    public void visit(DB_TINYINT data) {
    }

    @Override
    public void visit(DB_DATE data) {
        try {
            currentFile.writeLong(data.getDate());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DATETIME data) {
        try {
            currentFile.writeLong(data.getDateTime());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DOUBLE data) {
        try {
            currentFile.writeDouble(data.getDoubleValue());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_REAL data) {
        try {
            currentFile.writeFloat(data.getFloatValue());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_INT data) {
        try {
            currentFile.writeInt(data.getIntValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_BIGINT data) {
        try {
            currentFile.writeLong(data.getBigInt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_SMALLINT data) {
        
    }

    @Override
    public void visit(DB_TEXT data) {
    }

    @Override
    public void visit(DB_Record data) {
    }
    
}
