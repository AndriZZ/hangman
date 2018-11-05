
package Server;

public class Hangman {
	private ServerSideInfo serverSideInfo;
	private String chosenWord;
	private String clientGuess;
	private Server2Client server2Client;
	public enum gameMessages {
	letterNotExist = "Letter does not exist";
	 youLost = "You lost!!!";
	 gameOver = "Game over!";
	letterAlreadyGuessed = "Letter has already been guessed.";
	emptyMessage = "Click on a letter first!";
	tryAgain = "Try another one!";
	 youWin = "You win!!!";
	 gameWon = "Game won!";
	 correctLetter = "Letter correct!";
	 keepGuessing = "Keep guessing";
	}
//make classes not void + enum of string here
	public Hangman(String chosenWord, ServerSideInfo serverSideInfo, Client2Server client2Server,
			Server2Client server2Client) {
		this.serverSideInfo = serverSideInfo;
		this.chosenWord = chosenWord;
		this.server2Client = server2Client;
		this.clientGuess = client2Server.clientword;

	}

	private Server2Client calculate() {

		if (clientGuess.isEmpty()) {
			calculateEmptyMessage();
		} else if (clientGuess.length() == 1) {
			server2Client = calculateLetter();
		}
		return new Server2Client(server2Client);
	}

	private void calculateEmptyMessage() {
		server2Client.message = "";
		serverSideInfo.info = emptyMessage;
	}

	private Server2Client calculateLetter() {
		if (chosenWord.indexOf(clientGuess) == -1) {
			calculateLetterNotPresent();

		}
		if ((server2Client.word.toString()).indexOf(clientGuess) != -1) {
			calculateletterAlreadyPresent();

		} else {
			calculateLetterPresent();
		}
		return server2Client;
	}

	private void calculateLetterNotPresent() {
		server2Client.message = letterNotExist;
		server2Client.failAttempts--;
		serverSideInfo.info = String.valueOf(server2Client.failAttempts) + " attempts are left!";
		if (server2Client.failAttempts == 0) {
			server2Client.message = youLost;
			server2Client.word = chosenWord;// server reveals to client the actual word
			serverSideInfo.info = gameOver;
			serverSideInfo.games++;
			server2Client.score = 0;

		}

	}

	private void calculateletterAlreadyPresent() {
		server2Client.message = letterAlreadyGuessed;
		serverSideInfo.info = tryAgain;
	}

	private void calculateLetterPresent() {
		char[] c = new char[clientGuess.length()];
		c = clientGuess.toCharArray();
		for (int i = 0; i < chosenWord.length(); i++) {
			if (String.valueOf(chosenWord.charAt(i)).equalsIgnoreCase(String.valueOf(c[0]))) {
				StringBuilder sb = new StringBuilder(server2Client.word);
				sb.setCharAt(i, c[0]);
				server2Client.word = sb.toString();
				if (server2Client.word.indexOf("_") == -1) {// No underscore -> word is whole!
					calculateWordCorrect();
				} else {
					calculateLetterCorrect();
				}
			}

		}
	}

	private void calculateWordCorrect() {
		server2Client.word = chosenWord;
		server2Client.message = youWin;
		serverSideInfo.info = gameWon;
		server2Client.score++;
		serverSideInfo.games++;
	}

	private void calculateLetterCorrect() {
		server2Client.message = correctLetter;
		serverSideInfo.info = keepGuessing;
	}

}