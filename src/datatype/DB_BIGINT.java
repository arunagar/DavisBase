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
public class DB_BIGINT implements DataType{
    
    private long bigInt;

    public void setBigInt(long bigInt) {
        this.bigInt = bigInt;
    }

    public long getBigInt() {
        return bigInt;
    }
    

    @Override
    public void accept(DataTypeConverter v) {
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        bigInt = Long.parseLong(sdata);
    }
    
    @Override
    public String toString(){
        return Long.toString(bigInt);
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public byte getSerialCode() {
        return bigInt == 0L ? (byte) 0x03 : 0x07;
    }

}
