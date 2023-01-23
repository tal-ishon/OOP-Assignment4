import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ishon.
 * Abstract Class UnaryExpression.
 * This class represents a unary expression.
 * It is a subclass of UnaryExpression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression ex;
    /**
     * UnaryExpression constructor.
     * Instantiates a new UnaryExpression.
     *
     * @param ex the given expression
     */
    protected UnaryExpression(Expression ex) {
        this.ex = ex;
    }
    /**
     * GetEx method.
     *
     * @return this expression.
     */
    protected Expression getEx() {
        return ex;
    }
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>(getEx().getVariables());
        List<String> newList = new ArrayList<>();
        for (String s : list) { // make sure there are no duplicated variables
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }
        return newList;
    }
    /**
     * GetOperator abstract method.
     * all classes inherit from BinaryExpression must implement this method.
     * Each class has it's unique operator.
     *
     * @return the unique string operator of the specific binary class.
     */
    protected abstract String getOperator();
    @Override
    public String toString() {
        return getOperator() + "(" + getEx().toString() + ")";
    }
    /**
     * GetClassOperator abstract method.
     * all classes inherit from UnaryExpression must implement this method.
     * Each class has it's unique operator.
     *
     * @param e the expression received
     * @return the unique class operator of the specific binary class.
     */
    protected abstract Expression getClassOperator(Expression e);
    @Override
    public Expression assign(String var, Expression expression) {
        Expression e = getEx().assign(var, expression);
        return getClassOperator(e);
    }
}
