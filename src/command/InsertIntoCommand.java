/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

/**
 *
 * @author Swetha
 */
public class InsertIntoCommand {
        public String tableName="";
	public String[] valuesToInsert;
	public void setTableName(String tname){
		tableName = tname;
	}
	
	public void setValues(String values[]){
		this.valuesToInsert = values;
	}
    
}
