/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import davisbase.ColumnStructure;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Swetha
 */
public class CreateTableCommand {

    private String tableName = null;
    private Vector<ColumnStructure> columns;

    public String getTableName() {
        return tableName;
    }

    public Vector<ColumnStructure> getColumns() {
        return columns;
    }

    public void execute(String tableName, List<ColumnStructure> colStruct) {

    }

    public CreateTableCommand() {
        columns = new Vector<ColumnStructure>();
    }

    public void setTableName(String tblName) {
        tableName = tblName;
    }

    public void addColumn(ColumnStructure col) {
        int OrdPos = columns.size() + 1;
        col.position = OrdPos;
        columns.add(col);
    }

}
