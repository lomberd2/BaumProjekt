import BinaryTrees.*;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOperator {

    @Test
    void TestValue() {
        double value = new Value(25).getValue();
        assertEquals(25.0, value);
    }

    @Test
    void TestAdditionOperator() {
        double addition = new Add(10, 10).getValue();
        assertEquals(20.0, addition);
    }

    @Test
    void TestDivisionOperator() {
        double division = new Divide(10, 1).getValue();
        assertEquals(10.0, division);
    }

    @Test
    void TestMultiplicationOperator() {
        double multiplicationResult = new Multiply(25, 5).getValue();
        assertEquals(125.0, multiplicationResult);
    }

    @Test
    void TestSubtractionOperator() {
        double subtractionResult = new Subtract(100, 25).getValue();
        assertEquals(75.0, subtractionResult);
    }

    @Test
    void TestPowerOperator() {
        double powerResult = new Power(10, 5).getValue();
        assertEquals(100000.0, powerResult);
    }

    @Test
    void TestNestedOperators() {
        //Operator1 = Equals 250.0
        Operator operator1 = new Multiply(25, 10);
        //Operator2 = Equals 0.1 ==> BinaryTrees.Operator(5, 50, BinaryTrees.Divide)
        Operator operator2 = new Divide(new Subtract(10, 5), new Add(40, 10));
        //Operator1(250) * Operator2(0.1) = 25
        double nestedOperatorsResult = new Multiply(operator1, operator2).getValue();
        assertEquals(25.0, nestedOperatorsResult);
    }

    @Test
    void TestToString() {
        String powerResult = new Power(10, 5).toString();
        assertEquals("(10.0,5.0)^", powerResult);
    }

    @Test
    void TestToStringNested() {
        //Operator1 (25.0,10.0)*
        Operator operator1 = new Multiply(25, 10);
        //Operator2 = Equals 0.1 ==> BinaryTrees.Operator(5, 50, BinaryTrees.Divide) == ((10.0,5.0)/,(40.0,10.0)+)/
        Operator operator2 = new Divide(new Subtract(10, 5), new Add(40, 10));
        //Operator1(250) * Operator2(0.1) = (Operator1, Operator2)*
        String nestedOperatorsResult = new Multiply(operator1, operator2).toString();
        assertEquals("((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)*", nestedOperatorsResult);
    }

    @Test
    void TestStringParserEasy() throws ExecutionControl.NotImplementedException {
        String operatorParseString = "((10.0,5.0)/,(40.0,10.0)+)/";
        Operator parsedOperator = (Operator) StringToNodeParser.getOperatorFromString(operatorParseString);
        assertEquals(operatorParseString, parsedOperator.toString());
        assertEquals(0.04, parsedOperator.getValue());
    }

    @Test
    void TestStringParserHard() throws ExecutionControl.NotImplementedException {
        // ((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)* ==> (25.0, 25.0)+ == 50.0
        String operatorParseString = "(((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)*,25.0)+";
        Operator parsedOperator = (Operator) StringToNodeParser.getOperatorFromString(operatorParseString);
        assertEquals(operatorParseString, parsedOperator.toString());
        assertEquals(50.0, parsedOperator.getValue());
    }

    @Test
    void NewParserTest() throws Exception {
        //[1, 2, +, 3, 4, *, -, 5, 6, 7, ^, ^, 8, *, +, 9, -]
        // -(9, +( *( 8,(^(^(7,6),5)-(*(4,3),+(1,2)
        // (1,2)+ --> (3,4)* --> - --> ((1,2)+,(3,4)*)- -->
        String parsingString ="1+2--3*4+5^7*-8-9";
        String parsingString2 = "1+2-3*4+5^6^7*8-9";
        String parsingString3 = "(-3.31 + 4)*(-15.5547 - 6)";

        Node parsedNode = TermParser.parse(parsingString);
        Node parsedNode2 = TermParser.parse(parsingString2);
        Node parsedNode3 = TermParser.parse(parsingString3);

        assertEquals("((((1.0,2.0)+,(-3.0,4.0)*)-,((5.0,7.0)^,-8.0)*)+,9.0)-", parsedNode.toString());
        assertEquals("((((1.0,2.0)+,(3.0,4.0)*)-,((5.0,(6.0,7.0)^)^,8.0)*)+,9.0)-", parsedNode2.toString());
        assertEquals("((-3.31,4.0)+,(-15.5547,6.0)-)*", parsedNode3.toString());

        parsingString = TermParser.parseToString(parsingString);
        parsingString2 = TermParser.parseToString(parsingString2);
        parsingString3 = TermParser.parseToString(parsingString3);

        assertEquals("((((1,2)+,(-3,4)*)-,((5,7)^,-8)*)+,9)-", parsingString);
        assertEquals("((((1,2)+,(3,4)*)-,((5,(6,7)^)^,8)*)+,9)-", parsingString2);
        assertEquals("((-3.31,4)+,(-15.5547,6)-)*", parsingString3);
    }

    @Test
    void NewParserSecondTest() throws Exception {
        String parsingString =".154 + 21 - 31.243 / 80,21";
        Node parsedNode = TermParser.parse(parsingString);
        parsingString = TermParser.parseToString(parsingString);
        System.out.println(parsingString);
        System.out.println(parsedNode.toString());
        System.out.println(parsedNode.getValue());
    }

    @Test
    void LucasTest() throws Exception {
        String parsingString = "3 * (1 + 2)";
        Node parsedNode = TermParser.parse(parsingString);
        System.out.println(parsedNode.toString());
        System.out.println(parsedNode.getValue());
    }

}