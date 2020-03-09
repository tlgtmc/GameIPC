package com.atmaca360T.GameIPC.player;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * PlayerBuilder class.
 * Builder Design Pattern applied to initialize a new user. 
 * @author TCTATMACA
 *
 */
public class PlayerBuilder {

	private String name;
	private AtomicInteger messageCount = new AtomicInteger(0);
	private String message;
	
	public PlayerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public PlayerBuilder setMessageCount(int messageCount) {
		this.messageCount.set(messageCount);
		return this;
	}
	
	public Player getPlayer() {
		Player player = new Player(name, messageCount, message);
		return player;
	}

	public PlayerBuilder initializeMessage() {
		this.message = "Greetings buddy";
		return this;
	}
}
