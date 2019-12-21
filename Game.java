import java.util.*;
import java.io.*;


public class Game {
    /** The main program will perform the following steps
    * 1. Take in a text file
    * 2. Read through the text file and select a random word
    * 3. Create an instance of ImageBuilder and pass the random word through
    */


    public static void main(String[] args) throws FileNotFoundException {
        ImageBuilder n = new ImageBuilder(grabWord(args[0]));

    }
    /**
     * Opens a file and parses the formatted file
     *	reads on each line of the file and splits the string into an array
     *	grabs the ages of the entries and sums them to print to screen
     * @param fileName the path to input file
     *
     */
    private static String grabWord(String fileName)throws FileNotFoundException {
        int sum = 0;
        ArrayList<String> words = new ArrayList<>();
        Scanner fileScan = new Scanner(new File(fileName));
        while(fileScan.hasNextLine()){
            sum++;
            String line = fileScan.nextLine();
            words.add(line);
        }
        Random rand = new Random();
        int randNum = rand.nextInt(sum);
        // Prints random index of line number grabbed from file
        System.out.println(randNum);
        String test = words.get(randNum-1);
        // Prints our the word for testing purposes (Left this on for grading purposes)
        System.out.println(test);
        return (test);
    }
}
