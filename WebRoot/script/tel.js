function popEditTel(s,t1,t2){
	//title
	var telt = document.getElementById("tel-title");
	telt.innerHTML="";
	document.getElementById("teltitle").innerHTML="";
	//clear
	document.getElementById("t1").value='';
	if(s=="edit"){
		telt.innerHTML="<font color='#fff'>修改号码信息</font>";
		document.getElementById("teltitle").innerHTML="号码:";
		document.getElementById("eflag").value=1;
		document.getElementById("t0").value=t1;
		document.getElementById("t1").value=t2;
	}else{
		telt.innerHTML="<font color='#fff'>添加号码信息</font>";
		document.getElementById("teltitle").innerHTML="号码列表(多个号码用,隔开):";
		document.getElementById("eflag").value=0;
	}
	
	
	var oWin = document.getElementById("win-tel");
	var oLay = document.getElementById("overlay-tel");
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
function subTelBt(){
	if(document.getElementById("t1").value!=''){
		document.telForm.action="taskManageAction_saveTel.action";
		document.telForm.submit();
	}else{
		alert("号码不能为空");
	}
	
}