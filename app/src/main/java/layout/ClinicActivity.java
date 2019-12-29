package layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import mallcom.android.yourmall.Item;
import mallcom.android.yourmall.ItemAdapter;
import mallcom.android.yourmall.MarketAdapterRecyclerView;
import mallcom.android.yourmall.R;
import mallcom.android.yourmall.Word;
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
/**
 * Created by pc on 20/07/2019.
 */

public class ClinicActivity extends AppCompatActivity{

    private ListenerRegistration listener;


    private RecyclerView mMainList;

    private static final String TAG = "shoppingActivity";

    private List<Word> wordList;
    private ArrayList<Item> itemList;
    private Item item;
    private Intent intent;
    private ItemAdapter itemAdapter;
    private Context mContext;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mShoppingRed = db.collection("Clinics");
    private MarketAdapterRecyclerView adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        setUpRecyclerView();


    }


    private void setUpRecyclerView() {


        Query query = mShoppingRed.orderBy("priority", Query.Direction.DESCENDING);


        FirestoreRecyclerOptions<Word> options = new FirestoreRecyclerOptions.Builder<Word>().setQuery(query, Word.class).build();

        adapter = new MarketAdapterRecyclerView(options, this);
        wordList = new ArrayList<>();
        itemList = new ArrayList<>();


        RecyclerView recyclerView = findViewById(R.id.list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MarketAdapterRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {


                CollectionReference path = documentSnapshot.getReference().collection("ShoppingItems");

                listener = path.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                        if (e != null) {
                            return;
                        }

                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {


                            if (dc.getType() == DocumentChange.Type.ADDED) {


                                item = dc.getDocument().toObject(Item.class);
                                itemList.add(item);


                            }


                        }

                        intent = new Intent(ClinicActivity.this, ItemMarkerActivity.class);
                        intent.putParcelableArrayListExtra("itemList", itemList);
                        startActivity(intent);
                    }
                });

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        if (itemList != null){
            itemList.clear();
        }

    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        if(listener != null){
            listener.remove();
        }

    }

}

