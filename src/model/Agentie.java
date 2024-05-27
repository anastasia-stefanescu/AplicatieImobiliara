package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Agentie extends Proprietar {
    private int comision;

    public Agentie() {}
    public Agentie(Scanner scanner){
        super(scanner);
        System.out.println(" - Comision(numere 0-100!): ");
        int s = scanner.nextInt();
        this.comision = s;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }


    public int getComision() {
        return comision;
    }

    @Override
    public String toString() {
        return
                      super.nume +
                      " Comision ='" + String.valueOf(comision) +
                      ", Suma disponibila =" + String.valueOf(super.suma_bani);

    }
}
