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

import datatype.DB_Record;
import datatype.DataTypeWriteConverter;
import datatype.DatabaseFactory;

/**
 *
 * @author Swetha
 */
public class BTree {
    
    int pageSize = 512;
    int offsetForNoOfRows = 2;
    int offsetForContentArea = 0x01F8;
    int sizeOfRowIdOffset = 4;

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
    
    public void insertRow(TableStructure tabStruct, String[] attrTypes, String[] values){
        DataTypeWriteConverter writeConverter = new DataTypeWriteConverter();
        writeConverter.setCurrentFile(tabStruct.tableName + ".tbl");
        RandomAccessFile randFile = FileController.getFileControl().getFile(tabStruct.tableName);
        DB_Record record = DatabaseFactory.getSingle().createRecordWithValue(attrTypes, values);
        try {
            randFile.seek(offsetForNoOfRows);
            int noOfRows = randFile.readByte();
            int writeOffset =  (noOfRows * sizeOfRowIdOffset) + offsetForContentArea;
            randFile.seek(writeOffset);
            int sizeOfRecord = sizeOfPayload(record);
            // Write the size of payload
            randFile.writeShort(sizeOfRecord);
            //write the rowId

            //Write column types
            record.addSerialCodes();
            //Write column values
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int sizeOfPayload(DB_Record record) {
        int size = 0;
        for (int i =0; i <  record.getVecDataTypes().size(); i++) {
            size += record.getVecDataTypes().get(i).size();
        }
        return size;
    }
}
