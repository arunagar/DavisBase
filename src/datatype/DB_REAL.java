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
public class DB_REAL implements DataType{
    
    private float floatValue;

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        floatValue = Float.parseFloat(sdata);
    }
    
    @Override
    public String toString(){
        return Float.toString(floatValue);
    }
    
}
