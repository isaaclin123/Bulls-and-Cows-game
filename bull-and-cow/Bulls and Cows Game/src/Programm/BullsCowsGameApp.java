package Programm;

import java.util.ArrayList;
import java.util.Deque;

/***
 * The BullsCowsGameApp class is the entrance to run the programme. It contains a start method
 * which will run through the process of the game and some other methods.
 * This app has implement all the basic features mentioned on the description and incorporate  2
 * configurations, which allows the user to play as many times as possible and quit
 * the game after the game starts, and it allows the user to enter 4, 5 or 6 digits numbers to
 * play the game. In addition, it has additional features which will remind the user every 7 rounds
 * and check if the user still want to play the game. And when the user wins, it will ask if the
 * user want to play one more time and restart the game. And I have also introduce some time
 * intervals while the programme is running to enhance the user experience.
 */

public class BullsCowsGameApp {
    private int round;/* To record the how many rounds the player played with*/
    private boolean userWin;
    private boolean computerWin;
    private Deque<String> autoGuessData;/* A deque to store the pre-written random guesses from
    file*/

    public void start(){
        ArrayList<String> userGuessRecord = new ArrayList<>();//a list to store all the users
        // guesses
        ArrayList<String> computerGuessRecord = new ArrayList<>();//a list to store all the
        // computer guesses
        System.out.println("Welcome to the **Bulls and Cows Game**");
        Label:
        while (true) {
            System.out.println("" +
                    "What would you like to do?\n"+
                    "1.\tEnter any character to start the game\n"+
                    "2.\tEnter Y to quit");

            String answer =Utility.askInformation();
            if (answer.equalsIgnoreCase("y")){
                Utility.printGoodBye();
                break;
            }
            //Initialize the Player's secret code, since there is only one play, so I have make
            // the Player's field and method all static so that they can be easier to called from
            // other classes
            Player.setCodeLength(Utility.askForCodeLength());
            Player.setSecretCode(Utility.askUserForCode(Player.getCodeLength()));
            System.out.println("Your secret code length is "+ Player.getCodeLength());
            System.out.println("You secret code is: "+ Player.getSecretCode());
            System.out.println();
            System.out.println("Here comes your component***"+ Computer.NAME +"***");
            System.out.println();
            System.out.println("Please choose "+ Computer.NAME +"'s intelligence level:\n" +
                    "1.Enter 1 to choose the less intelligent "+ Computer.NAME +"\n"+
                    "2.Enter 2 to choose the smart "+ Computer.NAME +"\n"+
                    "3.Enter 3 to choose the highly intelligent "+ Computer.NAME);
            //Initialize the computer instance base on user's choice and the code and the computer
            // code list
            Computer computer=generateAI();
            System.out.println();
            System.out.println(Computer.NAME +" is generating his secret code.....");
            Utility.timeSleep();
            System.out.println(Computer.NAME +"'s secret code length is :"+ Player.getCodeLength());
            String computerSecretCode = computer.getComputerSecretCode();
//            System.out.println("His secret code is "+computerSecretCode);
//            uncomment this to test the code
            System.out.println();
            Utility.timeSleep();
            System.out.println("***************************************************************");
            System.out.println("The minimum rounds for the game would be 7, but you can " +
                    "play as many rounds as you like!\n Or type in \"quit\" after the game " +
                    "starts if you like to leave the game!");
            System.out.println("In addition, I will remind you every 7 rounds!");
            boolean isDraw = true;
            boolean isAuto = true;
            //Ask the user to choose manual guess or automatic guessing
            int userChoice = GuessReader.askUserToReadGuess();
            if (userChoice==2){
                autoGuessData = GuessReader.readGuessData();
                userGuessRecord.addAll(autoGuessData);
            }
            if (userChoice==1){
                isAuto=false;
            }
            System.out.println("**************Now, let's get started*******************");
            //Game starts from here, if the user choose automatic guessing, it will generate
            // automatic guessing first, otherwise will go for manual guessing, all of manual
            // guessing will be validated, and the result of bulls and cows will be provided
            // afterwards.When the game ends, user will be asked if he/she want to save the game
            // history.
            while (isDraw) {
                System.out.println("----------------------------------------------------");
                round++;
                System.out.println("Round" + round);
                System.out.println("Please enter your guess");
                String userGuess;
                if (isAuto){
                    userGuess =autoGuessData.pollFirst();
                    System.out.println("Your guess of "+Computer.NAME+"'s secret code: " + userGuess);
                    System.out.print("Result: ");
                    System.out.println(printBullsAndCows(checkBullsAndCows(computer.getComputerSecretCode(),
                            userGuess)));
                    if(autoGuessData.isEmpty()){
                        System.out.println("\nNo more random guesses available,please enter " +
                                "manually from now on");
                        isAuto=false;
                    }
                    Utility.timeSleep();
                }else{
                    while (true) {
                        userGuess = Utility.askInformation();
                        if (!Utility.isValidCode(userGuess)) {
                            if(userGuess.equalsIgnoreCase("quit")){
                                System.out.println("Would you like to quit? Type in \"Y\"" +
                                        " to quit or any other character to stay in the game\"");
                                String confirmation = Utility.askInformation();
                                if (confirmation.equalsIgnoreCase("Y")) {
                                    GameWriter.writeGameHistory(computerSecretCode,round,
                                            userGuessRecord, computerGuessRecord,userWin,computerWin);
                                    Utility.printGoodBye();
                                    break Label;
                                }
                            }
                            System.out.println("Error,each digit need to be unique and the code " +
                                    "length is " + Player.getCodeLength() + "\n" +
                                    " try again or type in \"quit\" to leave:");
                        } else {
                            System.out.println("Your guess of "+Computer.NAME+"'s secret code: " + userGuess);
                            System.out.print("Result: ");
                            System.out.println(printBullsAndCows(checkBullsAndCows(computer.getComputerSecretCode(),
                                    userGuess)));
                            userGuessRecord.add(userGuess);
                            Utility.timeSleep();
                            break;
                        }
                    }
                }
                System.out.println();
                String computerGuess = computer.guessCode();
                System.out.println(Computer.NAME + "'s guess of your code: " + computerGuess);
                computerGuessRecord.add(computerGuess);
                System.out.print("Result: ");
                System.out.println(printBullsAndCows(checkBullsAndCows(Player.getSecretCode(),
                        computerGuess)));
                if (checkWinner(checkBullsAndCows(computer.getComputerSecretCode(),
                        userGuess))) {
                    userWin = true;
                    System.out.println("You are a winner!!!Great job!!!(^o^)");
                    System.out.println("Would you like to play the game again? enter \"Y\" to " +
                            "play again, or any other character to leave the game");
                    String confirmation = Utility.askInformation();
                    if (confirmation.equalsIgnoreCase("Y")) {
                        isDraw = false;
                    } else {
                        GameWriter.writeGameHistory(computerSecretCode,round,
                                userGuessRecord, computerGuessRecord,userWin,computerWin);
                        Utility.printGoodBye();
                        break Label;
                    }
                }
                if (checkWinner(checkBullsAndCows(Player.getSecretCode(),
                        computerGuess))) {
                    computerWin = true;
                    System.out.println(Computer.NAME + " beat you, :(");
                    GameWriter.writeGameHistory(computerSecretCode,round,
                            userGuessRecord, computerGuessRecord,userWin,computerWin);
                    Utility.printGoodBye();
                    break Label;
                }
                if (round % 7 == 0) {
                    System.out.println("You have played " + round + " rounds with " + Computer.NAME + ", " +
                            "would you like to exit the game?\n Type in \"Y\" if you don't want " +
                            "to continue, or any other character to stay in the game");
                    if ("Y".equalsIgnoreCase(Utility.askInformation())) {
                        GameWriter.writeGameHistory(computerSecretCode,round,
                                userGuessRecord, computerGuessRecord,userWin,computerWin);
                        Utility.printGoodBye();
                        break Label;
                    }
                }
            }
        }
    }
    /**
     *This method is to generate a AI base on the user's input
     */
    private Computer generateAI(){
        String answer;
        while (true) {
            answer =Keyboard.readInput();
            if (!Utility.isDigit(answer)||(!(answer.equals("1"))&&!(answer.equals("2"))&&!(answer.equals(
                    "3")))){
                System.out.println("please choose a number from 1-3");
                continue;
            }
            switch(answer.charAt(0)){
                case '1':
                    System.out.println(Computer.NAME+" :hello mate!");
                    Utility.timeSleep();
                    return new EasyAI();
                case '2':
                    System.out.println(Computer.NAME+" :Let's see how smart you are!");
                    Utility.timeSleep();
                    return new MediumAI();
                case '3':
                    System.out.println(Computer.NAME+" :You will never beat me!");
                    Utility.timeSleep();
                    return new IntelligentAI();
            }
        }
    }

