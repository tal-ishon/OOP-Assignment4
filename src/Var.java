import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tal Ishon.
 * The Class Var.
 */
public class Var implements Expression {

    private String variable;

    /**
     * Var constructor.
     * Instantiates a new Var.
     *
     * @param s the string symbols the variable.
     */
    public Var(String s) {
        this.variable = s;
    }
    /**
     * getVariable method.
     *
     * @return this variable.
     */
    public String getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return getVariable();
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(getVariable())) {
            return assignment.get(getVariable());
        } else {
            throw new Exception("The variable " + getVariable() + " is not in assignment list");
        }
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("The variable " + getVariable() + " is not in assignment list");
    }
    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(getVariable());
        if (list.isEmpty()) { // make sure list is not empty
            return null;
        }
        return list;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        if (getVariable().equals(var)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
