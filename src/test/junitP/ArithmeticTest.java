package junitP;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import Server.Client2Server;
import Server.Hangman;
import Server.Server2Client;
import Server.ServerSideInfo;
import Server.DictionaryDealer;

@RunWith(Parameterized.class)
public class ArithmeticTest {
	// private int firstNumber;
	// private int secondNUmber;
//	private int expectedResult;
//	private Arithmetic arithmetic;
	private DictionaryDealer textfile;
	// private String word;
	String expectedString;
	String chosenWord = "";
	Server2Client serverData = new Server2Client();
	Client2Server client2Server = new Client2Server();
	Server2Client server2Client = new Server2Client();
	ServerSideInfo serverSideInfo = new ServerSideInfo();

	Hangman hangman;
	// Hangman hangman = new Hangman(chosenWord, serverSideInfo, client2Server,
	// server2Client);

	// String charOfWord1,String charOfWord2,String charOfWord3,String
	// charOfWord4,String charOfWord5,String charOfWord6
	public ArithmeticTest(String expectedWord) {
		super();
		// this.expectedString=expectedString;
		// this.word = word;
		// this.serverData = serverData;
		// this.client2server = client2server;
		// this.server2Client = server2Client;
		/*
		 * Hangman hangman = new Hangman(chosenWord, serverSideInfo, client2Server,
		 * server2Client);
		 * 
		 * server2Client.setWord(word); client2Server.setclientword("P");
		 * 
		 * char[] letters = { 'a', 'e', 'o' }; for (char letter : letters) { chosenWord
		 * = chosenWord + letter; hangman.calculate();
		 * 
		 * }
		 */

		// client2server.clientword =
		// charOfWord1+charOfWord2+charOfWord3+charOfWord4+charOfWord5+charOfWord6;
		chosenWord = expectedWord;
		// server2Client.setWord(expectedWord);
		// client2Server.setclientword(word);

	}

	@Before
	public void initialize() {
		// arithmetic = new Arithmetic();
		// textfile = new DictionaryDealer();
		// serverData = new Server2Client();
		// server2Client = new Server2Client();
		// client2server = new Client2Server();
		// hangme = new Hangme();
		client2Server.setclientword("");
		hangman=new Hangman(chosenWord, serverSideInfo, client2Server, server2Client);

	}

	@Parameterized.Parameters
	public static Collection input() {
		return Arrays.asList(new Object[][] { { "Potato" } });

		// new Object[][] { { "Potato","P","o","t","a","o" }, { "Penguin",
		// "P","e","n","g","u","i","n" }, { "Orange", "u","r","a","n","g","e" } });

	}

	@Test
	public void testArithmeticTest() {
		// System.out.println("Sum of Numbers = :"+expectedResult);
		// assertEquals(expectedResult, arithmetic.sum(firstNumber, secondNUmber));
		// assertEquals(expectedString, textfile.dashme(word));
		// String k =hangman("Potato",serverSideInfo, client2Server, server2Client);
		// String k = hangman.calculate().getWord();
		// System.out.println(k + " word");


		// server2Client.setWord(word);
		// client2Server.setclientword("P");

		char[] letters = { 'P', 'o', 't', 'a', 't', 'o' };
		for (char letter : letters) {
			client2Server.setclientword(client2Server.getclientword() + letter);
			serverData = hangman.calculate();

		}

		assertEquals(serverData.getWord(), chosenWord);
	}

}
