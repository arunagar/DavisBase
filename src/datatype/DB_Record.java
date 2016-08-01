/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.util.Vector;

/**
 *
 * @author Swetha
 */
public class DB_Record implements DataType{
    
    Vector<DataType> vecDataTypes = null;

    public Vector<DataType> getVecDataTypes() {
        return vecDataTypes;
    }
    
    public DB_Record(){
        vecDataTypes = new Vector<>();
    }

    public void addSerialCodes(DataTypeWriteConverter v) {
        for(int i=0;i<vecDataTypes.size();i++){
            DataType elements = vecDataTypes.get(i);
            v.writeByte(elements.getSerialCode());
        }
    }

    @Override
    public void accept(DataTypeConverter v) {
        for(int i=0;i<vecDataTypes.size();i++){
            DataType elements = vecDataTypes.get(i);
            elements.accept(v);
        }
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
    }
    
    public void addData(DataType dataType){
        vecDataTypes.add(dataType);
    }
    
    @Override
    public String toString(){
        String res ="";
        for (DataType dat : vecDataTypes) {
            res += dat.toString()+"|\t";
        }
        return res;
    }

    @Override public int size() {
        return 0;
    }

    @Override public byte getSerialCode() {
        return 0;
    }

}
