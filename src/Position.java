/**
 * Created by Rukshan on 11/28/2019.
 */
public class Position {
    private String unicode ;
    private float xDirAdj;
    private float yDirAdj;
    private String word;
    private int isUsed = 0;
    private float test;
    private String fullword;
    private int isFullWord = 0;

    public Position(String unicode, float xDirAdj, float yDirAdj) {
        this.unicode = unicode;
        this.xDirAdj = xDirAdj;
        this.yDirAdj = yDirAdj;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public float getxDirAdj() {
        return xDirAdj;
    }

    public void setxDirAdj(float xDirAdj) {
        this.xDirAdj = xDirAdj;
    }

    public float getyDirAdj() {
        return yDirAdj;
    }

    public void setyDirAdj(float yDirAdj) {
        this.yDirAdj = yDirAdj;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public float getTest() {
        return test;
    }

    public void setTest(float test) {
        this.test = test;
    }

    public String getFullword() {
        return fullword;
    }

    public void setFullword(String fullword) {
        this.fullword = fullword;
    }

    public int getIsFullWord() {
        return isFullWord;
    }

    public void setIsFullWord(int isFullWord) {
        this.isFullWord = isFullWord;
    }
}
