<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/splitpage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/changeColor.js"></script>
</head>
<body style="background:#E0EEFB;">
<h3 class="jiangbu-title">菜单类别</h3>
<div id="jiangbu-data">
<div style="margin:0 auto; width:200px; padding-top:10px;">
<table class="data_list" cellpadding="0" cellspacing="0" width="180px;">
   	<thead>
    <tr class="tabtr12">
        <td width="20%">编号</td>
        <td width="20%">类别</td>
    </tr>
    </thead>
    <tbody id="splitpage">
    <s:iterator value="#session.vts.list" var="ls">
    	<tr>
    		<td align="center"><s:property value="#ls.c0"/></td>
    		<td align="center">
    			<a href="${pageContext.request.contextPath }/system/menuManageAction_menuNameList.action?kid=<s:property value='#ls.c0'/>"><s:property value="#ls.c1"/></a>
    		</td>
    	</tr>	
    </s:iterator>
	</tbody>
</table>
</div>
</div>
</body>
</html>