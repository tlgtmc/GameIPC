package com.atmaca360T.GameIPC.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.atmaca360T.GameIPC.player.Player;

/**
 * 
 * @author TCTATMACA
 *
 * BaseApi is the Abstract Class for Client and Server classes. 
 * Common objects and methods are placed in this class.
 * 
 */
public abstract class BaseApi {

	Socket socket = null;
	Player player = null;
	Player sender = null;

	/**
	 * Each side (server, client) has it's own implementation on chat. 
	 * The main difference will be the count check.
	 * @param player : This class gets a player object to get the message, messageCount etc..
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 */
	abstract void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException;

	/**
	 * This method is common for both client and server side. 
	 * Closes the streams and finalizes the application.
	 * 
	 * @param os : Stands for ObjectOutputStream object
	 * @param is : Stands for ObjectInputStream object
	 * @throws IOException
	 */
	protected void exitApplication() throws IOException {
		System.out.println("Finalizing the game, thank you for playing...");
		System.exit(0);
	}

	/**
	 * Common print method.
	 * Instead of writing System.out.println everywhere, this is a simplified solution
	 * 
	 * @param s
	 */
	protected void print(String s) {
		System.out.println(s);
	}
}
