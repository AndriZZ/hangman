package junitP;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Server.Client2Server;
import Server.Hangman;
import Server.Server2Client;
import Server.ServerSideInfo;

public class MyTester {
	public static void main(String[] args) throws IOException {
		String expectedString;
		String chosenWord = "Potato";
		Server2Client serverData = new Server2Client();
		serverData.setWord("");
		Client2Server client2Server = new Client2Server();
		Server2Client server2Client = new Server2Client();
		ServerSideInfo serverSideInfo = new ServerSideInfo();

		server2Client.setScore(0);
		server2Client.setFailAttempts(10);
		serverSideInfo.setGames(0);
		server2Client.setMessage("");
		serverSideInfo.setInfo("");
		serverSideInfo.setDictionaryFile("");
		server2Client.setWord("Potato");

		client2Server.setclientword("");

		char[] letters = { 'P', 'o', 't', 'a', 't', 'o' };
		for (char letter : letters) {
			Hangman hangman = new Hangman(chosenWord, serverSideInfo, client2Server, server2Client);
			client2Server.setclientword(client2Server.getclientword() + letter);
			serverData = hangman.calculate();

		}

		System.out.println((serverData.getWord().equals(chosenWord)));

	}
}
