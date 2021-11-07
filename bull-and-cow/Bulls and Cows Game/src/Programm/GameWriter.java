package Programm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static Programm.BullsCowsGameApp.checkBullsAndCows;
import static Programm.BullsCowsGameApp.printBullsAndCows;


public class GameWriter {

    /**
     *This method will return true if the user want to save the game history,otherwise will
     * return false
     */
    public static boolean ifSave(){
        System.out.println("Would you like to save your game history? Type in \"Y\" if you do, " +
                "Or other character if you don't");
        String confirmation = Utility.askInformation();
        return "Y".equalsIgnoreCase(confirmation);
    }

    /**
     *This method will print out the game history
     */
    public static void writeGameHistory(String computerSecretCode, int round,
                                        ArrayList<String> userGuessRecord,
                                        ArrayList<String> computerGuessRecord,boolean userWin,
                                        boolean computerWin){
        boolean confirm = ifSave();
        String fileName;
        if(confirm){
            System.out.println("Please enter a file name:");
            fileName = Utility.askInformation();
            try (PrintWriter gameWriter = new PrintWriter(new FileWriter(fileName+".txt"),true)) {
                gameWriter.println("Bulls & Cows game result");
                gameWriter.println("Your code: "+Player.getSecretCode());
                gameWriter.println(Computer.NAME+"'s code: "+computerSecretCode);
                for (int i = 0; i <round ; i++) {
                    gameWriter.println("---");
                    gameWriter.println("Turn "+(i+1));
                    //Here is to ensure the code is not null and list is not empty and the index
                    // of the code won't be larger than the available indexes
                    if (userGuessRecord.size()!=i&&!userGuessRecord.isEmpty()&&userGuessRecord.get(i)!=null){
                        gameWriter.println("You guessed: "+ userGuessRecord.get(i)+", scoring, "+
                                printBullsAndCows(checkBullsAndCows(computerSecretCode, userGuessRecord.get(i))));
                    }
                    if(computerGuessRecord.size()!=i&&!computerGuessRecord.isEmpty()&&computerGuessRecord.get(i)!=null){
                        gameWriter.println(Computer.NAME+" guessed: "+computerGuessRecord.get(i)+
                                ", scoring, "+ printBullsAndCows(checkBullsAndCows(Player.getSecretCode(),
                                computerGuessRecord.get(i))));
                    }
                }
                if (userWin){
                    gameWriter.println("You are a winner!!!Great job!!!(^o^)");
                }
                if (computerWin){
                    gameWriter.println(Computer.NAME + " beat you, :(");
                }
                if (!userWin&&!computerWin){
                    gameWriter.println("No one win. Did you quit?");
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            System.out.println("File saved.");
        }
    }


}
