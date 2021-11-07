package Programm;

import java.util.ArrayList;

public abstract class Computer {
    public  static final String NAME = "Albert Einstein";/* Computer's name */
    private final ArrayList<String> secretCodesList;/* All possible secret codes*/
    private final String computerSecretCode;/*Computer's secret code */


    /**
     * Based on the user's chosen code length, the computer constructor will initialize a code list
     * and generate a secret code once an computer instance has been created.
     */
    public Computer(){
        this.secretCodesList =generateCodeList(Player.getCodeLength());
        this.computerSecretCode =generateComputerCode();
    }


    public String getComputerSecretCode() { return computerSecretCode; }

    public ArrayList<String> getSecretCodesList() { return secretCodesList; }



    /**
     *This method is to generate all the codes based on the given code length. For example, it
     * will generate all 4 digit numbers and convert them to the 4 digit format if they
     * contain less than 4 digit(eg. 1--->0001). Then the method will remove the number which
     * contains duplicated digits. Finally the method will add the validated number to a list and
     * return it to Computer.
     */
    public ArrayList<String> generateCodeList(int codeLength){
        ArrayList<String> codesList = new ArrayList<>();
        String code = null;
        for (int i = 0; i < Math.pow(10, codeLength); i++) {
           switch (codeLength) {
               case 4: code = String.format("%04d", i);
                    break;
               case 5: code = String.format("%05d", i);
                    break;
               case 6: code = String.format("%06d", i);
                    break;
               default:
                   break;
            }
            assert code != null;
            if (!Utility.isDuplicate(code)){codesList.add(code);}
        }
        return codesList;
    }
    /**
     *This method is to generate computer's secret code or guesses by generate a random index and
     *  get the code form the code list based on the index
     */
    public String generateComputerCode(){
        int index = (int) (Math.random() * this.secretCodesList.size());
        return this.secretCodesList.get(index);
    }

    public abstract String guessCode();

}
