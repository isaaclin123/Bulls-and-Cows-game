package Programm;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class GuessReader  {

    /**
     *This method is to ask user's choice of manual or automatic guessing.
     */
    public static int askUserToReadGuess() {
        System.out.println("Now, please enter 1 if you like to enter guess manually, or 2 to " +
                "generate guesses automatically");
        String answer;
        while (true) {
            answer = Utility.askInformation();
            if (!(answer.equals("1")) && !(answer.equals("2"))) {
                System.out.println("Error,try again:");
            }else{
                return Integer.parseInt(answer);
            }
        }
    }

    /**
     * This method is to read the pre-written guess from files based on users' code length, if
     * the file does not exist or does not match the code length entered before, it will recurse
     * to the method itself again until the user to enter an correct file name
     */
    public static Deque<String> readGuessData(){
        String fileName;
        System.out.println("Please enter a file name for the random guess.\n"+
                "Type in \"FourDigitGuess\" if you code length is 4\n" +
                "Type in \"FiveDigitGuess\" if you code length is 5\n" +
                "Type in \"SixDigitGuess\" if you code length is 6\n ");
        fileName=Utility.askInformation();
        Deque<String> guessData = new ArrayDeque<>();
        try (BufferedReader guessReader = new BufferedReader(new FileReader(fileName))) {
            String data;
            //Here is to ensure the the user choose the correct file based on the code length
            if (Player.getCodeLength()==4&&fileName.equalsIgnoreCase("FourDigitGuess")) {
                while (((data=guessReader.readLine())!=null)){
                    guessData.addFirst(data);
                }
            }else if(Player.getCodeLength()==5&&fileName.equalsIgnoreCase("FiveDigitGuess")){
                while (((data=guessReader.readLine())!=null)){
                    guessData.addFirst(data);
                }
            }else if(Player.getCodeLength()==6&&fileName.equalsIgnoreCase("SixDigitGuess")){
                while (((data=guessReader.readLine())!=null)){
                    guessData.addFirst(data);
                }
            }else{
                System.out.println("file name does not match with your code length");
                System.out.println();
                return readGuessData();
            }
        } catch (IOException e) {
            System.out.println("file does not exist, try again");
            return readGuessData();
        }
        return guessData;
    }

}
