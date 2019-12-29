package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import mallcom.android.yourmall.Item;
import mallcom.android.yourmall.ItemAdapter;
import mallcom.android.yourmall.R;

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

public class ItemMarkerActivity extends AppCompatActivity implements ItemAdapter.onMallListener{

    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_PRICE = "price";


    private static final String TAG = "ItemMarkerActivity";
    private ArrayList<Item> itemList;
    private ItemAdapter itemAdapter;
    private RecyclerView mMainList;
    private Item item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_card_list);

        itemList = new ArrayList<>();



        getIncommingIntent();


    }

    private void getIncommingIntent() {



        if (getIntent().hasExtra("itemList")){


            itemList = getIntent().getParcelableArrayListExtra("itemList");

        }
        mMainList = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        mMainList.setLayoutManager(gridLayoutManager);
        itemAdapter = new ItemAdapter(itemList, this, this);
        mMainList.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();

    }




    @Override
    public void onMallClick(int position) {

        Intent intent = new Intent(this, GalleryActivity.class);
        item = itemList.get(position);

        intent.putExtra(EXTRA_URL, item.getImageUrl());
        intent.putExtra(EXTRA_DESCRIPTION, item.getDescription());
        intent.putExtra(EXTRA_PRICE, item.getPrice());

        startActivity(intent);


    }
}
