package com.base.utils;

/**
 * 链接地址类
 * 
 * @author xieyw
 * 
 */
public class UrlUtils {

//	public static String URL_BASE = "http://192.168.0.190:9901/supplier/";// 基础链接
//	public static String URL_BASE = "http://192.168.0.224/app/supplierbase/";// 基础链接
	public static String URL_BASE = "http://192.168.0.223/app/";// 基础链接
//	public static String URL_BASE = "http://192.168.0.4:8080/supplier/";// 基础链接
	public static String URL_LOGIN = URL_BASE + "login?";// 登录地址
	public static String URL_PURORDER = URL_BASE + "supplierorder/getAreadyReadyOrder?";// 订单地址
	public static String URL_SUBMIT_ORDER = URL_BASE + "submit?";// 订单地址
	
	public static String URL_IDEN_CODE = URL_BASE + "sendValidateCode?";  //发送验证码
	public static String URL_IDEN_CODE_JUDGED = URL_BASE + "saveAccount?";   //输入验证码验证
	public static String URL_REGISTERR_CODE = URL_BASE + "saveSupplier?";   //注册供应商和仓库信息
	
	public static String URL_GET_PLACE = "http://192.168.0.223/app/supplierbase/getPlaces?devceid=";
	
	
}
