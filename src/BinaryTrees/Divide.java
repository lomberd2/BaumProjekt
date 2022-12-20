package BinaryTrees;

public class Divide extends Operator {

    public Divide(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    public Divide(double leftValue, double rightValue) {
        super(leftValue, rightValue);
    }

    public Divide(double leftValue, Node rightNode) {
        super(leftValue, rightNode);
    }

    public Divide(Node leftNode, double rightValue) {
        super(leftNode, rightValue);
    }

    @Override
    public double getValue() {
        return this.leftNode.getValue() / this.rightNode.getValue();
    }

    @Override
    public String getOperator() {
        return "/";
    }

    @Override
    public String toString() {
        return super.toString(this.getOperator());
    }
}
