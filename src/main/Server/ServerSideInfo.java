package Server;

import java.io.Serializable;

public class ServerSideInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7600353571804851515L;
	protected int games;// total games played
	protected String info;// hint message
	protected String hashCategory;
	protected String dictionaryFile;
	
	public String getHashCategory() {
		return hashCategory;
	}


	public void setHashCategory(String hashCategory) {
		this.hashCategory = hashCategory;
	}
	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	public String getDictionaryFile() {
		return dictionaryFile;
	}

	public void setDictionaryFile(String dictionaryFile) {
		this.dictionaryFile = dictionaryFile;
	}

}
