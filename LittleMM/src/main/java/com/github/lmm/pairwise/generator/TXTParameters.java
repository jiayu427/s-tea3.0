package com.github.lmm.pairwise.generator;

import com.github.lmm.pairwise.FullCombinationAlgorithm;
import com.github.lmm.pairwise.core.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TXTParameters implements Parameters{
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
	public TXTParameters(String path){
		file=new File(path);
		this.parameters= new ArrayList<Parameter>();
		this.target=new FullCombinationAlgorithm();
		if(path.contains(File.separator)){
			this.sourceName=path.substring(path.lastIndexOf(File.separator)+1);
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
    public TXTParameters(File file){
        this(file.getAbsolutePath());
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

	private CombinationList generateParameters(){
		formatParameters();
		return this.target.generate(new ParameterList(this.parameters), -1);
	}

	public void generate(){
		try {
			FileWriter output=new FileWriter(new File("params/"+this.sourceName));
			CombinationList cList=generateParameters();
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
			//System.out.println("添加了"+pvp.getParameterValue());
		}
		this.lineparams.add(lp);
	}



    public List<LineParameters>  getParameters(){
        List<LineParameters> parameters=new ArrayList<LineParameters>();
        try {
            FileReader input=new FileReader("params/PICT-"+this.file.getName());
            BufferedReader br = new BufferedReader(input);
            String linestr=br.readLine();
            int i=0;
            while(linestr!=null){
                if(i==0){
                    i++;
                    linestr=br.readLine();
                    continue;
                }
                LineParameters lineParameters=new LineParameters();
                String[] params= linestr.split(",");
                for(String param:params){
                    lineParameters.addParameter(param);
                }
                parameters.add(lineParameters);
                i++;
                linestr=br.readLine();
            }
            br.close();
            return parameters;
        } catch (IOException e) {
            throw new RuntimeException("获取PICT文件的参数的时候出现了错误！",e);
        }
    }
}
