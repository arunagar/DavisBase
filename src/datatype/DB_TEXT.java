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
public class DB_TEXT implements DataType{

    int maxLen = 0;
    String textData;
    DB_TINYINT tiny;

    public void setTiny(byte tiny) {
        this.tiny.setTinyInt(tiny);
    }

    public String getTextData() {
        return textData;
    }

    public long getTiny() {
        return tiny.getTinyInt();
    }
    
    public DB_TEXT(){
        tiny = new DB_TINYINT();
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }
    
    @Override
    public void accept(DataTypeConverter v) {
        tiny.accept(v);
        v.visit(this);
    }

    @Override
    public void setData(String sdata) {
        textData = sdata;
        byte n = (byte)sdata.length();
        setTiny(n);
    }
    
    @Override
    public String toString(){
        return textData;
    }

    @Override
    public int size() {
        return (int)getTiny();
    }

    @Override
    public byte getSerialCode() {
        return (byte) (12 + tiny.size());
    }
}
