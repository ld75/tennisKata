package tennis;

import tennis.displayer.Displayer;
import tennis.displayer.TextDisplayer;
import tennis.winnerRules.IRule;

import java.util.ArrayList;
import java.util.Hashtable;
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

    public void play() {
        if(p1.score.equals("Winner")|| p2.score.equals("Winner"))
        {
            fillHistory();
            displayer.display();
        }
        else {
            rule.runOnePlay();
            fillHistory();
            displayer.display();
        }
    }

    private void fillHistory() {
        Hashtable<String, String> iteration = new Hashtable<String, String>();
        iteration.put(p1.score, p2.score);
        history.scores.add(iteration);
    }
}
