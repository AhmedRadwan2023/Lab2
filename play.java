package eg.edu.alexu.csd.datastructure.hangman.csX22;

import eg.edu.alexu.csd.datastructure.hangman.IHangman;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;



public class play implements IHangman {
 

    public  static int l = 1100; 
	String [] word = new String [l];
	int maxWrong;
	char[] initialword = new char [20];
	String secWord;
	
	@Override
	public void setDictionary(String[] words) {
		word = words;
	}

	@Override
	public String selectRandomSecretWord() {
		int r =(int)(Math.random()*l );
		return word[r];
	}

	@Override
	public String guess(Character c) throws Exception {
		int temp;
		String tempWord;
		temp = secWord.indexOf(c);
		if (this.buggyWords(secWord)) {//checking is the secret word from the dictionary is a buggy word
			throw new Exception("this is a buggy word");
		}
		if (!Character.isLetter(c)) {//check if the user didn't enter an english character
			System.out.println("you must enter an english character");
		}
		else if (temp==-1) {
			maxWrong--;
		}
		if (maxWrong <= 0) {
			return null;
		}
		while (temp!=-1) {
			initialword[temp]=c;
			temp = secWord.indexOf(c,temp+1);
		}
		tempWord=new String(initialword).substring(0,secWord.length());
		return tempWord;
	}

	@Override
	public void setMaxWrongGuesses(Integer max) {
		maxWrong=max;
	}
	
	
	public void readfrmfile (String a[]) {
		try {
                    try (BufferedReader f = new BufferedReader (new FileReader ("words.txt"))) {
                        for (int i=0;i< l ;i++) {
                            a[i]= f.readLine();
                        }
                    }
		} catch (FileNotFoundException e) {
			System.out.println("the dictionary file doesn't exist");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}


    public void game () {
		char check = 'y',c;
            try (Scanner s = new Scanner(System.in)) {
                int x;
                String finalWord = null;
                while (check=='y') {
                    x=0;
                    for (int i=0;i<20;i++)initialword[i]='-';
                    secWord=this.selectRandomSecretWord();
                    this.setMaxWrongGuesses(10);
                    for (int i=0;i<20;i++)System.out.println();
                    while (true) {
                        if (x==0) {
                            try {
                                for (int i=0;i<secWord.length();i++)System.out.print('-');
                                System.out.println("\t\t"+maxWrong+" Attempts Left");
                                x++;
                            }catch (Exception e) {
                                System.out.println("error in choosing the word from dictionary please insert the correct dictionary file then play again");
                                break;
                            }
                        }
                        
                        System.out.println(" enter a character:");
                        c=s.next().toLowerCase().charAt(0); 
                        for (int i=0;i<50;i++)System.out.println();
                        try {
                            finalWord=this.guess(c);
                        } catch (Exception e) {
                        }
                        System.out.println(finalWord +"\t\t"+maxWrong+" Attempts Left");
                        if (finalWord==null) {
                            System.out.println("\n\nGame Over \nthe secret word was : "+ secWord +"\nYou lose the game !!!!!!!Try again to win\n");break;
                        }
                        else if (finalWord.equals(secWord)){
                            System.out.println("\nWow !!!!!! you won the game");break;
                        }
                    }
                    System.out.println("Do you want to play again ? [y,n]");
                    check=s.next().charAt(0);
                }
            }
	}
	
	public boolean buggyWords(String buggy) {
		for (int i=0;i<buggy.length();i++) {
			if (!Character.isLetter(buggy.charAt(i)) && buggy.charAt(i)!='-') {
				return true;
			}
		}
		return false ;
	}
}