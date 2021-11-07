package Programm;

public class EasyAI extends Computer{
    @Override
    /* This method will generate a random code form the code list*/
    public String guessCode() {
        return generateComputerCode();
    }
}
