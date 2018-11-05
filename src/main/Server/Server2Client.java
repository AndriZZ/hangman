
package Server;

import java.io.Serializable;

public class Server2Client implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum State {
		START, PICKCATEGORY, PLAY
	}

	// private static final long serialVersionUID = -7386258182412348165L;
	protected String word;// dashes
	protected int failAttempts;// fail attempt counter
	protected int score;// score of the player
	protected String message;// replied message to the letter or word that client sends
	protected State gameStates;
	
	public void execute() {
	}

	public State getState() {
		return gameStates;
	}

	public void setState(State state) {
		this.gameStates = state;
	}

	public Server2Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Server2Client(Server2Client sToC) {
		super();
		this.word = sToC.word;
		failAttempts = sToC.failAttempts;
		this.score = sToC.score;
		this.message = sToC.message;
	}

	public int getFailAttempts() {
		return failAttempts;
	}

	public void setFailAttempts(int FailAttempts) {
		this.failAttempts = FailAttempts;
	}

	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	

}
