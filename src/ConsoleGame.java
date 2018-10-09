import java.util.HashMap;
import java.util.Scanner;


class ConsoleGame {
    private HashMap<String, String> data = new HashMap<String, String>(){{
    put("Pulp Fiction", "Tarantino");
    put("A Clockwork Orange", "Kubrick");
    put("Blue Velvet", "Lynch");
}};

    void startGame(){

        Player player = createUser();

        String botInstruction = "I'd like you to play the game!\n" +
                "I will send you the name of movie and you'll name the director.\n";

        String botHelp = "\\start - start game \n" +
                "\\score - show current score \n" +
                "\\stop - stop game \n" +
                "\\help - help?";

        QuizBot quizBot = new QuizBot(data, player);
        Bot bot = new Bot(botInstruction, botHelp, player, quizBot);
        Scanner scan = new Scanner(System.in);

        System.out.println(bot.getStartMessage());

        while (player.wantsToPlay && player.Known.size() != data.size()){
            String nextBotMessage = bot.getNextMessage();
            System.out.println("Next movie: " + nextBotMessage );
            String userAnswer = scan.nextLine();
            String botAnswer = bot.processInput(nextBotMessage, userAnswer, player);
            System.out.println(botAnswer);
        }

        System.out.println("Your score: " + player.score);
        System.out.println("End game.");
    }

    private Player createUser(){
        System.out.println("Enter your name");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();
        System.out.println("Hello, " + userName);

        return new Player(userName);
    }


}
