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
public abstract class DBTable {
	
	protected String mAttrsType[];
	protected String mAttrsNames[];
	
	protected String tname;
	protected DBTable parent;
	
	public void accept(DBTableVisitor t){
		
	}
	
	public void setAttrNames(String attrsNames[]){
		mAttrsNames = attrsNames;
	}
	
	public String[] getAttrNames(){
		return mAttrsNames;
	}
	
	void setAttrsType(String inforAttrs[]){
		mAttrsType = inforAttrs;
	}
	
	public String[] getAttrsType(){
		return mAttrsType;
	}
	
	public String getName(){
		return tname;
	}
	
	public void setName(String name){
		tname = name;
	}
	public DBTable getParent(){
		return parent;
	}
	public void setParent(DBTable p){
		parent = p;
	}
}
