package com.example.scalpel;

public class Communication{
	static String hand[]=new String[]{"","执弓式","握持式","执笔式","反挑式"};
	static int handid,stdhandid;
	static int vetical,angle,force,stdangle,stdforce,stdtime;
	static double leng,stdleng;
	static long tmstart,tmend;
	static boolean receive;
	static int nownum;
	static double marhand,marvetical,marangle,marforce,marleng,martotal,martime;
	static double[] marhandtp=new double[5];
	static void ReceiveStart(){
		receive=true;
		nownum=0;
		for(int i=1;i<=4;i++) marhandtp[i]=0;
		marhand=0;
		marvetical=0;
		marangle=0;
		marforce=0;
		marleng=0;
		martime=0;
	}
	static void ReceiveEnd(){
		receive=false;
	}
	static boolean getReceive(){
		return receive;
	}
	static int getHandid(){
		return handid; 
	}
	static int getAngle(){
		return angle;
	}
	static int getForce(){
		return force;
	}
	static String getHand(){
		return hand[getHandid()];
	}
	static double getLeng(){
		return leng;
	}
	static void setHandid(int tmp){
		handid=tmp;
	}
	static void setVetical(int tmp){
		vetical=tmp;
	}
	static void setAngle(int tmp){
		angle=tmp;
	}
	static void setForce(int tmp){
		force=tmp;
	}
	static void setLeng(double tmp){
		leng=tmp;
	}
	static void setTmstart(long tmp){
		tmstart=tmp;
	}
	static void setTmend(long tmp){
		tmend=tmp;
	}
	static void setStdHand(int tmp){
		stdhandid=tmp;
	}
	static void setStdAngle(int tmp){
		stdangle=tmp;
	}
	static void setStdForce(int tmp){
		stdforce=tmp;
	}
	static void setStdLength(double tmp){
		stdleng=tmp;
	}
	static void setStdTime(int tmp){
		stdtime=tmp;
	}
	static void addnownum(){
		nownum++;
	}
	static void addHand(int _id,int tmp){
		marhandtp[_id]+=tmp;
		for(int i=1;i<=4;i++)
			marhand=Math.max(marhand, marhandtp[i]/nownum);
	}
	static void addVetical(int tmp){
		double tp=100-Math.max(0, Math.floor(Math.abs(tmp-90)-2));
		marvetical=(marvetical*(nownum-1)+tp)/nownum;
	}
	static void addAngle(int tmp){
		double tp=100-Math.max(0,Math.floor(Math.abs(tmp-stdangle)-2));
		marangle=(marangle*(nownum-1)+tp)/nownum;
	}
	static void addForce(int tmp){
		double tp=100-Math.max(0,Math.floor((Math.abs(tmp-stdforce)-20)/2));
		marforce=(marforce*(nownum-1)+tp)/nownum;
	}
	static void addLeng(double tmp){
		marleng=100-Math.max(0,(Math.floor((Math.abs(tmp-stdleng)-0.5)/0.1)));
	}
	static void addTime(){
		martime=100-Math.max(0,(Math.floor((Math.abs((tmend-tmstart-2000)/100-stdtime*10)-10)/1)));
	}
	static void cal(){
		martotal=(marhand*0.6+marvetical*0.2+marangle*0.2)*0.5+(marforce*0.5+marleng*0.5)*0.3+martime*0.2;
	}
}