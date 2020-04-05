package dexeinc.alephcalculator.assets;

import android.view.View;
import android.widget.Button;

public class CalculatorButtonBuilder {
    private Button button;
    private int numeriValue;

    public CalculatorButtonBuilder(View view, int numericValue){
        this.button = (Button) view;
        this.numeriValue = numericValue;
    }
}
