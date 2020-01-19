/**
 * Created by Rukshan
 */
class ObjCreater {
    private Node node = new Node();
    private Node nodeBottom = new Node();
    private Node nodeLeft = new Node();

    ObjCreater() {
    }
//    public ObjCreater(String[][] values, float[] keys) {
//
//        node.id = keys[0];
//        node.xcordinate = Float.parseFloat(values[0][3]);
//        node.ycordinate = Float.parseFloat(values[0][2]);
//        node.val = values[0][0];
//
//        if(values.length==2) {
//            Probility[]  prob = probMaker(values);
//            node.rightId = keys[1];
//            node.bottomId = keys[2];
//            node.prob = prob[0];
//            node.rightProb = prob[1];
//            node.bottomProb = prob[2];
//            String[] val = {values[0][0],values[0][1],values[1][1],values[0][2],values[0][3],values[0][5]
//                    ,values[0][6],values[1][5],values[1][6]};
//            leftObjMaker(val,keys);
//            bottomObjMaker(val,keys);
//            LogicSpace.nodeArray[LogicSpace.nodeCount] = node;
//            LogicSpace.nodeArray[LogicSpace.nodeCount+1] = nodeBottom;
//            LogicSpace.nodeArray[LogicSpace.nodeCount+2] = nodeLeft;
//            LogicSpace.nodeCount = LogicSpace.nodeCount+3;
//        }
//        else if(values.length==1){
//            String[] val = {values[0][0],values[0][1],values[0][2],values[0][3],values[0][5]
//                    ,values[0][6]};
//            System.out.println("\nCreated   : " );
//            if(values[0][4].equals("X")) {
//                Probility[]  prob = probMaker(values);
//                node.rightId = keys[1];
//                node.prob = prob[0];
//                node.rightProb = prob[1];
//                System.out.println("\nCreated   : " );
//                leftObjMaker(val,keys);
//                LogicSpace.nodeArray[LogicSpace.nodeCount] = node;
//                LogicSpace.nodeArray[LogicSpace.nodeCount+1] = nodeLeft;
//                LogicSpace.nodeCount = LogicSpace.nodeCount+2;
//            }
//            else{
//                Probility[]  prob = probMaker(values);
//                node.bottomId = keys[1];
//                node.prob = prob[0];
//                node.bottomProb = prob[1];
//                bottomObjMaker(val,keys);
//                LogicSpace.nodeArray[LogicSpace.nodeCount] = node;
//                LogicSpace.nodeArray[LogicSpace.nodeCount+1] = nodeBottom;
//                LogicSpace.nodeCount = LogicSpace.nodeCount+2;
//            }
//        }
//
//    }

    Node objCreate(String[] values, float key) {

        node.id = key;
        node.xcordinate = Float.parseFloat(values[1]);
        node.ycordinate = Float.parseFloat(values[2]);
        node.val = values[0];
        ProbCalculater obj = new ProbCalculater();
        node.prob = obj.probSetter(values[0]);
        node.totalPro = (node.prob.numProb + node.prob.spaceProb+node.prob.symProb);

        return node;
    }
    Node objCreatetwo(String[] values, float key , String[] val) {

        node.id = key;
        node.xcordinate = Float.parseFloat(values[1]);
        node.ycordinate = Float.parseFloat(values[2]);
        node.val = values[0];
        ProbCalculater obj = new ProbCalculater();
        node.prob = obj.probSetterTwo(values[0],val);
        node.totalPro = (node.prob.numProb + node.prob.spaceProb+node.prob.symProb);

        return node;
    }

//    private Probility[] probMaker(String[][] values) {
//
//        if (values.length == 2) {
//            String[] val = {values[0][0], values[0][1], values[1][1], values[0][2], values[0][3], values[0][5]
//                    , values[0][6], values[1][5], values[1][6]};
//            ProbCalculater obj = new ProbCalculater(val);
//            return obj.probSetterTwo();
//
//        } else if (values.length == 1) {
//            String[] val = {values[0][0], values[0][1], values[0][2], values[0][3], values[0][5]
//                    , values[0][6]};
//            ProbCalculater obj = new ProbCalculater(val);
//            return obj.probSetterOne();
//        } else {
//            return new Probility[1];
//        }
//    }

    int spaceProbMaker(String[] val) {

        ProbCalculater obj = new ProbCalculater();
        return obj.spaceCheck(val);
    }

    void bottomObjMaker(String[] val, float[] keys) {

        nodeBottom.id = keys[1];
        nodeLeft.topId = keys[0];
        nodeBottom.xcordinate = Float.parseFloat(val[4]);
        nodeBottom.ycordinate = Float.parseFloat(val[5]);
        nodeBottom.val = val[1];
        System.out.println("Created bottom  : " + val[1]);
    }


    Probility probMakerOne(String value) {

        ProbCalculater obj = new ProbCalculater();
        return obj.probSetter(value);
    }

}
