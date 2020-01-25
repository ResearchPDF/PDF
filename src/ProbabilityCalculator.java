/**
 * Created by Rukshan
 */
class ProbabilityCalculator {

    ProbabilityCalculator() {
    }

    Probability probSetter(String value) {

        Probability obj;
        obj = new Probability(numCheck(value), symbolCheck(value), 0);
        return obj;
    }

    Probability probSetterTwo(String val, String[] value) {

        Probability obj;
        obj = new Probability(numCheck(val), symbolCheck(val), spaceCheck(value));
        return obj;
    }

    private int numCheck(String value) {
        int e;
        for (e = 0; e < value.length(); e++) {
            if (Character.isDigit(value.charAt(e))) {

                break;
            }

        }
        if (e == value.length()) {
            return +2;
        } else {
            return -2;
        }

    }

    private int symbolCheck(String value) {
        if (value.charAt(value.length() - 1) == ':' | value.charAt(value.length() - 1) == '-') {
            return 2;
        } else {
            return 0;
        }

    }

    int spaceCheck(String[] val) {
        float idPositionX = Float.parseFloat(val[0]);
        float idPositionY = Float.parseFloat(val[1]);
        float xPosition = Float.parseFloat(val[2]);
        float yPosition = Float.parseFloat(val[3]);
        if ((xPosition - idPositionX) > (yPosition - idPositionY)) {
            return 1;
        } else {
            return 0;
        }

    }
}
