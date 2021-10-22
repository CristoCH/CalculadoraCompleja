package dad.calculadora;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NumComplejo {

    private DoubleProperty real = new SimpleDoubleProperty();
    private DoubleProperty imaginaria = new SimpleDoubleProperty();

    public NumComplejo() {
        super();
    }

    public NumComplejo(DoubleProperty real, DoubleProperty imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }

    public double getReal() {
        return real.get();
    }

    public DoubleProperty realProperty() {
        return real;
    }

    public void setReal(double real) {
        this.real.set(real);
    }

    public double getImaginaria() {
        return imaginaria.get();
    }

    public DoubleProperty imaginariaProperty() {
        return imaginaria;
    }

    public void setImaginaria(double imaginaria) {
        this.imaginaria.set(imaginaria);
    }
}
