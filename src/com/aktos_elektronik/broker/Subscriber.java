package com.aktos_elektronik.broker;

import org.zeromq.ZMQ;

public class Subscriber implements Runnable{

	
	public void run() {
		
		ZMQ.Context subsCnt = ZMQ.context(1);
		ZMQ.Socket subsSck = subsCnt.socket(ZMQ.SUB);
		subsSck.setRcvHWM(1);
		subsSck.setLinger(0);
		subsSck.subscribe("".getBytes());
		subsSck.connect("tcp://localhost:5013");
		
		while(true){
			try {
				byte[] rcv = subsSck.recv(0);
				String rcvStr = new String(rcv, "UTF-8");				
				System.out.println("Got message : "+ rcvStr);
			
				//Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
