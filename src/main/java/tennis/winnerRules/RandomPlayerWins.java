package tennis.winnerRules;

import tennis.Game;
import tennis.Joueur;

import java.security.SecureRandom;

public class RandomPlayerWins extends AbstractRule {


    private Joueur looser;
    private Joueur winner;

    @Override
    protected String somebodyWins() {
        setRandomWinner();
        if (winner.score.equals(DEUCE)){
            if(looser.score.equals(ADV)){
                looser.score=DEUCE;
            }else{
                winner.score=ADV;
            }
        }else if (winner.score.equals(ADV)){
            winner.score= WINNER;
        }
        else {
            int index = Game.scores.indexOf(winner.score);
            winner.score = Game.scores.get(index + 1);
        }
        return "1";
    }

    private void setRandomWinner() {
        winner = p1;
        looser = p2;
        SecureRandom random = new SecureRandom();
        int deisgnWinner = random.nextInt(2) + 1;
        if (deisgnWinner == 2) {
            winner = p2;
            looser = p1;
        }
    }


}
