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

    public static CommandExecuter getSingle() {
        if (cmdExecuter == null) {
            cmdExecuter = new CommandExecuter();
        }
        return cmdExecuter;
    }

    public static void showTables() {
        DatabaseInfo dbInfo = DatabaseInfo.getSingle();
        Set tableSet = dbInfo.table.entrySet();
        Iterator setIterator = tableSet.iterator();
        while (setIterator.hasNext()) {
            Map.Entry tblEn = (Map.Entry) setIterator.next();
            String tblName = ((TableStructure) tblEn.getValue()).tableName;
            System.out.println(tblName);
        }
    }

    void createTable(String tableName, Vector<ColumnStructure> colStructVector) {
        DatabaseInfo dbInfo = DatabaseInfo.getSingle();
        if (dbInfo.table.get(tableName) != null) {
            System.out.println("The table name given already exists");
            return;
        }
        TableStructure tabStruct = TableFactory.getSingle().createTable(tableName);
        for (ColumnStructure c : colStructVector) {
            if (tabStruct.ifColumnExists(c.columnName)) {
                System.out.println("The column " + c.columnName + " already exists");
                return;
            }
            tabStruct.addColumn(c);
        }
        dbInfo.addTable(tabStruct);
        createTabFile(tabStruct);
    }

    public static void createTabFile(TableStructure tabStruct) {
        String tabName = tabStruct.tableName;
        FileController.getFileControl().getFile(tabName + ".tbl");
    }

    public void dropTable(String tname) {
        if (!DatabaseInfo.getSingle().isTablePresent(tname)) {
            System.out.println(tname + " is not existing");
            return;
        }
        DatabaseInfo.getSingle().dropTable(tname);
        FileController.getFileControl().deleteFile(tname);
    }
    
    public void insertIntoValues(String tabName, String[] values){
        if (!DatabaseInfo.getSingle().isTablePresent(tabName)) {
            System.out.println(tabName + " is not existing");
            return;
        }
        if(values.length != getNoOfColumnsInTab(tabName)){
            System.out.println("Invalid nu,ber of values to insert");
            return;
        }
        Table.insertIntoValues(values, tabName);
    }
    
    private int getNoOfColumnsInTab(String tabName){
        TableStructure tabStruct = DatabaseInfo.getSingle().getTable(tabName);
        return tabStruct.noOfColumns();
    }
}
