package com.example.sanghyuknam.rxexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import rx.Observable;

import static com.example.sanghyuknam.rxexample.R.id.et1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = (EditText) findViewById(et1);
        TextView result = (TextView) findViewById(R.id.result);
        result.setMovementMethod(new ScrollingMovementMethod());
        Button do_btn = (Button) findViewById(R.id.do_btn);

        do_btn.setOnClickListener(v -> {
            result.setText("");
            Observable.just(editText.getText().toString())
                    .map(dan -> Integer.parseInt(dan))
                    .filter(dan -> 1 <dan && dan < 10)
                    .flatMap(dan -> Observable.range(1, 9), (dan, row) -> dan + " * " + row + " = " + (dan * row))
                    .map(row -> row + '\n')
                    .subscribe(result::append,
                            e -> Toast.makeText(getApplication(), "GUGUDan should be between 2 and 9 dan.",
                                    Toast.LENGTH_SHORT).show());
        });
    }
}
