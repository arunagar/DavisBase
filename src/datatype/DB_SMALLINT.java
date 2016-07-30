/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

/**
 *
 * @author Swetha
 */
public class DB_SMALLINT implements DataType{
    
    private short shortData;

    public short getShortData() {
        return shortData;
    }

    public void setShortData(short shortData) {
        this.shortData = shortData;
    }

    @Override
    public void accept(DataTypeConverter v) {
    v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        shortData = Short.parseShort(sdata);
    }
    
    @Override
    public String toString(){
        return Short.toString(shortData);
    }
    
}
