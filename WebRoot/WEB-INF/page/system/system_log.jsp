<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/datePicker/skin/WdatePicker.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/changeColor.js"></script>
	<script type="text/javascript">
		function firstPage(){
			$('#curPage').val('1');
			var startdate = $('#startdate').val();
			var enddate = $('#enddate').val();
			if(startdate > enddate){
				alert("开始日期不能大于结束日期 !");
			}else{
				$("#searchForm").submit();
			}
		}
	
		function lastPage(){
			$('#curPage').val($('#totalPage').val());
			var startdate = $('#startdate').val();
			var enddate = $('#enddate').val();
			if(startdate > enddate){
				alert("开始日期不能大于结束日期 !");
			}else{
				$("#searchForm").submit();
			}
		}
	
		function prePage(){
			var nowPage=parseInt($('#curPage').val());
	        if (nowPage !=1){
	    		nowPage=nowPage-1;
	    		$('#curPage').val(nowPage+'');
	    		var startdate = $('#startdate').val();
	    		var enddate = $('#enddate').val();
	    		if(startdate > enddate){
	    			alert("开始日期不能大于结束日期 !");
	    		}else{
	    			$("#searchForm").submit();
	    		}
	        }
		}
	
		function nextPage(){
			var nowPage=parseInt($('#curPage').val());
			var allpage=parseInt($('#totalPage').val());
	        if (nowPage !=allpage){
	    		nowPage=nowPage+1;
	    		$('#curPage').val(nowPage+'');
	    		var startdate = $('#startdate').val();
	    		var enddate = $('#enddate').val();
	    		if(startdate > enddate){
	    			alert("开始日期不能大于结束日期 !");
	    		}else{
	    			$("#searchForm").submit();
	    		}
	    	}
		}
	
		function jumpPage(){
			var nowPage = $('#num').val();
			if(nowPage==""){
				alert("请输入页码");
			}
			if(!isNaN(nowPage)){
				$('#curPage').val(nowPage+'');
				if(parseInt(nowPage)>parseInt($('#totalPage').val())){
					$('#num').val($('#totalPage').val());
				}
			}else{
				$('#curPage').val('1');
			}
			var startdate = $('#startdate').val();
			var enddate = $('#enddate').val();
			if(startdate > enddate){
				alert("开始日期不能大于结束日期 !");
			}else{
				$("#searchForm").submit();
			}
		}
	</script>
</head>
<body style="background:#E0EEFB;">
<div id="logCon">
	<form action="${pageContext.request.contextPath }/system/systemLogAction_home.action" method="post" id="searchForm" name="searchForm">
	<ul>
    	<li>开始日期：<input type="text" name="startdate" id="startdate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'enddate\')||\'2050-01-01\'}',skin:'whyGreen'})" value="<s:property value="startdate"/>"/></li>
        <li>结束日期：<input type="text" name="enddate" id="enddate" class="Wdate" style="width:90px; height:18px;" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startdate\')}',maxDate:'%y-%M-%d',skin:'whyGreen'})" value="<s:property value="enddate"/>"/></li>
        <li><input type="submit" id="searchImg" value="查询" class="button4"/></li>
    </ul>
    <input type="hidden" id="totalPage" name="totalPage" value="<s:property value="totalPage" />">
   	<input type="hidden" id="curPage" name="curPage" value="<s:property value="curPage" />">
    </form>
    <div class="content-log">
    	<table class="data_list" cellpadding="0" cellspacing="0">
        	<thead>
                <tr class="tabtr1">
                    <td width="10%">编号</td>
                    <td width="10%">来源</td>
                    <td width="10%">类型</td>
                    <td width="20%">日期时间</td>
                    <td width="50%" class="tabtd1">描述</td>
                </tr>
            </thead>
            <tbody>
                <s:iterator id="log" value="#session.vts.list" status="lo">
				<tr align="center" style="font-size: 12px; height: 20px">
					<td><s:property value="#log.logid"/></td>
					<td><s:property value="#log.sender"/></td>
					<td>
						<s:if test="#log.msgtype==1">信息</s:if>
						<s:elseif test="#log.msgtype==2">警告</s:elseif>
						<s:else>错误</s:else>
					</td>
					<td><s:property value="#log.occdt"/></td>
					<td align="left" class="tabtd1">&nbsp;&nbsp;<s:property value="#log.content"/></td>
				</tr>
				</s:iterator>
            </tbody>
        </table>
    </div>
    <!-- js splitpage -->
    <div class="split-page">
    	<a type="button" id="firstP" href="javascript:firstPage()">首页 </a>
  		<a type="button" id="preP" href="javascript:prePage()">上一页&nbsp; </a>
  		<s:property value="curPage" />/<s:property value="totalPage" />&nbsp;
  		<input id="num" type="text" size="3" style="height:12px;" value="<s:property value="curPage" />" onfocus="this.select()"/>
  		<a type="button" id="inputP" href="javascript:jumpPage()">跳转</a>
  		<a type="button" id="nextP" href="javascript:nextPage()">下一页&nbsp;</a> 
	  	<a type="button" id="lastP" href="javascript:lastPage()">尾页 </a>
    </div>
</div>
</body>
</html>