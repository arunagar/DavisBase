/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import davisbase.ParsingUtility;

/**
 *
 * @author Swetha
 */
public class DatabaseFactory {
    private static DatabaseFactory dataFactory= null;
    
    public static DatabaseFactory getSingle(){
        if(dataFactory == null){
            dataFactory = new DatabaseFactory();
        }
        return dataFactory;
    }
    
    public DataType create(String name){
        DataType dataType = null;
        String nameFormat = name.toLowerCase();
        switch(nameFormat){
            case "date":
                dataType = new DB_DATE();
                break;
            case "datetime":
                dataType = new DB_DATETIME();
                break;
            case "double":
                dataType = new DB_DOUBLE();
                break;
            case "real":
                dataType = new DB_REAL();
                break;
            case "int":
                dataType = new DB_INT();
                break;
            case "bigint":
                dataType = new DB_BIGINT();
                break;
            case "smallint":
                dataType = new DB_SMALLINT();
                break;
            case "tinyint":
                dataType = new DB_TINYINT();
                break;
            case "varchar":
                dataType = new DB_VARCHAR();
                break;
            case "record":
                dataType = new DB_Record();
                break;
            default:
                
        }
        return dataType;
    }
    
    private DataType parseType(String name){
        DataType dataType = null;
        if(name.startsWith("varchar")){
            String val = ParsingUtility.getParanthesisValue(name);
            val = val.trim();
            dataType = new DB_VARCHAR();
            DB_VARCHAR varchar = (DB_VARCHAR)dataType;
            varchar.setMaxLen(Integer.parseInt(val));
        }
        else{
            dataType = null;
        }
        return dataType;
    }
    
    public DB_Record createNewRecord(String[] data){
        DB_Record record = (DB_Record)create("record");
        for(int i=0;i<data.length;i++){
            String s = data[i];
            DataType dataType = create(s);
            record.addData(dataType);
        }
        return record;
    }
    
    /*public DB_Record createNewTableRecord(String[] data){
    }*/
    
}
