/**
 * Created by Rukshan
 */
class Node {

    private float id;
    private float leftId;
    private float rightId;
    private float bottomId;
    private float topId;
    private String value;
    private Probability probability;
    private int totalPro;
    private Boolean isUsed;
    private Boolean isKeyWord;
    private Boolean isNotKeyWord;
    private Boolean destroy = false;

    Node() {
        id = 0;
        leftId = 0;
        bottomId = 0;
        rightId = 0;
        topId = 0;
        totalPro = 0;
        isUsed = false;
        isNotKeyWord = false;
        isKeyWord = false;
    }

    float getId() {
        return id;
    }

    void setId(float id) {
        this.id = id;
    }

    void setLeftId(float leftId) {
        this.leftId = leftId;
    }

    float getRightId() {
        return rightId;
    }

    void setRightId(float rightId) {
        this.rightId = rightId;
    }

    float getBottomId() {
        return bottomId;
    }

    void setBottomId(float bottomId) {
        this.bottomId = bottomId;
    }

    float getTopId() {
        return topId;
    }

    void setTopId(float topId) {
        this.topId = topId;
    }

    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    void setXCoordinate(float xCoordinate) {
        float xCoordinate1 = xCoordinate;
    }

    void setYCoordinate(float yCoordinate) {
        float yCoordinate1 = yCoordinate;
    }

    Probability getProbability() {
        return probability;
    }

    void setProbability(Probability probability) {
        this.probability = probability;
    }

    int getTotalPro() {
        return totalPro;
    }

    void setTotalPro(int totalPro) {
        this.totalPro = totalPro;
    }

    Boolean getUsed() {
        return isUsed;
    }

    void setUsed(Boolean used) {
        isUsed = used;
    }

    Boolean getKeyWord() {
        return isKeyWord;
    }

    void setKeyWord(Boolean keyWord) {
        isKeyWord = keyWord;
    }

    Boolean getNotKeyWord() {
        return isNotKeyWord;
    }

    void setNotKeyWord(Boolean notKeyWord) {
        isNotKeyWord = notKeyWord;
    }

    Boolean getDestroy() {
        return destroy;
    }

    void setDestroy(Boolean destroy) {
        this.destroy = destroy;
    }
}
