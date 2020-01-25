import java.util.*;

class LogicSpace {

    private static float pageWidth = ReadPdf.pageWidth;
    private static List<Position> positionList;

    static {
        positionList = ReadPdf.getPositionList();
    }

    private Map<Float, Node> hashMapWords = new HashMap<>();
    private Map<String, String> hashMapRelations = new HashMap<>();


    LogicSpace() {
        // TODO Auto-generated constructor stub
    }

    private static String[][] getXYValues(int b) {
        String p[] = new String[6];
        int q = 0, r = 1;
        int a1;
        float xCoordinate = positionList.get(b).getxCoordinate();
        float yCoordinate = positionList.get(b).getyCoordinate();
        p[0] = positionList.get(b).getFullWord();
        for (a1 = b + 1; a1 < positionList.size() - 1; a1++) {
            if (positionList.get(a1).getIsFullWord() == 1) {
                float xXCoordinate = positionList.get(a1).getxCoordinate();
                float xYCoordinate = positionList.get(a1).getyCoordinate();
                if (yCoordinate == xYCoordinate & (xXCoordinate - xCoordinate) < pageWidth / 2.5) {
                    p[r] = positionList.get(a1).getFullWord();
                    break;
                } else {
                    break;
                }
            }
        }
        int counterX = 0;
        for (String aP : p)
            if (aP != null) {
                counterX++;
            }
        String values[][] = new String[4][10];
        if (counterX % 2 == 0) {
            for (int c = 0; c < counterX; c++) {
                values[q][0] = p[c];
                values[q][1] = p[c + 1];
                values[q][2] = "" + positionList.get(b).getyCoordinate();
                values[q][3] = "" + positionList.get(b).getxCoordinate();
                values[q][4] = "X";
                values[q][5] = "" + positionList.get(a1).getyCoordinate();
                values[q][6] = "" + positionList.get(a1).getxCoordinate();
                c++;
                q++;
            }
        }
        String py[] = new String[6];
        int ry = 1;
        int a;
        py[0] = positionList.get(b).getFullWord();
        for (a = b + 1; a < positionList.size() - 1; a++) {
            if (positionList.get(a).getIsFullWord() == 1) {
                float yYCoordinate = positionList.get(a).getyCoordinate();
                float yXCoordinate = positionList.get(a).getxCoordinate();
                if (yXCoordinate == xCoordinate & (yYCoordinate - yCoordinate) < 40) {
                    py[ry] = positionList.get(a).getFullWord();
                    break;
                }
            }

        }
        int counterY = 0;
        for (String aPy : py) {
            if (aPy != null) {
                counterY++;
            }
        }
        if (counterY % 2 == 0) {
            for (int c = 0; c < counterY; c++) {
                values[q][0] = py[c];
                values[q][1] = py[c + 1];
                values[q][2] = "" + positionList.get(b).getyCoordinate();
                values[q][3] = "" + positionList.get(b).getxCoordinate();
                values[q][4] = "Y";
                values[q][5] = "" + positionList.get(a).getyCoordinate();
                values[q][6] = "" + positionList.get(a).getxCoordinate();
                c++;
                q++;
            }
        }
        String[][] subArray = new String[q][10];
        for (int i = 0; i < q; i++) {
            subArray[i] = Arrays.copyOfRange(values[i], 0, 9);
        }
        return subArray;
    }

