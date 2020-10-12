package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, bntPlusMinus, btnPlus, btnMinus, btnMultiplication, btnDiviation, btnDot, btnEqual;

    TextView tvresult;
    TextView tvequation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnClear = findViewById(R.id.btnClear);
        bntPlusMinus = findViewById(R.id.btnPlusMinus);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btnDiviation = findViewById(R.id.btnDiviation);
        btnDot = findViewById(R.id.btnDot);
        btnEqual = findViewById(R.id.btnEqual);
        tvresult = findViewById(R.id.Result_Field);
        tvequation = findViewById(R.id.Equation_Field);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn3.getText().toString());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn4.getText().toString());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn5.getText().toString());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn6.getText().toString());
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn7.getText().toString());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn8.getText().toString());
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn9.getText().toString());
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btn0.getText().toString());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btnPlus.getText().toString());
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btnMinus.getText().toString());
            }
        });

        btnDiviation.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btnDiviation.getText().toString());
            }
        });

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btnMultiplication.getText().toString());
            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                tvresult.setText(val + btnDot.getText().toString());
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View v) {
                String val = tvresult.getText().toString();
                if (val.isEmpty()) {
                    tvresult.setText("");
                } else {
                    val = val.substring(0, val.length() - 1);
                    tvresult.setText(val);
                }
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = tvresult.getText().toString();
                //String replacedString = val.replace('÷','/').replace('×', '*');
                double result = eval(val);
                String r = String.valueOf(result);
                tvresult.setText(r);
                tvequation.setText(val);
            }
        });
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}