/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Swetha
 */
public class DatabaseLoader {
    List<TableStructure> tables = new ArrayList<>();
    private String tableInfoFileName = "davisbase_tables.tbl";
    private String columnInfoFileName = "davisbase_columns.tbl";
    
    private DatabaseLoader(){
        
    }
    
    private void loadTableFiles(){
        TableFileReader fileReader = new TableFileReader();
        fileReader.loadTableFromFile(tableInfoFileName);
        
    }
    
    private void loadTableColumns(){
        
    }
}
