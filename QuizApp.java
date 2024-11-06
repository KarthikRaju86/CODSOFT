import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static final String[][] QUESTIONS = {
        {"What is the capital of France?", "A) Berlin", "B) Madrid", "C) Paris", "D) Rome"},
        {"Which planet is known as the Red Planet?", "A) Earth", "B) Venus", "C) Mars", "D) Jupiter"},
        {"What is the largest ocean on Earth?", "A) Atlantic", "B) Indian", "C) Arctic", "D) Pacific"},
        {"What is 5 + 7?", "A) 10", "B) 11", "C) 12", "D) 13"},
        {"What is the chemical symbol for water?", "A) H2O", "B) O2", "C) CO2", "D) N2"}
    };

    private static final char[] ANSWERS = {'C', 'C', 'D', 'C', 'A'};
    private static final int TIMER_SECONDS = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        String[] userAnswers = new String[QUESTIONS.length];
        int correctAnswers = 0;
        int incorrectAnswers = 0;

        for (int i = 0; i < QUESTIONS.length; i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println(QUESTIONS[i][0]);
            System.out.println(QUESTIONS[i][1]);
            System.out.println(QUESTIONS[i][2]);
            System.out.println(QUESTIONS[i][3]);
            System.out.println(QUESTIONS[i][4]);

            Timer timer = new Timer();
            final int questionIndex = i;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    userAnswers[questionIndex] = "Timeout";
                }
            }, TIMER_SECONDS * 1000);

            System.out.print("Your answer (A/B/C/D): ");
            String answer = scanner.next().toUpperCase();
            timer.cancel();
            userAnswers[questionIndex] = answer;

            if (answer.charAt(0) == ANSWERS[i]) {
                score++;
                correctAnswers++;
            } else {
                incorrectAnswers++;
            }
        }

        System.out.println("\nQuiz Completed!");
        System.out.println("Your score: " + score + "/" + QUESTIONS.length);
        System.out.println("Correct answers: " + correctAnswers);
        System.out.println("Incorrect answers: " + incorrectAnswers);

        System.out.println("\nYour answers summary:");
        for (int i = 0; i < QUESTIONS.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + QUESTIONS[i][0]);
            System.out.println("Your answer: " + userAnswers[i]);
            System.out.println("Correct answer: " + ANSWERS[i]);
            System.out.println();
        }

        double percentage = ((double) correctAnswers / QUESTIONS.length) * 100;
        System.out.println("You scored " + String.format("%.2f", percentage) + "% in the quiz.");

        scanner.close();
    }
}