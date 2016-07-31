/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Swetha
 */
public class TableStructure {
    public String tableName;
    public long rows = 0;
    Map<String, ColumnStructure> columns = new TreeMap<>();
    Map<Integer, ColumnStructure> orderColums = new TreeMap<>();
    String columNames[] = null;
    String [] columTypeNames = null;
    String [] columnKeys = null;
    String [] isNulls = null;
    
    public void addColumn(ColumnStructure col){
        columns.put(col.columnName, col);
        orderColums.put(col.position, col);
    }
    
    public ColumnStructure getColumn(int ordPos){
		return orderColums.get(ordPos);
    }
    
    public boolean ifColumnExists(String columnName){
        return columns.containsKey(columnName);
    }
    
    public int noOfColumns(){
        return columns.size();
    }
    
    public void updateVectors(){
		columNames = new String[orderColums .size()];
		columTypeNames= new String[orderColums .size()];
		columnKeys= new String[orderColums .size()];
		isNulls= new String[orderColums .size()];
		//be careful i-1.
		for(int i = 1; i <= orderColums .size(); i++){
			ColumnStructure col = orderColums .get(i);
			columNames[i-1] = col.columnName;
			columTypeNames[i-1] = col.columnType;
			isNulls[i-1] = col.isNullable;
			columnKeys[i-1] = col.isPrimaryKey;
		}
	}
    
    public String[] getColumNames(){
		if(columNames==null){
			updateVectors();
		}
		return columNames;
	}
	
	public String[] getIsNulls(){
		if(isNulls==null){
			updateVectors();
		}
		return isNulls;		
	}
	
	public String[] getColumnKeys(){
		if(columnKeys==null){
			updateVectors();
		}
		return columnKeys;			
	}
	
	public String[] getColumTypeNames(){
		if(columTypeNames==null){
			updateVectors();
		}
		return columTypeNames;		
	}
        
        public void addRow(){
		rows++;
	}
    
    
}
