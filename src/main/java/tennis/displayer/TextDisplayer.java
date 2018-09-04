package tennis.displayer;

import tennis.History;

public class TextDisplayer implements Displayer {
    private final History history;

    public TextDisplayer(History history) {
        this.history = history;
    }

    public void display() {
        history.scores.stream().forEach(e->System.out.println(e.toString()));
    }
}
