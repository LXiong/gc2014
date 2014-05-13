<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/splitpage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/changeColor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/tel.js"></script>
	<style type="text/css">
		#overlay-tel{position:absolute;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;} 
		#win-tel{position:absolute;top:50%;left:50%;width:400px;height:200px;background:#EAECEA;border:4px solid #F7F7F7;margin:-102px 0 0 -202px;display:none;}
		h2{font-size:12px;height:18px;text-align:right;background:#3F89EC;border-bottom:3px solid #F7F7F7;padding:2px;cursor:move;margin-top:2px;} 
		h2 span{border:0px solid #f90;padding:0 2px;} 
	</style>
	<script type="text/javascript">
		function emptyTel(){
			if(confirm('您确定要清空吗？')){
				document.form1.action="taskManageAction_emptyTel.action";
				document.form1.submit();
			}
		}
		function resetTel(){
			if(confirm('您确定要重置吗？')){
				document.form1.action="taskManageAction_resetTel.action";
				document.form1.submit();
			}
		}
	</script>
</head>
<body style="background:#E0EEFB;">
<form name="form1" action="" method="post">
	<input type="hidden" value="<s:property value="tid"/>" name="tid"/>
	<input type="hidden" value="<s:property value="rflag"/>" name="rflag"/>
</form>
<div id="jiangbu-data">
<p id="task-tel-title">
	<span>
		<s:if test="rflag==1">
		<input type="button" value="返回" onclick="history.go(-<s:property value='rflag'/>)" class="button43"/>
		</s:if>
		<s:else>
 		<input type="button" value="返回" onclick="location.href='${pageContext.request.contextPath }/taskManageAction_home.action'" class="button43"/>
 		</s:else>
 	</span>
	<span>
		<input type="button" value="清空" onclick="emptyTel()" class="button43"/>
	</span>
	<span>
		<input type="button" value="重置" onclick="resetTel()" class="button43"/>
	</span>
	<span>		
		<input type="button" value="导出" onclick="location.href='${pageContext.request.contextPath }/taskManageAction_exportTel.action?tid=<s:property value="tid"/>&rflag=<s:property value="rflag"/>'" class="button43"/>
	</span>
	<span>
 		<input type="button" value="添加" onclick="popEditTel('add','','')" class="button43"/>
    </span>	
</p>
<table class="data_list" cellpadding="0" cellspacing="0" width="100%">
   	<thead>
    <tr class="tabtr">
        <td width="8%">号码编号</td>
        <td width="15%">呼叫号码</td>
        <td width="15%">状态</td>
        <td width="15%">呼叫时间</td>
        <td width="15%">通话时长(秒)</td>
        <td width="20%">操作</td>
    </tr>
    </thead>
    <tbody id="splitpage">
    <s:iterator value="#session.vts.list" var="ls" status="sc">
    <tr style="display:none">
        <td><s:property value="#ls.c0"/></td>
        <td align="left">&nbsp;<s:property value="#ls.c1"/></td>
        <td>
        	<s:property value="#application.vta.getListString('callresult',#ls.c2)"/>
        </td>
        <td><s:property value="#ls.c3"/></td>
        <td><s:property value="#ls.c4"/></td>
        <td>
        	<a href="javascript:popEditTel('edit','<s:property value="#ls.c0"/>','<s:property value="#ls.c1"/>')">修改</a>
        	<a href="javascript:if(confirm('确定要删除吗?')) location.href='${pageContext.request.contextPath }/taskManageAction_deleteTel.action?tid=<s:property value="tid"/>&ttid=<s:property value="#ls.c0"/>&rflag=<s:property value="rflag"/>'">删除</a>
        </td>
    </tr>
    </s:iterator>
	</tbody>
</table>
</div>
<div class="split-page">
	<input type="hidden" id="pageRows" value="28"/>
	<div id="changePage"></div>
</div>

<!-- add telnumber -->
<div id="overlay-tel"></div>
<div id="win-tel" style="line-height: 30px">
<h2 style="line-height:20px; text-align:left"><span id="tel-title"><font color="#fff">修改任务信息</font></span></h2>
<form name="telForm" action="" method="post">
<input type="hidden" name="rflag" value="<s:property value='rflag'/>"/>
<input type="hidden" name="tid" value="<s:property value='tid'/>"/>
<input type="hidden" id="t0" name="ttid" value=""/>
<!-- eidt flag -->
<input type="hidden" id="eflag" name="eflag" value=""/>
<div class="edit-list">
	<table width="400px" cellpadding="0" cellspacing="0">
		<tr height="10px"></tr>
		<tr>
			<td width="10%"></td>
			<td><span id="teltitle">号码列表(多个号码用,隔开):</span>&nbsp;&nbsp;</td>
		</tr>
		<tr height="20px">
			<td width="10%"></td>
			<td>
				<textarea rows="3" cols="35" style="width:300px; height:80px; resize:none;" id="t1" name="tellist"></textarea>
			</td>
		</tr>
	</table>
</div>
<div style="margin-top:5px; margin-left:130px;">
	<input type="button" value="确定" class="button4" onclick="subTelBt()"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="close" type="button" value="取消" class="button4"/>
</div>
</form>
</div> 
<!-- add telnumber -->

</body>
</html>