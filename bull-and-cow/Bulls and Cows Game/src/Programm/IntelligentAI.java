package Programm;

import java.util.ArrayList;
import java.util.Iterator;

public class IntelligentAI extends Computer {
    /**
     *This method is will remove the code that have been used before firstly, and than it will
     * iterate thorough all the the possible code with the computer guess and compare them with
     * the bulls and cows result we get before to find out all the possible code . If a number's
     * bulls and cows result doesn't match the previous result, it will be remove from the code list
     */
    @Override
    public String guessCode() {
        ArrayList<String> secretCodesList = getSecretCodesList();
//        System.out.println(secretCodesList.size());
//        uncomment this to test if the size of the list has changed
        String computerGuess = generateComputerCode();
        secretCodesList.remove(computerGuess);
        Iterator<String> codesIterator = secretCodesList.iterator();
        int[] bullsAndCows = BullsCowsGameApp.checkBullsAndCows(Player.getSecretCode(), computerGuess);
        int bulls = bullsAndCows[0];
        int cows =bullsAndCows[1];
        while (codesIterator.hasNext()){
            int[] possibleBullsAndCows = BullsCowsGameApp.checkBullsAndCows(codesIterator.next(),
                    computerGuess);
            int possibleBulls = possibleBullsAndCows[0];
            int possibleCows = possibleBullsAndCows[1];
            if(!(bulls==possibleBulls&&cows==possibleCows)){
                codesIterator.remove();
            }
        }
        return computerGuess;
    }
}
