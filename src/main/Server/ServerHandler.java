package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import java.util.HashMap;
import java.util.List;

import Server.DictionaryDealer;
import Server.Hangman;
import Server.Client2Server;
import Server.StateClass;

class ServerHandler extends Thread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	HashMap<String, List<String>> hashMap;
	ServerSideInfo serverSideInfo = new ServerSideInfo();
	Server2Client server2Client = new Server2Client();// server's response to client
	Client2Server client2server = new Client2Server();

	boolean shouldBreak = false;
	String chosenWord = ""; // -->don't forget to get it from StateClass method

	public ServerHandler(Socket socket) throws IOException {

		this.clientSocket = socket;
		server2Client.setState(Server2Client.State.START);
	}

	@Override
	public void run() {

		Hangman hangman;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();

		}
		while (true) {
			try {
				client2server = (Client2Server) in.readObject();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				break;
			} catch (IOException e1) {
				e1.printStackTrace();
				break;
			}
			Server2Client response = new Server2Client();
			StateClass stateClass = new StateClass(server2Client, client2server);

			if (server2Client.getState() == Server2Client.State.START) {

				hashMap = stateClass.serverStartState(serverSideInfo, server2Client);

			}

			else if (server2Client.getState() == Server2Client.State.PICKCATEGORY) {
				stateClass.PickActionValidator(hashMap, client2server.getclientword(), shouldBreak, response);
				if (shouldBreak == true) {
					try {
						out.writeObject(response);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				chosenWord = stateClass.serverPickCategoryState(hashMap, response, chosenWord);

			}

			else if (server2Client.gameStates == Server2Client.State.PLAY) {
				hangman = new Hangman(chosenWord, serverSideInfo, client2server, server2Client);
				response = stateClass.serverPlayState(hangman, response, serverSideInfo);
			}

			try {
				out.writeObject(response);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

		}

	}

}
