package BinaryTrees;

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
/*public abstract class BinaryTrees.Operator implements BinaryTrees.Node {
    protected BinaryTrees.Node leftNode;
    protected BinaryTrees.Node rightNode;

    BinaryTrees.Operator(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}

class BinaryTrees.Add extends BinaryTrees.Operator {
    BinaryTrees.Add(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() + rightNode.getValue();
    }
}

class BinaryTrees.Subtract extends BinaryTrees.Operator {
    BinaryTrees.Subtract(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() - rightNode.getValue();
    }
}

class BinaryTrees.Multiply extends BinaryTrees.Operator {
    BinaryTrees.Multiply(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() * rightNode.getValue();
    }
}

class BinaryTrees.Divide extends BinaryTrees.Operator {
    BinaryTrees.Divide(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return leftNode.getValue() / rightNode.getValue();
    }
}

class BinaryTrees.Power extends BinaryTrees.Operator {
    BinaryTrees.Power(BinaryTrees.Node leftNode, BinaryTrees.Node rightNode) {
        super(leftNode, rightNode);
    }
    @Override
    public double getValue() throws Exception {
        return Math.pow(leftNode.getValue(), rightNode.getValue());
    }
}*/