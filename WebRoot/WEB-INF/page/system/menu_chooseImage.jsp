<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/clearFile.js"></script>
	
</head>
<body style="background:#E0EEFB;">
	<input type="button" onclick="history.go(-1)" value="返回"/>
	菜名:<s:property value="dishName"/>
	<img alt="图片" src="<s:property value="dishUrl"/>" width="400" height="300">
	<form name="form1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath }/menuManageAction_uploadImg.action">
    	<input type="hidden" name="dishId" value="<s:property value="dishId"/>"/>
    	<input type="hidden" name="kid" value="<s:property value="kid"/>"/>
    	<div style="margin-left: 35%;margin-top: 20%">
    	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >    	   
          <tr>              
               <td >&nbsp;图片上传<font color='red'>*.gif,*.jpg,*.png,*.bmp</font></td>              
           </tr>  
           <tr>   
               <td width="80%">            	
		         <input type="file" name="image" maxlength="160" onchange="checkImgType(this);" width="300px"/>&nbsp;	                     
		       </td>    
           </tr>  
           <tr>
           		<td><input type="submit" value="上传"/></td>
           </tr>            
    	</table>  
       </div>
    </form>
</body>
</html>