package cn.voicet.gc.util;

/**
 * @see 导航路径工具类
 */
public class SubPathTitle {
	
	private final int MAXSUBITEMNUM = 16;
	private SubPathItem[] subPathItem;
	private int curPos;
	
	public SubPathTitle(){
		curPos = 0;
		subPathItem = new SubPathItem[MAXSUBITEMNUM];
		for (int i=0; i<MAXSUBITEMNUM; i++) {
			subPathItem[i] = new SubPathItem();
		}
	}
	
	/** 初始化 */
	public void initPath(){
		curPos=0;
	}
	
	private class SubPathItem {
		public String sName;
		public String sExt;
		public String stag;//
		
		public SubPathItem() {
			sName = "";
			sExt = "";
		}
	}
	
	public String getCurKey(){
		if(curPos>0){
			return subPathItem[curPos-1].stag;
		}
		return "";
	}
	
	public void setRoot(String sName, String sExt,String stag) {
		setCurInfo(sName, sExt,stag);
		curPos = 1;
	}
	
	public boolean hasRoot() {
		return curPos>0;
	}
	
	private void setNextInfo(String sName, String sExt,String stag) {
		if (curPos < MAXSUBITEMNUM) {
			setCurInfo(sName, sExt,stag);
			curPos++;
		}
	}

	public void setInfoByEx(String sName,String sEx,String stag) {
		boolean bFound=false;
		for (int i=0; i<curPos; i++) {
			if (subPathItem[i].sExt.equals(sEx)) {
				subPathItem[i].sName=sName;
				curPos = i + 1;
				bFound=true;
			}
		}
		if(!bFound) {
			setNextInfo(sName,sEx,stag);
		}
	}

	/** 页面返回 */
	public void rollBack() {
		if(curPos>1)
			curPos--;
	}
	
	public void setCurInfo(String sName, String sExt,String stag) {
		subPathItem[curPos].sName = sName;
		subPathItem[curPos].sExt = sExt;
		subPathItem[curPos].stag=stag;
	}

	/** 导航路径 */
	public String getHtmlString() {
		String navPath = "";
		for (int i = 0; i < curPos; i++) {
			navPath += "<a href='govBrowerAction_viewArea.do?viewBM="
					+ subPathItem[i].sExt + "'";
			navPath += " style='cursor:pointer; color:#fff' target='mainFrame' class='toolbar_eixt'>";
			navPath += subPathItem[i].sName;
			navPath += "</a>";
			if (i < curPos - 1) {
				navPath += ">>";
			}
		}
		return navPath;
	}

	public void setCurPos(int iPos) {
		if (iPos > 0 && iPos < MAXSUBITEMNUM && iPos <= curPos) {
			curPos = iPos;
		}
	}
	
}
