package mallcom.android.yourmall;

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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

/**
 * Created by pc on 02/08/2019.
 */

public class MarketAdapterRecyclerView extends FirestoreRecyclerAdapter<Word, MarketAdapterRecyclerView.MarketHolder> {

    private OnItemClickListener listener;
    public Context mContext;

    public MarketAdapterRecyclerView(@NonNull FirestoreRecyclerOptions<Word> options, Context context) {
        super(options);
        this.mContext = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MarketHolder marketHolder, int i, @NonNull Word word) {



        marketHolder.marketName.setText(word.getMarketName());
        marketHolder.goodsType.setText(word.getGoodsType());
        marketHolder.location.setText(word.getLocation());
        Picasso.with(mContext)
                .load(word.getImageUrl())
                .fit()
                .centerCrop().into(marketHolder.imageUrl);

    }

    @NonNull
    @Override
    public MarketHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,
                viewGroup, false);

        return new MarketHolder(v);
    }

    class MarketHolder extends RecyclerView.ViewHolder{
        public TextView marketName;
        public TextView goodsType;
        public TextView location;
        public ImageView imageUrl;


        public MarketHolder(@NonNull View itemView) {
            super(itemView);

            marketName = (TextView) itemView.findViewById(R.id.market_name);
            goodsType = (TextView) itemView.findViewById(R.id.item_type);
            location = (TextView) itemView.findViewById(R.id.location);
            imageUrl = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }

        }


    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
