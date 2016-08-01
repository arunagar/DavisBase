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

    static int pageSize = 512;
    static int offsetForNoOfRows = 2;
    static int offsetForContentArea = 0x01F8;
    static int sizeOfRowIdOffset = 4;

    public void initializeTable(String fileName) {
        RandomAccessFile randFile = FileController.getFileControl().getFile(fileName);
        try {
            randFile.setLength(0);
            randFile.setLength(pageSize * 3);
            // initialize interior page
            randFile.seek(0);
            randFile.writeByte(0x05);
            randFile.writeByte(0x00);
            // offset for start of content area 0x01F8 (copying from prof code)
            randFile.writeShort(0x01F8);
        } catch (IOException ex) {
            Logger.getLogger(BTree.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertRow(TableStructure tabStruct, String[] attrTypes, String[] values) {
        DataTypeWriteConverter writeConverter = new DataTypeWriteConverter();
        writeConverter.setCurrentFile(tabStruct.tableName + ".tbl");
        DB_Record record = DatabaseFactory.getSingle().createRecordWithValue(attrTypes, values);
        System.out.println(tabStruct.rows);
        int writeOffset = ((int) tabStruct.rows * sizeOfRowIdOffset) + offsetForContentArea;
        writeConverter.seek(writeOffset);
        short sizeOfRecord = sizeOfPayload(record);
        // Write the size of payload
        writeConverter.writeShort(sizeOfRecord);
        // write the rowId
        record.getVecDataTypes().get(0).accept(writeConverter);
        record.getVecDataTypes().remove(0);
        // Write column types
        record.addSerialCodes(writeConverter);
        // Write column values
        record.accept(writeConverter);
        // Incremenet no of rows
        writeConverter.seek(offsetForNoOfRows);
        // add the offset for the row
        writeConverter.writeByte((byte)tabStruct.rows);
        displayBinaryHex(tabStruct.tableName);
    }

    static void displayBinaryHex(String tableName) {
        RandomAccessFile ram = FileController.getFileControl().getFile(tableName + ".tbl");
        try {
            System.out.println("Dec\tHex\t 0  1  2  3  4  5  6  7  8  9  A  B  C  D  E  F");
            ram.seek(0);
            long size = ram.length();
            int row = 1;
            System.out.print("0000\t0x0000\t");
            int rowCount = 1;
            while(ram.getFilePointer() < size) {
                System.out.print(String.format("%02X ", ram.readByte()));
                // System.out.print(ram.readByte() + " ");
                if(row % 16 == 0) {
                    System.out.println();
                    System.out.print(String.format("%04d\t0x%04X\t", row, row));
                }
                row++;
            }
            if(row % 256 == 0) {
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    private static short sizeOfPayload(DB_Record record) {
        short size = 0;
        for (int i = 0; i < record.getVecDataTypes().size(); i++) {
            size += record.getVecDataTypes().get(i).size();
        }
        return size;
    }
}
