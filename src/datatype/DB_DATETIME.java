/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Swetha
 */
public class DB_DATETIME implements DataType{
    
    private long dateTime;
    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'_'hh:mm:ss");

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        Date d;
        try {
            d = dateTimeFormat.parse(sdata);
            dateTime = d.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(DB_DATETIME.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString(){
        Date d = new Date();
        d.setTime(dateTime);
        String dateTimeStr = dateTimeFormat.format(d);
        return dateTimeStr;
    }
    
}
