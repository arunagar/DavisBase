/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import datatype.DB_Record;
import datatype.DatabaseFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author Swetha
 */
public class DatabaseInfo {

    Map<String, TableStructure> table = new TreeMap<>();
    private static DatabaseInfo dataInfo = null;
    List<TableStructure> tables = new ArrayList<>();
    private String tableInfoFileName = "davisbase_tables.tbl";
    private String columnInfoFileName = "davisbase_columns.tbl";

    public static DatabaseInfo getSingle() {
        if (dataInfo == null) {
            dataInfo = new DatabaseInfo();
        }
        return dataInfo;
    }
    
    public boolean isTablePresent(String tableName){
        return table.containsKey(tableName);
    }
    
    public void dropTable(String tableName){
        table.remove(tableName);
    }

    private DatabaseInfo() {
        init();
    }

    private void init() {
        loadTableFiles();
        loadTableColumns();
    }

    private void loadTableFiles() {
        TableFileReader fileReader = TableFactory.getSingle().createInforTablesReadWriter();
        fileReader.loadTableFromFile(tableInfoFileName);
        List<DB_Record> tableRocords = fileReader.getDbRecords();
        for (int i = 0; i < tableRocords.size(); i++) {
            DB_Record tr = tableRocords.get(i);
            String tableName = tr.getVecDataTypes().get(0).toString();
            String rows = tr.getVecDataTypes().get(1).toString();
            long lRows = Long.parseLong(rows);
            TableStructure st = TableFactory.getSingle().createTable(tableName);
            st.rows = lRows;
            addTable(st);
        }

    }

    private void loadTableColumns() {
        TableFileReader loader = TableFactory.getSingle().createInforColumnsReadWriter();
        loader.loadTableFromFile(columnInfoFileName);
        List<DB_Record> tableRocords = loader.getDbRecords();
        for (int i = 0; i < tableRocords.size(); i++) {
            DB_Record tr = tableRocords.get(i);
            String tbName = tr.getVecDataTypes().get(0).toString();
            String colName = tr.getVecDataTypes().get(1).toString();
            String ordPos = tr.getVecDataTypes().get(2).toString();
            String colType = tr.getVecDataTypes().get(3).toString();
            String isNullable = tr.getVecDataTypes().get(4).toString();
            String priKey = tr.getVecDataTypes().get(5).toString();

            int iordPos = Integer.parseInt(ordPos);
            ColumnStructure col = TableFactory.getSingle().createColumn(tbName, colName);
            col.isPrimaryKey = priKey;
            col.columnType = colType;
            col.isNullable = isNullable;
            col.position = iordPos;
            TableStructure st = table.get(tbName);

            st.addColumn(col);
        }
        //updateColumInfo();
    }

    public void addTable(TableStructure tab) {
        table.put(tab.tableName, tab);
        tables.add(tab);
    }

    public TableStructure getTable(String tableName) {
        return table.get(tableName);
    }

    public void saveFilesToSystem() {
        saveTable();
        saveColumns();
    }

    private void saveTable() {
        TableFileReader saver = TableFactory.getSingle().createInforTablesReadWriter();
        Set tableSet = DatabaseInfo.getSingle().table.entrySet();
        Iterator setIterator = tableSet.iterator();
        while (setIterator.hasNext()) {
            Map.Entry tblEn = (Map.Entry) setIterator.next();
            String tblAttrs[] = new String[2];
            tblAttrs[0] = ((TableStructure) tblEn.getValue()).tableName;
            tblAttrs[1] = Long.toString(((TableStructure) tblEn.getValue()).rows);
            DB_Record r = (DB_Record) DatabaseFactory.getSingle().createSysTableRecordWithValue(tblAttrs);
            saver.addRecord(r);
        }
        
		saver.WriteTableToFile(tableInfoFileName);	
    }

    private void saveColumns() {
        TableFileReader saver = TableFactory.getSingle().createInforTablesReadWriter();
        Set tableSet = DatabaseInfo.getSingle().table.entrySet();
        Iterator setIterator = tableSet.iterator();
        while (setIterator.hasNext()) {
            Map.Entry tblEn = (Map.Entry) setIterator.next();
            String tblAttrs[] = new String[2];
            tblAttrs[0] = ((TableStructure) tblEn.getValue()).tableName;
            tblAttrs[1] = Long.toString(((TableStructure) tblEn.getValue()).rows);

            //cols
            Set colSet = ((TableStructure) tblEn.getValue()).columns.entrySet();
            Iterator iCol = colSet.iterator();
            while (iCol.hasNext()) {
                Map.Entry colEn = (Map.Entry) iCol.next();
                String colAttrs[] = new String[6];
                colAttrs[0] = ((TableStructure) tblEn.getValue()).tableName;
                colAttrs[1] = ((ColumnStructure) colEn.getValue()).columnName;
                colAttrs[2] = Integer.toString(((ColumnStructure) colEn.getValue()).position);
                colAttrs[3] = ((ColumnStructure) colEn.getValue()).columnType;
                colAttrs[4] = ((ColumnStructure) colEn.getValue()).isNullable;
                colAttrs[5] = ((ColumnStructure) colEn.getValue()).isPrimaryKey;
                DB_Record r = (DB_Record) DatabaseFactory.getSingle().createSysColumnRecordWithValue(colAttrs);
                saver.addRecord(r);

            }   
        }
        saver.WriteTableToFile(columnInfoFileName);
    }

}
