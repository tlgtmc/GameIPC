package com.atmaca360T.GameIPC.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.atmaca360T.GameIPC.player.Player;
import com.atmaca360T.GameIPC.player.PlayerBuilder;
import com.atmaca360T.GameIPC.util.Constants;

/**
 * Server side application extends to the BaseApi Abstract Class.
 * 
 * This application creates host for the messaging. After creating the host, it
 * starts to listen inputStream. Once client connected and sent first message,
 * application continues messaging.
 * 
 * @author TCTATMACA
 *
 */
public class Server extends BaseApi {

	ServerSocket serverSocket;

	/**
	 * No-args constructor of Server() class. Creates host and waits for any client
	 * connection & input.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Server() {
		super();
		try (ServerSocket serverSocket = new ServerSocket(Constants.port);
			 Socket socket = serverSocket.accept();
			 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());) {

			player = new PlayerBuilder().setName(Constants.player1).getPlayer();

			print("Server Initialized");

			chat(player, is, os);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method listens inputStream. If there are any available inputs, Once it
	 * receives any inputs from client side, it manipulates the message and publish
	 * reply.
	 * 
	 * After publishing the message, it checks whether players reached the aimed
	 * messageCount or not. If message count reached, application finalization
	 * starts.
	 * 
	 * If not, chat goes on.
	 */
	@Override
	void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException {
		while ((sender = (Player) is.readObject()) != null) {
			player.getResponseFor(sender);
			print("Sending From Server: " + player.getMessage());
			os.reset();
			os.writeObject(player);
			if (sender.getMessageCount().intValue() == Constants.messageCount && player.getMessageCount().intValue() == Constants.messageCount) {
				super.exitApplication();
			}
		}
	}
}
