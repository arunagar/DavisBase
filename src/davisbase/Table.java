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
public class Table {

    public static void insertIntoValues(String[] values, String tabName) {
        TableStructure tabStruct = DatabaseInfo.getSingle().getTable(tabName);
        String columTypes[] = tabStruct.getColumTypeNames();
        String columNames[] = tabStruct.getColumNames();
        String columKeys[] = tabStruct.getColumnKeys();

        for (int i = 0; i < columKeys.length; i++) {
            if (columKeys[i].equalsIgnoreCase("pri")) {
                if (columTypes[i].equalsIgnoreCase("date")) {
                    if (!ParsingUtility.validateDate(values[i])) {
                        System.out.println(" date formal is not right");
                        return;
                    }
                }
                if (columTypes[i].equalsIgnoreCase("datetime")) {
                    if (!ParsingUtility.validateDateTime(values[i])) {
                        System.out.println(" datetime formal is not right");
                        return;
                    }
                }
            }
        }
        String fileName = tabName+".tbl";
        BTree.insertRow(tabStruct, columTypes, values);
        //TableFileReader.insertIntoValues(fileName, columTypes, values);
        tabStruct.addRow();
    }

}
