package Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StateClass {

	// protected String chosenWord = "";
	protected String categories;
	protected HashMap<String, List<String>> hashMap;
	protected String filename;
	ServerSideInfo serverSideInfo = new ServerSideInfo();
	Server2Client server2Client = new Server2Client();// server's server2Client to client
	Client2Server client2server = new Client2Server();
	Selector selector = new Selector();
	Alternator alternator = new Alternator();
	DictionaryDealer dictionaryDealer = new DictionaryDealer();
	// boolean shouldBreak=false;

	StateClass(Server2Client server2Client, Client2Server client2server) {

		this.server2Client = server2Client;
		this.client2server = client2server;

	}

	HashMap<String, List<String>> serverStartState(ServerSideInfo serverSideInfo, Server2Client server2Client) {

		server2Client.score = 0;
		server2Client.failAttempts = 10;
		serverSideInfo.games = 0;
		server2Client.message = "";
		serverSideInfo.info = "";
		serverSideInfo.dictionaryFile = "";

		if (client2server.getclientword().endsWith(".txt")) {

			serverSideInfo.setDictionaryFile(client2server.getclientword());
			filename = serverSideInfo.getDictionaryFile();

			try {
				hashMap = selector.selectDictionary(alternator.readFileContent(filename));
				categories = dictionaryDealer.takeCategories(hashMap);
				serverSideInfo.setHashCategory(categories);
				server2Client.setMessage((serverSideInfo.getHashCategory()));
				server2Client.setState(Server2Client.State.PICKCATEGORY);
				server2Client.setWord("");
				server2Client.setState(Server2Client.State.PICKCATEGORY);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			server2Client.setMessage("Invalid dictionary");
		}
		return hashMap;
	}

	Server2Client categoryValidator(HashMap<String, List<String>> hashMap, String clientword, boolean shouldBreak,
			Server2Client server2Client) {
		try {

			int u = Integer.parseInt(clientword);
			if (u < 1 || u > hashMap.size()) {
				System.out.println("Invalid category");
				server2Client.setMessage("Invalid category number");
				shouldBreak = true;

			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid categoryCatch");
			server2Client.setMessage("Invalid dictionaryZZZZZ");
			shouldBreak = true;

		}
		return server2Client;
	}

	int userOptionGetter(Client2Server client2Server) {
		int userOption = Integer.valueOf(client2server.getclientword());
		return userOption;
	}

	String serverPickCategoryState(HashMap<String, List<String>> hashMap, Server2Client server2Client, String chosenWord) {
		int userOption = userOptionGetter(client2server);
		try {
			chosenWord = selector.selectCategory(hashMap, userOption);
			chosenWord = chosenWord.substring(1, chosenWord.length());
			System.out.println("Chosen word for client is: " + chosenWord);
			server2Client.word = dictionaryDealer.dashString(chosenWord);
			server2Client.setState(Server2Client.State.PLAY);
			server2Client.setState(Server2Client.State.PLAY);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chosenWord;

	}

	Server2Client serverPlayState(Hangman hangman, Server2Client server2Client, ServerSideInfo serverSideInfo) {
		if (serverSideInfo.info.equals("Game won!")) {
			server2Client.gameStates = Server2Client.State.PICKCATEGORY;
			server2Client.gameStates = Server2Client.State.PICKCATEGORY;
			serverSideInfo.setHashCategory(categories);
			server2Client.setMessage((serverSideInfo.getHashCategory()));

			serverSideInfo.info = "New game";
		} else if (serverSideInfo.info.equals("Game over!")) {
			server2Client.gameStates = Server2Client.State.PICKCATEGORY;
			server2Client.gameStates = Server2Client.State.PICKCATEGORY;
			serverSideInfo.setHashCategory(categories);
			server2Client.setMessage((serverSideInfo.getHashCategory()));
			server2Client.setFailAttempts(10);

			serverSideInfo.info = "New game";
		} else {
			server2Client = hangman.calculate();

		}
		return server2Client;
	}

}
