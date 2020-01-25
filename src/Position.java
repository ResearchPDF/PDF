/**
 * Created by Rukshan
 */
class Position {
    private String unicode;
    private float xCoordinate;
    private float yCoordinate;
    private String word;
    private int isUsed;
    private float test;
    private String fullWord;
    private int isFullWord;

    Position(String unicode, float xCoordinate, float yCoordinate) {
        this.unicode = unicode;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        isUsed = 0;
        isFullWord = 0;
    }

    String getUnicode() {
        return unicode;
    }

    float getxCoordinate() {
        return xCoordinate;
    }

    float getyCoordinate() {
        return yCoordinate;
    }

    String getWord() {
        return word;
    }

    void setWord(String word) {
        this.word = word;
    }

    int getIsUsed() {
        return isUsed;
    }

    void setIsUsed() {
        this.isUsed = 1;
    }

    float getTest() {
        return test;
    }

    void setTest(float test) {
        this.test = test;
    }

    String getFullWord() {
        return fullWord;
    }

    void setFullWord(String fullWord) {
        this.fullWord = fullWord;
    }

    int getIsFullWord() {
        return isFullWord;
    }

    void setIsFullWord() {
        this.isFullWord = 1;
    }
}
