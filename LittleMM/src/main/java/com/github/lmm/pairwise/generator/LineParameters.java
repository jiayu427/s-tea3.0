package com.github.lmm.pairwise.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LineParameters {
	
	private List<String> parameters;
	public LineParameters(){
		this.parameters=new ArrayList<String>();
	}
	public void CreateLineParamters(String...strings){
		this.parameters.addAll(Arrays.asList(strings));
	}
	public void addParameter(String e){
		this.parameters.add(e);
	}
	
	public String toString(){
		String text="";
		for(String parameter:this.parameters){
			text=text+","+parameter;
		}
		return text.substring(1);
	}
	public int size(){
		return this.parameters.size();
	}
}
