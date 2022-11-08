import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public class StringToNodeParser {
    private static Map<String, String> objToStringMap = new HashMap<>();

    public static Node getOperatorFromString(String inputString) {
        SortedSet<Integer> tempBraces = new TreeSet<>();
        ArrayList<String> splittetBraces = new ArrayList<>();
        Map<String, String> merkMap = new HashMap<>();
        objToStringMap = new HashMap<>();

        for (int j = 0; j < inputString.length(); j++) {
            char currentChar = inputString.charAt(j);

            if (currentChar == '(')
            {
                //Wenn aktueller "(" ist
                tempBraces.add(j);
            }
            else if (currentChar == ')')
            {
                //Wenn aktueller ")" ist
                if (!tempBraces.isEmpty()) {
                    //Wenn ( vorgemerkt
                    int lastIndexLeftBrace = tempBraces.last();
                    tempBraces.remove(tempBraces.last());

                    String newAddition = inputString.substring(lastIndexLeftBrace, j + 2);
                    //Check if already exists
                    for (String allreadyIn: splittetBraces) {
                       newAddition = newAddition.replace(allreadyIn, merkMap.get(allreadyIn));
                    }

                    int merkInt = merkMap.size()+1;
                    String objName = "OBJ" + merkInt;
                    merkMap.put(newAddition, objName);
                    objToStringMap.put(objName, newAddition);
                    splittetBraces.add(newAddition);
                }
            }
        }
        String[] splittetInput = splittetBraces.toArray(new String[0]);

        /*for (String splittetString: splittetInput) {
            System.out.println(splittetString);
            if (splittetString.contains("OBJ")) {
                int OBJStart = splittetString.indexOf("OBJ");
                int OBJEnde = OBJStart + 4;
                int OBJSecondStart = splittetString.indexOf("OBJ", OBJEnde), OBJSecondEnd;
                String firstObjString = splittetString.substring(OBJStart, OBJEnde), secondObjString = "";
                if (OBJSecondStart != -1) {
                    OBJSecondEnd = OBJSecondStart + 4;
                    secondObjString = splittetString.substring(OBJSecondStart, OBJSecondEnd);
                }

                System.out.print(firstObjString);
                System.out.println(" ObjMap: " + reverseMerkMap.get(firstObjString));
                System.out.print(secondObjString);
                System.out.println(" ObjMap: " + reverseMerkMap.get(secondObjString));

                //System.out.println(firstObjString);
                if (!secondObjString.isEmpty()) {
                    //System.out.println("Sek Obj String: " + secondObjString);

                    if (!objNodeMap.containsKey(secondObjString)) {
                        String obj = reverseMerkMap.get(secondObjString);
                        Operators operator = getOperatorFromString(obj.substring(obj.length()-1, obj.length()));
                        String firstValue = obj.substring(1, obj.indexOf(","));
                        String secondValue = obj.substring(obj.indexOf(",")+1, obj.indexOf(")"));
                        try {
                            double firstDouble = Double.parseDouble(firstValue);
                            double seconodDouble = Double.parseDouble(secondValue);
                            objNodeMap.put(firstObjString, new Operator(firstDouble, seconodDouble, operator));
                        } catch (Exception e) {}
                    }
                }

                if (!objNodeMap.containsKey(firstObjString)) {
                    String obj = reverseMerkMap.get(firstObjString);
                    Operators operator = getOperatorFromString(obj.substring(obj.length()-1, obj.length()));
                    String firstValue = obj.substring(1, obj.indexOf(","));
                    String secondValue = obj.substring(obj.indexOf(",")+1, obj.indexOf(")"));
                    try {
                        double firstDouble = Double.parseDouble(firstValue);
                        double seconodDouble = Double.parseDouble(secondValue);
                        objNodeMap.put(firstObjString, new Operator(firstDouble, seconodDouble, operator));
                    } catch (Exception e) {}
                }


            }
            else {
                //System.out.println(splittetString);
                try {
                    String sVal1 = splittetString.substring(1, splittetString.indexOf(","));
                    String sVal2 = splittetString.substring(splittetString.indexOf(",")+1, splittetString.indexOf(")"));
                    Operators operator_ = getOperatorFromString(splittetString.substring(splittetString.length()-1, splittetString.length()));
                    Operator opera = new Operator(Double.parseDouble(sVal1), Double.parseDouble(sVal2), operator_);
                    stringNodeMap.put(splittetString, opera);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            rootNodeString = splittetString;
        }

        String rootNodeLeftString = rootNodeString.substring(1, rootNodeString.indexOf(","));
        String rootNodeRightString = rootNodeString.substring(rootNodeString.indexOf(",")-1, rootNodeString.indexOf(")"));
        Operators rootNodeOperator = getOperatorFromString( rootNodeString.substring(rootNodeString.length()-1, rootNodeString.length()));
        Node rootNodeLeft, rootNodeRight;

        if (rootNodeLeftString.contains("OBJ")) {
            rootNodeLeft = objNodeMap.get(rootNodeLeftString);
        } else {
            //rootNodeLeft = stringNodeMap()
        }

        System.out.println("======ENDE");
        i = 0;

        System.out.println();
        for (int x = splittetInput.length - 1; x > -1; x--) {
            System.out.println(splittetInput[x]);
            if (x == splittetInput.length - 1) {
                System.out.println("Root Node Above");

            } else
            if (splittetInput[x].contains("OBJ")) {
                int OBJStart = splittetInput[x].indexOf("OBJ");
                int OBJEnde = OBJStart + 4;
                String objString = splittetInput[x].substring(OBJStart, OBJEnde);
                String objSecString = "";

                int OBJSecond = splittetInput[x].indexOf("OBJ", OBJEnde);
                if (OBJSecond != -1) {
                    int OBJSecondEnd = OBJSecond + 4;
                    objSecString = splittetInput[x].substring(OBJSecond, OBJSecondEnd);
                }

                Node leftNode = objNodeMap.get(objString);
                Node rightNode = null;

                //System.out.println("Inserted: " + objNodeMap.get(objString));
                if (!objSecString.isEmpty()) {
                    //System.out.println("Inserted: " + reverseMerkMap.get(objSecString));
                    rightNode = objNodeMap.get(objSecString);
                }

                Operators operator = getOperatorFromString(splittetInput[x].substring(splittetInput[x].length() - 1, splittetInput[x].length()));

                if (leftNode != null) {
                    if (rightNode != null) {
                        tree.add(new Operator(leftNode, rightNode, operator));
                    } else {
                        int ads = splittetInput[x].indexOf(",");
                        int adend = splittetInput[x].indexOf(")");
                        String val = splittetInput[x].substring(ads + 1, adend);
                        try {
                            rightNode = new Value(Double.parseDouble(val));
                            tree.add(new Operator(leftNode, rightNode, operator));
                        } catch (Exception e) {
                            System.err.println(e);
                        }

                    }
                } else {
                    try {
                        String split = splittetInput[x];
                        String leftVal = split.substring(1, split.indexOf(","));
                        String rightVal = split.substring(split.indexOf(",") + 1, split.indexOf(")"));
                        Operators operator_ = getOperatorFromString(split.substring(split.length() - 1, split.length()));
                        tree.add(new Operator(Double.parseDouble(leftVal), Double.parseDouble(rightVal), operator_));
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            } else {
                try {
                    String split = splittetInput[x];
                    String leftVal = split.substring(1, split.indexOf(","));
                    String rightVal = split.substring(split.indexOf(",") + 1, split.indexOf(")"));
                    Operators operator_ = getOperatorFromString(split.substring(split.length() - 1, split.length()));
                    tree.add(new Operator(Double.parseDouble(leftVal), Double.parseDouble(rightVal), operator_));
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

        Node root = null;

        for (int j = 0; j < splittetInput.length; j++) {
            String curString = splittetInput[j];
            String curStringLeftNode = curString.substring(1, curString.indexOf(","));
            String curStringRightNode = curString.substring(curString.indexOf(",") + 1, curString.indexOf(")"));
            Operators curStringOperator = getOperatorFromString(curString.substring(curString.length()-1, curString.length()));

            Node leftNode = null, rightNode = null;

            //Left Node
            if (curStringLeftNode.contains("OBJ")) {
                //LeftNodeString is OBJ
                leftNode = objNodeMap.get(curStringLeftNode);
                System.out.println("OBJ LEFT NODE: " + curStringLeftNode);
                System.out.println(leftNode);
            } else {
                //LeftNode is not OBJ
                try {
                    leftNode = new Value(Double.parseDouble(curStringLeftNode));
                    System.out.println("NOTHING LEFT NODE: " + curStringLeftNode);
                } catch (Exception e) {}
            }

            //Right Node
            if (curStringRightNode.contains("OBJ")) {
                //LeftNodeString is OBJ
                String reverseString = reverseMerkMap.get(curStringRightNode);
                if (reverseString.contains("OBJ")) {
                    Node leftNode_, rightNode_;
                    String leftNodeStr_ = reverseString.substring(1, reverseString.indexOf(",")), rightNodeStr_ = reverseString.substring(reverseString.indexOf(",")-1, reverseString.indexOf(")"));
                    try {
                        Operators operator = getOperatorFromString(reverseString.substring(reverseString.length()-1, reverseString.length()));
                        if (leftNodeStr_.contains("OBJ")) {
                            leftNode_ = objNodeMap.get(leftNodeStr_);
                        } else {
                            leftNode_ = new Value(Double.parseDouble(leftNodeStr_));
                        }
                        if (rightNodeStr_.contains("OBJ")) {
                            rightNode_ = objNodeMap.get(rightNodeStr_);
                        } else {
                            rightNode_ = new Value(Double.parseDouble(rightNodeStr_));
                        }
                        rightNode = new Operator(leftNode_, rightNode_, operator);
                    } catch (Exception e) {

                    }
                } else {
                    rightNode = stringNodeMap.get(reverseString);
                }

                System.out.println("OBJ RiGHT NODE: " + curStringRightNode);
                System.out.println("OBJ RIGHT NODE MAP: " + rightNode);
            } else {
                //LeftNode is not OBJ
                try {
                    rightNode = new Value(Double.parseDouble(curStringRightNode));
                    System.out.println("NOTHING RIGHT NODE: " + curStringRightNode);
                } catch (Exception e) {}
            }

            if (j == splittetInput.length - 1) {
                System.out.println("Root Node (Last)");
                if (leftNode != null && rightNode != null) {
                    root = new Operator(leftNode, rightNode, curStringOperator);
                }
            }
        }

        //String rootNodeString = splittetInput[splittetInput.length - 1];
        /*if (rootNodeString.contains("OBJ")) {
            root = objNodeMap.get(rootNodeString);
        }*/

        //System.out.println(root.toString());
        //System.out.println(tree.get(0).toString());

        /*for (i = 0; i + 1 < inputString.length(); i = lastIndex) {
            int indexLeftBrace = inputString.indexOf("(", i);
            int indexRightBrace = inputString.indexOf(")", i);

            System.out.println(i);

            if( !tempBraces.isEmpty() ) {
                //Wenn tempBraces nicht Leer ist (Klammern vorgemerkt)
                int indexLeftBraceNext = inputString.indexOf(inputString.indexOf("(", indexLeftBrace + 1));
                if (indexLeftBraceNext != -1) {
                    if (indexLeftBraceNext > indexRightBrace) {
                        //Wenn ")" vor der Nächsten "(" kommt
                        int lastIndexLeftBrace = tempBraces.get(tempBraces.size()-1);
                        tempBraces.remove(tempBraces.size()-1);

                        splittetBraces.add(inputString.substring(lastIndexLeftBrace, indexRightBrace + 1));
                        lastIndex = indexRightBrace;
                    } else {
                        //Wenn "(" vor der nächsten ")" kommt
                        tempBraces.add(indexLeftBraceNext);
                        lastIndex = indexLeftBraceNext;
                    }
                }
                else {
                    //keine "(" Next Left Brace mehr
                    int lastIndexLeftBrace = tempBraces.get(tempBraces.size()-1);
                    tempBraces.remove(tempBraces.size()-1);

                    splittetBraces.add(inputString.substring(lastIndexLeftBrace, indexRightBrace + 1));
                    lastIndex = indexRightBrace;
                }

            }
            else if ( indexLeftBrace != -1 && indexRightBrace != -1) {
                //Wenn es ein mindestens eine LeftBrace und eine Right Brace gibt
                int indexLeftBraceNext = inputString.indexOf(inputString.indexOf("(", indexLeftBrace + 1));
                int indexRightBraceNext = inputString.indexOf(inputString.indexOf(")", indexRightBrace + 1));

                if (indexLeftBraceNext != -1) {
                    //Wenn es mehr als eine Left Brace noch gibt
                    if (indexLeftBraceNext < indexRightBrace) {
                        // ==> (( || Mehr als eine Left Brace vor der Schließenden Right Brace
                        tempBraces.add(indexLeftBrace);
                        tempBraces.add(indexLeftBraceNext);

                        lastIndex = indexLeftBraceNext;
                    } else {
                        // ==> () || Schließende Right Brace vor der nächsten Left Brace
                        splittetBraces.add(inputString.substring(indexLeftBrace, indexRightBrace + 1));

                        lastIndex = indexRightBrace;
                    }
                } else {
                    //Wenn es keine Left Brace mehr gibt
                    splittetBraces.add(inputString.substring(indexLeftBrace, indexRightBrace + 1));
                    lastIndex = indexRightBrace;
                }

                if (indexLeftBraceNext < indexRightBraceNext) {

                }

            }
            else if ( indexRightBrace != -1) {
                System.out.println("Not ment to be here");
            }
        }*/

        return getNodeFromString(splittetInput[splittetInput.length-1]);
    }

    private static Node getNodeFromString(String nodeString) {
        int nodeStringKommaIndex = nodeString.indexOf(",");
        int nodeStringEndIndex = nodeString.indexOf(")");
        String leftNodeString = nodeString.substring(1, nodeStringKommaIndex);
        String rightNodeString = nodeString.substring(nodeStringKommaIndex + 1, nodeStringEndIndex);
        String nodeOperatorString = nodeString.substring(nodeString.length()-1);

        Node leftNode = null, rightNode = null;
        Operator returnOperator = null;


        if (leftNodeString.contains("OBJ")) {
            //Left Node ist ein OBJ
            String objString = objToStringMap.get(leftNodeString);
            leftNode = getNodeFromString(objString);
        } else {
            //Left Node ist kein OBJ
            try {
                leftNode = new Value(Double.parseDouble(leftNodeString));
            } catch (Exception e) {
                System.err.println("Error Trying to get Left Node:\n" + e);
            }
        }


        if (rightNodeString.contains("OBJ")) {
            //Right Node ist ein OBJ
            String objString = objToStringMap.get(rightNodeString);
            rightNode = getNodeFromString(objString);
        } else {
            //Right Node ist kein OBJ
            try {
                rightNode = new Value(Double.parseDouble(rightNodeString));
            } catch (Exception e) {
                System.err.println("Error Trying to get Right Node:\n" + e);
            }
        }

        try {
            if (leftNode != null && rightNode != null)
                returnOperator = getOperatorsFromString(nodeOperatorString, leftNode, rightNode);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return returnOperator;
    }

    public static Operator getOperatorsFromString(String operator, Node leftNode, Node rightNode) throws ExecutionControl.NotImplementedException {
        return switch (operator) {
            case "+" -> new Add(leftNode, rightNode);
            case "-" -> new Subtract(leftNode, rightNode);
            case "^" -> new Power(leftNode, rightNode);
            case "*" -> new Multiply(leftNode, rightNode);
            case "/" -> new Divide(leftNode, rightNode);
            default -> throw new ExecutionControl.NotImplementedException("Operator not implemented");
        };
    }
}
