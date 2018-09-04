package tennis;

import tennis.displayer.Displayer;
import tennis.displayer.ScoreData;
import tennis.displayer.TextDisplayer;
import tennis.winnerRules.IRule;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static List<String> scores= new ArrayList<String>();
    protected final Joueur p2;
    protected final Joueur p1;
    public History history;
    private IRule rule;
    private Displayer displayer;

    public Game(Joueur joueur1, Joueur joueur2) {
        this.p1=joueur1;
        this.p2=joueur2;
    }

    public void setRules(IRule rule) {
        this.rule = rule;
        this.rule.setPlayer1(this.p1);
        this.rule.setPlayer2(this.p2);
    }
    public void initialize()
    {
        history=new History();
        displayer = new TextDisplayer(history);
        scores.add("15");
        scores.add("30");
        scores.add("40");
        scores.add("Winner");
    }

    public void playOneBall() {
            rule.runOneBall();
            fillHistory();
            displayer.displayLastExchage();
    }

    private void fillHistory() {

        ScoreData scoredata = new ScoreData(p1.score,p2.score,p1.setScore,p2.setScore);
        history.scores.add(scoredata);
    }

    public void playASet() {
        while(p1.setWinned.size()<2)
            playOneBall();
    }
    public void playAMatch() {
        while(p1.winTheMatch!=true && p2.winTheMatch!=true) {
            playOneBall();
        }
        System.out.println("-----------RESULTS-----------");
        displayer.displayAll();
        System.out.println("RESULTS! winner: is "+(p1.winTheMatch?"p1":"p2"));
    }
}
