package BinaryTrees;

public class Subtract extends Operator {

    public Subtract(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    public Subtract(double leftValue, double rightValue) {
        super(leftValue, rightValue);
    }

    public Subtract(double leftValue, Node rightNode) {
        super(leftValue, rightNode);
    }

    public Subtract(Node leftNode, double rightValue) {
        super(leftNode, rightValue);
    }

    @Override
    public String toString() {
        return super.toString(this.getOperator());
    }

    @Override
    public double getValue() {
        return this.leftNode.getValue() - this.rightNode.getValue();
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
