package Programm;

public class Player {
    private static  String secretCode; /* User's secret code*/

    private static  int codeLength; /* User's code length*/

    public static void setSecretCode(String secretCode) { Player.secretCode = secretCode; }

    public static void setCodeLength(int codeLength) { Player.codeLength = codeLength; }

    public static int getCodeLength() { return codeLength; }

    public static String getSecretCode() { return secretCode; }

}
