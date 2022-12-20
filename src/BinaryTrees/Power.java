package BinaryTrees;

public class Power extends Operator {

    public Power(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    public Power(double leftValue, double rightValue) {
        super(leftValue, rightValue);
    }

    public Power(double leftValue, Node rightNode) {
        super(leftValue, rightNode);
    }

    public Power(Node leftNode, double rightValue) {
        super(leftNode, rightValue);
    }

    @Override
    public double getValue() {
        return Math.pow(this.leftNode.getValue(), this.rightNode.getValue());
    }

    @Override
    public String getOperator() {
        return "^";
    }

    @Override
    public String toString() {
        return super.toString(this.getOperator());
    }
}
