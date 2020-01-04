package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.converter.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        convertCurrency();

    }

    private void convertCurrency() {
        RetrofitBuilder.getService()
                .currency(API_KEY)
                 .enqueue(new Callback<JsonObject>() {
                     @Override
                     public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                         getJsonObject(response);
                     }

                     @Override
                     public void onFailure(Call<JsonObject> call, Throwable t) {
                         Toast.makeText(getApplicationContext(), t.getLocalizedMessage() + "Internet OFF", Toast.LENGTH_LONG).show();

                     }
                 });
    }



    private void getJsonObject(Response<JsonObject> response){
      //  JsonObject object = new Gson().fromJson(response, JsonObject.class);
        JsonObject rates = response.body().getAsJsonObject("rates");
        Object[] ratesKey = rates.keySet().toArray();
        ratesValue = new ArrayList<>();
        for (Object o : ratesKey) {
            ratesValue.add(String.valueOf(rates.getAsJsonPrimitive(o.toString())));
        }
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

}
