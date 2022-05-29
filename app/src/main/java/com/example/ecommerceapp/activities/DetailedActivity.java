package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.models.CategoryProductsModel;
import com.example.ecommerceapp.models.NewProductsModel;
import com.example.ecommerceapp.models.PopularProductsModel;
import com.example.ecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {


    ImageView detailedImg;
    TextView rating,name,description,price,quantity;
    Button addToCart,buyNow;

    ImageView addItems,removeItems;

    int totalQuantity = 1;
    int totalPrice = 0;


    //New Product
    NewProductsModel newProductsModel = null;

    //Popular Products
    PopularProductsModel popularProductsModel = null;

    //Show All Products
    ShowAllModel showAllModel = null;

    //Category Products
    CategoryProductsModel categoryProductsModel = null;


    FirebaseAuth auth;
    private FirebaseFirestore firestore;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        //Actionbar hide
        getSupportActionBar().hide();



        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();




        final Object obj = getIntent().getSerializableExtra("detailed");

        //New Products
        if(obj instanceof NewProductsModel){

            newProductsModel = (NewProductsModel) obj;

        }

        //Popular Products
        else if(obj instanceof PopularProductsModel){
            popularProductsModel = (PopularProductsModel) obj;
        }

        //Show All Products
        else if(obj instanceof ShowAllModel){
            showAllModel = (ShowAllModel) obj;
        }

        //Category Products
        else if(obj instanceof CategoryProductsModel){
            categoryProductsModel = (CategoryProductsModel) obj;
        }

        detailedImg = findViewById(R.id.detailed_img);

        name = findViewById(R.id.detailed_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailed_desc);
        price = findViewById(R.id.detailed_price);
        quantity = findViewById(R.id.quantity);

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.remove_item);


        //New products
        if(newProductsModel != null){

            Glide.with(getApplicationContext()).load(newProductsModel.getImg_url()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }



        //Popular products
        if(popularProductsModel != null){

            Glide.with(getApplicationContext()).load(popularProductsModel.getImg_url()).into(detailedImg);
            name.setText(popularProductsModel.getName());
            rating.setText(popularProductsModel.getRating());
            description.setText(popularProductsModel.getDescription());
            price.setText(String.valueOf(popularProductsModel.getPrice()));

            totalPrice = popularProductsModel.getPrice() * totalQuantity;


        }


        //Show All Products
        if(showAllModel != null){

            Glide.with(getApplicationContext()).load(showAllModel.getImg_url()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));

            totalPrice = showAllModel.getPrice() * totalQuantity;

        }


        //Popular products
        if(categoryProductsModel != null){

            Glide.with(getApplicationContext()).load(categoryProductsModel.getImg_url()).into(detailedImg);
            name.setText(categoryProductsModel.getName());
            rating.setText(categoryProductsModel.getRating());
            description.setText(categoryProductsModel.getDescription());
            price.setText(String.valueOf(categoryProductsModel.getPrice()));
        }

        //Buy Now
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(DetailedActivity.this,AddressActivity.class);

               if(newProductsModel!=null){
                   intent.putExtra("item",newProductsModel);
               }
               if(popularProductsModel!=null){
                   intent.putExtra("item",popularProductsModel);
               }
                if(categoryProductsModel!=null){
                    intent.putExtra("item",categoryProductsModel);
                }
               startActivity(intent);
            }
        });


        //Add to Cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtoCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity<10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if(newProductsModel!=null){

                        totalPrice = newProductsModel.getPrice() * totalQuantity;

                    }
                    if(popularProductsModel!=null){
                        totalPrice = popularProductsModel.getPrice() * totalQuantity;

                    }
                    if(categoryProductsModel!=null){
                        totalPrice = categoryProductsModel.getPrice() * totalQuantity;

                    }

                }

            }
        });

        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuantity>1){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }

            }
        });

    }

    private void addtoCart() {

        String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap= new HashMap<>();

        cartMap.put("productName",name.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalprice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                Toast.makeText(DetailedActivity.this,"Added To A Cart",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }




}