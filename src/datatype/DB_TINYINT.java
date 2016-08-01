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
public class DB_TINYINT implements DataType{
    
    private byte byteData;

    public short getTinyInt() {
        return byteData;
    }

    public void setTinyInt(byte tinyInt) {
        this.byteData = tinyInt;
    }

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        byteData = Byte.parseByte(sdata);
    }
    
    @Override
    public String toString(){
        return Byte.toString(byteData);
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public byte getSerialCode() {
        return 0x04;
    }
}
