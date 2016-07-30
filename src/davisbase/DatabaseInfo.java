/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import datatype.DB_Record;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    public static DatabaseInfo getSingle(){
        if(dataInfo == null){
            dataInfo = new DatabaseInfo();
        }
        return dataInfo;
    }
    
    private DatabaseInfo(){
        init();
    }
    
    private void init(){
        loadTableFiles();
        loadTableColumns();
    }
    
    private void loadTableFiles(){
        TableFileReader fileReader = TableFactory.getSingle().createInforTablesReadWriter();
        fileReader.loadTableFromFile(tableInfoFileName);
        List<DB_Record> tableRocords = fileReader.getDbRecords();
		for(int i = 0; i < tableRocords.size();i++){
			DB_Record tr= tableRocords.get(i);
			String tableName = tr.getVecDataTypes().get(0).toString();
			String rows = tr.getVecDataTypes().get(1).toString();
			long lRows = Long.parseLong(rows);
			TableStructure st = TableFactory.getSingle().createTable(tableName);
			st.rows = lRows;
                        addTable(st);
		}	
        
    }
    
    private void loadTableColumns(){
        TableFileReader loader = TableFactory.getSingle().createInforColumnsReadWriter();				
		loader.loadTableFromFile(columnInfoFileName);
		List<DB_Record> tableRocords = loader.getDbRecords();
		for(int i = 0; i < tableRocords.size(); i++){
			DB_Record tr = tableRocords.get(i);
			String tbName = tr.getVecDataTypes().get(0).toString();
			String colName = tr.getVecDataTypes().get(1).toString();
			String ordPos = tr.getVecDataTypes().get(2).toString();
			String colType = tr.getVecDataTypes().get(3).toString();
			String isNullable = tr.getVecDataTypes().get(4).toString();
			String priKey = tr.getVecDataTypes().get(5).toString();
			
			int iordPos = Integer.parseInt(ordPos);
			ColumnStructure col = TableFactory.getSingle().createColumn(tbName,colName);
			col.isPrimaryKey = priKey;
			col.columnType = colType;
			col.isNullable = isNullable;
			col.position = iordPos;			
			TableStructure st = table.get(tbName);
			
			st.addColumn(col);			
		}	
		//updateColumInfo();
    }
    
    public void addTable(TableStructure tab){
        table.put(tab.tableName, tab);
        tables.add(tab);
    }
    
    public TableStructure getTable(String tableName){
        return table.get(tableName);
    }
    
}
