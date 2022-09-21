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
        double addition = new Operator(10, 10, Operators.ADD).getValue();
        assertEquals(20.0, addition);
    }

    @Test
    void TestDivisionOperator() {
        double division = new Operator(10, 1, Operators.DIVIDE).getValue();
        assertEquals(10.0, division);
    }

    @Test
    void TestMultiplicationOperator() {
        double multiplicationResult = new Operator(25, 5, Operators.MULTIPLY).getValue();
        assertEquals(125.0, multiplicationResult);
    }

    @Test
    void TestSubtractionOperator() {
        double subtractionResult = new Operator(100, 25, Operators.SUBTRACT).getValue();
        assertEquals(75.0, subtractionResult);
    }

    @Test
    void TestPowerOperator() {
        double powerResult = new Operator(10, 5, Operators.POWER).getValue();
        assertEquals(100000.0, powerResult);
    }

    @Test
    void TestNestedOperators() {
        //Operator1 = Equals 250.0
        Operator operator1 = new Operator(25, 10, Operators.MULTIPLY);
        //Operator2 = Equals 0.1 ==> Operator(5, 50, Divide)
        Operator operator2 = new Operator(new Operator(10, 5, Operators.SUBTRACT), new Operator(40, 10, Operators.ADD), Operators.DIVIDE);
        //Operator1(250) * Operator2(0.1) = 25
        double nestedOperatorsResult = new Operator(operator1, operator2, Operators.MULTIPLY).getValue();
        assertEquals(25.0, nestedOperatorsResult);
    }

    @Test
    void TestToString() {
        String powerResult = new Operator(10, 5, Operators.POWER).toString();
        assertEquals("(10.0,5.0)^", powerResult);
    }

    @Test
    void TestToStringNested() {
        //Operator1 (25.0,10.0)*
        Operator operator1 = new Operator(25, 10, Operators.MULTIPLY);
        //Operator2 = Equals 0.1 ==> Operator(5, 50, Divide) == ((10.0,5.0)/,(40.0,10.0)+)/
        Operator operator2 = new Operator(new Operator(10, 5, Operators.SUBTRACT), new Operator(40, 10, Operators.ADD), Operators.DIVIDE);
        //Operator1(250) * Operator2(0.1) = (Operator1, Operator2)*
        String nestedOperatorsResult = new Operator(operator1, operator2, Operators.MULTIPLY).toString();
        assertEquals("((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)*", nestedOperatorsResult);
    }

    @Test
    void TestStringParserEasy() {
        String operatorParseString = "((10.0,5.0)/,(40.0,10.0)+)/";
        Operator parsedOperator = (Operator) StringToNodeParser.getOperatorFromString(operatorParseString);
        assertEquals(operatorParseString, parsedOperator.toString());
        assertEquals(0.04, parsedOperator.getValue());
    }

    @Test
    void TestStringParserHard() {
        // ((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)* ==> (25.0, 25.0)+ == 50.0
        String operatorParseString = "(((25.0,10.0)*,((10.0,5.0)-,(40.0,10.0)+)/)*,25.0)+";
        Operator parsedOperator = (Operator) StringToNodeParser.getOperatorFromString(operatorParseString);
        assertEquals(operatorParseString, parsedOperator.toString());
        assertEquals(50.0, parsedOperator.getValue());
    }
}