package com.example.project1yingyingzhao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.project1yingyingzhao.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.seekBar.setOnSeekBarChangeListener(new seekBarListener());
        binding.principle.addTextChangedListener(new principleListener());
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculate(binding.radioGroup);
            }
        });
        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculate(binding.checkBox);
            }
        });
        binding.uninstall.setOnClickListener(this::uninstall);
        binding.cal.setOnClickListener(this::calculate);
    }

    private class seekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            float J = (float) progress / 1000;
            binding.rateView.setText("Interest rate:" + String.format("%.1f", J * 100) + "%");
            calculate(binding.result); // live update
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    private class principleListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int
                count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            calculate(binding.principle);
        }

    }
    public void calculate(View v){
        String input = binding.principle.getText().toString();
        if(!validate(input)){
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_LONG).show();
            return;
        }
        float P = Float.parseFloat(input);
        float J = (float)binding.seekBar.getProgress() / 1000;
        int N = getNumOfMonth();
        float T = binding.checkBox.isChecked()? 0.1f / 100 * P : 0f;
        float R = CalculateUtil.calculateTax(P, J / 12, N, T);
        binding.result.setText("Monthly payment:" + String.format("%.2f", R));
    }
    // validate user input: not empty and up to 2 decimal places
    public boolean validate(String input){
        if (input.length() == 0) {
            binding.result.setText("Please enter the principle. Then Press CALCULATE for monthly payments.");
           return false;
        } else if (input.indexOf(".") >= 0 && (input.indexOf(".") < input.length() - 3)){
            binding.result.setText("Please enter a valid number. 2 decimal digits max. Then Press CALCULATE for monthly payments.");
            return false;
        }
        return true;
    }

    public int getNumOfMonth(){
        if(binding.b15.isChecked()) return 15 * 12;
        else if(binding.b20.isChecked()) return 20 * 12;
        else return 30 * 12;
    }

    public void uninstall(View v){
        Intent delete = new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + getPackageName()));
        startActivity(delete);
    }
}