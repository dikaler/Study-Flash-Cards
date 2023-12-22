/*
This project is a Flashcard Application
It allows users to study a set of flashcards with questions and answers
Users can add custom flashcards or study the pre-set ones

## Usage Instructions
1. Launch the application.
2. The application has several existing flashcards, you can also add custom flashcards.
3. Choose to start studying or exit the application.
4. While studying, enter your answers and get instant feedback.
5. Optionally, requeue flashcards for further practice.

## Possible Future Features
- Include different study modes (e.g., multiple-choice).
- Provide options for saving and loading flashcards.
*/

import java.util.*;

public class flashCard {
    private static Deque<String> answers;
    private static Deque<String> questions;
    private static int correct;
    private static int numberOfFlashCards;
    private static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        questions = new ArrayDeque<>();
        answers = new ArrayDeque<>();

        // Pre-set questions
        questions.add("How many continents are there?: ");
        answers.add("7");

        questions.add("How many US states are there?: ");
        answers.add("50");

        questions.add("What is the study of the human body called?: ");
        answers.add("anatomy");

        // Initialize starting size of deck
        numberOfFlashCards = questions.size();

        // Prompt user to add flashcards or not
        // If yes the AddFlashCards method is called
        System.out.println("There are " + numberOfFlashCards + " flashcards. ");
        System.out.println("Would you like to add some? (yes/no): ");

        if (reader.nextLine().toLowerCase().equals("yes")) {
            AddFlashCards(numberOfFlashCards, answers, questions, reader);
        }

        // Prompt user to begin studying
        // If yes, the Study method is called and the user will test their knowledge
        // If no, the program is terminated
        System.out.println("Would you like to begin studying? (yes/no): ");
        if (reader.nextLine().toLowerCase().equals("yes")) {
            System.out.println(Study(correct, numberOfFlashCards, answers, questions, reader));
        }
    }


    private static void AddFlashCards(int numberOfFlashCards, Deque<String> answers, Deque<String> questions,Scanner reader) {
        boolean doneAdding = false;

        // Allow user to add flashcards until they choose not to
        while (!doneAdding) {
            System.out.println("Enter the question you would like to add: ");
            questions.add(String.valueOf(reader.nextLine()));
            System.out.println("Enter the answer for the question you've entered: ");
            answers.add(String.valueOf(reader.nextLine()));
            numberOfFlashCards++;

            // Prompt user to see if they are done adding
            System.out.println("Are you done adding flashcards? (yes/no): ");
            if (reader.nextLine().equals("yes")) {
                doneAdding = true;
            }
        }
    }

    private static String Study(int correct, int numberOfFlashCards, Deque<String> answers, Deque<String> questions,Scanner reader) {
        // Initialize number of attempts and questions for stats at the end
        int attempts = 0;
        int questionNumber = 1;

        // Once there are no more questions and answers, all flash cards have been reviewed
        // Once all cards are reviewed the user will view their stats
        while (!questions.isEmpty() && !answers.isEmpty()) {

            // Get question and answer from respective deques
            // Display the questions and prompt user for the answer
            String actualQuestion = questions.poll();
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Question " + questionNumber + ": " + actualQuestion);
            String actualAnswer = answers.poll();
            System.out.println("Enter your answer: ");
            String userInput = reader.nextLine();

            // Correct case
            if (String.valueOf(userInput).toLowerCase().equals(actualAnswer)) {
                correct++;
                System.out.println("Good job, you have gotten: " + correct + " correct.");

            // Incorrect case
            } else {
                System.out.println("Incorrect, the answer is : " + actualAnswer);
            }

            // Increment for accurate stats at the end
            questionNumber++;
            attempts++;

            // The user can add the question back to the deque to retest themselves
            System.out.println("Would you like to requeue this questions (yes/no): ");
            String reQueue = reader.nextLine();

            if (String.valueOf(reQueue).toLowerCase().equals("yes") | String.valueOf(reQueue).equals("y")) {
                questions.add(actualQuestion);
                answers.add(actualAnswer);
                System.out.println("Done.");
            }
        }

        // User receives their stats for this study session
        return "You have gone through all of the flashcards. You got " + correct + " out of " + attempts + ".";

    }
}