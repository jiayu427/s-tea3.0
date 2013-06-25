package com.github.lmm.pairwise.generator;

import com.github.lmm.pairwise.FullCombinationAlgorithm;
import com.github.lmm.pairwise.core.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TXTParamters {
    static{
        if(!new File("params").exists()){
            new File("params").mkdir();
        }
    }
	private String sourceName;
	private File file;
	List<Parameter> parameters ;
	Algorithm target;
	private List<String> paramNames;
	private List<LineParameters> lineparams;
	public TXTParamters(String path){
		file=new File(path);
		this.parameters= new ArrayList<Parameter>();
		this.target=new FullCombinationAlgorithm();
		if(path.contains(File.separator)){
			this.sourceName=path.substring(path.lastIndexOf(File.separator));
			this.sourceName="PICT-"+sourceName;
			try {
				new File("params/"+sourceName).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			this.sourceName="PICT-"+path;
			try {
				new File("params/"+sourceName).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected void formatParameters(){
		try {
			FileReader filereader = new FileReader(file);
			BufferedReader reader = new BufferedReader(filereader);
			String text = reader.readLine();
			long id=0;
			while(text!=null){
				String value = text.substring(0,text.indexOf(":"));
				String[] paras=text.substring(text.indexOf(":")+1).split(",");
				this.parameters.add(new Parameter(id, value, Arrays.asList(paras)));
				id++;
				text=reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private CombinationList generate(){
		formatParameters();
		return this.target.generate(new ParameterList(this.parameters), -1);
	}

//	public static void main(String[] args) {
//		TXTParamters txtParamters = new TXTParamters("generator.txt");
//		txtParamters.formatParameters();
//		CombinationList ct = txtParamters.generate();
//		System.out.println(ct.toString());
//	}

	public void generateTXT(){
		try {
			FileWriter output=new FileWriter(new File("params/"+this.sourceName));
			CombinationList cList=generate();
			int i=0;
			for(Combination comb:cList.getCombinations()){
				formatCombination(comb);
				if(i==0){
					String text="";
					for(String name:this.paramNames){
						text=text+","+name;
					}
					output.write(text.substring(1)+"\n");
				}
				for(LineParameters lp:this.lineparams){
					output.write(lp.toString()+"\n");
				}
				i++;
			}
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void formatCombination(Combination comb){
		this.paramNames= new ArrayList<String>();
		this.lineparams=new ArrayList<LineParameters>();
		LineParameters lp =new LineParameters();
		for(ParameterValuePair pvp:comb.getMap().values()){
			this.paramNames.add(pvp.getParameterName());
			lp.addParameter(pvp.getParameterValue());
			System.out.println("添加了"+pvp.getParameterValue());
		}
		this.lineparams.add(lp);
	}
	
	public static void main(String[] args) {
		TXTParamters txt = new TXTParamters("test.txt");
		txt.generateTXT();
	}
}
