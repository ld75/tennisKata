package tennis;

public class TextAfficheur implements Afficheur {
    private final History history;

    public TextAfficheur(History history) {
        this.history = history;
    }

    public void affiche() {
        history.scores.stream().forEach(e->System.out.println(e.toString()));
    }
}
