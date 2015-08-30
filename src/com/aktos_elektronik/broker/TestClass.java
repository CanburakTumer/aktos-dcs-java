/**
 * @author: Canburak Tümer
 * @version: 20150830.02
 * @description : Main class for running publisher and subscriber threads.
 */

package com.aktos_elektronik.broker;

import java.util.Scanner;
import com.aktos_elektronik.params.*;

public class TestClass {

	public static void main(String[] args) {
		
		Publisher pubs = new Publisher();
		Subscriber subs = new Subscriber();
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		int choice;
		String value;
		
		Thread pubsThread = new Thread(pubs, "Pubs Thread");
		Thread subsThread = new Thread(subs, "Subs Thread");
		
		System.out.println("Input Hostname : ");
		Params.setHost(input.nextLine());
		System.out.println("Publisher Port : ");
		Params.setPubPort(input.nextLine());
		System.out.println("Subscriber Port : ");
		Params.setSubPort(input.nextLine());
		
		pubsThread.start();
		subsThread.start();
				
		while(true){
			System.out.println("Choose message to send and input selection number: \n"
					+"1. Green Led ON\n" +
					"2. Green Led OFF\n" +
					"3. Test Pin ON\n" +
					"4. Test Pin OFF\n" +
					"5. Piston enab ON\n" +
					"6. Piston enab OFF\n" +
					"7. Graph Slider Value Set\n" +
					"8. Group Slider Value Set\n" +
					"9. Analog 1 Value Set\n" +
					"0. Analog 2 Value Set\n");
			choice = input.nextInt();
			switch(choice){
				case 1:
					pubs.sendMessage("green-led", "true");
					break;
				case 2:
					pubs.sendMessage("green-led", "false");
					break;
				case 3:
					pubs.sendMessage("test-pin", "true");
					break;
				case 4:
					pubs.sendMessage("test-pin", "false");
					break;
				case 5:
					pubs.sendMessage("piston.enab", "true");
					break;
				case 6:
					pubs.sendMessage("piston.enab", "false");
					break;
				case 7:
					System.out.println("Enter a value (0-1000) : ");
					value = input2.nextLine();
					pubs.sendMessage("slider-2", value);
					break;
				case 8:
					System.out.println("Enter a value (0-1000) : ");
					value = input2.nextLine();
					pubs.sendMessage("slider-1", value);
					break;
				case 9:
					System.out.println("Enter a value (0-1000) : ");
					value = input2.nextLine();
					pubs.sendMessage("analog-1", value);
					break;
				case 0:
					System.out.println("Enter a value (0-1000) : ");
					value = input2.nextLine();
					pubs.sendMessage("analog-2", value);
					break;
			}
			}

	}

}
