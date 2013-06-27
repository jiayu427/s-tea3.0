package com.github.lmm.pairwise.generator;

import com.github.lmm.pairwise.algorithm.FullCombinationAlgorithm;
import com.github.lmm.pairwise.core.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ouamaqing
 * Date: 13-6-27
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
public class ExcelParameters implements Parameters{
    static{
        if(!new File("params").exists()){
            new File("params").mkdir();
        }
    }
    /**用来记录所有的行元素*/
    private List<LineParameters> lineParameterses;
    private File file;
    private int rows;
    private Workbook workbook;
    private Sheet sheet;
    List<Parameter> parameters ;
    Algorithm target;
    private List<String> paramNames;
    private List<LineParameters> templineparams;
    public ExcelParameters(String path) {
        this.file = new File(path);
        this.lineParameterses= new ArrayList<LineParameters>();
        this.target=new FullCombinationAlgorithm();
        try {
            this.workbook= WorkbookFactory.create(file);
            this.parameters=new ArrayList<Parameter>();
            this.sheet=workbook.getSheetAt(0);
            this.rows=this.sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            throw new RuntimeException("打开Excel文件的时候出现了读写错误，最大的可能是文件不存在:"+path);
        } catch (InvalidFormatException e) {
            throw new RuntimeException("打开Excel文件的时候出现了读写错误，最大的可能是文件不存在:"+path);
        }
    }

    public ExcelParameters(File file){
        this(file.getAbsolutePath());
    }

    /**这个内部方法用来生成指定格式的excel文件*/
    private CombinationList generateParameters(){
        formatParameters();
        return this.target.generate(new ParameterList(this.parameters), -1);
    }

    private void formatCombination(Combination comb){
        this.paramNames= new ArrayList<String>();
        this.templineparams=new ArrayList<LineParameters>();
        LineParameters lp =new LineParameters();
        for(ParameterValuePair pvp:comb.getMap().values()){
            this.paramNames.add(pvp.getParameterName());
            lp.addParameter(pvp.getParameterValue());
        }
        this.templineparams.add(lp);
    }
    @Override
    public void generate() {
        Workbook pictworkbook= new XSSFWorkbook();
        Sheet mysheet=pictworkbook.createSheet("pict");
        CombinationList clist = generateParameters();
        int i=0;
        for(Combination combination:clist.getCombinations()){
            formatCombination(combination);
            if(i==0){
                int j=0;
                Row myrow=mysheet.createRow(i);
                for(String name:this.paramNames){
                    myrow.createCell(j).setCellValue(name);
                    j++;
                }
                i++;
                continue;
            }else{
                for(LineParameters lp:this.templineparams){
                    this.lineParameterses.add(lp);
                    Row myrow = mysheet.createRow(i);
                    int k=0;
                    for(String p:lp.getParameters()){
                        myrow.createCell(k).setCellValue(p);
                        k++;
                    }
                    i++;
                }
            }

        }
        File fileone = new File("params/PICT-"+this.file.getName().substring(0,this.file.getName().indexOf("."))+".xlsx");
        if(!fileone.exists()){
            try {
                fileone.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream output = new FileOutputStream(fileone);
            pictworkbook.write(output);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LineParameters> getParameters() {
        return this.lineParameterses;
    }

    /**这个方法用来收集存在的pict原始文件的参数*/
    private void formatParameters(){
        for(int i=0;i<this.rows;i++){
            Row row=sheet.getRow(i);
            int cols = row.getPhysicalNumberOfCells();
            String name=row.getCell(0).toString();
            String [] paras=new String[cols-1];
            for(int j=1;j<cols;j++){
                paras[j-1]=row.getCell(j).toString();
            }
            this.parameters.add(new Parameter((long)i,name, Arrays.asList(paras)));
        }
    }
}
