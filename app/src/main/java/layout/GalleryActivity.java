package layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import mallcom.android.yourmall.R;

import static layout.ItemMarkerActivity.EXTRA_DESCRIPTION;
import static layout.ItemMarkerActivity.EXTRA_PRICE;
import static layout.ItemMarkerActivity.EXTRA_URL;
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
 * Created by pc on 08/08/2019.
 */

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if (getIntent().hasExtra(EXTRA_URL) &&
                getIntent().hasExtra(EXTRA_DESCRIPTION)
                && getIntent().hasExtra(EXTRA_PRICE)) {

            Intent intent = getIntent();
            String imageUrl = intent.getStringExtra(EXTRA_URL);
            String description = intent.getStringExtra(EXTRA_DESCRIPTION);
            String price = intent.getStringExtra(EXTRA_PRICE);


            ImageView imageView = findViewById(R.id.image_gallery);
            TextView textViewPrice = findViewById(R.id.price_gallery);
            TextView textView = findViewById(R.id.description_gallery);

            Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
            textViewPrice.setText("Price: "+price);
            textView.setText("Description: "+description);

        }
        }

    }

