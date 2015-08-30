/**
 * @author: Canburak Tümer
 * @version: 20150830.02
 * @description : Main class for running publisher and subscriber threads.
 */

package com.aktos_elektronik.broker;

public class Broker {

	public static void main(String[] args) {
		
		Publisher pubs = new Publisher();
		Subscriber subs = new Subscriber();
		
		Thread pubsThread = new Thread(pubs, "Pubs Thread");
		Thread subsThread = new Thread(subs, "Subs Thread");
		
		pubsThread.start();
		subsThread.start();

	}

}
