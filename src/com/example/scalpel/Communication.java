package com.example.scalpel;

public class Communication{
	static String hand[]=new String[]{"ִ��ʽ","�ճ�ʽ","ִ��ʽ","����ʽ"};
	static int handid;
	static double vetical,angle,force,leng,bend;
	static long tmstart,tmend;
	static int getHandid(){
		return 0; 
	}
	static int getAngle(){
		return 45;
	}
	static int getForce(){
		return 100;
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
	static void setVetical(double tmp){
		vetical=tmp;
	}
	static void setAngle(double tmp){
		angle=tmp;
	}
	static void setForce(double tmp){
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

}