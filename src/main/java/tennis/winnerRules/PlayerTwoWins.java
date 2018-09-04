package tennis.winnerRules;

import tennis.Game;

public class PlayerTwoWins extends AbstractRule {


    @Override
    protected String somebodyWins() {
        if (p2.score.equals(DEUCE1)){
            if(p1.score.equals(ADV)){
                p1.score= DEUCE;
            }else{
                p2.score=ADV;
            }
        }else if (p2.score.equals(ADV)){
            p2.score= WINNER;
        }
        else {
            int index = Game.scores.indexOf(p2.score);
            p2.score = Game.scores.get(index + 1);
        }
        return "1";
    }



}
