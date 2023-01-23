import java.util.Map;

/**
 * @author Tal Ishon.
 * The Class Not.
 */
public class Not extends UnaryExpression {
    /**
     * Not constructor.
     * Instantiates a new Not.
     *
     * @param ex the given expression
     */
    public Not(Expression ex) {
        super(ex);
    }
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return !getEx().evaluate(assignment);
    }

    /**
     * GetClassOperator method.
     *
     * @param e the expression received
     * @return the new Not class operator.
     */
    protected Expression getClassOperator(Expression e) {
        return new Not(e);
    }

    @Override
    protected String getOperator() {
        return "~";
    }
    @Override
    public Expression nandify() {
        return new Nand(getEx().nandify(), getEx().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(getEx().norify(), getEx().norify());
    }

    @Override
    public Expression simplify() {
        Expression e = getEx().simplify();
        // cases exp is a val
        if (e.toString().equals("T")) { // exp is ~(T) which means to F
            return new Val(false);
        }
        if (e.toString().equals("F")) { // exp is ~(F) which means to T
            return new Val(true);
        }
        // if we got here exp is not a val
        return new Not(e);
    }
}
