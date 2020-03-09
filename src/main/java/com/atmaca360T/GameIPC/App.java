package com.atmaca360T.GameIPC;

import java.net.ConnectException;

import com.atmaca360T.GameIPC.api.Client;
import com.atmaca360T.GameIPC.api.Server;

/**
 * 
 * Start point of the application.
 * 
 * @author TCTATMACA
 *
 */
public class App {

	/**
	 * The application first tries to connect to a server, If there is not a
	 * published server, then Client() class throw a ConnectException which is
	 * cathed by try-catch block to initialize a server.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			startClient();
		} catch (ConnectException e) {
			startServer();
		}
	}

	/**
	 * Method to create a Client() instance.
	 * 
	 * @throws ConnectException
	 */
	private static void startClient() throws ConnectException {
		print("Looking for a server");
		try {
			new Client();
		} catch (ConnectException e) {
			throw new ConnectException();
		}
	}

	/**
	 * Method to create a Server() instance.
	 * 
	 */

	private static void startServer() {
		print("Server could not found");
		print("Initializing a Server");
		new Server();
	}

	/**
	 * Instead of writing System.out.println, this is a simplified solution It gets
	 * the value for the given key from the bundle.
	 * 
	 * @param str
	 */
	private static void print(String str) {
		System.out.println(str);
	}
}
