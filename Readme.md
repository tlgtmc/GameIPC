#Problem:
Given a Player class - an instance of which can communicate with other Players.

#####The requirements are as follows:

1. Create 2 Player instances
2. One of the players should send a message to second player (let's call this player "initiator")
3. When a player receives a message, it should reply with a message that contains the received message concatenated with the value of a counter holding the number of messages this player already sent.
4. Finalize the program (gracefully) after the initiator sent 10 messages and received back 10 messages (stop condition)
5. Both players should run in the same java process (strong requirement)
6. Document for every class the responsibilities it has.
7. Additional challenge (nice to have) opposite to 5: have every player in a separate JAVA process.


#Class Definitions:

#####BaseApi: 
 * BaseApi is the Abstract Class for Client and Server classes. 
 * Common objects and methods are placed in this class.
	
#####Client:
 * Client side application extends to the BaseApi Abstract Class.
 * 
 * This application checks if there is available host, if yes, then establishes the connection.
 * Once it's connected to a server, it creates a player object and sends the first message.
 * 
 * Afterwards, it starts listening, if receives any inputs from the server.  
 
#####Server:
 * Server side application extends to the BaseApi Abstract Class.
 * 
 * This application creates host for the messaging.
 * After creating the host, it starts to listen inputStream. 
 * Once client connected and sent first message, application continues messaging.
 
#####Player:
 * Serializable Player class which has name, messageCount, message variables.
 
#####PlayerBuilder:
 * Builder Design Pattern applied to initialize a new user. 
 
#####Constants:
 * Constants class for the constant values. 

#####App:
 * Start point of the application. Creates Server and Client instances
 
 
 
#How to run?

First of all create a package by running below command:

    mvn package -Dmaven.test.skip=true

Then run sh file. It will launch two instances of the application. First one will be the server, and the second one will be the client. 

    .\run.sh

After execution finishes, press any key to exit.
