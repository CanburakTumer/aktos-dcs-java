/**
 * @author: Canburak Tümer
 * @version: 20150830.02
 * @description : This class is a publisher for ZeroMQ messages.
 * It send messages which change state of a LED connected to a RaspberryPi running aktos-dcs.
 */
package com.aktos_elektronik.broker;

import org.zeromq.ZMQ;
import com.aktos_elektronik.json_ops.*;

import java.util.Date;
import java.util.UUID;


public class Publisher implements Runnable {
	ZMQ.Context pubCnt;
	ZMQ.Socket pubSck ;	
	UUID senderID;
	//int mesID;
	Date date;
	
	public void run() {
		pubCnt = ZMQ.context(1);
		pubSck = pubCnt.socket(ZMQ.PUB);
		pubSck.setLinger(0);
		pubSck.setSndHWM(1);
		senderID = UUID.randomUUID();
		//mesID = 0;		
		pubSck.connect("tcp://localhost:5012");
		date= new java.util.Date();
		
		/*
		while(true){
			
			 
			// message to change LED on.
			String msgT = new JSONObject("{sender: [canburak], timestamp: "+ date.getTime() +", msg_id: canburak"+ mesID +", payload: {IoMessage: { val: true, pin_name: green-led } }}").toString() ;
			mesID++;
			// message to change LED off.
			String msgF = new JSONObject("{sender: [canburak], timestamp: "+ date.getTime() +", msg_id: canburak"+ mesID +", payload: {IoMessage: { pin_name: green-led, val: false} }}").toString();
			
			pubSck.send(msgT);
			System.out.println(msgT);
			try {
				// sleep a second for observing LED is on.
				Thread.sleep(1000);
				pubSck.send(msgF);
				System.out.println(msgF);
				// sleep a second for observing LED is off.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mesID++;
		}		*/
		
	}
	
	public void sendMessage(String pin_name, String value){
		String message = "{sender: [";
		message += senderID;
		message += "], timestamp: ";
		message += date.getTime();
		message += ", msg_id: ";
		message += senderID+""+(Math.random()*1000);
		message += ", payload: {IoMessage: {pin_name: ";
		message += pin_name;
		message += ", val: ";
		message += value;
		message += "}}}";
		
		pubSck.send(new JSONObject(message).toString());
		
	}
	
}
