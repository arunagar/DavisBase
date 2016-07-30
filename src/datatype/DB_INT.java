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
public class DB_INT implements DataType{
    
    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        intValue = Integer.parseInt(sdata);
    }
    
    @Override
    public String toString(){
        return Integer.toString(intValue);
    }
    
}