    void getText() {

        List<String> arr = new ArrayList<>(5);
        arr.add(" ");
        arr.add("\n");
        arr.add("\t");
        arr.add("x0B");
        arr.add("\f");
        arr.add("\r");

        System.out.println(positionList.size() - 1);
        //Create words within the loop
        for (int a = 0; a < positionList.size(); a++) {
            Position position = positionList.get(a);
            if (position.getUnicode() == null) {
                break;
            }
            if (!arr.contains(position.getUnicode())) {
                String ab = position.getUnicode();
                int b = 1;
                while (!arr.contains(positionList.get(a + b).getUnicode())) {
                    float f1 = positionList.get(a + b - 1).getyCoordinate();
                    float f2 = positionList.get(a + b).getyCoordinate();
                    float f3 = positionList.get(a + b - 1).getxCoordinate();
                    float f4 = positionList.get(a + b).getxCoordinate();
                    float f5 = f4 - f3;
                    if (f1 == f2) {
                        if (0 < f5 & f5 < 15) {
                            ab = ab + positionList.get(a + b).getUnicode();
                            if ((a + b) >= positionList.size() - 1) {
                                break;
                            }
                            b++;
                        } else {
                            b--;
                            break;
                        }
                    } else {
                        b--;
                        break;
                    }
                }
                positionList.get(a).setWord(ab);
                positionList.get(a).setIsUsed();
                positionList.get(a).setTest(positionList.get(a + b).getxCoordinate());
                a = a + b;
            }
        }
        for (int a = 0; a < positionList.size(); a++) {
            if (positionList.get(a).getIsUsed() == 1) {
                String fullWord = positionList.get(a).getWord();
                int b = 1;
                int c = a;
                while (b < 50) {
                    if ((a + b) >= positionList.size() - 1) {
                        break;
                    }
                    if (positionList.get(a + b).getIsUsed() == 1) {
                        float f1 = positionList.get(a + b).getxCoordinate();
                        float f2 = positionList.get(c).getTest();
                        if (1 < f1 - f2 & f1 - f2 < 5) {
                            fullWord = fullWord + " " + positionList.get(a + b).getWord();
                            c = a + b;
                        } else {
                            break;
                        }
                    }
                    b++;
                }

                positionList.get(a).setIsFullWord();
                positionList.get(a).setFullWord(fullWord);
                a = a + b - 1;
            }
        }
        for (int a = 0; a < positionList.size() - 1; a++) {
            if (positionList.get(a).getIsFullWord() == 1) {
                String[][] value = getXYValues(a);
                if (value.length > 1) {
                    float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                    float idXValue = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                    float idYValue = objIdMaker(Float.parseFloat(value[1][6]), Float.parseFloat(value[1][5]));
                    Node node;
                    String[] probVal = {value[0][3], value[0][2], value[0][6], value[1][5]};
                    if (!hashMapWords.containsKey(idKey)) {
                        ObjMaker obj = new ObjMaker();
                        String[] objVal = {value[0][0], value[0][3], value[0][2]};

                        node = obj.objMakeTwo(objVal, idKey, probVal);
                        node.setRightId(idXValue);
                        node.setBottomId(idYValue);
                        hashMapWords.put(idKey, node);
                    } else {
                        ObjMaker obj = new ObjMaker();
                        node = hashMapWords.get(idKey);
                        node.getProbability().setSpaceProbability(obj.spaceProbabilityMaker(probVal));
                        node.setTotalPro(node.getProbability().getSpaceProbability() + node.getTotalPro());
                        node.setRightId(idXValue);
                        node.setBottomId(idYValue);
                    }
                    if (!hashMapWords.containsKey(idXValue)) {
                        ObjMaker obj = new ObjMaker();
                        String[] objVal = {value[0][1], value[0][5], value[0][6]};
                        node = obj.objCreate(objVal, idXValue);
                        node.setLeftId(idKey);
                        hashMapWords.put(idXValue, node);
                    } else {
                        node = hashMapWords.get(idXValue);
                        node.setLeftId(idKey);
                    }
                    if (!hashMapWords.containsKey(idYValue)) {
                        ObjMaker obj = new ObjMaker();
                        String[] objVal = {value[1][1], value[1][5], value[1][6]};
                        node = obj.objCreate(objVal, idYValue);
                        node.setTopId(idKey);
                        hashMapWords.put(idYValue, node);
                    } else {
                        node = hashMapWords.get(idYValue);
                        node.setTopId(idKey);
                    }

                } else {
                    if (value.length == 1) {
                        if (value[0][4].equals("X")) {
                            float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                            float idXValue = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                            if (!hashMapWords.containsKey(idKey)) {
                                ObjMaker obj = new ObjMaker();
                                String[] objVal = {value[0][0], value[0][3], value[0][2]};
                                Node node = obj.objCreate(objVal, idKey);
                                node.setRightId(idXValue);
                                hashMapWords.put(idKey, node);
                            } else {
                                Node node = hashMapWords.get(idKey);
                                node.setRightId(idXValue);
                            }
                            if (!hashMapWords.containsKey(idXValue)) {
                                ObjMaker obj = new ObjMaker();
                                String[] objVal = {value[0][1], value[0][5], value[0][6]};
                                Node node = obj.objCreate(objVal, idXValue);
                                node.setLeftId(idKey);
                                hashMapWords.put(idXValue, node);
                            } else {
                                Node node = hashMapWords.get(idXValue);
                                node.setLeftId(idKey);
                            }
                        } else if (value[0][4].equals("Y")) {
                            float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                            float idYValue = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                            if (!hashMapWords.containsKey(idKey)) {
                                ObjMaker obj = new ObjMaker();
                                String[] objVal = {value[0][0], value[0][3], value[0][2]};
                                Node node = obj.objCreate(objVal, idKey);
                                node.setBottomId(idYValue);
                                hashMapWords.put(idKey, node);
                            } else {
                                Node node = hashMapWords.get(idKey);
                                node.setBottomId(idYValue);
                            }
                            if (!hashMapWords.containsKey(idYValue)) {
                                ObjMaker obj = new ObjMaker();
                                String[] objVal = {value[0][1], value[0][5], value[0][6]};
                                Node node = obj.objCreate(objVal, idYValue);
                                node.setTopId(idKey);
                                hashMapWords.put(idYValue, node);
                            } else {
                                Node node = hashMapWords.get(idYValue);
                                node.setTopId(idKey);
                            }
                        }

                    }
                }
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if ((!node.getUsed()) & (node.getRightId() != 0 & node.getBottomId() != 0)) {
                valChanger(node.getId());
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if ((!node.getUsed()) & (node.getRightId() != 0 & node.getBottomId() == 0) & node.getProbability().getNumberProbability() == 2) {
                node.setUsed(true);
                node.setKeyWord(true);
                hashMapWords.get(node.getRightId()).setUsed(true);
                hashMapWords.get(node.getRightId()).setNotKeyWord(true);
                hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
            } else if ((!node.getUsed()) & (node.getRightId() == 0 & node.getBottomId() != 0) & node.getProbability().getNumberProbability() == 2) {
                node.setUsed(true);
                node.setKeyWord(true);
                hashMapWords.get(node.getBottomId()).setUsed(true);
                hashMapWords.get(node.getBottomId()).setNotKeyWord(true);
                hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
            }
        }
        //checking the conditions
        for (String key : hashMapRelations.keySet()) {
            System.out.println("Keyword : " + key + "\tValue :" + hashMapRelations.get(key));
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if (!node.getUsed()) {
                if (node.getRightId() == 0 & node.getBottomId() != 0) {
                    if (node.getKeyWord()) {
                        hashMapWords.get(node.getBottomId()).setNotKeyWord(true);
                    }
                }
                if (node.getRightId() != 0 & node.getBottomId() == 0) {
                    if (node.getKeyWord()) {
                        hashMapWords.get(node.getRightId()).setNotKeyWord(true);
                    }
                }
            }
        }
        for (Object key : hashMapWords.keySet()) {
            Node node = hashMapWords.get(key);
            if (node != null) {
                if (node.getRightId() != 0 & node.getBottomId() != 0) {
                    ////System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val + "\t Next " +hashMapWords.get(node.getBottomId()).val);
                    if (hashMapWords.get(node.getRightId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord(true);
                        node.setUsed(true);
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val);
                    }
                    if (hashMapWords.get(node.getBottomId()).getKeyWord()) {
                        hashMapWords.get(node.getRightId()).setNotKeyWord(true);
                        node.setUsed(true);
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getBottomId()).val);
                    }
                    if (hashMapWords.get(node.getRightId()).getKeyWord()) {
                        hashMapWords.get(node.getBottomId()).setNotKeyWord(true);
                        node.setUsed(true);
                        // System.out.println("Keyword : " + node.val + "\tValue :" +hashMapWords.get(node.getRightId()).val);
                    }
                }
                if (node.getRightId() == 0 & node.getBottomId() != 0) {
                    if (hashMapWords.get(node.getBottomId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord(true);
                        node.setUsed(true);
                    }
                }

                if (node.getRightId() != 0 & node.getBottomId() == 0) {
                    if (hashMapWords.get(node.getRightId()).getNotKeyWord() & !node.getNotKeyWord()) {
                        node.setKeyWord(true);
                        node.setUsed(true);
                    }
                }
            }
        }

    }

    private int valChanger(Float id) {
        Node node = hashMapWords.get(id);
        if (node.getRightId() != 0 & node.getBottomId() == 0) {
            if (node.getTopId() != 0) {
                hashMapWords.get(node.getTopId()).setTotalPro(hashMapWords.get(node.getTopId()).getTotalPro() + 1);
                if (hashMapWords.get(node.getRightId()).getTopId() != 0) {
                    node.setUsed(true);
                    node.setKeyWord(true);
                    hashMapWords.get(node.getRightId()).setUsed(true);
                    hashMapWords.get(node.getRightId()).setNotKeyWord(true);
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
                    hashMapWords.get(hashMapWords.get(node.getRightId()).getTopId()).setTotalPro(hashMapWords.get(hashMapWords.get(node.getRightId()).getTopId()).getTotalPro() - 1);
                    return 0;
                }
            } else {
                node.setUsed(true);
                node.setKeyWord(true);
                hashMapRelations.put(node.getValue(), "");
                hashMapWords.get(node.getRightId()).setTotalPro(hashMapWords.get(node.getRightId()).getTotalPro() + 1);
                return valChanger(node.getRightId());
            }
            return 0;
        } else if (node.getRightId() == 0 & node.getBottomId() != 0) {
            node.setUsed(true);
            node.setKeyWord(true);
            hashMapWords.get(node.getBottomId()).setUsed(true);
            hashMapWords.get(node.getBottomId()).setNotKeyWord(true);
            hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
            return 0;
        }
        if ((!node.getUsed()) & node.getRightId() != 0 & node.getBottomId() != 0) {
            if (node.getTotalPro() < hashMapWords.get(node.getRightId()).getTotalPro()) {
                return 0;
            }
            if (hashMapWords.get(node.getRightId()).getTotalPro() < hashMapWords.get(node.getBottomId()).getTotalPro()) {
                //System.out.println("Keyword : " + node.val+ "\tValue :"+ node.totalPro);
                if (node.getTotalPro() > hashMapWords.get(node.getRightId()).getTotalPro()) {
                    node.setUsed(true);
                    node.setKeyWord(true);
                    hashMapWords.get(node.getRightId()).setUsed(true);
                    hashMapWords.get(node.getRightId()).setNotKeyWord(true);
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getRightId()).getValue());
                    hashMapWords.get(node.getBottomId()).setTotalPro(hashMapWords.get(node.getBottomId()).getTotalPro() + 1);
                    if (hashMapWords.get(node.getRightId()).getBottomId() != 0) {
                        hashMapWords.get(hashMapWords.get(node.getRightId()).getBottomId()).setTotalPro(hashMapWords.get(hashMapWords.get(node.getRightId()).getBottomId()).getTotalPro() - 1);
                    }
                    return valChanger(node.getBottomId());
                } else {
                    return 0;
                }
            } else if (hashMapWords.get(node.getRightId()).getTotalPro() == hashMapWords.get(node.getBottomId()).getTotalPro()) {
                return valChanger(node.getBottomId());
            } else {
                if (node.getTotalPro() > hashMapWords.get(node.getBottomId()).getTotalPro()) {
                    node.setUsed(true);
                    node.setKeyWord(true);
                    hashMapWords.get(node.getBottomId()).setUsed(true);
                    hashMapWords.get(node.getBottomId()).setNotKeyWord(true);
                    hashMapRelations.put(node.getValue(), hashMapWords.get(node.getBottomId()).getValue());
                    hashMapWords.get(node.getRightId()).setTotalPro(hashMapWords.get(node.getRightId()).getTotalPro() + 1);
                    if (hashMapWords.get(node.getBottomId()).getRightId() != 0) {
                        hashMapWords.get(hashMapWords.get(node.getBottomId()).getRightId()).setTotalPro(hashMapWords.get(hashMapWords.get(node.getBottomId()).getRightId()).getTotalPro() - 1);
                    }
                    return valChanger(node.getRightId());
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    private float objIdMaker(float x, float y) {
        return x + (y / 1000);
    }

}
