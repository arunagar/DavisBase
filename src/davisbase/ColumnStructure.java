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
public class ColumnStructure {
    public String tableName = "";
    public String columnName = "";
    public String columnType = "";
    public String isNullable = "";
    public String isPrimaryKey = "";
    public int position;
    
    public ColumnStructure(){
        isNullable = "yes";
        isPrimaryKey = "";
    }
}
