import java.util.ArrayList;
import java.util.List;

/**
 * @author Tal Ishon.
 * Abstract Class BinaryExpression.
 * This class represents a binary expression.
 * It is a subclass of BaseExpression.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression expression1;
    private Expression expression2;

    /**
     * GetOperator abstract method.
     * all classes inherit from BinaryExpression must implement this method.
     * Each class has it's unique operator.
     *
     * @return the unique string operator of the specific binary class.
     */
    protected abstract String getOperator();
    /**
     * GetClassOperator abstract method.
     * all classes inherit from BinaryExpression must implement this method.
     * Each class has it's unique operator.
     *
     * @param e1 the expression received
     * @param e2 the other expression received
     * @return the unique class operator of the specific binary class.
     */
    protected abstract Expression getClassOperator(Expression e1, Expression e2);

    /**
     * BinaryExpression constructor.
     * Instantiates a new And.
     *
     * @param ex1 one of the expressions received.
     * @param ex2 one of the expressions received.
     */
    protected BinaryExpression(Expression ex1, Expression ex2) {
        this.expression1 = ex1;
        this.expression2 = ex2;
    }

    /**
     * GetEx1 method.
     *
     * @return this expression1.
     */
    protected Expression getEx1() {
        return expression1;
    }

    /**
     * GetEx2 method.
     *
     * @return this expression2.
     */
    protected Expression getEx2() {
        return expression2;
    }
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.addAll(getEx1().getVariables());
        list.addAll(getEx2().getVariables());
        List<String> newList = new ArrayList<>();
        for (String s : list) { // make sure there are no duplicated variables
           if (!newList.contains(s)) {
               newList.add(s);
           }
        }
        return newList;
    }
    @Override
    public String toString() {
        return "(" + getEx1() + getOperator() + getEx2() + ")";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        Expression e1 = getEx1().assign(var, expression);
        Expression e2 = getEx2().assign(var, expression);
        return getClassOperator(e1, e2);
    }

    /**
     * IsVal method.
     * checks if any of the expressions is a val and the other is a variable
     *
     * @param e1 the expression which is checked if a val (T/F)
     * @param e2 the expression which is checked if a variable
     * @param s1 the string which represents the val T/F
     * @param s2 the string which represents the other val T/F
     * @return if one of the expressions is a val and the other is a variable - returns true, otherwise, returns false.
     */
    protected Boolean isVal(Expression e1, Expression e2, String s1, String s2) {
        return e1.toString().equals(s1)
                && !e2.toString().equals(s1)
                && !e2.toString().equals(s2);
    }
}
