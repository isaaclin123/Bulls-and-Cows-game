package Programm;



import java.util.ArrayList;

public class MediumAI extends Computer{
    /**
     *This method  will remove the code from the code list that have been used before
     */
    @Override
    public String guessCode() {
        ArrayList<String> secretCodesList = getSecretCodesList();
        String code = generateComputerCode();
        secretCodesList.remove(code);
        return code;
    }

}
