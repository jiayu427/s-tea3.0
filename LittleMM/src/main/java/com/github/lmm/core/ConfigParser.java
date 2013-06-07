package com.github.lmm.core;

import java.io.File;
import java.util.Map;

import com.github.lmm.exception.ConfigWrongException;

public class ConfigParser {
	private static Map<String,String> parse(){
		if(!new File("resource"+File.separator+"config.properties").exists()){
			throw new ConfigWrongException("config配置信息无法扫描到，也许是无法找到config.properties文件！请检查配置文件");
		}
		return PropertiesTool.getPropertiesMap("resource"+File.separator+"config.properties");
	}

	/**得到截屏的路径，如果不存在的话创建一个*/
	public static String getScreenShotPath(){
		String path =parse().get("screenshot_path");
		if(!(new File(path).isDirectory())){
			new File(path).mkdir();
		}
		return path;
	}
	/**
	 * @return 返回IE的分布式地址
	 * */
	public static String getIENode(){
		return parse().get("node-ie");
	}
	/**
	 * @return 返回Firefox的分布式地址
	 * */
	public static String getFirefoxNode(){
		return parse().get("node-firefox");
	}
	/**
	 * @return 返回htmlunit的分布式地址
	 * */
	public static String getHtmlUnitNode(){
		return parse().get("node-htmlUnit");
	}
	/**@return 返回chrome的分布式地址*/
	public static String getChromeNode(){
		return parse().get("node-chrome");
	}
	/***@return 返回safara的分布式地址*/
	public static String getSafariNode(){
		return parse().get("node-safari");
	}
	/**@return 返回opare的分布式地址*/
	public static String getOperaNode(){
		return parse().get("node-safari");
	}
	/**@return 返回错误日志的路径*/
	public static String getErrorLogDir(){
		return parse().get("error-log");
	}
	/**返回我们设置的Base-name的值*/
	public static String getBaseName(){
		return parse().get("base-name");
	}
	
	
	private static Map<String,String> mailParse(){
		return PropertiesTool.getPropertiesMap("mailconfig.properties");
	}
	/**邮箱的配置**/
	public static String getMailHost(){
		return mailParse().get("mail-host");
	}
	/**设置服务器端端口*/
	public static String getMailPort(){
		return mailParse().get("mail-port");
	}
	/**@return 邮箱服务器的用户名*/
	public static String getMailUsername(){
		return mailParse().get("mail-username");
	}
	/**@return 邮箱服务器的密码*/
	public static String getMailPassword(){
		return mailParse().get("mail-password");
	}
	/**@return 是否需要身份验证*/
	public static Boolean  getMailValidate(){
		if(mailParse().get("mail-validate").equals("true")){
			return true;
		}else if(mailParse().get("mail-validate").equals("false")){
			return false;
		}else{
			return false;
		}
	}
	/**@return 发送邮件人的email地址*/
	public static String getFromMail(){
		return mailParse().get("from-email");
				
	}
	/**@return 发送给的人*/
	public static String getToEMail(){
		return mailParse().get("to-email");
	}
}



