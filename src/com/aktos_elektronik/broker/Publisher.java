/**
 * @author: Canburak Tümer
 * @version: 20150830.02
 * @description : This class is a publisher for ZeroMQ messages.
 * It send messages which change state of a LED connected to a RaspberryPi running aktos-dcs.
 */
package com.aktos_elektronik.broker;

import org.zeromq.ZMQ;
import com.aktos_elektronik.json_ops.*;


public class Publisher implements Runnable {

	
	public void run() {
		ZMQ.Context pubCnt = ZMQ.context(1);
		ZMQ.Socket pubSck = pubCnt.socket(ZMQ.PUB);
		pubSck.setLinger(0);
		pubSck.setSndHWM(1);
		
		pubSck.connect("tcp://localhost:5012");
		
		// mesID -> Message ID to differentiate each message from the previous one.
		// Messages with same IDs will be discarded by aktos-dcs.
		int mesID = 0;
		
		while(true){
			java.util.Date date= new java.util.Date();
			 
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
		}
		
		
		
	}


}
