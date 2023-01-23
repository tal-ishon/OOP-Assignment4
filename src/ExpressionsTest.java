import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tal Ishon.
 * Class ExpressionsTest.
 * contains main method - tests code.
 */
public class ExpressionsTest {
    /**
     * Main method.
     *
     * @param args the input.
     */
    public static void main(String[] args) {
        Expression e = new Xor(new Or(new Var("x"), new Val(true)), new Nand(new Var("y"), new Var("t")));
        System.out.println(e.toString());
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", true);
        assignment.put("t", true);
        Boolean value;
        try {
            value = e.evaluate(assignment);
            System.out.println("The result is: " + value);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(e.nandify());
        System.out.println(e.norify());
        System.out.println(e.simplify());
    }
}
