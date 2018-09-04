package tennis.displayer;

import tennis.History;

public class TextAfficheur implements Displayer {
    private final History history;

    public TextAfficheur(History history) {
        this.history = history;
    }

    public void display() {
        history.scores.stream().forEach(e->System.out.println(e.toString()));
    }
}
