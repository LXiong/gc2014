<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.net.*" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String account = "";
	String password = "";
	String checked = "";
	Cookie cookies[] = request.getCookies();
	for (int i=0; cookies!=null && i<cookies.length; i++) {
		Cookie cookie = cookies[i];
		if (cookie!=null && "account".equals(cookie.getName())) {
			account = URLDecoder.decode(cookie.getValue(),"UTF-8");	
			checked = "checked";
		}
		if (cookie!=null && "password".equals(cookie.getName())) {
			password = cookie.getValue();
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style-b.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.5.1.min.js"></script>
 	<style type="text/css">
 		.errorMessage{padding-bottom:2px; padding-right:30px; height:5px}
 	</style>
 	<title>电话自动外呼系统</title>
 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<table width="1024px" height="768px" border="0" cellpadding="0" cellspacing="0" align="center" background="${pageContext.request.contextPath }/images/vt_rec_loginback3.jpg ">
		<tr>
			<td>
				<table width="566px" height="251px" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr height="85px" >
						<td background="${pageContext.request.contextPath }/images/vt_rec_login1.png">
							<table>
								<tr>
									<td class="version"><s:property value="#application.vta.product"/></td>
								</tr>
								<tr>
									<td class="company"><strong><s:property value="#application.vta.customer"/></strong></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr height="130px">
						<td background="${pageContext.request.contextPath }/images/vt_rec_login2.png">
							<form name="Form1" action="${pageContext.request.contextPath}/userAction_login.action" method="post" onsubmit="return check()">
							<table cellpadding="0" cellspacing="0">
								<tr>
									<td width="284px"></td>
									<td width="284px">
										<table class="table_l" border="0" cellpadding="0" cellspacing="0">
											<colgroup>
												<col width="90"/>
												<col/>
											</colgroup>
											<tbody>
												<tr height="31px" style="padding-top:6px;">
													<th>账号&nbsp;</th>
													<td><input type="text" id="account" name="account" value="<%=account %>" tabindex="1" class="inputbox" onblur="this.className='inputbox'" onfocus="this.className='inputbox2'"/></td>
												</tr>
												<tr height="31px">
													<th>密码&nbsp;</th>
													<td><input type="password" id="password" name="password" value="<%=password %>" tabindex="2" class="inputbox" onblur="this.className='inputbox'" onfocus="this.className='inputbox2'"/></td>
												</tr>
												<tr height="31px">
													<th></th>
													<td>
														<input type="checkbox" id="rememberPass" name="rememberPass" value="yes" <%=checked %> class="cbox-midd"/><label for="auto" class="lab-midd">记住密码</label>
													</td>
												</tr>
												<tr height="30px">
													<th></th>
													<td>
														<input type="submit" value="登&nbsp;&nbsp;录" class="button4"/>
													</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr height="32px">
						<td background="${pageContext.request.contextPath }/images/vt_rec_login3.png" style="text-align:right;">
							<font style="color:red"><s:fielderror value="error"/></font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
<!-- 登录时获取焦点 -->
<script type="text/javascript">
 	$(document).ready(function() {
		document.all.account.focus();
	});
	function check(){
	    var theForm = document.forms[0];
	    if(!checkNull(theForm.account)){
			alert("请输入用户名");
			theForm.account.focus(); 
			return false;
		}
		if(Trim(theForm.account.value)==""){
			alert("请输入用户名");
			theForm.account.focus();
			return false;
	    }
	}
</script>
</body>
</html>