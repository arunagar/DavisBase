/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

/**
 *
 * @author Swetha
 */
public class TableFactory {
    private static TableFactory tableFac = null;
    public String[] tableAttributeType;
    public String[] columnAttributeType;
    
    private TableFactory(){
        String attributes = "text,long";
        tableAttributeType = attributes.split(",");
        attributes = "text,int,text,text,text";
        columnAttributeType = attributes.split(",");
    }
    
    public ColumnStructure createColumn(String tabName, String colName){
        ColumnStructure colStruct = new ColumnStructure();
        colStruct.columnName = colName;
        colStruct.tableName = tabName;
        return colStruct;
    }
    
    public static TableFactory getSingle(){
        if(tableFac == null){
            tableFac = new TableFactory();
        }
        return tableFac;
    }
    
    public TableFileReader createInforTablesReadWriter(){
		TableFileReader t = new TableFileReader();
		t.setAttrsType(tableAttributeType);
		return t;		
	}
	
	public TableFileReader createInforColumnsReadWriter(){
		TableFileReader t = new TableFileReader();
		t.setAttrsType(columnAttributeType);
		return t;		
	}   
        
        public TableStructure createTable(String tableName){
            TableStructure tabStruct = new TableStructure();
            tabStruct.tableName = tableName;
            return tabStruct;
        }
}
