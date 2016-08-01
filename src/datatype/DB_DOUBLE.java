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
public class DB_DOUBLE implements DataType{
    
    private double doubleValue;

    public double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        doubleValue = Double.parseDouble(sdata);
    }
    
    @Override
    public String toString(){
        return Double.toString(doubleValue);
    }

    @Override
    public int size() {
        return 8;
    }

    @Override
    public byte getSerialCode() {
        return 0x09;
    }
}
