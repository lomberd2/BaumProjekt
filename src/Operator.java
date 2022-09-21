enum Operators {
    ADD {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() + rightNode.getValue();
        }
        @Override
        public String toString() {
            return "+";
        }
    },
    SUBTRACT {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() - rightNode.getValue();
        }
        @Override
        public String toString() {
            return "-";
        }
    },
    MULTIPLY {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() * rightNode.getValue();
        }
        @Override
        public String toString() {
            return "*";
        }
    },
    DIVIDE {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return leftNode.getValue() / rightNode.getValue();
        }
        @Override
        public String toString() {
            return "/";
        }
    },
    POWER {
        @Override
        double getValue(Node leftNode, Node rightNode) {
            return Math.pow(leftNode.getValue(), rightNode.getValue());
        }
        @Override
        public String toString() {
            return "^";
        }
    };

    abstract double getValue(Node leftNode, Node rightNode);
    public abstract String toString();
}

public class Operator implements Node {
    private final Node leftNode;
    private final Node rightNode;
    private final Operators operator;

    Operator(Node leftNode, Node rightNode, Operators operator) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.operator = operator;
    }

    Operator(double leftValue, Node rightNode, Operators operator) {
        this(new Value(leftValue), rightNode, operator);
    }

    Operator(double leftValue, double rightValue, Operators operator) {
        this(new Value(leftValue), new Value(rightValue), operator);
    }

    Operator(Node leftNode, double rightValue, Operators operator) {
        this(leftNode, new Value(rightValue), operator);
    }


    @Override
    public double getValue() {
       return this.operator.getValue(this.leftNode, this.rightNode);
    }

    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append("(");
        outputString.append(leftNode.toString());
        outputString.append(",");
        outputString.append(rightNode.toString());
        outputString.append(")");
        outputString.append(this.operator.toString());
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