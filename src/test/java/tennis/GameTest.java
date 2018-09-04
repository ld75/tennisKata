package tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tennis.displayer.Displayer;
import tennis.displayer.ScoreData;
import tennis.displayer.TextDisplayer;
import tennis.winnerRules.IRule;
import tennis.winnerRules.PlayerOneWins;
import tennis.winnerRules.RandomPlayerWins;

import java.util.Hashtable;


public class GameTest {
    // Sprint1 UserStory 1:-----------------
    @Test
    public void ifPlayRun_ThenOnePlayerWins()
    {
        Game game = initializeTest(new PlayerOneWins());
        Assertions.assertEquals("0",game.p1.score);
        game.play();
        Assertions.assertEquals("15",game.p1.score);
        game.play();
        Assertions.assertEquals("30",game.p1.score);
        game.play();
        Assertions.assertEquals("40",game.p1.score);
        game.play();
        Assertions.assertEquals("0",game.p1.score);
        Assertions.assertEquals("0",game.p2.score);
    }

    private Game initializeTest(IRule rule) {
        Joueur joueur1 = new Joueur();
        Joueur joueur2 = new Joueur();
        Game game = new Game(joueur1, joueur2);
        //game.setRules(new RandomRules());
        game.setRules(rule);
        game.initialize();
        return game;
    }

    @Test
    public void inEachTurn_CanDisplayHistory()
    {
        Game game = initializeTest(new PlayerOneWins());
        int iteration = 0;
        do
        {
            game.play();
            iteration++;
            Assertions.assertEquals(iteration,game.history.scores.size());
        } while(!game.p1.score.equals("0"));

    }
    @Test
    public void historyFilled_DisplayHistory()
    {
        History history = new History();
        ajouteHistory(history,"0","15");
        ajouteHistory(history,"15","15");
        ajouteHistory(history,"15","30");
        Displayer displayer = new TextDisplayer(history);
        displayer.displayAll();
    }

    private void ajouteHistory(History history, String s1, String s2) {
        Hashtable<String, String> it1 = new Hashtable<String, String>();
        new ScoreData("1","2",3,3);
        history.scores.add(new ScoreData("1","2",3,3));
    }
    @Test
    public void gameEnds_DisplayHistory()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.play();
        game.p1.score="Winner";
        game.play();
        // look at console
    }

    // Sprint1 UserStory 2:-----------------
    @Test
    public  void bothplayers40_DeuceActivated()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.score="30";
        game.p2.score="40";
        game.play();
        Assertions.assertEquals("DEUCE",game.p1.score);
        Assertions.assertEquals("DEUCE",game.p2.score);
    }
    @Test
    public  void winsDeuce_HasAdvantage()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.score="DEUCE";
        game.p2.score="DEUCE";
        game.play();
        Assertions.assertEquals("ADV",game.p1.score);
        Assertions.assertEquals("DEUCE",game.p2.score);
    }
    @Test
    public  void winAdv_Winner()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.score="ADV";
        game.p2.score="DEUCE";
        game.play();
        Assertions.assertEquals("0",game.p1.score);
        Assertions.assertEquals("0",game.p2.score);
    }
    @Test
    public void looseAdv_Deuce()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.score="DEUCE";
        game.p2.score="ADV";
        game.play();
        Assertions.assertEquals("DEUCE",game.p1.score);
        Assertions.assertEquals("DEUCE",game.p2.score);
    }

    @Test
    public void gameEnds_setScoreUpdated()
    {
        Game game = initializeTest(new PlayerOneWins());
        do
        {
            game.play();

        } while (!game.p1.score.equals("0"));

        Assertions.assertEquals(1,game.p1.setScore);
    }
    // Sprint2 UserStory 1:-----------------
    @Test
    public void winnerSetScore6AndOtherPlayerHasSetScoreLessThen4_winnerWinTheSet()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.setScore=5;
        game.p2.setScore=4;
        game.p1.score="40";
        game.p2.score="30";
        game.play();
        Assertions.assertTrue(game.p1.setWinned.get(0)==true);
        Assertions.assertTrue(game.p2.setWinned.get(0)==false);
        Assertions.assertTrue(game.p1.setScore==6);
        Assertions.assertTrue(game.p2.setScore==4);
    }
    @Test
    public void winnerSetScore6AndOtherPlayerHasSetScore5_newGameWith7BeingTheLimit()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.setScore=5;
        game.p2.setScore=5;
        game.p1.score="40";
        game.p2.score="30";
        game.play();
        Assertions.assertTrue(game.p1.setScore==6);
        Assertions.assertTrue(game.p2.setScore==5);
        Assertions.assertTrue(game.p1.score.equals("0"));
        Assertions.assertTrue(game.p2.score.equals("0"));
        do{
            game.play();
        }while(!game.p1.score.equals("0"));
        Assertions.assertTrue(game.p1.setWinned.get(0)==true);
        Assertions.assertTrue(game.p2.setWinned.get(0)==false);
        Assertions.assertTrue(game.p1.setScore==7);
        Assertions.assertTrue(game.p2.setScore==5);
    }
    // Sprint2 UserStory 2:-----------------
    @Test
    public void bothSetScore6OrMoreAndPlayerWithTwoMoreSets_PlayerWins()
    {
        Game game = initializeTest(new PlayerOneWins());
        game.p1.setScore=6;
        game.p2.setScore=6;
        game.p1.score="40";
        game.p2.score="30";
        game.play();
        Assertions.assertTrue(game.p1.setScore==7);
        Assertions.assertTrue(game.p2.setScore==6);
        Assertions.assertTrue(game.p1.score.equals("0"));
        Assertions.assertTrue(game.p2.score.equals("0"));
        do{
            game.play();
        }while(!game.p1.score.equals("0"));
        Assertions.assertTrue(game.p1.setWinned.get(0)==true);
        Assertions.assertTrue(game.p2.setWinned.get(0)==false);
        Assertions.assertTrue(game.p1.setScore==8);
        Assertions.assertTrue(game.p2.setScore==6);
    }

    //final test RandomPlay
    @Test
    public void randomRules_ResultAreDisplayed()
    {
        Game game = initializeTest(new RandomPlayerWins());
        game.playASet();
    }

}
