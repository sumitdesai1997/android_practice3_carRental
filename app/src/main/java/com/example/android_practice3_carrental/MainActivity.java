package com.example.android_practice3_carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    Spinner spBrand,spModel;
    ImageView imgCar;
    TextView tvDrate, tvCMillage, tvRdays, tvTotAmount;
    SeekBar sbRdays;
    RadioButton rb21, rb2264, rb65;
    CheckBox cbNavigator, cbChild, cbBike;
    Button btnTotal;

    public static String brandList[] = {"Tata", "Hyundai", "Honda", "Mahindra"};
    public static ArrayList<Model> modelList = new ArrayList<>();
    public static ArrayList<String> tempModelList = new ArrayList<>();
    public static double total = 0;
    public static double insurance = 30;
    public static double options = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spBrand = findViewById(R.id.spBrand);
        spModel = findViewById(R.id.spModel);
        imgCar = findViewById(R.id.imgCar);
        tvDrate = findViewById(R.id.tvDrate);
        tvCMillage = findViewById(R.id.tvCMillage);
        tvRdays = findViewById(R.id.tvRdays);
        tvTotAmount = findViewById(R.id.tvTotAmount);
        sbRdays = findViewById(R.id.sbRdays);
        rb21 = findViewById(R.id.rb21);
        rb2264 = findViewById(R.id.rb2264);
        rb65 = findViewById(R.id.rb65);
        cbNavigator = findViewById(R.id.cbNavigator);
        cbChild = findViewById(R.id.cbChild);
        cbBike = findViewById(R.id.cbBike);
        btnTotal = findViewById(R.id.btnTotal);

        tvRdays.setText("1");
        tvTotAmount.setText("0");
        fillData();

        // FOR BRAND NAME SPINNER
        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, brandList);
        spBrand.setAdapter(aa1);
        spBrand.setOnItemSelectedListener(new SpinnerEvents());

        // FOR MODEL NAME SPINNER
        spModel.setOnItemSelectedListener(new SpinnerEvents());

        // FOR SEEKBAR
        sbRdays.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvRdays.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // FOR RADIO BUTTONS
        rb21.setOnClickListener(new RadioButtonEvents());
        rb2264.setOnClickListener(new RadioButtonEvents());
        rb65.setOnClickListener(new RadioButtonEvents());

        // FOR CHECKBOXES
        cbNavigator.setOnCheckedChangeListener(new CheckboxEvents());
        cbBike.setOnCheckedChangeListener(new CheckboxEvents());
        cbChild.setOnCheckedChangeListener(new CheckboxEvents());

        // FOR BUTTON
        btnTotal.setOnClickListener(new ButtonEvents());
    }

    public static void fillData(){
        modelList.add(new Model("Harrier", brandList[0],"harrier", 5.5, 6573));
        modelList.add(new Model("Nexon", brandList[0],"nexon", 6.5, 4857));
        modelList.add(new Model("Electrical", brandList[0],"electrical", 8.5, 9577));
        modelList.add(new Model("Altroz", brandList[0],"altroz", 7.5, 3547));
        modelList.add(new Model("i20", brandList[1],"i20", 1.5, 875));
        modelList.add(new Model("Verna", brandList[1],"verna", 3.5, 563));
        modelList.add(new Model("Creta", brandList[1],"creta", 3.25, 145));
        modelList.add(new Model("i10", brandList[1],"i10", 2.5, 655));
        modelList.add(new Model("Santro", brandList[1],"santro", 2.25, 676));
        modelList.add(new Model("Amaze", brandList[2],"amaze", 5.0, 78483));
        modelList.add(new Model("Civic", brandList[2],"civic", 4.0, 88373));
        modelList.add(new Model("Thar", brandList[3],"thar", 2.75, 3717));
        modelList.add(new Model("Verito", brandList[3],"verito", 3.75, 4854));
        modelList.add(new Model("XUV", brandList[3],"xuv", 4.75, 4821));
    }

    public class SpinnerEvents implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(parent.getId() == R.id.spBrand){
                tempModelList.clear();
                fillTempModelList(brandList[position]);
                ArrayAdapter aa2 = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, tempModelList);
                spModel.setAdapter(aa2);
            } else {
               Model obj = getModelObject(tempModelList.get(position));
               tvDrate.setText(String.valueOf(obj.getdRate()));
               tvCMillage.setText(String.valueOf(obj.getcMillage()));

               int imgId=getResources().getIdentifier(obj.getImage(),"mipmap",getPackageName());
               imgCar.setImageResource(imgId);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            if(parent.getId() == R.id.spBrand){
                fillTempModelList(brandList[0]);
            } else {
                Model obj = getModelObject(tempModelList.get(0));
                tvDrate.setText(String.valueOf(obj.getdRate()));
                tvCMillage.setText(String.valueOf(obj.getcMillage()));

                int imgId=getResources().getIdentifier(obj.getImage(),"mipmap",getPackageName());
                imgCar.setImageResource(imgId);
            }
        }
    }

    public static void fillTempModelList(String brandName){
        for(Model model:modelList){
            if(model.getBrandName().equals(brandName)){
                tempModelList.add(model.getModelName());
            }
        }
    }

    public static Model getModelObject(String modelName){
        for(Model model: modelList){
            if(model.getModelName().equals(modelName)){
                return model;
            }
        }
        return null;
    }

    public class RadioButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rb21:
                    insurance = 30;
                    break;
                case R.id.rb2264:
                    insurance = 17;
                    break;
                case R.id.rb65:
                    insurance = 22;
                    break;
            }

        }
    }

    public class CheckboxEvents implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            options = 0;
            if(cbNavigator.isChecked()){
                options += 5;
            }
            if (cbChild.isChecked()){
                options += 7;
            }
            if(cbBike.isChecked()){
                options += 10;
            }
        }
    }

    public class ButtonEvents implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            total = 0;
            double rDays = Double.parseDouble(tvRdays.getText().toString());
            double dRate = Double.parseDouble(tvDrate.getText().toString());
            //double insurance = Double.parseDouble(tvInsurance.getText().toString());

            total = ((rDays * dRate) + insurance + options);
            tvTotAmount.setText(String.format("%.2f",total));
        }
    }

}