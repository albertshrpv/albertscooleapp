package de.albert.drinkordie;
import java.util.Arrays;
import java.util.Random;

public class Quiz {
    private String quiz;
    private String quizSolution;
    private String[] players;
    private int quizIndex;
    private int maxQuizRounds;

    public Quiz(String quiz, String quizSolution, String[] players, int index)
    {
        this.quiz = quiz;
        this.quizSolution = quizSolution;
        this.players = Arrays.copyOf(players, players.length);
        this.quizIndex = index;
        maxQuizRounds = 1;
    }

    public String getQuiz()
    {
        String randomPlayer = players[0];
        String randomPlayer2 = players[1];
        String randomPlayer3 = players[2];
        return String.format(quiz, randomPlayer, randomPlayer2, randomPlayer3, getRandomSipCount());
    }

    public String getQuizSolution()
    {
        String randomPlayer = players[0];
        String randomPlayer2 = players[1];
        String randomPlayer3 = players[2];
        return String.format(quizSolution, randomPlayer, randomPlayer2, randomPlayer3, getRandomSipCount(), getRandomNumber(), getKopfZahl(), getPromi(), getPromi1());
    }

    public int getQuizIndex()
    {
        return quizIndex;
    }

    public boolean round()
    {
        return true;
    }

    private String getRandomSipCount()
    {
        Random random = new Random();
        int randomSipNumber = random.nextInt(4) + 2;
        String randomSipCount = "" + randomSipNumber;
        return randomSipCount;
    }

    private String getRandomNumber()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return "" + randomNumber;
    }

    private String getKopfZahl()
    {
        String[] kopfOderZahl = new String[2];
        kopfOderZahl[0] = "Kopf";
        kopfOderZahl[1] = "Zahl";
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return kopfOderZahl[randomNumber];
    }

    private String getPromi()
    {
        String[] promi = new String[4];
        promi[0] = "Thor";
        promi[1] = "Daenarys Targaryen, Sturmtochter, erste ihres Namens, Mutter der Drachen, Sprengerin der Ketten, Königin der Andalen und der Ersten Menschen";
        promi[2] = "Katniss Everdeen";
        promi[3] = "Batman";
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return promi[randomNumber];
    }
    private String getPromi1()
    {
        String[] promi = new String[4];
        promi[0] = "Padme";
        promi[1] = "Mikaela Banes";
        promi[2] = "Han Solo";
        promi[3] = "Iron Man";
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        return promi[randomNumber];
    }
}
