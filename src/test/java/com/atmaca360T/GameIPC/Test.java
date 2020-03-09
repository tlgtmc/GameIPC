package com.atmaca360T.GameIPC;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

import com.atmaca360T.GameIPC.player.Player;
import com.atmaca360T.GameIPC.player.PlayerBuilder;
import com.atmaca360T.GameIPC.util.Constants;

@org.junit.FixMethodOrder
public class Test {

	static Player serverPlayer = null;
	static Player clientPlayer = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serverPlayer = new PlayerBuilder().setName(Constants.player2).initializeMessage().getPlayer();
		clientPlayer = new PlayerBuilder().setName(Constants.player1).getPlayer();
	}

	@org.junit.Test

	public void testPlayers() {
		assertEquals(Constants.player1, clientPlayer.getName());
		assertEquals(Constants.player2, serverPlayer.getName());
	}

	@org.junit.Test
	public void testMessageIncrease() {
		int serverPlayerMsgCount = serverPlayer.getMessageCount().intValue();
		int clientPlayerMsgCount = clientPlayer.getMessageCount().intValue();
		
		serverPlayer.increaseMessageCount();
		clientPlayer.increaseMessageCount();
		
		assertEquals(++serverPlayerMsgCount, serverPlayer.getMessageCount().intValue());
		assertEquals(++clientPlayerMsgCount, clientPlayer.getMessageCount().intValue());
	}

	@org.junit.Test
	public void testPlayerMessage() {
		clientPlayer.getResponseFor(serverPlayer);
		serverPlayer.getResponseFor(clientPlayer);

		assertEquals("Greetings buddy" + " 1", clientPlayer.getMessage());
		assertEquals("Greetings buddy" + " 1 1", serverPlayer.getMessage());
	}
}
