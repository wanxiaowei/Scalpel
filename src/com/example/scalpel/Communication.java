package com.example.scalpel;

public class Communication{
	static String hand[]=new String[]{"执弓式","握持式","执笔式","反挑式"};
	static int handid,stdhandid;
	static int vetical,angle,force,stdvetical,stdangle,stdforce;
	static double leng,bend,stdleng;
	static long tmstart,tmend;
	static boolean receive;
	static int nownum;
	static double marhand,marangle,marforce,marleng,martotal;
	static void ReceiveStart(){
		receive=true;
		nownum=0;
		marhand=0;
		marangle=0;
		marforce=0;
		marleng=0;
	}
	static void ReceiveEnd(){
		receive=false;
	}
	static boolean getReceive(){
		return receive;
	}
	static int getHandid(){
		handid=0;
		return handid; 
	}
	static int getAngle(){
		angle=45;
		return angle;
	}
	static int getForce(){
		force=100;
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
	static void setBend(double tmp){
		bend=tmp;
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
	static void addnownum(){
		nownum++;
	}
	static void addHand(int tmp){
		marhand=(marhand*(nownum-1)+tmp)/nownum;
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
	static void cal(){
		martotal=(marleng+marhand+marangle+marforce)/4;
	}
}