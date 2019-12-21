import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/** This program will do the following steps
 * 1. Take in a word and convert it to an array, eventually displaying underscores for each letter
 * 2. Create a gui that allows you to enter in individual letters through a text field
 * 3. If correct, it adds the letter to the underscore area
 * 4. If incorrect, a piece of the hangman paints
 * 5. You get 7 incorrect guesses before losing
 */

public class ImageBuilder extends JPanel {

    // Set Frame Size Variables
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 800;
    //Create frame
    private JFrame frame;
    private int FIELD_WIDTH = 10;
    // Create Panels
    private JPanel panel1;
    private JPanel panel2;
    final JTextField b = new JTextField(FIELD_WIDTH);
    // Create Labels
    private JLabel underscores;
    private JLabel wordLabel;
    private JLabel wordToGuessLabel;
    private JLabel textLabel;
    private JLabel guessText;
    private JLabel guessedLettersArea;
    // Variable for number of letters in word
    private int numOfLetters;
    // Text field
    private JTextField wordInput;
    // Text are for the word
    private JTextArea wordResults;
    // Create button
    private JButton button;
    // String for word
    private String word;
    // Array list for __'s and word
    private ArrayList<String> wordDisplay;
    // String for guessed letters
    private String guessedLetters;
    // DrawArea
    private DrawArea draw;
    private int incorrectGuesses;
    private int correctGuesses;

    /**
     * Constructor for gui
     * @param word is the word we are trying to guess
     */
    public ImageBuilder(String word){
        draw = new DrawArea();
        // Pass the word through
        this.word = word;

        // Initialize word display array to display __ array
        wordArrayCreator();

        // Call Methods to fully build gui
        createAndShowGui();
        createButton();
        createTextField();
        createGuessArea();

        // Add components to gui
        panel1.add(textLabel);
        panel1.add(wordInput);
        panel1.add(button);
        panel1.add(guessText);
        panel1.add(guessedLettersArea);

        // Set frame as visible
        frame.setVisible(true);

        // Add panel1 to frame
        frame.add(panel1);
    }

    /**
     * Creates wordDisplay Array to display word
     */
    private void wordArrayCreator() {
        // Create array list for word, replace each word with string: "__ "
        wordDisplay = new ArrayList<>();
        for (int i = 0; i <= word.length() - 1; i++) {
            String add = "__ ";
            wordDisplay.add(add);
        }
    }

