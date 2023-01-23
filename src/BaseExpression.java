import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tal Ishon.
 * Abstract Class BaseExpression.
 * this is an abstract class which represents a basic expression.
 */
public abstract class BaseExpression implements Expression {
    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();
        return this.evaluate(assignment);
    }
}
