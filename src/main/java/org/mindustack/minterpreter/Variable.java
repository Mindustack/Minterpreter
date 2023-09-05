package org.mindustack.minterpreter;

public class Variable {


    protected final String name;
    protected double value;

    Variable(String name) {
        this.name = name;
        value = 0;
    }

    public Variable setValue(boolean value) {

        if (value) {
            this.value = 1;

        } else {
            this.value = 0;
        }

        return this;

    }

    public void setValue(Variable reg) {
        this.setValue(reg.value);
    }

    public Variable setValue(double value) {

        this.value = value;

        return this;

    }
}
