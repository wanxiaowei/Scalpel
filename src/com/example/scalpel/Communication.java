package com.example.scalpel;

public class Communication{
	static String hand[]=new String[]{"ִ��ʽ","�ճ�ʽ","ִ��ʽ","����ʽ"};
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

}