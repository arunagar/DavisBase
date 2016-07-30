/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davisbase;

/**
 *
 * @author Swetha
 */
public interface DBTableVisitor {
    
	public void visit(TableStructure t);
	public void visit(TableFileReader t);
    
}
