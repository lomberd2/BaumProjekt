public class Add extends Operator {
    public Add(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    public Add(double leftValue, double rightValue) {
        super(leftValue, rightValue);
    }

    public Add(double leftValue, Node rightNode) {
        super(leftValue, rightNode);
    }

    public Add(Node leftNode, double rightValue) {
        super(leftNode, rightValue);
    }

    @Override
    public double getValue() {
        return this.leftNode.getValue() + this.rightNode.getValue();
    }

    @Override
    public String toString() {
        return super.toString(this.getOperator());
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
