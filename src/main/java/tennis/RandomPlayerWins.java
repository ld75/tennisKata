package tennis;

import java.security.SecureRandom;

public class RandomPlayerWins extends AbstractRule{



    @Override
    protected String somebodyWins() {
        SecureRandom random = new SecureRandom();
        int deisgnWinner = random.nextInt(2)+1;
        Joueur winner = p1;
        Joueur looser = p2;
        if (deisgnWinner==2) {
            winner = p2;
            looser = p1;
        }
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



}
