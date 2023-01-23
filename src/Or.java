import java.util.Map;
/**
 * @author Tal Ishon.
 * The Class Or.
 */
public class Or extends BinaryExpression {
    /**
     * Or constructor.
     * Instantiates a new Or.
     *
     * @param ex1 one of the expressions received.
     * @param ex2 one of the expressions received.
     */
    public Or(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }
    @Override
    protected String getOperator() {
        return " | ";
    }

    /**
     * GetClassOperator method.
     *
     * @param e1 the expression received
     * @param e2 the other expression received
     * @return the new Or class operator.
     */
    protected Expression getClassOperator(Expression e1, Expression e2) {
        return new Or(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getEx1().evaluate(assignment) || getEx2().evaluate(assignment);
    }

    @Override
    public Expression nandify() {
        Expression e1 = new Nand(getEx1().nandify(), getEx1().nandify());
        Expression e2 = new Nand(getEx2().nandify(), getEx2().nandify());
        return new Nand(e1, e2);
    }

    @Override
    public Expression norify() {
        Expression e = new Nor(getEx1().norify(), getEx2().norify());
        return new Nor(e, e);
    }

    @Override
    public Expression simplify() {
        Expression e1 = getEx1().simplify();
        Expression e2 = getEx2().simplify();
        if (e1.toString().equals(e2.toString())) {  // check if expressions are the same
            return e1;
        }
        // cases one of the expressions is value and not variable
        if (isVal(e1, e2, "T", "F") || isVal(e2, e1, "F", "T")) {
            // e1 is T and e2 is a var or e1 is var and e2 is F - return e1
            return e1;
        }
        if (isVal(e2, e1, "T", "F") || isVal(e1, e2, "F", "T")) {
            // e2 is T and e1 is a var or e2 is var and e1 is F - return e2
            return e2;
        }
        // check if both expressions are values - if so, simplify them according to logic roles
        if (e1.toString().equals("T") && e2.toString().equals("F")
                || e2.toString().equals("T") && e1.toString().equals("F")) {
            return new Val(true);
        }
        // if both expressions are true or both false - we would have executed the first "if" condition
        // so - if we got here both expressions are variables - try to simplify them
        return new Or(e1.simplify(), e2.simplify());
    }
}
