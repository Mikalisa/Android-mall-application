package layout;
/**
 MIT License

 Copyright (c) 2019 Michal Youssef Issa

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.

 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

import mallcom.android.yourmall.DataSectionModel;
import mallcom.android.yourmall.R;
import mallcom.android.yourmall.RecyclerViewDataAdapter;
import mallcom.android.yourmall.SingleItem;


public class HomeFragment extends Fragment implements RecyclerViewDataAdapter.onMallListener {

    private ArrayList<DataSectionModel> allSampleData;
    private AdapterView.OnItemSelectedListener listener;
    RecyclerViewDataAdapter adapter;
    private ArrayList<SingleItem> shoppings;
    private ArrayList<SingleItem> offers;
    private ArrayList<SingleItem> rest;
    private ArrayList<SingleItem> clinic;
    private SingleItem singleItem;



    // setup for firebase database, offers section
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference offers1 = db.collection("Offers");

    // setup for firebase database, shopping section
    private CollectionReference mShopping = db.collection("Shopping");

    // setup for Restaurant section
    private CollectionReference restaurant = db.collection("Rest");

    // setup for clinic section
    private CollectionReference mClinic = db.collection("Clinics");




    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        allSampleData = new ArrayList<>();
        offers = new ArrayList<>();
        shoppings = new ArrayList<>();
        rest = new ArrayList<>();
        clinic = new ArrayList<>();

        createDummyData();

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewDataAdapter(allSampleData, getContext(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    private void createDummyData() {

        DataSectionModel dm = new DataSectionModel();
        dm.setHeaderTitle(getString(R.string.offers));

        DataSectionModel dm0 = new DataSectionModel();
        dm0.setHeaderTitle(getString(R.string.shopping));

        DataSectionModel dm1 = new DataSectionModel();
        dm1.setHeaderTitle(getString(R.string.rest));

        DataSectionModel dm2 = new DataSectionModel();
        dm2.setHeaderTitle(getString(R.string.clinics));

        offerSection();
        shoppingSection();
        restSection();
        clinicSection();


        dm.setAllItemInSection(offers);
        dm0.setAllItemInSection(shoppings);
        dm1.setAllItemInSection(rest);
        dm2.setAllItemInSection(clinic);

        allSampleData.add(dm);
        allSampleData.add(dm0);
        allSampleData.add(dm1);
        allSampleData.add(dm2);
    }

    private void offerSection (){
        offers1.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {


                    if (dc.getType() == DocumentChange.Type.ADDED) {


                        singleItem = dc.getDocument().toObject(SingleItem.class);
                        offers.add(singleItem);
                        adapter.notifyDataSetChanged();


                    }


                }

            }
        });

    }

    private void shoppingSection (){
        mShopping.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {


                    if (dc.getType() == DocumentChange.Type.ADDED) {


                        singleItem = dc.getDocument().toObject(SingleItem.class);
                        shoppings.add(singleItem);
                        adapter.notifyDataSetChanged();


                    }


                }

            }
        });



    }

    private void restSection (){

        restaurant.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {


                    if (dc.getType() == DocumentChange.Type.ADDED) {


                        singleItem = dc.getDocument().toObject(SingleItem.class);
                        rest.add(singleItem);
                        adapter.notifyDataSetChanged();


                    }


                }

            }
        });

    }

    private void clinicSection (){

        mClinic.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {


                    if (dc.getType() == DocumentChange.Type.ADDED) {


                        singleItem = dc.getDocument().toObject(SingleItem.class);
                        clinic.add(singleItem);
                        adapter.notifyDataSetChanged();


                    }


                }

            }
        });

    }


    @Override
    public void onMallClick(int position) {
        final Intent intent;
        switch (position){
            case 0:
                intent =  new Intent(getActivity(), OffersActivity.class);
                break;

            case 1:
                intent =  new Intent(getActivity(), shoppingActivity.class);
                break;

            case 2:
                intent =  new Intent(getActivity(), RestActivity.class);
                break;

            default:
                intent =  new Intent(getActivity(), ClinicActivity.class);
                break;
        }
        startActivity(intent);

    }
}


