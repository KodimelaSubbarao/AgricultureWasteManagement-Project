//package com.voidmain.servlets;
//
//import java.io.File;
//
//
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload2.jakarta.servlet5.JakartaServletFileUpload;
//import org.apache.tomcat.util.http.fileupload.FileItem;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//
//
//
//
//public class HttpRequestParser {
//
//	public static Map<Object,List<String>> parseMultiPartRequest(HttpServletRequest request,Object obj)
//	{
//		Map<Object,List<String>> map=new HashMap<Object, List<String>>();
//
//		if(JakartaServletFileUpload.isMultipartContent(request))
//		{
//			try {
//
//				List<String> files=new ArrayList<String>();
//
//				for (FileItem item : new JakartaServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request))) {
//
//					if (!item.isFormField()) {
//						
//						item.write(new File(request.getServletContext().getRealPath("/")+"uploads/"+item.getName()));
//						files.add(item.getName());
//					}
//					else
//					{
//						String fieldName = item.getFieldName();
//						String fieldValue = item.getString();
//
//						Method[] methods=obj.getClass().getDeclaredMethods();
//
//						for(Method method : methods)
//						{
//							String methodName=method.getName();
//
//							if(methodName.equalsIgnoreCase("set"+fieldName))
//							{
//								try {
//
//									method.setAccessible(true);
//
//									String type=method.getParameterTypes()[0].getName();
//
//									if(type.equals("java.lang.String"))
//									{
//										method.invoke(obj,method.invoke(obj,fieldValue));
//
//									}else if(type.equals("int"))
//									{
//										method.invoke(obj,Integer.parseInt(request.getParameter(fieldValue)));
//									}else if(type.equals("float"))
//									{
//										method.invoke(obj,Float.parseFloat(request.getParameter(fieldValue)));
//									}else if(type.equals("long"))
//									{
//										method.invoke(obj,Long.parseLong(request.getParameter(fieldValue)));
//									}else if(type.equals("dobule"))
//									{
//										method.invoke(obj,Double.parseDouble(request.getParameter(fieldValue)));
//									}else if(type.equals("boolean"))
//									{
//										method.invoke(obj,Boolean.parseBoolean(request.getParameter(fieldValue)));
//									}
//
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				}
//
//				map.put(obj,files);
//			}
//			catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return map;
//	}
//
//	public static Object parseRequest(HttpServletRequest request,Object obj)
//	{
//		Enumeration<String> enumeration=request.getParameterNames();
//
//		while(enumeration.hasMoreElements())
//		{
//			String parameterName=enumeration.nextElement();
//
//			Method[] methods=obj.getClass().getDeclaredMethods();
//
//			for(Method method : methods)
//			{
//				String methodName=method.getName();
//
//				if(methodName.equalsIgnoreCase("set"+parameterName))
//				{
//					try {
//
//						method.setAccessible(true);
//
//						String type=method.getParameterTypes()[0].getName();
//
//						if(type.equals("java.lang.String"))
//						{
//							method.invoke(obj,request.getParameter(parameterName));
//						}else if(type.equals("int"))
//						{
//							method.invoke(obj,Integer.parseInt(request.getParameter(parameterName)));
//						}else if(type.equals("float"))
//						{
//							method.invoke(obj,Float.parseFloat(request.getParameter(parameterName)));
//						}else if(type.equals("long"))
//						{
//							method.invoke(obj,Long.parseLong(request.getParameter(parameterName)));
//						}else if(type.equals("dobule"))
//						{
//							method.invoke(obj,Double.parseDouble(request.getParameter(parameterName)));
//						}else if(type.equals("boolean"))
//						{
//							method.invoke(obj,Boolean.parseBoolean(request.getParameter(parameterName)));
//						}
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//
//		return obj;
//	}
//}
