/**
 * Created by Rukshan
 */
class Probability {

    private int numberProbability;
    private int symbolProbability;
    private int spaceProbability;

    Probability(int numberProbability, int symbolProbability, int spaceProbability) {
        this.numberProbability = numberProbability;
        this.symbolProbability = symbolProbability;
        this.spaceProbability = spaceProbability;
    }

    int getNumberProbability() {
        return numberProbability;
    }

    int getSymbolProbability() {
        return symbolProbability;
    }

    int getSpaceProbability() {
        return spaceProbability;
    }

    void setSpaceProbability(int spaceProbability) {
        this.spaceProbability = spaceProbability;
    }
}
