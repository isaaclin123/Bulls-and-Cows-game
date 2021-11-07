package Programm;

/***
 * This class is to provide some common methods that will be used in the programme
 */

public class Utility {

    /*A method to print goodbye message */
    public static void printGoodBye(){
        System.out.println("See you next time!");
    }

    /*A method to shortly stop the programme from running for 1 second*/
    public static void timeSleep(){
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * This method is to ask the user's information and make sure there will be no null and empty
     * string involved
     */
    public static String askInformation(){
        String answer;
        for (; ; ) {
            answer = Keyboard.readInput();
            if ((answer!=null)&&!answer.isEmpty()) {
                break;
            }else{
                System.out.println("Error,try again:");
            }
        }
        return answer;
    }

    /***
     * This method is to ask the user for his secret code's length, user can only choose,4,5 or 6
     */
    public static int askForCodeLength(){
        String codeLength;
        System.out.println("Please choose the length of the code, the minimum length " +
                "is 4, the maximum length is 6" +
                " ");
        while (true) {
            codeLength =Keyboard.readInput();
            if (!(codeLength.equals("4"))&&!(codeLength.equals("5"))&&!(codeLength.equals("6"))){
                System.out.println("Please enter a number between4-6");
            }else{
                return Integer.parseInt(codeLength);
            }
        }
    }


    /***
     * This method is to ask the user to enter a code
     *
     */
    public static String askUserForCode(int codeLength){
        System.out.println("Please enter a "+codeLength+" digits secret code(eg. 1234 if your" +
                        " code length is 4, "+"54321 if you code length is 5), and ensure " +
                "each digit to be unique");
        String code =Keyboard.readInput();
        while (!isValidCode(code,codeLength)) {
            System.out.print("Error, try again: ");
            code = Keyboard.readInput();
        }
        return code;

    }

    /***
     * This method is to validate the code the user entered.Return true if the code's format is a
     * number and each digit is unique and matches the code's length that entered before, otherwise
     * will return false
     */
    public static boolean isValidCode(String code, int codeLength) {

        return isDigit(code)&&(!isDuplicate(code))&&isSameSize(code,codeLength);
    }
    /***
     * This overloaded method is to validate the code the user entered.Return true if the code's
     * format is a number and each digit is unique,and matches the code's length that entered before,
     * otherwise will return false
     */
    public static boolean isValidCode(String code) {

        return isDigit(code)&&(!isDuplicate(code))&&isSameSize(code,Player.getCodeLength());
    }

    /***
     * This method is to check the if the input is a number format
     *
     */
    public static boolean isDigit(String code){
        for (int i = 0; i < code.length(); i++) {
            if (!Character.isDigit(code.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /***
     * This method is to check if a number's each digit is unique
     *
     */
    public static boolean isDuplicate(String code){
        for (int i = 0; i < code.length(); i++) {
            for (int j = i+1; j <code.length() ; j++) {
                if (code.charAt(i)==code.charAt(j)){
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * This method is to check if the number matches the code's length that entered before
     */
    public static boolean isSameSize( String code,int codeLength){
        return code.length() == codeLength;
    }

}

