package com.example.scalpel;

public class Communication{
	static String hand[]=new String[]{"执弓式","握持式","执笔式","反挑式"};
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