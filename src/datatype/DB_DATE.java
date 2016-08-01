/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Swetha
 */
public class DB_DATE implements DataType{

    private long date;
    static SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd"); 

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }
    
    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        try{
            Date d = dateFormat.parse(sdata);
            date = d.getTime();
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
    
    public String toString(){
        Date d = new Date();
        d.setTime(date);
        String dateStr = dateFormat.format(d);
        return dateStr;
    }

    @Override
    public int size() {
        return 8;
    }

    @Override
    public byte getSerialCode() {
        return 0x0B;
    }
}
