/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.websocketbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author dan
 */
@Startup
@Singleton
public class Init {
    public static List<Car> list=new ArrayList<Car>();
    public static List<String> list_country=new ArrayList<String>();
    public static List<String> list_index=new ArrayList<String>();
    public static int jj=0;
    @PostConstruct//创建Init对象后立刻被执行
    public void ini(){
        
        String rootPath=Init.class.getClassLoader().getResource("").getPath();
        BufferedReader reader = null;
        String path=getClass().getClassLoader().getResource("data.txt").getFile().toString().replaceAll("%20", " ");
        String path1=getClass().getClassLoader().getResource("country.txt").getFile().toString().replaceAll("%20", " ");
        //File file=new File(path);
        try {          
            reader = new BufferedReader(new FileReader(path));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String[] strs=tempString.split("\t");
                Car c=new Car();
                c.setEname(strs[0]);
                c.setCname(strs[1]);
                c.setCountry(strs[2]);
                if(strs.length>3)
                    c.setGroup(strs[3]);
                list.add(c);  
                list_index.add(strs[0].toLowerCase()+strs[1]);
            }
            reader = new BufferedReader(new FileReader(path1));
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                tempString=tempString.replaceAll("[\t]","").toLowerCase();
                list_country.add(tempString);            
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
