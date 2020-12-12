package com.example.yaatris;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Adventures#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Adventures extends Fragment  {
    private CardView card;
    List<AdventureData> adventureDataList = new ArrayList<>();
    RecyclerView recyclerView;
    AdventureAdapter adventureAdapter;
    ArrayList<AdvnetureModel> models = new ArrayList<>();
    private StorageReference mStorageRef;

    public Adventures() {
        // Required empty public constructor
    }

    public static Adventures newInstance() {
        Adventures fragment = new Adventures();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_adventures, container, false);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AdventureCompanyImages");

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adventureDataList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    AdventureData advnt = postSnapshot.getValue(AdventureData.class);
                    adventureDataList.add(advnt);
                    final AdvnetureModel m = new AdvnetureModel();
                    m.setAdventureName(advnt.adventureName);
                    m.setSponsor(advnt.cmail);
                    m.setFrom(advnt.from);
                    m.setTo(advnt.to);
                    m.setPrice(advnt.price);
//                    mStorageRef = FirebaseStorage.getInstance().getReference().child("AdventureCompanyImages/" + advnt.imageURL);
//                    try {
//                        final File locaFile = File.createTempFile(advnt.imageURL, "");
//                        mStorageRef.getFile(locaFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                Bitmap bitmap = BitmapFactory.decodeFile(locaFile.getAbsolutePath());
//                                m.setImage(bitmap);
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//
//                            }
//                        });
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    m.setImage(R.drawable.udpr);
                    models.add(m);
                }
                adventureAdapter = new AdventureAdapter(getActivity(), models);
                recyclerView.setAdapter(adventureAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

//        card = (CardView) v.findViewById(R.id.card_view);
//        card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Calling EditText is empty or no method.
//                openAdventure();
//            }
//        });
        return v;
    }

    private  void openAdventure(){
        Intent in = new Intent(getActivity(), AdventureDetails.class);
        startActivity(in);
    }

//    private ArrayList<AdvnetureModel> getAdventureList(){
//        ArrayList<AdvnetureModel> models = new ArrayList<>();
//
//        AdvnetureModel m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        m.setImage(R.drawable.udpr);
//        models.add(m);
//
//        m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        models.add(m);
//
//
//
//        m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        m.setImage(R.drawable.udpr);
//        models.add(m);
//        m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        m.setImage(R.drawable.udpr);
//        models.add(m);
//        m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        m.setImage(R.drawable.udpr);
//        models.add(m);
//        m = new AdvnetureModel();
//        m.setAdventureName("Hello");
//        m.setSponsor("Tripaxy");
//        m.setFrom("29 Dec");
//        m.setTo("23 Jan");
//        m.setPrice("Rs. 15000");
//        m.setImage(R.drawable.udpr);
//        models.add(m);
//
//        return  models;
//    }
}