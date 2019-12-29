package mallcom.android.yourmall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


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
 * Created by pc on 05/08/2019.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    public ArrayList<Item> ItemList;
    public Context mContext;
    private onMallListener mOnMallListener;





    private static final int NO_POSITION = -1;

    public ItemAdapter(ArrayList<Item> ItemList, Context mContext, onMallListener mOnMallListener) {

        this.ItemList = ItemList;
        this.mContext = mContext;
        this.mOnMallListener = mOnMallListener;



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_card_view_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnMallListener);

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Item item = ItemList.get(i);
        viewHolder.itemType.setText(ItemList.get(i).getItemType());
        Picasso.with(mContext)
                .load(item.getImageUrl())
                .fit()
                .centerCrop().into(viewHolder.imageUrl);



    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        View mView;

        public TextView price;
        public TextView description;
        public TextView itemType;
        public ImageView imageUrl;
        onMallListener onMallListener;




        public ViewHolder(@NonNull View itemView, onMallListener mOnMallListener) {
            super(itemView);
            mView = itemView;

            itemType = (TextView) mView.findViewById(R.id.card_view_image_title);
            imageUrl = (ImageView) mView.findViewById(R.id.card_view_image);
            this.onMallListener = mOnMallListener;

            mView.setOnClickListener(this);





        }

        @Override
        public void onClick(View v) {
            if(mOnMallListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mOnMallListener.onMallClick(position);
                }

            }
        }


    }

    public interface onMallListener{
        void onMallClick(int position);

    }

}
