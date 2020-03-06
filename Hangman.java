package eg.edu.alexu.csd.datastructure.hangman.csX22;


public class Hangman {

	public static void main(String[] args) {
		play g = new play();
		String[] wordsfrmdic= new String[play.l];
		g.readfrmfile (wordsfrmdic);
		g.setDictionary(wordsfrmdic);
		g.game();
	}
	

}