/**
 * @author: Canburak Tümer
 * @version: 20150830.02
 * @description : This class is a publisher for ZeroMQ messages.
 * It send messages which change state of a LED connected to a RaspberryPi running aktos-dcs.
 */
package com.aktos_elektronik.broker;

import org.zeromq.ZMQ;
import com.aktos_elektronik.json_ops.*;
import com.aktos_elektronik.params.*;

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
		pubSck.connect("tcp://"+Params.getHost()+":"+Params.getPubPort());
		date= new java.util.Date();
		
		
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
