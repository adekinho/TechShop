package com.example.musicshop1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;
    ArrayList<String> spinnerArrayList;
    ArrayAdapter<String> spinnerAdapter;


    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.informationTextView);
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("HyperX Cloud 2 Wireless");
        spinnerArrayList.add("Logitech G pro X");
        spinnerArrayList.add("Corsair HS70 PRO");
        spinnerArrayList.add("Razer Blackshark v2 PRO");
        spinnerArrayList.add("Steel Series Arctis 7");
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {

        //Тут используется ХашМап. Оно хорошо подайдет для использывания. включает в себя саму строку и к ниму прилогается, так сказать ключ.

        goodsMap = new HashMap();
        goodsMap.put("HyperX Cloud 2 Wireless", 140.0);
        goodsMap.put("Logitech G pro X", 180.0);
        goodsMap.put("Corsair HS70 PRO", 170.0);
        goodsMap.put("Razer Blackshark v2 PRO", 130.0);
        goodsMap.put("Steel Series Arctis 7", 160.0);
    }

    public void increaseQuantity(View view) {
        quantity = quantity + 1;
        TextView quantityNumber = findViewById(R.id.quantityNumber);
        quantityNumber.setText("" + quantity);

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView quantityNumber = findViewById(R.id.quantityNumber);
        quantityNumber.setText("" + quantity);

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        //С помощью switch можно не использывать Иф. На много лутчше будет использывать этот способ написания.

        switch (goodsName) {
            case "HyperX Cloud 2 Wireless":
                goodsImageView.setImageResource(R.drawable.cloud_hyperx);
                break;
            case "Logitech G pro X":
                goodsImageView.setImageResource(R.drawable.logitech);
                break;
            case "Corsair HS70 PRO":
                goodsImageView.setImageResource(R.drawable.corsair);
                break;
            case "Razer Blackshark v2 PRO":
                goodsImageView.setImageResource(R.drawable.razer);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.arctis);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void addToCart(View view) {

        Order order = new Order();

        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity * price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("goodsNameForIntent", order.goodsName);
        orderIntent.putExtra("quantityForIntent", order.quantity);
        orderIntent.putExtra("priceForIntent", order.price);
        orderIntent.putExtra("orderPriceForIntent", order.orderPrice);
        startActivity(orderIntent);

    }
}