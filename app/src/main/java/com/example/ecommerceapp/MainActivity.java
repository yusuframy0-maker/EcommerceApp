
package com.example.ecommerceapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    ArrayList<Product> products;
    ArrayList<Product> filteredProducts;

    EditText searchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.productsRecyclerView);
        searchEditText = findViewById(R.id.searchEditText);

        products = new ArrayList<>();
        filteredProducts = new ArrayList<>();

        products.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam",
                "US$48.89", R.drawable.camera_placeholder));

        products.add(new Product("Bitzwolf B-CC1 Camera 1080P HD Webcam",
                "US$55.89", R.drawable.camera_placeholder));

        products.add(new Product("Bitzwolf HD Camera",
                "US$78.89", R.drawable.camera_placeholder));

        products.add(new Product("Wireless Security Camera",
                "US$88.55", R.drawable.camera_placeholder));

        products.add(new Product("HD Webcam",
                "US$23.67", R.drawable.camera_placeholder));

        products.add(new Product("Smart Camera",
                "US$44.89", R.drawable.camera_placeholder));

        filteredProducts.addAll(products);

        adapter = new ProductAdapter(filteredProducts);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {

                filterProducts(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void filterProducts(String query) {

        filteredProducts.clear();

        for (Product product : products) {

            if (product.getName().toLowerCase()
                    .contains(query.toLowerCase())) {

                filteredProducts.add(product);
            }
        }

        adapter.notifyDataSetChanged();
    }
}
