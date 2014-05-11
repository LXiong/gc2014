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
	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/jquery.Jcrop.css" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/style/demos.css" type="text/css" />	
	<script src="${pageContext.request.contextPath }/script/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/script/jquery.Jcrop.js"></script>		
</head>
<body style="background:#E0EEFB;">
	<form name=form1 method=post action="<%=path%>/imgCrop">
    	
    	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" >    	   
           <tr>    
               <td id="cropTd" style="display:none"><input type="button" value="剪切照片" id="cropButton"/></td>    
           </tr>   
           
            <tr>              
              <td id="imgTd" style="width:${param.width}px;height:${param.height}px;" align="center" style="padding-top:5px;">    
              	 <c:choose>
              	 	<c:when test="${param.tag eq 0}">
              	 		<img  src="<%=path%>${param.oldImgPath}" id="imgCrop" name="imgCrop" border="0" />     
              	 	</c:when>
              	 	<c:when test="${param.tag eq 1}">
              	 		<img  src="<%=path%>${param.imgName}" id="imgCrop" name="imgCrop" border="0" />    
              	 	</c:when>
              	 </c:choose>  
            </td>               
           </tr> 
             
            <tr>  
               <td>
               	     <label>X1 <input type="text" size="4" id="labelX" name="labelX" /></label>
					<label>Y1 <input type="text" size="4" id="labelY" name="labelY" /></label>
					<label>X2 <input type="text" size="4" id="labelX2" name="labelX2" /></label>
					<label>Y2 <input type="text" size="4" id="labelY2" name="labelY2" /></label>
					<label>W <input type="text" size="4" id="labelW" name="labelW" /></label>
					<label>H <input type="text" size="4" id="labelH" name="labelH" /></label>
               </td>    
           </tr>
           
            <tr>  
               <td  colspan="2"><a href="index.jsp">返回上传图片</a></td>    
           </tr>            
     </table>
    
	<input type="hidden"  id="x" name="x" />
	<input type="hidden"  id="y" name="y" />
	<input type="hidden"  id="x2" name="x2" />
	<input type="hidden"  id="y2" name="y2" />
	<input type="hidden"  id="w" name="w" />
	<input type="hidden"  id="h" name="h" />  
	
	<!-- 源图片宽度和高度 -->
	<input type="hidden"  id="width" name="width" value="${param.width}"/>  
	<input type="hidden"  id="height" name="height" value="${param.height}"/>
	<input type="hidden"  id="oldImgPath" name="oldImgPath" value="${param.oldImgPath}"/>  
	<input type="hidden"  id="imgRoot" name="imgRoot" value="${param.imgRoot}"/>	
	<input type="hidden"  id="imgFileExt" name="imgFileExt" value="${param.imgFileExt}"/>	
	
    </form>
</body>
<script type="text/javascript">

	jQuery(document).ready(function(){		
	
		
		jQuery('#imgCrop').Jcrop({
			onChange: showCoords,
			onSelect: showCoords
		});	
		
		jQuery('#cropButton').click(function(){
			    var x = jQuery("#x").val();
				var y = jQuery("#y").val();
				var w = jQuery("#w").val();
				var h = jQuery("#h").val();			
				
				if(w == 0 || h == 0 ){
					alert("您还没有选择图片的剪切区域,不能进行剪切图片!");
					return;
				}	
				alert("你要剪切图片的X坐标: "+x + ",Y坐标: " + y + ",剪切图片的宽度: " + w + ",高度：" + h );
				if(confirm("确定按照当前大小剪切图片吗")){				
					document.form1.submit();
				}
		 });
		
	
	});
	
	function showCoords(c)
	{
		jQuery('#x').val(c.x);
		jQuery('#y').val(c.y);
		jQuery('#x2').val(c.x2);
		jQuery('#y2').val(c.y2);
		jQuery('#w').val(c.w);
		jQuery('#h').val(c.h);	
		
		jQuery('#labelX').val(c.x);
		jQuery('#labelY').val(c.y);
		jQuery('#labelX2').val(c.x2);
		jQuery('#labelY2').val(c.y2);
		jQuery('#labelW').val(c.w);
		jQuery('#labelH').val(c.h);	
		
		//显示剪切按键
		jQuery('#cropTd').css("display","");
				
	}
</script>
</html>