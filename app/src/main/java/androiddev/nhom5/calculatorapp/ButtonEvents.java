package androiddev.nhom5.calculatorapp;

import android.view.*;
import android.widget.*;

public class ButtonEvents {

    public void ClickOnNumberButton(final Button button, final TextView tView) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tView.setText(tView.getText().toString() + button.getText());
            }
        });
    }
}
