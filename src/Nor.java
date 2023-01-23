import java.util.Map;
/**
 * @author Tal Ishon.
 * The Class Nor.
 */
public class Nor extends BinaryExpression {
    /**
     * Nor constructor.
     * Instantiates a new Nor.
     *
     * @param ex1 one of the expressions received.
     * @param ex2 one of the expressions received.
     */
    public Nor(Expression ex1, Expression ex2) {
       super(ex1, ex2);
    }
    @Override
    protected String getOperator() {
        return " V ";
    }

    /**
     * GetClassOperator method.
     *
     * @param e1 the expression received
     * @param e2 the other expression received
     * @return the new Nor class operator.
     */
    protected Expression getClassOperator(Expression e1, Expression e2) {
        return new Nor(e1, e2);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !(getEx1().evaluate(assignment) || getEx2().evaluate(assignment));
    }

    @Override
    public Expression nandify() {
        Expression nandEx1 = getEx1().nandify();
        Expression nandEx2 = getEx2().nandify();
        Expression e = new Nand(new Nand(nandEx1, nandEx1), new Nand(nandEx2, nandEx2));
        return new Nand(e.nandify(), e.nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getEx1().norify(), getEx2().norify());
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
        if (isVal(e1, e2, "T", "F") || isVal(e2, e1, "T", "F")) {
            // exp1 is T and exp2 is var or exp2 is T and exp1 is var - return false
            return new Val(false);
        }
        if (isVal(e1, e2, "F", "T")) {
            // e1 is F and e2 is a var - return not e2
            Expression e = new Not(e2);
            return e.simplify();
        }
        if (isVal(e2, e1, "F", "T")) {
            // e2 is F and e1 is a var - return not e1
            Expression e = new Not(e1);
            return e.simplify();
        }
        // check if both expressions are values - if so, simplify them according to logic roles
        if (e1.toString().equals("T") && e2.toString().equals("F")
                || e2.toString().equals("T") && e1.toString().equals("F")) {
            return new Val(false);
        }
        // if both expressions are true or both false - we would have executed the first "if" condition
        // so - if we got here both expressions are variables - try to simplify them
        return new Nor(e1.simplify(), e2.simplify());
    }
}
