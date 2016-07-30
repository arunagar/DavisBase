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
    
    public void addColumn(ColumnStructure col){
        columns.put(col.columnName, col);
    }
    
    public boolean ifColumnExists(String columnName){
        return columns.containsKey(columnName);
    }
}
