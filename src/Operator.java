
public abstract class Operator implements Node {
    protected final Node leftNode;
    protected final Node rightNode;

    Operator(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    Operator(double leftValue, Node rightNode) {
        this(new Value(leftValue), rightNode);
    }

    Operator(double leftValue, double rightValue) {
        this(new Value(leftValue), new Value(rightValue));
    }

    Operator(Node leftNode, double rightValue) {
        this(leftNode, new Value(rightValue));
    }

    public abstract String getOperator();

    /*@Override
    public double getValue() {
       return this.operator.getValue(this.leftNode, this.rightNode);
    }*/

    protected String toString(String operatorString) {
        StringBuilder outputString = new StringBuilder();
        outputString.append("(");
        outputString.append(leftNode.toString());
        outputString.append(",");
        outputString.append(rightNode.toString());
        outputString.append(")");
        outputString.append(operatorString);
        return outputString.toString();
    }
}

/**
 * ▼▼▼ Alternative Möglichkeit ▼▼▼
 */
/*public abstract class Operator implements Node {
    protected Node leftNode;
    protected Node rightNode;

    Operator(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}

class Add extends Operator {
    Add(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() + rightNode.getValue();
    }
}

class Subtract extends Operator {
    Subtract(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() - rightNode.getValue();
    }
}

class Multiply extends Operator {
    Multiply(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() * rightNode.getValue();
    }
}

class Divide extends Operator {
    Divide(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() / rightNode.getValue();
    }
}

class Power extends Operator {
    Power(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return Math.pow(leftNode.getValue(), rightNode.getValue());
    }
}*/