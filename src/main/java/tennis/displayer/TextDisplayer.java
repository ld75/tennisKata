package tennis.displayer;

import tennis.History;

public class TextDisplayer implements Displayer {
    private final History history;

    public TextDisplayer(History history) {
        this.history = history;
    }

    public void displayAll() {
        history.scores.stream().forEach(this::displayScoreData);
    }

    @Override
    public void displayLastExchage() {
        displayScoreData(history.scores.get(history.scores.size()-1));
    }

    private void displayScoreData(ScoreData e) {
        String display = String.format("%1$10S%2$10S%3$10S%4$10S", e.score1,e.score2,Integer.toString(e.set1),Integer.toString(e.set2)); //%argumentNumeroCommenceParUn$-tailleMinARespecterAjouteEspaceSiPlusPetit (si l'argument est plus grand que -xS il ne le tronque pas) S ou s pour la casse
        System.out.println(display);
    }
}
