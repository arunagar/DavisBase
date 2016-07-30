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
public interface DataType {
    public void accept(DataTypeConverter v);
	 public void setData(String sdata);
	 public String toString();
    
}
