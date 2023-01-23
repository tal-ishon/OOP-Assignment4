import java.util.Map;
/**
 * @author Tal Ishon.
 * The Class Xor.
 */
public class Xnor extends BinaryExpression {
    /**
     * Xnor constructor.
     * Instantiates a new Xnor.
     *
     * @param ex1 one of the expressions received.
     * @param ex2 one of the expressions received.
     */
    public Xnor(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }
    @Override
    protected String getOperator() {
        return " # ";
    }

    /**
     * GetClassOperator method.
     *
     * @param e1 the expression received
     * @param e2 the other expression received
     * @return the new Xnor class operator.
     */
    protected Expression getClassOperator(Expression e1, Expression e2) {
        return new Xnor(e1, e2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !((getEx1().evaluate(assignment) && !getEx2().evaluate(assignment)
                || !getEx1().evaluate(assignment) && getEx2().evaluate(assignment)));
    }

    @Override
    public Expression nandify() {
        Expression nandEx1 = getEx1().nandify();
        Expression nandEx2 = getEx2().nandify();
        Expression e1 = new Nand(new Nand(nandEx1, nandEx1), new Nand(nandEx2, nandEx2));
        Expression e2 = new Nand(nandEx1, nandEx2);
        return new Nand(e1, e2);
    }

    @Override
    public Expression norify() {
        Expression norEx1 = getEx1().norify();
        Expression norEx2 = getEx2().norify();
        Expression e1 = new Nor(norEx1, new Nor(norEx1, norEx2));
        Expression e2 = new Nor(norEx2, new Nor(norEx1, norEx2));
        return new Nor(e1, e2);
    }

    @Override
    public Expression simplify() {
        Expression e1 = getEx1().simplify();
        Expression e2 = getEx2().simplify();
        if (e1.toString().equals(e2.toString())) {  // check if expressions are the same
            return new Val(true);
        }
        // check if both expressions are values - if so, simplify them according to logic roles
        if (e1.toString().equals("T") && e2.toString().equals("F")
                || e2.toString().equals("T") && e1.toString().equals("F")) {
            return new Val(false);
        }
        // if both expressions are true or both false - we would have executed the first "if" condition
        // so - if we got here both expressions are variables - try to simplify them
        return new Xnor(e1.simplify(), e2.simplify());
    }
}
