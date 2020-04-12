package dexeinc.alephcalculator.arithmetic;

public enum Operand{
    Addition("+"),
    Substraction("-"),
    Multiplication("*"),
    Division("/");

    public final String operand;

    Operand(String operand){
        this.operand = operand;
    }

    @Override
    public String toString() {
        return this.operand;
    }
}
