package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.converter.R;
import com.google.gson.JsonObject;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.converter.BuildConfig.API_KEY;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.edit_input)
    EditText edit_input;
    @BindView(R.id.txt_output)
    TextView txt_Output;
    @BindView(R.id.spinner_one)
    NiceSpinner spinnerBase;
    @BindView(R.id.spinner_two)
    NiceSpinner spinnerSecond;
    private ArrayList<String> ratesValue;
    private   Object[] ratesKey ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        convertCurrency();
        spinnerList();
        getConverterMethod();

    }






    private void spinnerList(){
        spinnerBase.setOnSpinnerItemSelectedListener((parent, view, position, id) -> {
          
        });

        spinnerSecond.setOnSpinnerItemSelectedListener((parent, view, position, id) -> {

        });
    }

    private void getConverterMethod(){

        edit_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_Output.setText(s.toString());

            }
        });
    }

    private void convertCurrency() {
        RetrofitBuilder.getService()
                .currency(API_KEY)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject object = response.body();
                        if (response.isSuccessful() && response.body() !=null){
                            JsonObject jsonObject = object.getAsJsonObject("rates");
                            ratesValue = new ArrayList<>();
                            ratesKey = jsonObject.keySet().toArray();
                            for (Object jobject : ratesKey) {
                                ratesValue.add(String.valueOf(jsonObject.getAsJsonPrimitive(jobject.toString())));
                            }
                            ArrayAdapter<Object> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, ratesKey);
                            spinnerBase.setAdapter(adapter);
                            spinnerSecond.setAdapter(adapter);
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage() + "Internet OFF", Toast.LENGTH_LONG).show();

                    }
                });
    }
}
