package tennis.winnerRules;

import tennis.Joueur;

public interface IRule {
    void setPlayer2(Joueur p2);

    void setPlayer1(Joueur p1);

    void runOneBall();
}
