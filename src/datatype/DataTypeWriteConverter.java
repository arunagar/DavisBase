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
public class DataTypeWriteConverter implements DataTypeConverter {

    RandomAccessFile currentFile = null;

    public void setCurrentFile(String fileName) {
        currentFile = FileController.getFileControl().getFile(fileName);
    }

    @Override
    public void visit(DB_TINYINT data) {
        try {
            currentFile.writeByte(data.getTinyInt());
        } catch (IOException ex) {
            Logger.getLogger(DataTypeWriteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_DOUBLE data) {
        try {
            currentFile.writeDouble(data.getDoubleValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(DB_REAL data) {
        try {
            currentFile.writeFloat(data.getFloatValue());
        } catch (IOException e) {
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
        try {
            currentFile.writeShort(data.getShortData());
        } catch (IOException ex) {
            Logger.getLogger(DataTypeWriteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void visit(DB_TEXT data) {
        String textData = data.getTextData();
        try {
            currentFile.writeBytes(textData);
        } catch (IOException ex) {
            Logger.getLogger(DataTypeWriteConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void visit(DB_Record data) {
    }

    public void clearFile() {
        try {
            currentFile.setLength(0L);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFilePointer() {
        long currpos = 0;
        try {
            currpos = currentFile.getFilePointer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currpos;
    }

    public void seekToEnd() {
        long pos;
        try {
            pos = currentFile.length();
            currentFile.seek(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seek(long pos) {
        try {
            currentFile.seek(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
