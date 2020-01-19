import java.util.*;
import java.io.FileWriter;

class LogicSpace {

    static String[][] valuesPair = new String[100][7];
    private static float pWid = ReadPdf.pWid;
    private static String[][] value = new String[10][10];
    Map<Float, Node> hmm = new HashMap<>();
    Map<String, String> hmm2 = new HashMap<>();
    static List<Position> positionList = ReadPdf.positionList;


    LogicSpace() {
        // TODO Auto-generated constructor stub

    }

    private static String[][] getX(Float x, int b) {
        String p[] = new String[6];
        int q = 0, r = 1;
        int a1;
        p[0] = positionList.get(b).getFullword();
        for (a1 = b + 1; a1 < positionList.size()-1; a1++) {
            if (positionList.get(a1).getIsFullWord() == 1) {
                float f1 = positionList.get(b).getxDirAdj();
                float f2 = positionList.get(a1).getxDirAdj();
                float f3 = positionList.get(a1).getyDirAdj();
                if (f3 == x & (f2 - f1) < pWid / 2.5) {
                    p[r] = positionList.get(a1).getFullword();
                    break;
                } else {
                    break;
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < p.length; i++) {
            if (p[i] != null) {
                counter++;
            }
        }
        String values[][] = new String[4][10];
        if (counter % 2 == 0) {
            for (int c = 0; c < counter; c++) {
                values[q][0] = p[c];
                values[q][1] = p[c + 1];
                values[q][2] = ""+positionList.get(b).getyDirAdj();
                values[q][3] =  ""+positionList.get(b).getxDirAdj();
                values[q][4] = "X";
                values[q][5] =  ""+positionList.get(a1).getyDirAdj();
                values[q][6] =  ""+positionList.get(a1).getxDirAdj();
                c++;
                q++;
            }
        }
        String py[] = new String[6];
        int ry = 1;
        int a;
        py[0] = positionList.get(b).getFullword();
        float f1 = positionList.get(b).getxDirAdj();
        for (a = b + 1; a <  positionList.size()-1; a++) {
            if (positionList.get(a).getIsFullWord() == 1) {
                float f4 = positionList.get(b).getyDirAdj();
                float f2 = positionList.get(a).getyDirAdj();
                float f3 = positionList.get(a).getxDirAdj();
                if (f3 == f1 & (f2 - f4) < 40) {
                    py[ry] = positionList.get(a).getFullword();
                    break;
                }
            }
        }
        int countery = 0;
        for (int i = 0; i < py.length; i++) {
            if (py[i] != null) {
                countery++;
            }
        }
        if (countery % 2 == 0) {
            for (int c = 0; c < countery; c++) {
                values[q][0] = py[c];
                values[q][1] = py[c + 1];
                values[q][2] = ""+positionList.get(b).getyDirAdj();
                values[q][3] = ""+positionList.get(b).getxDirAdj();
                values[q][4] = "Y";
                values[q][5] = ""+positionList.get(a).getyDirAdj();
                values[q][6] = ""+positionList.get(a).getxDirAdj();
                c++;
                q++;
            }
        }
        String[][] subarray = new String[q][10];
        for (int i = 0; i < q; i++) {
            subarray[i] = Arrays.copyOfRange(values[i], 0, 9);
        }
        return subarray;
    }

    void getText() {

        List<String> arr = new ArrayList<>(5);
        arr.add(" ");
        arr.add("\n");
        arr.add("\t");
        arr.add("x0B");
        arr.add("\f");
        arr.add("\r");

        System.out.println(positionList.size()-1);
        //Create words whin the loop
        for (int a = 0; a < positionList.size(); a++) {
            Position position = positionList.get(a);
            if (position.getUnicode() == null) {
                break;
            }
            if (!arr.contains(position.getUnicode())) {
                String ab = position.getUnicode();
                int b = 1;
                while (!arr.contains(positionList.get(a+b).getUnicode())) {
                    float f1 = positionList.get(a+b-1).getyDirAdj();
                    float f2 = positionList.get(a+b).getyDirAdj();
                    float f3 = positionList.get(a+b-1).getxDirAdj();
                    float f4 = positionList.get(a+b).getxDirAdj();
                    float f5 = f4 - f3;
                        if (f1 == f2) {
                            if (0 < f5 & f5 < 15) {
                                ab = ab + positionList.get(a + b).getUnicode();
                                if((a+b)>=positionList.size()-1) {
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
                positionList.get(a).setIsUsed(1);
                positionList.get(a).setTest(positionList.get(a + b).getxDirAdj());
                a = a + b;
            }
        }
        for (int a = 0; a < positionList.size(); a++) {
            if (positionList.get(a).getIsUsed() ==1) {
                String fullWord = positionList.get(a).getWord();
                int b = 1;
                int c = a;
                while (b < 50) {
                    if((a+b)>=positionList.size()-1){break;}
                    if (positionList.get(a+b).getIsUsed() == 1) {
                        float f1 = positionList.get(a+b).getxDirAdj();
                        float f2 = positionList.get(c).getTest();
                        if (1 < f1 - f2 & f1 - f2 < 5) {
                            fullWord = fullWord + " " + positionList.get(a+b).getWord();
                            c = a + b;
                        } else {
                            break;
                        }
                    }
                    b++;
                }

                positionList.get(a).setIsFullWord(1);
                positionList.get(a).setFullword(fullWord);
                a = a + b - 1;
            }
        }
        for (int a = 0; a < positionList.size()-1; a++) {
            if (positionList.get(a).getIsFullWord() == 1) {
                float f2 = positionList.get(a).getxDirAdj();
                value = getX(f2, a);
                if (value.length > 0) {
                }
                if (value.length > 1) {
                    float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                    float idXval = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                    float idYval = objIdMaker(Float.parseFloat(value[1][6]), Float.parseFloat(value[1][5]));
                    Node node;
                    String[] probVal = {value[0][3], value[0][2], value[0][6], value[1][5]};
                    if (!hmm.containsKey(idKey)) {
                        ObjCreater obj = new ObjCreater();
                        String[] objVal = {value[0][0], value[0][3], value[0][2]};

                        node = obj.objCreatetwo(objVal, idKey, probVal);
                        node.rightId = idXval;
                        node.bottomId = idYval;
                        hmm.put(idKey, node);
                    } else {
                        ObjCreater obj = new ObjCreater();
                        node = hmm.get(idKey);
                        node.prob.spaceProb = obj.spaceProbMaker(probVal);
                        node.totalPro += node.prob.spaceProb;
                        node.rightId = idXval;
                        node.bottomId = idYval;
                    }
                    if (!hmm.containsKey(idXval)) {
                        ObjCreater obj = new ObjCreater();
                        String[] objVal = {value[0][1], value[0][5], value[0][6]};
                        node = obj.objCreate(objVal, idXval);
                        node.leftId = idKey;
                        hmm.put(idXval, node);
                    } else {
                        node = hmm.get(idXval);
                        node.leftId = idKey;
                    }
                    if (!hmm.containsKey(idYval)) {
                        ObjCreater obj = new ObjCreater();
                        String[] objVal = {value[1][1], value[1][5], value[1][6]};
                        node = obj.objCreate(objVal, idYval);
                        node.topId = idKey;
                        hmm.put(idYval, node);
                    } else {
                        node = hmm.get(idYval);
                        node.topId = idKey;
                    }

                } else {
                    if (value.length == 1) {
                        if (value[0][4].equals("X")) {
                            float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                            float idXval = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                            if (!hmm.containsKey(idKey)) {
                                ObjCreater obj = new ObjCreater();
                                String[] objVal = {value[0][0], value[0][3], value[0][2]};
                                Node node = obj.objCreate(objVal, idKey);
                                node.rightId = idXval;
                                hmm.put(idKey, node);
                            } else {
                                Node node = hmm.get(idKey);
                                node.rightId = idXval;
                            }
                            if (!hmm.containsKey(idXval)) {
                                ObjCreater obj = new ObjCreater();
                                String[] objVal = {value[0][1], value[0][5], value[0][6]};
                                Node node = obj.objCreate(objVal, idXval);
                                node.leftId = idKey;
                                hmm.put(idXval, node);
                            } else {
                                Node node = hmm.get(idXval);
                                node.leftId = idKey;
                            }
                        } else if (value[0][4].equals("Y")) {
                            float idKey = objIdMaker(Float.parseFloat(value[0][3]), Float.parseFloat(value[0][2]));
                            float idYval = objIdMaker(Float.parseFloat(value[0][6]), Float.parseFloat(value[0][5]));
                            if (!hmm.containsKey(idKey)) {
                                ObjCreater obj = new ObjCreater();
                                String[] objVal = {value[0][0], value[0][3], value[0][2]};
                                Node node = obj.objCreate(objVal, idKey);
                                node.bottomId = idYval;
                                hmm.put(idKey, node);
                            } else {
                                Node node = hmm.get(idKey);
                                node.bottomId = idYval;
                            }
                            if (!hmm.containsKey(idYval)) {
                                ObjCreater obj = new ObjCreater();
                                String[] objVal = {value[0][1], value[0][5], value[0][6]};
                                Node node = obj.objCreate(objVal, idYval);
                                node.topId = idKey;
                                hmm.put(idYval, node);
                            } else {
                                Node node = hmm.get(idYval);
                                node.topId = idKey;
                            }
                        }

                    }
                }
            }
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if ((!node.isUsed) & (node.rightId != 0 & node.bottomId != 0)) {
                if (valChanger(node.id) == 0) {
                    continue;
                }
            }
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if ((!node.isUsed) & (node.rightId != 0 & node.bottomId == 0) & node.prob.numProb==2) {
                node.isUsed = true;
                node.isKeyWord = true;
                hmm.get(node.rightId).isUsed = true;
                hmm.get(node.rightId).isNotKeyWord = true;
                hmm2.put(node.val, hmm.get(node.rightId).val);
            } else if ((!node.isUsed) & (node.rightId == 0 & node.bottomId != 0)& node.prob.numProb==2) {
                node.isUsed = true;
                node.isKeyWord = true;
                hmm.get(node.bottomId).isUsed = true;
                hmm.get(node.bottomId).isNotKeyWord = true;
                hmm2.put(node.val, hmm.get(node.bottomId).val);
            }
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if (node.rightId != 0 & node.bottomId != 0) {
            } else if (node.rightId != 0 & node.bottomId == 0) {
                // System.out.println("Keyword : " + node.val + "\tXValue :" + node.totalPro);

            } else if (node.bottomId != 0) {
                //System.out.println("Keyword : " + node.val + "\tYValue :" +hmm.get(node.bottomId).val);
                //System.out.println("Keyword : " + node.val + "\tValue :"+ node.totalPro);
            }
        }

        //checking the conditions
        for (String key : hmm2.keySet()) {
            System.out.println("Keyword : " + key + "\tValue :" + hmm2.get(key));
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if (!node.isUsed) {
                if (node.rightId == 0 & node.bottomId != 0) {
                    if (node.isKeyWord) {
                        hmm.get(node.bottomId).isNotKeyWord = true;
                    }
                }
                if (node.rightId != 0 & node.bottomId == 0) {
                    if (node.isKeyWord) {
                        hmm.get(node.rightId).isNotKeyWord = true;
                    }
                }
            }
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if (node != null) {
                if (node.rightId != 0 & node.bottomId != 0) {
                    ////System.out.println("Keyword : " + node.val + "\tValue :" +hmm.get(node.rightId).val + "\t Next " +hmm.get(node.bottomId).val);
                    if (hmm.get(node.rightId).isNotKeyWord & !node.isNotKeyWord) {
                        node.isKeyWord = true;
                        node.isUsed = true;
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hmm.get(node.rightId).val);
                    }
                    if (hmm.get(node.bottomId).isKeyWord) {
                        hmm.get(node.rightId).isNotKeyWord = true;
                        node.isUsed = true;
                        //System.out.println("Keyword : " + node.val + "\tValue :" +hmm.get(node.bottomId).val);
                    }
                    if (hmm.get(node.rightId).isKeyWord) {
                        hmm.get(node.bottomId).isNotKeyWord = true;
                        node.isUsed = true;
                        // System.out.println("Keyword : " + node.val + "\tValue :" +hmm.get(node.rightId).val);
                    }
                }
                if (node.rightId == 0 & node.bottomId != 0) {
                    if (hmm.get(node.bottomId).isNotKeyWord & !node.isNotKeyWord) {
                        node.isKeyWord = true;
                        node.isUsed = true;
                    }
                }

                if (node.rightId != 0 & node.bottomId == 0) {
                    if (hmm.get(node.rightId).isNotKeyWord & !node.isNotKeyWord) {
                        node.isKeyWord = true;
                        node.isUsed = true;
                    }
                }
            }
        }
        for (Object key : hmm.keySet()) {
            Node node = hmm.get(key);
            if (node != null) {
                if (node.isKeyWord) {
                    if (node.rightId != 0 && (hmm.get(node.rightId).isNotKeyWord & node.bottomId == 0)) {
                        // System.out.println("Keyword : ...." + node.val + "\tValue :" +hmm.get(node.rightId).val);
                    } else if (node.bottomId != 0 && (hmm.get(node.bottomId).isNotKeyWord & node.rightId == 0)) {
                        // System.out.println("Keyword : " + node.val + "\tValue ....:" +hmm.get(node.bottomId).val);
                    } else if (node.rightId != 0 & node.bottomId != 0) {
                        if (hmm.get(node.bottomId).isKeyWord) {
                            // System.out.println("Keyword : *****" + node.val + "\tValue :" +hmm.get(node.rightId).val);
                        } else if (hmm.get(node.rightId).isKeyWord) {
                            // System.out.println("Keyword : " + node.val + "\tValue *****:" +hmm.get(node.bottomId).val);
                        }
                    }

                } else if (node.rightId != 0 && (hmm.get(node.rightId).isNotKeyWord & !node.isNotKeyWord)) {
                }
            }
        }

    }

    private int valChanger(Float id) {
        Node node = hmm.get(id);
        if (node.rightId != 0 & node.bottomId == 0) {
            if (node.topId != 0) {
                hmm.get(node.topId).totalPro += 1;
                if (hmm.get(node.rightId).topId != 0) {
                    node.isUsed = true;
                    node.isKeyWord = true;
                    hmm.get(node.rightId).isUsed = true;
                    hmm.get(node.rightId).isNotKeyWord = true;
                    hmm2.put(node.val, hmm.get(node.rightId).val);
                    hmm.get(hmm.get(node.rightId).topId).totalPro -= 1;
                    return 0;
                }
            } else {
                node.isUsed = true;
                node.isKeyWord = true;
                hmm2.put(node.val, "");
                hmm.get(node.rightId).totalPro += 1;
                return valChanger(node.rightId);
            }
            return 0;
        } else if (node.rightId == 0 & node.bottomId != 0) {
            node.isUsed = true;
            node.isKeyWord = true;
            hmm.get(node.bottomId).isUsed = true;
            hmm.get(node.bottomId).isNotKeyWord = true;
            hmm2.put(node.val, hmm.get(node.bottomId).val);
            return 0;
        }
        if ((!node.isUsed) & node.rightId != 0 & node.bottomId != 0) {
            if (node.totalPro < hmm.get(node.rightId).totalPro) {
                return 0;
            }
            if (hmm.get(node.rightId).totalPro < hmm.get(node.bottomId).totalPro) {
                //System.out.println("Keyword : " + node.val+ "\tValue :"+ node.totalPro);
                if (node.totalPro > hmm.get(node.rightId).totalPro) {
                    node.isUsed = true;
                    node.isKeyWord = true;
                    hmm.get(node.rightId).isUsed = true;
                    hmm.get(node.rightId).isNotKeyWord = true;
                    hmm2.put(node.val, hmm.get(node.rightId).val);
                    hmm.get(node.bottomId).totalPro += 1;
                    if (hmm.get(node.rightId).bottomId != 0) {
                        hmm.get(hmm.get(node.rightId).bottomId).totalPro -= 1;
                    }
                    return valChanger(node.bottomId);
                } else {
                    return 0;
                }
            } else if (hmm.get(node.rightId).totalPro == hmm.get(node.bottomId).totalPro) {
                return valChanger(node.bottomId);
            } else {
                if (node.totalPro > hmm.get(node.bottomId).totalPro) {
                    node.isUsed = true;
                    node.isKeyWord = true;
                    hmm.get(node.bottomId).isUsed = true;
                    hmm.get(node.bottomId).isNotKeyWord = true;
                    hmm2.put(node.val, hmm.get(node.bottomId).val);
                    hmm.get(node.rightId).totalPro += 1;
                    if (hmm.get(node.bottomId).rightId != 0) {
                        hmm.get(hmm.get(node.bottomId).rightId).totalPro -= 1;
                    }
                    return valChanger(node.rightId);
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

//    void fileWrite(){
//        try{
//            FileWriter fw=new FileWriter("C:\\Users\\Rukshan\\Documents\\Research\\RTest01\\testout.txt");
//            for (String val: hmm2.keySet()) {
//                fw.write(val+","+hmm2.get(val)+"\n");
//            }
//            //fw.write("Welcome to javaTpoint.");
//            fw.close();
//            Process p = Runtime.getRuntime().exec("python C:\\Users\\Rukshan\\Documents\\Research\\RTest01\\test.py");
//        }catch(Exception e){System.out.println(e);}
//        System.out.println("Success...");
//}
    private float objIdMaker(float x, float y)
    {
        return x + (y / 1000);
    }

}
