package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapters.CategoryAdapter;
import com.example.ecommerceapp.models.CategoryModel;
import com.example.ecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowAllCategoryActivity extends AppCompatActivity {


    RecyclerView catRecyclerView;

    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;


    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_category);

        //Actionbar hide
        getSupportActionBar().hide();

        firestore = FirebaseFirestore.getInstance();


        catRecyclerView = findViewById(R.id.show_all_category_rec);
        catRecyclerView.setLayoutManager(new GridLayoutManager(this,3));


        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);

        firestore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for (DocumentSnapshot doc:task.getResult().getDocuments()){

                                CategoryModel categoryModel = doc.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();

                            }
                        }

                    }
                });


    }
}