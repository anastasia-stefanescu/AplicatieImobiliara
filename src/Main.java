import serviciu.Serviciu;

public class Main {
    public static void main(String[] args) {
        Serviciu serviciu = Serviciu.getInstance();

        String str = serviciu.getUserInput();
        while (!str.equals("stop"))
        {
            serviciu.processInput(str);
            str = serviciu.getUserInput();
        }
    }
}