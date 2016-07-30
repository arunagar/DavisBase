/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import datatype.DB_Record;
import datatype.DataTypeReadConverter;
import datatype.DatabaseFactory;

/**
 *
 * @author Swetha
 */
public class TableFileReader extends DBTable {

    List<DB_Record> dbRecords;

    public TableFileReader() {
        dbRecords = new ArrayList<>();
    }

    public List<DB_Record> getDbRecords() {
        return dbRecords;
    }

    public void loadTableFromFile(String fileName) {
        DataTypeReadConverter readCon = new DataTypeReadConverter();
        readCon.setCurrentFile(fileName);
        readCon.seek(0L);
        DB_Record record = null;
        RandomAccessFile randFile = readCon.getCurrentFile();
        try {
            while (randFile.getFilePointer() < randFile.length() - 1) {
                record = DatabaseFactory.getSingle().createNewRecord(mAttrsType);
                record.accept(readCon);
                addRecord(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRecord(DB_Record rec) {
        dbRecords.add(rec);
    }
}
