/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Swetha
 */
public class CommandExecuter {
    private static CommandExecuter cmdExecuter = null;
    private DatabaseInfo dataInfo;
    
    public static CommandExecuter getSingle(){
        if(cmdExecuter == null){
            cmdExecuter = new CommandExecuter();
        } 
        return cmdExecuter;
    }
    
    public static void showTables(){
        DatabaseInfo dbInfo = DatabaseInfo.getSingle();
        Set tableSet = dbInfo.table.entrySet();
        Iterator setIterator = tableSet.iterator();
		 while(setIterator.hasNext()) {
			 Map.Entry tblEn = (Map.Entry)setIterator.next();
			 String tblName =  ((TableStructure)tblEn.getValue()).tableName;
			 System.out.println(tblName);			 			     		     
		}
    }
    
    void createTable(String tableName, Vector<ColumnStructure> colStructVector){
        DatabaseInfo dbInfo = DatabaseInfo.getSingle();
        if(dbInfo.table.get(tableName) != null){
            System.out.println("The table name given already exists");
            return;
        }
        TableStructure tabStruct = TableFactory.getSingle().createTable(tableName);
        for(ColumnStructure c : colStructVector){
            if(tabStruct.ifColumnExists(c.columnName)){
                System.out.println("The column " +c.columnName+ " already exists");
                return;
            }
            tabStruct.addColumn(c);
        }
        dbInfo.addTable(tabStruct);
    }
    
    public static void createTabFile(TableStructure tabStruct){
        String tabName = tabStruct.tableName;
        FileController.getFileControl().getFile(tabName+".tbl");
    }
    
    
}
