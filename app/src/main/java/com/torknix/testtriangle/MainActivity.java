package com.torknix.testtriangle;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.editText_side1)
    TextInputEditText editTextSide1;
    @BindView(R.id.editText_side2)
    TextInputEditText editTextSide2;
    @BindView(R.id.editText_side3)
    TextInputEditText editTextSide3;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView_result)
    TextView textViewResult;
    @BindView(R.id.button_test)
    Button buttonTest;

    int side_1, side_2, side_3 = 0;
    @BindView(R.id.button_clear)
    Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextSide1.setText("");
                editTextSide2.setText("");
                editTextSide3.setText("");
                textViewResult.setText("Test Result");

                editTextSide1.setError(null);
                editTextSide2.setError(null);
                editTextSide3.setError(null);

                editTextSide1.requestFocus();
            }
        });


    }

    public int TestTriangle(int side1, int side2, int side3) {


        //Inputs don’t represent a triangle

        if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1) {


            //(side1 > side3 - side2 && side1 > side2 - side3 && side2 > side1 - side3)

            return 0;
        } else {

            //The triangle has no side of equal length
            if (side1 != side2 && side1 != side3) {
                return 1;

                //The triangle has 3 sides of equal length
            } else if (side1 == side2 && side1 == side3) {
                return 3;
                //The triangle has 2 sides of equal length
            } else if (side1 == side2 || side1 == side3 || side2 == side3) {
                return 2;

            }
        }

        //Default case
        return 4;

    }

    @OnClick(R.id.button_test)
    public void onViewClicked() {
        if (editTextSide1.getText().length() < 1 && editTextSide2.getText().length() < 1 && editTextSide3.getText().length() < 1) {
            editTextSide1.setError("You must introduce an integer value");
            editTextSide2.setError("You must introduce an integer value");
            editTextSide3.setError("You must introduce an integer value");

        } else if (editTextSide1.getText().length() < 1) {
            editTextSide1.setError("You must introduce an integer value");
        } else if (editTextSide2.getText().length() < 1) {
            editTextSide2.setError("You must introduce an integer value");
        } else if (editTextSide3.getText().length() < 1) {
            editTextSide3.setError("You must introduce an integer value");
        } else {

            side_1 = Integer.parseInt(editTextSide1.getText().toString());
            side_2 = Integer.parseInt(editTextSide2.getText().toString());
            side_3 = Integer.parseInt(editTextSide3.getText().toString());

            if (side_1 == 0) {
                editTextSide1.setError("The value must be > 0");
            } else if (side_2 == 0) {
                editTextSide2.setError("The value must be > 0");
            } else if (side_3 == 0) {
                editTextSide3.setError("The value must be > 0");
            } else {
                editTextSide1.setError(null);
                editTextSide2.setError(null);
                editTextSide3.setError(null);


                int result = TestTriangle(side_1, side_2, side_3);

                switch (result) {
                    case 0:
                        textViewResult.setText("Inputs don’t represent a triangle");
                        break;
                    case 1:
                        textViewResult.setText("The triangle has no side of equal length");
                        break;
                    case 2:
                        textViewResult.setText("The triangle has 2 sides of equal length");
                        break;
                    case 3:
                        textViewResult.setText("The triangle has 3 sides of equal length");
                        break;

                }

            }

        }


    }


}
