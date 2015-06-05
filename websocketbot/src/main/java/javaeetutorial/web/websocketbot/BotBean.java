/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.web.websocketbot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.inject.Named;

@Named
public class BotBean {
   
    /* Respond to a message from the chat */
    public String respond(String msg) {
        
        String response="";           
        boolean flag=true;
        /* Remove question marks */
        msg = msg.toLowerCase().replaceAll("\\?", "");
        if (msg.contains("how are you")) {
            response = "I'm doing great, thank you!";
        } else if (msg.contains("how old are you")) {
            Calendar dukesBirthday = new GregorianCalendar(1995, Calendar.MAY, 23);
            Calendar now = GregorianCalendar.getInstance();
            int dukesAge = now.get(Calendar.YEAR) - dukesBirthday.get(Calendar.YEAR);
            response = String.format("I'm %d years old.", dukesAge);
        } else if (msg.contains("when is your birthday")) {
            response = "My birthday is on May 23rd. Thanks for asking!";
        } else if (msg.contains("your favorite color")) {
            response = "My favorite color is blue. What's yours?";
        } 
        ////////////////////////////////////
        
        else if(Init.list_country.contains(msg)){//如果发现输入的是国家
            String ct="";
            if("德国australia".contains(msg)) ct="德国";
            else if("意大利italy".contains(msg)) ct="意大利";
            else if("英国britain".contains(msg)) ct="英国";
            else if("法国france".contains(msg)) ct="法国";
            else if("美国americausa".contains(msg)) ct="美国";
            else if("日本japan".contains(msg)) ct="日本";
            else if("韩国korea".contains(msg)) ct="韩国";
            else if("瑞典sweden".contains(msg)) ct="瑞典";
            else if("台湾taiwan".contains(msg)) ct="台湾";
            else if("中国china".contains(msg)) ct="中国";
            else response="您输入的国家没有知名汽车品牌哟";
            if(ct!="")
            {              
                for(Car c:Init.list)
                {
                    if(c.getCountry().equals(ct))
                    {
                        if(flag==true)
                        {
                            response=response+"    "+String.format("%1$-10s","中文名")+"\t"+String.format("%1$-15s\t","英文名")+"\t"+String.format("%1$-5s\t", "创始国家")+"集团\r\n";
                            flag=false;
                        }
                        if(c.getCname().length()<=4)
                            response=response+"    "+String.format("%1$-10s",c.getCname())+"\t"+String.format("%1$-15s\t", c.getEname())+"\t"+String.format("%1$-5s\t", c.getCountry());
                        else
                            response=response+"    "+String.format("%1$-10s",c.getCname())+String.format("%1$-15s\t", c.getEname())+"\t"+String.format("%1$-5s\t", c.getCountry());
                        if(c.getGroup()!=null)
                            response=response+c.getGroup()+"集团\r\n";
                        else
                            response=response+"\r\n";
                    }
                }
            }
        }else{
            for(int i=0;i<Init.list_index.size();i++)
            {
                if(Init.list_index.get(i).contains(msg))//如果输入的是汽车品牌
                {
                    //返回该汽车信息
                    Car c=Init.list.get(i);             
                    response=response+"resources/images/"+String.valueOf(i+1)+".jpg;";
                    response=response+"    中文名称："+c.getCname()+"\r\n"
                                    +"    英文名称："+c.getEname()+"\r\n"
                                    +"    发源地："+c.getCountry()+"\r\n";
                    if(c.getGroup()!=null)
                        response=response+"    集团："+c.getGroup()+"集团\r\n";
                    else
                        response=response+"    集团：无\r\n";
                    break;
                }
            }
            if(response=="")//如果上述条件都不符合，接下来检验是否是集团
            {
                for(Car c:Init.list)
                {
                    if(c.getGroup()!=null)
                    {
                        if(msg.contains(c.getGroup()) || c.getGroup().contains(msg))
                        {//返回这个集团的车的信息
                            if(flag==true)
                            {
                                response=response+"    "+String.format("%1$-10s","中文名")+"\t"+String.format("%1$-15s\t","英文名")+"\t"+String.format("%1$-5s\t", "创始国家")+"集团\r\n";
                                flag=false;
                            }
                            if(c.getCname().length()<=4)
                                response=response+"    "+String.format("%1$-10s",c.getCname())+"\t"+String.format("%1$-15s\t", c.getEname())+"\t"+String.format("%1$-5s\t", c.getCountry())+c.getGroup()+"集团\r\n";
                            else
                                response=response+"    "+String.format("%1$-10s",c.getCname())+String.format("%1$-15s\t", c.getEname())+"\t"+String.format("%1$-5s\t", c.getCountry())+c.getGroup()+"集团\r\n";
                        }
                    }
                }
            }
            if(response==""){//无法识别的信息
                response="resources/images/ku.jpg;我不太懂你在说什么呢%>_<%，人家也不是万能的啦……";
            //response = "Sorry, I did not understand what you said. ";
            //response += "You can ask me how I'm doing today; how old I am; or ";
            //response += "what my favorite color is.";
            
            }
        }
        
        /*
        else {//无法识别的信息
            response = "Sorry, I did not understand what you said. ";
            response += "You can ask me how I'm doing today; how old I am; or ";
            response += "what my favorite color is.";
        }
        */
        
        
        
        try {
            Thread.sleep(1200);
        } catch (InterruptedException ex) { }
        return response;
    }
}
