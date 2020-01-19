/**
 * Created by Rukshan
 */
class ProbCalculater {

    private String[] vale;

    ProbCalculater() {
    }

    Probility probSetter(String val) {

        Probility obj;
        obj = new Probility(numCheck(val), symbolCheck(val),0);
        return obj;
    }

    Probility probSetterTwo(String val,String[] value) {

        Probility obj;
        obj = new Probility(numCheck(val), symbolCheck(val),spaceCheck(value));
        return obj;
    }
    private int numCheck(String val) {
        int e;
        for (e = 0; e < val.length(); e++) {
            if (Character.isDigit(val.charAt(e))) {

                break;
            }

        }
        if (e == val.length()) {
            return +2;
        } else {
            return -2;
        }

    }

    private int symbolCheck(String val) {
        if (val.charAt(val.length() - 1) == ':' | val.charAt(val.length() - 1) == '-') {
            return 2;
        } else {
            return 0;
        }

    }

    int spaceCheck(String[] val) {
        float idPosX = Float.parseFloat(val[0]);
        float idPosY = Float.parseFloat(val[1]);
        float xPos = Float.parseFloat(val[2]);
        float yPos = Float.parseFloat(val[3]);
        if ((xPos-idPosX)>(yPos - idPosY)) {
            return 1;
        } else {
            return 0;
        }

    }
}
