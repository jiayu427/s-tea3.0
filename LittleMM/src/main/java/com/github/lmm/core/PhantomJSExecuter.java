package com.github.lmm.core;


import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PhantomJSExecuter {

    public static Process exec(File scriptFile, String... parameters) {
        File script = scriptFile;
        List<String> paramList = new LinkedList<String>();
        paramList.add("phantomjs");
        paramList.add(scriptFile.getAbsolutePath());
        paramList.addAll(Arrays.asList(parameters));
        parameters = paramList.toArray(new String[paramList.size()]);
        try {
            Process process=Runtime.getRuntime().exec(parameters);
            return process;
        } catch (IOException e) {
            throw new RuntimeException("执行命令的时候出现了异常！");
        }

    }

    public static String execute(File scriptFile,String... parameters){
        Process process=exec(scriptFile,parameters);
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        String str = "";
        try {
            String text = bufferedReader.readLine();
            while(text!=null){
                str+=text+"\n";
                text=bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return str;
    }
}

