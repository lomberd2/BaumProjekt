public class Multiply extends Operator {

    public Multiply(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    public Multiply(double leftValue, double rightValue) {
        super(leftValue, rightValue);
    }

    public Multiply(double leftValue, Node rightNode) {
        super(leftValue, rightNode);
    }

    public Multiply(Node leftNode, double rightValue) {
        super(leftNode, rightValue);
    }

    @Override
    public double getValue() {
        return this.leftNode.getValue() * this.rightNode.getValue();
    }

    @Override
    public String getOperator() {
        return "*";
    }

    @Override
    public String toString() {
        return super.toString(this.getOperator());
    }
}
