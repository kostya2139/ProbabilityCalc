package com.example.probabilitycalc;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  int k;
    Bernoulli distribution;

    private EditText inpP, inpN, inpK;
    private TextView result;
    private Spinner spinner;
    private final String arr[] = {"<", "=", ">", "<=", ">="};
    private String choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inpP=findViewById(R.id.editTextP);
        inpN=findViewById(R.id.editTextN);
        inpK=findViewById(R.id.editTextK);
        result=findViewById(R.id.textResult);
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    choose=arr[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }

    public void btnCalc(View view) {
        result.setText("wait...");
        if(read_data()) result.setText(String.valueOf(calculating()));
    }

    private double calculating() {
        switch (choose) {
            case "=":
                return distribution.calculatingPrime(k);
            case ">":
                return  distribution.calculatingFromTo(k+1, (int) distribution.n);
            case "<":
                return distribution.calculatingFromTo(0, k-1);
            case ">=":
                return  distribution.calculatingFromTo(k, (int) distribution.n);
            case "<=":
                return distribution.calculatingFromTo(0, k);
        }
        return 0;
    }

    private boolean read_data() {
        double p;
        int n;
        try {
            p = Double.parseDouble(inpP.getText().toString());
            if(p < 0 || p > 1) throw new Exception("p is incorrect");
        }
        catch (Exception e) {
            result.setText("incorrect input p");
            e.getStackTrace();
            return  false;
        }
        try {
            n = Integer.parseInt(inpN.getText().toString());
            if(n<0) throw new Exception("n is incorrect");
        }
        catch (Exception e) {
            result.setText("incorrect input n");
            e.getStackTrace();
            return  false;
        }
        try {
            k = Integer.parseInt(inpK.getText().toString());
            if(k<0 || k>n) throw new Exception("k is incorrect");
        }
        catch (Exception e) {
            result.setText("incorrect input k");
            e.getStackTrace();
            return  false;
        }
        try {
            distribution=new Bernoulli(p, (int) n);
        } catch (Exception e) {
            result.setText("incorrect input");
            e.getStackTrace();
            return  false;
        }
        return true;
    }
}