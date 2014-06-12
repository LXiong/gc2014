function popEditTask(s,t1,t2,t3,t4,t5,t6){
	//title
	var tt = document.getElementById("t-title");
	tt.innerHTML="";
	//clear
	document.getElementById("t1").value='';
	document.getElementById("t2").value='';
	document.getElementById("t3").value='';
	document.getElementById("t4").value='';
	document.getElementById("t5").value='';
	document.getElementById("t6").value='';
	document.getElementById("t7").value='';
	if(s=="edit"){
		tt.innerHTML="<font color='#fff'>修改任务信息</font>";
		document.getElementById("t1").value=1;
		document.getElementById("t2").value=t1;
		document.getElementById("t3").value=t2;
		document.getElementById("t4").value=t3;
		document.getElementById("t5").value=t4;
		document.getElementById("t6").value=t5;
		document.getElementById("t7").value=t6;
	}else{
		tt.innerHTML="<font color='#fff'>添加任务信息</font>";
		document.getElementById("t1").value=0;
	}
	
	
	var oWin = document.getElementById("win-task");
	var oLay = document.getElementById("overlay-task");
	var oClose = document.getElementById("close"); 
	var oH2 = oWin.getElementsByTagName("h2")[0]; 
	var bDrag = false; 
	var disX = disY = 0; 
	oLay.style.display = "block"; 
	oWin.style.display = "block" 
		oClose.onclick = function(){ 
		oLay.style.display = "none"; 
		oWin.style.display = "none" 
	}; 
	oClose.onmousedown = function(event){ 
		(event || window.event).cancelBubble = true; 
	}; 
	oH2.onmousedown = function(event){ 
		var event = event || window.event; 
		bDrag = true; 
		disX = event.clientX - oWin.offsetLeft; 
		disY = event.clientY - oWin.offsetTop; 
		this.setCapture && this.setCapture(); 
		return false 
	}; 
	document.onmousemove = function(event){ 
		if (!bDrag) return; 
		var event = event || window.event; 
		var iL = event.clientX - disX; 
		var iT = event.clientY - disY; 
		var maxL = document.documentElement.clientWidth - oWin.offsetWidth; 
		var maxT = document.documentElement.clientHeight - oWin.offsetHeight; 
		iL = iL < 0 ? 0 : iL; 
		iL = iL > maxL ? maxL : iL; 
		iT = iT < 0 ? 0 : iT; 
		iT = iT > maxT ? maxT : iT; 

		oWin.style.marginTop = oWin.style.marginLeft = 0; 
		oWin.style.left = iL + "px"; 
		oWin.style.top = iT + "px"; 
		return false 
	}; 
	document.onmouseup = window.onblur = oH2.onlosecapture = function(){ 
		bDrag = false; 
		oH2.releaseCapture && oH2.releaseCapture(); 
	}; 
}
function subTaskBt(){
	if(document.getElementById("t3").value!=''){
		document.taskForm.action="taskManageAction_saveTask.action";
		document.taskForm.submit();
	}else{
		alert("任务名称不能为空");
	}
}