    /**
     *This method is to compare a code and a guess and return an array containing bulls and cows
     * counts
     */
    public static int[] checkBullsAndCows(String secretCode, String guess ){
        int bulls =0;
        int cows =0;
        for (int i =0;i<secretCode.length();i++) {
                if (secretCode.charAt(i)==guess.charAt(i)){
                    bulls++;
                }
                if(secretCode.contains(String.valueOf(guess.charAt(i)))){
                    cows++;
                }
        }
        cows=cows-bulls;

        return new int[]{bulls,cows};
    }

    /**
     *This method is to return the bulls and cows result
     */
    public static String printBullsAndCows(int[] countBullsAndCows){
        return countBullsAndCows[0]+" bulls and "+countBullsAndCows[1]+" " +
                "cows";
    }

    /**
     *This method is to check who win the game. If the player or computer's guess result of bulls
     *  is the same number of the code length and cows is 0, return true,otherwise,return false
     */
    public static boolean checkWinner(int[] countBullsAndCows){
        return ((countBullsAndCows[0]) == Player.getCodeLength()) && (countBullsAndCows[1] == 0);
    }


    public static void main(String[] args){
        BullsCowsGameApp bullsCowsGameApp = new BullsCowsGameApp();
        bullsCowsGameApp.start();


    }
}
