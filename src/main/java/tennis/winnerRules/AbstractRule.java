package tennis.winnerRules;

import tennis.Joueur;
import tennis.winnerRules.IRule;

public abstract class AbstractRule implements IRule {
    public static final String WINNER = "Winner";
    public static final String DEUCE = "DEUCE";
    public static final String ADV = "ADV";
    public static final String DEUCE1 = "DEUCE";
    protected Joueur p1;
    protected Joueur p2;

    public void setPlayer2(Joueur p2) {
        this.p2=p2;
    }

    public void setPlayer1(Joueur p1) {
        this.p1=p1;
    }

    public void runOnePlay() {
        if(noorDeuceOrAdv()) {
            somebodyWins();
        }
        if(boothScores40())
        {
            p1.score="DEUCE";
            p2.score="DEUCE";
        }
        else if (somebodyWinnedASet()){
            updateSetScores();
            updateSetWinned();
            p1.score="0";
            p2.score="0";
        }

    }

    protected abstract String somebodyWins();

    private boolean boothScores40() {
        return p1.score.equals("40") && p2.score.equals("40");
    }

    private void updateSetWinned() {
        if(p1.setScore==6 && p2.setScore<=4){
            p1.setWinned.add(true);
            p2.setWinned.add(false);
        }
        else if(p2.setScore==6 && p1.setScore<=4){
            p1.setWinned.add(false);
            p2.setWinned.add(true);
        }
        else if(isTieBreak() && Math.abs(p1.setScore - p2.setScore) >= 2)
        {
            if (p1.setScore>p2.setScore)
            {
                p1.setWinned.add(true);
                p2.setWinned.add(false);
            }
            else {
                p1.setWinned.add(false);
                p2.setWinned.add(true);
            }
        }
        else if(p1.setScore==6 && p2.setScore>4){
            System.out.println("up To 7!");
        }
        else if(p2.setScore==6 && p1.setScore>4){
            System.out.println("up To 7!");
        }
        else if(p1.setScore==7){
            p1.setWinned.add(true);
            p2.setWinned.add(false);
        }
        else if(p2.setScore==7){
            p1.setWinned.add(false);
            p2.setWinned.add(true);
        }

    }

    private boolean isTieBreak() {
        return p1.setScore > 5 && p2.setScore > 5;
    }

    private void updateSetScores() {
        if(p1.score.equals("Winner")){
            p1.setScore=p1.setScore+1;
        }
        else{
            p2.setScore=p2.setScore+1;
        }
    }

    private boolean somebodyWinnedASet() {
        return p1.score.equals("Winner") || p2.score.equals("Winner");
    }

    private boolean noorDeuceOrAdv() {
        return !p1.score.equals("DEUCE")||!p2.score.equals("DEUCE")||!p1.score.equals("ADV")||!p2.score.equals("ADV");
    }
}
