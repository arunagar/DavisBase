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
import datatype.DataTypeWriteConverter;
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

    public void WriteTableToFile(String name) {
        DataTypeWriteConverter dtv = new DataTypeWriteConverter();
        dtv.setCurrentFile(name);
        dtv.clearFile();
        dtv.seek(0L);
        for (int i = 0; i < dbRecords.size(); i++) {
            DB_Record r = dbRecords.get(i);
            r.accept(dtv);
        }
    }
    
    public static void insertIntoValues(String fileName, String[] attributes,  String[] values){
        DataTypeWriteConverter writeConverter = new DataTypeWriteConverter();
        writeConverter.setCurrentFile(fileName);
        writeConverter.seekToEnd();
        DB_Record record = DatabaseFactory.getSingle().createRecordWithValue(attributes, values);
        record.accept(writeConverter);
        
    }
}