    /**
     * Creates base layer of gui w/o any
     * interactive changes
     */
    private void createAndShowGui() {
        // Initialize Frame and Panels
        frame = new JFrame("Hangman");
        panel1 = new JPanel();

        // Allows manual layout for both panels
        panel1.setLayout(null);

        // Add label to panel
        panel1.add(new JLabel("Word to Guess: "), BorderLayout.NORTH);
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initializes frame content
        frame.getContentPane();

        // Create label for array, replacing "[", "]" and "," with ""
        underscores = new JLabel(wordDisplay.toString().replace("[","")
                .replace("]","").replace(",",""));
        // Place underscore text label
        underscores.setBorder(BorderFactory.createLineBorder(Color.gray));
        underscores.setBounds(230, 20, 250, 22);
        // Add label of underscores to panel1
        panel1.add(underscores);

        // Initialize button
        button = new JButton("Submit Letter");
        button.setLayout(null);
        button.setBounds(335, 68, 120, 25);
        ActionListener listener = new AddLetterListener();
        button.addActionListener(listener);

        // Add button to panel1
        panel1.add(button);

        // Create label for number of letters text
        numOfLetters = word.length();

        // Create label for the amount of letters to guess
        wordToGuessLabel = new JLabel("The word to guess has " + numOfLetters + " letters");
        Dimension size1 = wordToGuessLabel.getPreferredSize();

        // Set location and size of wordToGuessLabel
        wordToGuessLabel.setBounds(230, 45, size1.width, size1.height);

        // String for letters that have been guess
        guessedLetters = "";
        guessText = new JLabel("Guessed Letters: ");
        guessText.setLayout(null);

        // Set location and size of label
        guessText.setBounds(165, 100, 150, 30);
        guessedLettersArea = new JLabel(guessedLetters.replace("[","")
                .replace("]","").replace(",",""));

        // Set location and size of label
        guessedLettersArea.setBounds(275, 105, 150, 22);
        guessedLettersArea.setBorder(BorderFactory.createLineBorder(Color.gray));

        // Create text label
        textLabel = new JLabel("Guess a Letter: ");
        Dimension size4 = textLabel.getPreferredSize();

        // Set location and size of label
        textLabel.setBounds(100, 30, size4.width, size4.height);

        // Create word input area
        wordInput = new JTextField(6);

        // Set word input Size and set default text as ""
        Dimension size5 = wordInput.getPreferredSize();
        wordInput.setBounds(260, 70, size5.width, size5.height);
        wordInput.setText("");
        wordInput.isEditable();
        wordInput.isEnabled();

        // Add label to panel
        panel1.add(wordToGuessLabel);

        // Initializes draw area, sets size and adds to panel
        draw = new DrawArea();
        draw.setPreferredSize(new Dimension(500, 500));
        panel1.add(draw);

        // Set up settings for frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * Creates label and text box to input
     * through action listener
     */
    private void createTextField() {
        textLabel = new JLabel("Guess a Letter: ");
        Dimension size4 = textLabel.getPreferredSize();

        // Set location and size of label
        textLabel.setBounds(100, 30, size4.width, size4.height);

        // Create word input area
        wordInput = new JTextField(6);

        // Set word input Size and set default text as ""
        Dimension size5 = wordInput.getPreferredSize();
        wordInput.setBounds(260, 70, size5.width, size5.height);
        wordInput.setText("");
        wordInput.isEditable();
        wordInput.isEnabled();
    }

    /**
     * Creates labels for guessed text and box for
     * displaying letters that have been guessed
     */
    private void createGuessArea() {
        // String for letters that have been guess
        guessedLetters = "";
        guessText = new JLabel("Guessed Letters: ");
        guessText.setLayout(null);

        // Set location and size of label
        guessText.setBounds(165, 100, 150, 30);
        guessedLettersArea = new JLabel(guessedLetters.replace("[","")
                .replace("]","").replace(",",""));

        // Set location and size of label
        guessedLettersArea.setBounds(275, 105, 150, 25);
        guessedLettersArea.setBorder(BorderFactory.createLineBorder(Color.gray));
    }

    /**
     * Creates button to submit guessed letter
     */
    private void createButton() {
        // Initialize button
        button = new JButton("Submit Letter");
        button.setLayout(null);
        button.setBounds(335, 68, 120, 25);
        ActionListener listener = new AddLetterListener();
        button.addActionListener(listener);

        // Add button to panel1
        panel1.add(button);
    }

    /**
     * Action listener for button and text box
     * to submit letter.  Does logic for if letter has been guessed
     * and calls methods to add letters to correct arrays
     */
    class AddLetterListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            // Grab text input
            String input = (wordInput.getText());
            input = input.toLowerCase();
            // Checks for at least one letter
            if (input.length() < 1) {
                JOptionPane.showMessageDialog(frame, "You need to guess one letter.");
                wordInput.setText("");
                return;
            }
            // Checks for more than one letter
            if (input.length() > 1) {
                JOptionPane.showMessageDialog(frame, "You can only guess one letter.");
                wordInput.setText("");
                return;
            }
            if (guessedLetters.contains(input)) {
                JOptionPane.showMessageDialog(frame, "You have already guessed that letter, try again.");
                wordInput.setText("");
                return;
            }
            else {
                // Checks if word contains input letter
                if (word.contains(input)) {
                    wordInput.setText("");
                    int i = word.indexOf(input);
                    // Locates first index
                    while(i >= 0) {
                        System.out.println(i);
                        updateArray(i, input);
                        updateGuesses(input);
                        i = word.indexOf(input, i+1);
                        correctGuesses++;
                        if (correctGuesses == word.length()) {
                            win();
                        }
                    }
                }
                // Incorrect guess, drawing and updating counters
                else {
                    updateGuesses(input);
                    drawingCall();
                    wordInput.setText("");
                    return;
                }
            }
        }
    }

    /**
     * Method for win, will draw winning text
     */
    private void win() {
        Dimension newD = new Dimension(700,700);
        draw.win();
        draw.setBounds(40, 100, newD.width, newD.height);
        wordInput.setEnabled(false);
    }

    /**
     * Updates the string that contains the guessed letter
     * @param letter : the letter that has been guessed
     */
    private void updateGuesses(String letter) {
        if (!(guessedLetters.contains(letter))) {
            guessedLetters = guessedLetters + " " + letter;
            guessedLettersArea.setText(guessedLetters);
        }
        if (correctGuesses == word.length()) {
            win();
        }
    }

    /**
     * Calls instance of draw and should call method within DrawArea class
     */
    private void drawingCall() {
        incorrectGuesses++;
        System.out.println(incorrectGuesses);
        Dimension newD = new Dimension(700,700);
        if(incorrectGuesses == 1) {
            draw.drawGallows();
            draw.setBounds(40, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 2) {
            draw.drawHead();
            draw.setBounds(40, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 3) {
            draw.drawBody();
            draw.setBounds(45, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 4) {
            draw.drawLeftArm();
            draw.setBounds(45, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 5) {
            draw.drawRightArm();
            draw.setBounds(45, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 6) {
            draw.drawLeftLeg();
            draw.setBounds(45, 100, newD.width, newD.height);
            repaint();
        }
        if(incorrectGuesses == 7) {
            wordInput.setEnabled(false);
            draw.drawRightLeg(word);
            draw.setBounds(45, 100, newD.width, newD.height);
            repaint();
        }
    }

    /**
     * Updates correctly guessed letters in the word array
     */
    private void updateArray(int placeOfIndex, String letter) {
        wordDisplay.set(placeOfIndex, letter);
        underscores.setText(wordDisplay.toString().replace("[","")
                .replace("]","").replace(",",""));
    }
}
