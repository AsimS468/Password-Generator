package PassGen;

// Import Statements
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
* Overview:
* This program generates strings that can be used as passwords.
* It uses words from the words.txt file to create passwords that are easier to remember.
* This program generates 3 different passwords with different levels of complexities:
*   -Simple: A random word from the words.txt file with 1-4 different numbers after.
*   -Tricky: A random word from the words.txt file followed with 1-3 special characters and 1-4 different numbers.
*   -Complex: A string of random letters and numbers which can be between 6-12 characters long.
*/

public class PasswordGenerator {
    // An array of strings holding all the special characters on the keyboard.
    private static String[] specialChars = {"`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "=", "+",
            "[", "{", "]", "}", "\\", "|", ";", ":", "'", "\"", ",", "<", ".", ">", "/", "?", " "};

    // An array of strings holding all the letters and numbers on the keyboard.
    private static String[] allChars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    //Constructor to create objects of this class.
    public PasswordGenerator() {
        this.specialChars = specialChars;
        this.allChars = allChars;
    }

    /* Method for selecting a random number between 1-61237.
     * Used for selecting a random line from the words.txt file to get the word on that corresponding line.
     * Modifies: this
     * Effects: randomly selects a number between 1-61237
     */
    public static int getRandomLineFromTXT() {
        Random rand = new Random();
        return rand.nextInt(61237) + 1;
    }

    /* Method for getting a random word from the words.txt file.
     * Uses a random number generated getRandomLineFromTXT method and gets the word on that corresponding line.
     * Requires: an integer between 1 and 61237.
     * Modifies: this
     * Effects: returns a random word from the words.txt file.
     */
    public static String getRandomWordFromTXT() throws IOException {
        int lineNumber = getRandomLineFromTXT();

        BufferedReader reader = new BufferedReader(new FileReader("src/data/words.txt"));
        String word;
        int currentLine = 1;

        // While loop that reads through the words.txt file to find the word on the specified line.
        while ((word = reader.readLine()) != null) {
            if (currentLine == lineNumber) {
                break;
            }
            currentLine++;
        }

        // Checks if the specified line exists.
        if (currentLine < lineNumber) {
            System.out.println("File does not contain that many lines.");
        }
        reader.close();
        return word;
    }

    /* Method for selecting a random number between 0-9.
     * Used for selecting a random single digit integer for the simple and tricky passwords.
     * Modifies: this
     * Effects: randomly selects a number between 0-9.
     */
    public static int getRandomNumericChar() {
        Random rand = new Random();
        return rand.nextInt(9);
    }

    /* Method for selecting a random number between 0-32.
     * Used for selecting a random number to get the corresponding element from the specialChars array for tricky passwords.
     * Modifies: this
     * Effects: randomly selects a number between 0-32.
     */
    public static int getRandomSpecialChar() {
        Random rand = new Random();
        return rand.nextInt(32);
    }

    /* Method for selecting a random number between 0-61.
     * Used for selecting a random number to get the corresponding element from the allChars array for complex passwords.
     * Modifies: this
     * Effects: randomly selects a number between 0-61.
     */
    public static int getRandomChar() {
        Random rand = new Random();
        return rand.nextInt(61);
    }

    /* Method for generating a simple password.
     * Requires: 1-4 randomly generated numbers and a word from the words.txt file.
     * Modifies: this
     * Effects: returns a string which includes a word from the words.txt file followed by 1-4 random integers.
     */
    public static String SimplePassword() throws IOException {

        /* randomly selects a number between 1 and 4.
         * This determines how manny numbers will be in the password.
         */
        Random rand = new Random();
        int numericCharCount = rand.nextInt(4) + 1;

        ArrayList<Integer> numbers = new ArrayList<Integer>(); // Creating an array list for the numbers.
        StringBuilder sb = new StringBuilder();

        /* Loop that calls the getRandomNumericChar method based on the value of numericCharCount
         * and adds the number to the numbers Array List
         */
        for (int i = 0; i < numericCharCount; i++) {
            numbers.add(getRandomNumericChar());
        }

        // Loop that puts the numbers in the numbers array list into the string builder.
        for (Integer item : numbers) {
            sb.append(item);
        }

        String nums = sb.toString(); // Turns the data in the string builder into a string.
        return getRandomWordFromTXT() + nums; // returns the simple Password
    }

    /* Method for generating a tricky password.
     * Requires: 1-4 randomly generated numbers, 1-3 randomly generated special characters, and a word from the words.txt file.
     * Modifies: this
     * Effects: returns a string which includes a word from the words.txt file followed by 1-3 randomly generated special characters and
     *          1-4 random integers.
     */
    public static String TrickyPassword() throws IOException {
        Random rand = new Random();

        /* randomly selects a number between 1 and 4.
         * This determines how manny numbers will be in the password.
         */
        int numericCharCount = rand.nextInt(4) + 1;

        /* randomly selects a number between 1 and 4.
         * This determines how manny special characters will be in the password.
         */
        int specialCharCount = rand.nextInt(3) + 1;

        ArrayList<Integer> numbers = new ArrayList<Integer>(); // Creating an array list for the numbers.
        ArrayList<String> specChars = new ArrayList<String>(); // Creating an array list for the special characters.
        StringBuilder sb1 = new StringBuilder(); // String builder for the numbers.
        StringBuilder sb2 = new StringBuilder(); // String builder for the special characters.

        /* Loop that calls the getRandomNumericChar method based on the value of numericCharCount
         * and adds the number to the numbers Array List
         */
        for (int i = 0; i < numericCharCount; i++) {
            numbers.add(getRandomNumericChar());
        }

        /* Loop that calls the getRandomSpecialChar method based on the value of specialCharCount
         * and adds the characters to the specChars Array List
         */
        for (int i = 0; i < specialCharCount; i++) {
            String char1 = specialChars[getRandomSpecialChar()];
            specChars.add(char1);
        }

        // Loop that puts the numbers in the numbers array list into the first string builder.
        for (Integer item : numbers) {
            sb1.append(item);
        }
        String nums = sb1.toString(); // Turns the data in the first string builder into a string.

        // Loop that puts the special characters in the specChars array list into the second string builder.
        for (String item : specChars) {
            sb2.append(item);
        }
        String sChars = sb2.toString(); // Turns the data in the second string builder into a string.

        return getRandomWordFromTXT() + sChars + nums; // Returns the tricky password
    }

    /* Method for generating a complex password.
     * Requires: the allChars array instance variable.
     * Modifies: this
     * Effects: returns a string of random letters and numbers which can be between 6-12 characters long.
     */
    public static String ComplexPassword() {
        Random rand = new Random();

        /* randomly selects a number between 6 and 12.
         * This determines how manny characters will be in the password.
         */
        int CharCount = rand.nextInt(6) + 6;
        ArrayList<String> chars = new ArrayList<String>(); // Creating an array list for the characters.
        StringBuilder sb = new StringBuilder();

        /* Loop that calls the getRandomChar method based on the value of CharCount
         * and adds the characters to the chars Array List
         */
        for (int i = 0; i < CharCount; i++) {
            String char1 = allChars[getRandomChar()];
            chars.add(char1);
        }

        // Loop that puts the characters in the chars array list into the string builder.
        for (String item : chars) {
            sb.append(item);
        }

        return sb.toString(); // returns the complex password.
    }

    // Main method for the class.
    public static void main(String[] args) throws IOException {
        System.out.println("Simple Password: " + SimplePassword());
        System.out.println("Tricky Password: " + TrickyPassword());
        System.out.println("Complex Password: " + ComplexPassword());
    }
}
