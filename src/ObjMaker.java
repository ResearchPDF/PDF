/**
 * Created by Rukshan
 */
class ObjMaker {
    private Node node = new Node();

    ObjMaker() {
    }

    Node objCreate(String[] values, float key) {

        node.setId(key);
        node.setXCoordinate(Float.parseFloat(values[1]));
        node.setYCoordinate(Float.parseFloat(values[2]));
        node.setValue(values[0]);
        ProbabilityCalculator obj = new ProbabilityCalculator();
        node.setProbability(obj.probSetter(values[0]));
        node.setTotalPro(node.getProbability().getNumberProbability() + node.getProbability().getSpaceProbability() + node.getProbability().getSymbolProbability());

        return node;
    }

    Node objMakeTwo(String[] values, float key, String[] val) {

        node.setId(key);
        node.setXCoordinate(Float.parseFloat(values[1]));
        node.setYCoordinate(Float.parseFloat(values[2]));
        node.setValue(values[0]);
        ProbabilityCalculator obj = new ProbabilityCalculator();
        node.setProbability(obj.probSetterTwo(values[0], val));
        node.setTotalPro(node.getProbability().getNumberProbability() + node.getProbability().getSpaceProbability() + node.getProbability().getSymbolProbability());

        return node;
    }

    int spaceProbabilityMaker(String[] val) {

        ProbabilityCalculator obj = new ProbabilityCalculator();
        return obj.spaceCheck(val);
    }
}
