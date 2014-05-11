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
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/splitpage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/changeColor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/dish.js"></script>
	<style type="text/css">
		#overlay-edit{position:absolute;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;} 
		#win-edit{position:absolute;top:50%;left:50%;width:500px;height:240px;background:#EAECEA;border:4px solid #F7F7F7;margin:-102px 0 0 -202px;display:none;}
		h2{font-size:12px;height:18px;text-align:right;background:#3F89EC;border-bottom:3px solid #F7F7F7;padding:2px;cursor:move;margin-top:2px;} 
		h2 span{border:0px solid #f90;padding:0 2px;} 
	</style>
</head>
<body style="background:#E0EEFB;">
<h3 class="jiangbu-title">菜单名列表</h3>
<div id="jiangbu-data">
<table class="data_list" cellpadding="0" cellspacing="0" width="100%">
   	<thead>
    <tr class="tabtr12">
        <td width="10%">编号</td>
        <td width="20%">菜名</td>
        <td width="10%">图片</td>
        <td width="10%">图标</td>
        <td width="15%">
        	<p>
        	<input type="button" value="返回" onclick="javascript:history.go(-1)" class="button43"/>
        	</p>
        </td>
    </tr>
    </thead>
    <tbody id="splitpage">
    <s:iterator value="#session.vts.list" var="ls" status="sc">
    <tr style="display:none">
        <td><s:property value="#ls.c0"/></td>
        <td><s:property value="#ls.c1"/></td>
        <td><a href="${pageContext.request.contextPath }/menuManageAction_chooseImage.action?kid=<s:property value="kid"/>&dishId=<s:property value="#ls.c0"/>&dishName=<s:property value="#ls.c1"/>&dishUrl=<%=path %>/images/<s:property value="#ls.c2"/>/yuxiang.jpg">查看</a></td>
        <td><a href="${pageContext.request.contextPath }/menuManageAction_chooseImage.action?kid=<s:property value="kid"/>&dishId=<s:property value="#ls.c0"/>&dishName=<s:property value="#ls.c1"/>&dishUrl=<s:property value="#ls.c3"/>">查看</a></td>
        <td>
        	<a href="javascript:updateMenu('edit','<s:property value="kid"/>','<s:property value="#ls.c0"/>','<s:property value="#ls.c1"/>','<s:property value="#ls.c2"/>','<s:property value="#ls.c3"/>')">修改</a>
        	<a href="javascript:deleteMenu('<s:property value="#ls.c0"/>')">删除</a>	
        </td>
    </tr>
    </s:iterator>
	</tbody>
</table>
</div>
<div class="split-page">
	<input type="hidden" id="pageRows" value="26"/>
	<div id="changePage"></div>
</div>

<!-- update name -->
<div id="overlay-edit"></div>
<div id="win-edit" style="line-height: 30px">
<h2 style="line-height:20px; text-align:left"><span><font color="#fff">修改菜单信息</font></span></h2>
<form name="menuForm" action="" method="post">
<input type="hidden" name="kid" value="<s:property value="kid"/>"/>
<input type="hidden" id="d1" name="dishtxt"/>
<input type="hidden" name="dishtxt" value="<s:property value="kid"/>"/>
<div class="edit-list">
	<table width="450px" cellpadding="0" cellspacing="0">
		<tr height="20px">
			<td width="30%" align="right">菜名:&nbsp;&nbsp;</td>
			<td width="35%" align="left"><input id="d2" name="dishtxt" class="inpasstxt" type="text" maxlength="20"/></td>
		</tr>
		<tr height="20px">
			<td width="30%" align="right">图片路径:&nbsp;&nbsp;</td>
			<td width="35%" align="left"><input id="d3" name="dishtxt" class="inpasstxt" type="text" maxlength="20"/></td>
		</tr>
		<tr height="20px">
			<td width="30%" align="right">图标路径:&nbsp;&nbsp;</td>
			<td width="35%" align="left"><input id="d4" name="dishtxt" class="inpasstxt" type="text" maxlength="20"/></td>
		</tr>
	</table>
</div>
<div style="margin-top:5px; margin-left:150px;">
	<input type="button" value="确定" class="button4" onclick="subMenuBt()"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="close" type="button" value="取消" class="button4"/>
</div>
</form>
</div> 
<!-- update name end -->

<!-- delete form -->
<form name="menuDelForm" action="" method="post">
	<input type="hidden" id="mids" name="mid" value=""/>
	<input type="hidden" name="kid" value="<s:property value="kid"/>"/>
</form>
</body>
</html>