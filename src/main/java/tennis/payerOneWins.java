package tennis;

import java.util.Set;

public class payerOneWins extends AbstractRule{


    @Override
    protected String somebodyWins() {
        if (p1.score.equals("DEUCE")){
            if(p2.score.equals("ADV")){
                p2.score="DEUCE";
            }else{
                p1.score="ADV";
            }
        }else if (p1.score.equals("ADV")){
            p1.score="Winner";
        }
        else {
            int index = Game.scores.indexOf(p1.score);
            p1.score = Game.scores.get(index + 1);
        }
        return "1";
    }



}
