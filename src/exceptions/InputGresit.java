package exceptions;

public class InputGresit extends Exception {
    public InputGresit(String s) {
        super(s);
        System.out.println("Tipul inputului este gresit / null");
    }
}
