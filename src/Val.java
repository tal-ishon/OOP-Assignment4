import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tal Ishon.
 * The Class Val.
 */
public class Val implements Expression {

    private boolean value;

    /**
     * Val constructor.
     * Instantiates a new Val.
     *
     * @param b the value of the Val (true or false).
     */
    public Val(boolean b) {
        this.value = b;
    }
    /**
     * GetVal method.
     *
     * @return this value.
     */
    public Boolean getVal() {
        return this.value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        return getVal();
    }

    @Override
    public Boolean evaluate() throws Exception {
        return getVal();
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>(); // return empty list
    }

    @Override
    public String toString() {
        if (getVal()) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
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
