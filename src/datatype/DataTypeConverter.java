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
public interface DataTypeConverter {
	public void visit(DB_TINYINT data);
	public void visit(DB_DATE data);
	public void visit(DB_DATETIME data);
	public void visit(DB_DOUBLE data);
	public void visit(DB_REAL data);
	public void visit(DB_INT data);
	public void visit(DB_BIGINT data);
	public void visit(DB_SMALLINT data);
	public void visit(DB_VARCHAR data);
	public void visit(DB_Record data);	
	//public void visit(IndexTableRecord data);
    
}
