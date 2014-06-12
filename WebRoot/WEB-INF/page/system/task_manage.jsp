<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/splitpage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/changeColor.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/task.js"></script>
	<style type="text/css">
		#overlay-task{position:absolute;top:0;left:0;width:100%;height:100%;background:#000;opacity:0.5;filter:alpha(opacity=50);display:none;} 
		#win-task{position:absolute;top:30%;left:40%;width:500px;height:400px;background:#EAECEA;border:4px solid #F7F7F7;margin:-102px 0 0 -202px;display:none;}
		h2{font-size:12px;height:18px;text-align:right;background:#3F89EC;border-bottom:3px solid #F7F7F7;padding:2px;cursor:move;margin-top:2px;} 
		h2 span{border:0px solid #f90;padding:0 2px;} 
	</style>
</head>
<body style="background:#E0EEFB;">
<div id="jiangbu-data">
<table class="data_list" cellpadding="0" cellspacing="0" width="100%">
   	<thead>
    <tr class="tabtr2">
        <td width="4%">任务编号</td>
        <td width="6%">任务<br/>状态</td>
        <td width="12%">任务名称</td>
        <td width="8%">工作模式</td>
        <td width="6%">最大试<br/>呼次数</td>
        <td width="8%">呼叫间隔<br/>时长(分钟)</td>
        <td width="12%">创建日期时间</td>
        <td width="4%">号码总数</td>
        <td width="6%">呼叫<br/>完成数</td>
        <td width="6%">呼叫<br/>应答数</td>
        <td width="18%">
        	<p>
        		<input type="button" value="导出" onclick="location.href='${pageContext.request.contextPath }/taskManageAction_exportTask.action'" class="button43"/>
        		&nbsp;&nbsp;
        		<input type="button" value="添加" onclick="popEditTask('add','','','','','','')" class="button43"/>
        	</p>
        </td>
    </tr>
    </thead>
    <tbody id="splitpage">
    <s:iterator value="#session.vts.list" var="ls" status="sc">
    <tr style="display:none">
        <td><s:property value="#ls.tid"/></td>
        <td>
        	<s:property value="#application.vta.getListString('taskstate',#ls.state)"/>
        </td>
        <td align="left" title="任务名称: <s:property value="#ls.tname"/>&#13任务内容: <s:property value="#ls.context"/>">&nbsp;<s:property value="#ls.tname.length()>11?#ls.tname.substring(0,10)+'...':#ls.tname"/></td>
        <td><s:property value="#ls.workmode"/></td>
        <td><s:property value="#ls.maxtrys"/></td>
        <td><s:property value="#ls.nextcalldelay"/></td>
        <td><s:property value="#ls.cdt"/></td>
        <td><s:property value="#ls.m"/></td>
        <td><s:property value="#ls.fn"/></td>
        <td><s:property value="#ls.sn"/></td>
        <td>
        	<a href="${pageContext.request.contextPath }/taskManageAction_stateTask.action?tid=<s:property value="#ls.tid"/>&state=1">激活</a>
        	<a href="${pageContext.request.contextPath }/taskManageAction_stateTask.action?tid=<s:property value="#ls.tid"/>&state=0">停止</a>
        	<a href="${pageContext.request.contextPath }/taskManageAction_viewNumber.action?tid=<s:property value="#ls.tid"/>">号码管理</a>
        	<a href="javascript:popEditTask('edit','<s:property value="#ls.tid"/>','<s:property value="#ls.tname"/>','<s:property value="#ls.workmode"/>','<s:property value="#ls.maxtrys"/>','<s:property value="#ls.nextcalldelay"/>','<s:property value="#ls.context"/>')">修改</a>
        	<a href="javascript:if(confirm('确定要删除吗?')) location.href='${pageContext.request.contextPath }/taskManageAction_deleteTask.action?tid=<s:property value="#ls.tid"/>'">删除</a>
        </td>
    </tr>
    </s:iterator>
	</tbody>
</table>
</div>
<div class="split-page">
	<input type="hidden" id="pageRows" value="29"/>
	<div id="changePage"></div>
</div>

<!-- update name -->
<div id="overlay-task"></div>
<div id="win-task" style="line-height: 30px">
<h2 style="line-height:20px; text-align:left"><span id="t-title"><font color="#fff">修改任务信息</font></span></h2>
<form name="taskForm" action="" method="post">
<!-- tid -->
<input type="hidden" id="t1" name="tasktxt"/>
<!-- eidt or add flag -->
<input type="hidden" id="t2" name="tasktxt"/>
<div class="edit-list">
	<table width="500px" cellpadding="0" cellspacing="0">
		<tr height="10px"></tr>
		<tr height="20px">
			<td width="35%" align="right">任务名称:&nbsp;&nbsp;</td>
			<td width="65%" align="left">
				<input id="t3" name="tasktxt" class="task-input" type="text" maxlength="20"/>
			</td>
		</tr>
		<tr height="20px">
			<td width="30%" align="right">工作模式:&nbsp;&nbsp;</td>
			<td width="70%" align="left">
				<s:select id="t4" name="tasktxt" list="#application.vta.GetList('workmode')" listKey="id" listValue="str" cssClass="sele"></s:select>
			</td>
		</tr>
		<tr height="20px">
			<td width="30%" align="right">最大试呼次数:&nbsp;&nbsp;</td>
			<td width="70%" align="left">
				<input id="t5" name="tasktxt" class="task-input2" type="text" maxlength="10"/>
			</td>
		</tr>
		<tr height="20px">
			<td width="30%" align="right">呼叫间隔时长(分钟):&nbsp;&nbsp;</td>
			<td width="70%" align="left">
				<input id="t6" name="tasktxt" class="task-input2" type="text" maxlength="10"/>
			</td>
		</tr>
		<tr height="20px">
			<td align="right" valign="top">任务内容:&nbsp;&nbsp;</td>
			<td align="left">
				<textarea id="t7" name="tasktxt" rows="3" cols="35" style="width:300px; height:120px; resize:none;"></textarea>
			</td>
		</tr>
		<tr height="10px"></tr>
	</table>
</div>
<div style="margin-top:5px; margin-left:150px;">
	<input type="button" value="确定" class="button4" onclick="subTaskBt()"/>&nbsp;&nbsp;&nbsp;&nbsp;
	<input id="close" type="button" value="取消" class="button4"/>
</div>
</form>
</div> 
<!-- update name end -->

</body>
</html>