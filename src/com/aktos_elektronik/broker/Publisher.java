package com.aktos_elektronik.broker;

import java.sql.Timestamp;

import org.zeromq.ZMQ;
import com.aktos_elektronik.json_ops.*;


public class Publisher implements Runnable {

	
	public void run() {
		ZMQ.Context pubCnt = ZMQ.context(1);
		ZMQ.Socket pubSck = pubCnt.socket(ZMQ.PUB);
		pubSck.setLinger(0);
		pubSck.setSndHWM(1);
		
		pubSck.connect("tcp://localhost:5012");
		int mesID = 0;
		
		while(true){
			java.util.Date date= new java.util.Date();
			 
			String msgT = new JSONObject("{sender: [canburak], timestamp: "+ date.getTime() +", msg_id: canburak"+ mesID +", payload: {IoMessage: { val: true, pin_name: green-led } }}").toString() ;
			mesID++;
			String msgF = new JSONObject("{sender: [canburak], timestamp: "+ date.getTime() +", msg_id: canburak"+ mesID +", payload: {IoMessage: { pin_name: green-led, val: false} }}").toString();
			/*pubSck.send("Canburak");
			System.out.println("Canburak");*/
			pubSck.send(msgT);
			System.out.println(msgT);
			try {
				Thread.sleep(1000);
				pubSck.send(msgF);
				System.out.println(msgF);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			mesID++;
		}
		
		
		
	}


}
