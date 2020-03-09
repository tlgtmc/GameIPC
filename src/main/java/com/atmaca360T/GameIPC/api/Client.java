package com.atmaca360T.GameIPC.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import com.atmaca360T.GameIPC.player.Player;
import com.atmaca360T.GameIPC.player.PlayerBuilder;
import com.atmaca360T.GameIPC.util.Constants;
/**
 * Client side application extends to the BaseApi Abstract Class.
 * 
 * This application checks if there is available host, if yes, then establishes the connection.
 * Once it's connected to a server, it creates a player object and sends the first message.
 * 
 * Afterwards, it starts listening, if receives any inputs from the server.  
 *  
 * @author TCTATMACA
 *
 */
public class Client extends BaseApi {

	/**
	 * No-args constructor of Client() class. 
	 * Checks for a server, if there is one, establishes the connection. 
	 * After initializing a player instance, it starts messaging by sending first message.
	 * 
	 * Then, it starts listening input stream. In case of any inputs, application will go on by replying user.
	 * @throws ConnectException 
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Client() throws ConnectException {
		super();
		try (Socket socket = new Socket(Constants.hostname, Constants.port);
			 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());){

			player = new PlayerBuilder().setName(Constants.player2).initializeMessage().getPlayer();

			print("Server found");
			print("Initializing Client");
			print("Client Connected!");

			player.getResponseFor(player);
			print("Sending From Client: " + player.getMessage());
			os.writeObject(player);

			chat(player, is, os);
		} catch (ConnectException e) {
			throw new ConnectException();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method listens inputStream. If there are any available inputs, 
	 * then it checks whether players reached the aimed messageCount or not.
	 * 
	 *  If message count reached, application finalization starts. 
	 *  
	 *  If not, chat goes on. 
	 */
	@Override
	void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException {
		while ((sender = (Player) is.readObject()) != null) {
			if (sender.getMessageCount().intValue() == Constants.messageCount && player.getMessageCount().intValue() == Constants.messageCount) {
				super.exitApplication();
			}
			player.getResponseFor(sender);
			print("Sending From Client: " + player.getMessage());
			os.reset();
			os.writeObject(player);
		}
	}
}
