import java.util.Map;
/**
 * @author Tal Ishon.
 * The Class Nand.
 */
public class Nand extends BinaryExpression {
    /**
     * Nand constructor.
     * Instantiates a new Nand.
     *
     * @param ex1 one of the expressions received.
     * @param ex2 one of the expressions received.
     */
    public Nand(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }
    @Override
    protected String getOperator() {
        return " A ";
    }

    /**
     * GetClassOperator method.
     *
     * @param e1 the expression received
     * @param e2 the other expression received
     * @return the new Nand class operator.
     */
    protected Expression getClassOperator(Expression e1, Expression e2) {
        return new Nand(e1, e2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getEx1().evaluate(assignment) && getEx2().evaluate(assignment));
    }

    @Override
    public Expression nandify() {
        return new Nand(getEx1().nandify(), getEx2().nandify());
    }

    @Override
    public Expression norify() {
        Expression e1 = new Nor(getEx1(), getEx1());
        Expression e2 = new Nor(getEx2(), getEx2());
        Expression e3 = new Nor(e1, e2);
        return new Nor(e3.norify(), e3.norify());
    }

    @Override
    public Expression simplify() {
        Expression e1 = getEx1().simplify();
        Expression e2 = getEx2().simplify();
        if (e1.toString().equals(e2.toString())) {  // check if expressions are the same
            Expression e = new Not(e1);
            return e.simplify();
        }
        // cases one of the expressions is value and not variable
        if (isVal(e1, e2, "T", "F")) {
            // e1 is T and e2 is a var - return not e2
            Expression e = new Not(e2);
            return e.simplify();
        }
        if (isVal(e2, e1, "T", "F")) {
            // e2 is T and e1 is a var - return not e1
            Expression e = new Not(e1);
            return e.simplify();
        }
        if (isVal(e2, e1, "F", "T") || isVal(e1, e2, "F", "T")) {
            // exp1 is F and exp2 is var or exp2 is F and exp1 is var - return true
            return new Val(true);
        }
        // check if both expressions are values - if so, simplify them according to logic roles
        if (e1.toString().equals("T") && e2.toString().equals("F")
                || e2.toString().equals("T") && e1.toString().equals("F")) {
            return new Val(true);
        }
        // if both expressions are true or both false - we would have executed the first "if" condition
        // so - if we got here both expressions are variables - try to simplify them
        return new Nand(e1.simplify(), e2.simplify());
    }
}
