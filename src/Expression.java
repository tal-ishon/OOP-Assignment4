import java.util.List;
import java.util.Map;

/**
 * @author Tal Ishon.
 * The Interface Expression.
 */
public interface Expression {
    /**
     * Evaluate method.
     * Evaluates the expression using the variable values provided
     * in the assignment.
     *
     * @param assignment the assignment which provides the variable values
     * @return the result of the expression (true or false)
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * Evaluate method.
     * A convenience method. Like the other `evaluate(assignment)` method,
     * but uses an empty assignment.
     * this method evaluates a given expression without using a map.
     * it assumes that the expression contains only values (T/F)
     * and throws exception if not.
     *
     * @return the result of the expression (true or false)
     * @throws Exception If the expression contains a variable an exception is thrown.
     */
    Boolean evaluate() throws Exception;

    /**
     * GetVariables method.
     *
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * ToString method.
     *
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * Assign method.
     * all occurrences of the variable var are replaced with
     * the provided expression (Does not modify the current expression).
     *
     * @param var        the variable we change
     * @param expression the expression which replaces the variable
     * @return a new expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Nandify method.
     *
     * @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * Norify method.
     *
     * @return the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * Simplify method.
     * Recursive method which gets to the 'leaf', checks whether 'leaf' is T/F/var
     * and returns the simplified expression according to the operators' (And, Or etc) logic
     *
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}
