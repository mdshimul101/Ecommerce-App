package com.example.ecommerceapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.ecommerceapp.R;
import com.example.ecommerceapp.adapters.CategoryProductsAdapter;
import com.example.ecommerceapp.adapters.ShowAllAdapter;
import com.example.ecommerceapp.models.CategoryProductsModel;
import com.example.ecommerceapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoryProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryProductsAdapter categoryProductsAdapter;
    List<CategoryProductsModel> categoryProductsModelList;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category_product);

        //Actionbar hide
        getSupportActionBar().hide();


        String type = getIntent().getStringExtra("type");


        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.show_category_product_rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        categoryProductsModelList = new ArrayList<>();
        categoryProductsAdapter = new CategoryProductsAdapter(this, categoryProductsModelList);
        recyclerView.setAdapter(categoryProductsAdapter);


        if ((type != null && type.equalsIgnoreCase("camera"))) {

            firestore.collection("Camera").whereEqualTo("type", "camera")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                  //  Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }

        if (type != null && type.equalsIgnoreCase("kids collection")) {

            firestore.collection("Kids Collection").whereEqualTo("type", "kids collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                //    Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }

        if (type != null && type.equalsIgnoreCase("men collection")) {

            firestore.collection("Men Collection").whereEqualTo("type", "men collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                  //  Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }

        if (type != null && type.equalsIgnoreCase("shoes collection")) {

            firestore.collection("Shoes Collection").whereEqualTo("type", "shoes collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                  //  Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }

        if (type != null && type.equalsIgnoreCase("watch collection")) {

            firestore.collection("Watch Collection").whereEqualTo("type", "watch collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                  //  Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }

        if (type != null && type.equalsIgnoreCase("women collection")) {

            firestore.collection("Women Collection").whereEqualTo("type", "women collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful()) {
                                for (DocumentSnapshot doc : task.getResult().getDocuments()) {

                                    CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
                                    categoryProductsModelList.add(categoryProductsModel);
                                    categoryProductsAdapter.notifyDataSetChanged();

                                  //  Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();

                                }
                            }

                        }
                    });

        }




//        if (type != null) {
//
//
//
//            if (type.equalsIgnoreCase("camera")) {
//
//                firestore.collection("Camera").whereEqualTo("type", "camera")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                                if (task.isSuccessful()) {
//                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
//
//                                        CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
//                                        categoryProductsModelList.add(categoryProductsModel);
//                                        categoryProductsAdapter.notifyDataSetChanged();
//
//                                        Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }
//
//                            }
//                        });
//            }
//
//
//            else if(type.equalsIgnoreCase("kids collection")){
//
//                firestore.collection("Kids Collection").whereEqualTo("type", "kids collection")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                                if (task.isSuccessful()) {
//                                    for (DocumentSnapshot doc : task.getResult().getDocuments()) {
//
//                                        CategoryProductsModel categoryProductsModel = doc.toObject(CategoryProductsModel.class);
//                                        categoryProductsModelList.add(categoryProductsModel);
//                                        categoryProductsAdapter.notifyDataSetChanged();
//
//                                        Toast.makeText(getApplicationContext(),"It "+type+" Category",Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }
//
//                            }
//                        });
//
//            }
//
//        }


    }
}