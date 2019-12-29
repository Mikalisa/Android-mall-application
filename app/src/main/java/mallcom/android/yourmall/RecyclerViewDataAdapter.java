package mallcom.android.yourmall;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<DataSectionModel> dataList;
    private onMallListener mOnMallListener;
    private Context mContext;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private final int limit = 10;

    public RecyclerViewDataAdapter(ArrayList<DataSectionModel> dataList, Context mContext, onMallListener onMallListener) {
        this.dataList = dataList;
        this.mContext = mContext;
        recycledViewPool = new RecyclerView.RecycledViewPool();
        this.mOnMallListener = onMallListener;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        ItemRowHolder rowHolder = new ItemRowHolder(v, mOnMallListener);
        return rowHolder;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final String sectionName = dataList.get(position).getHeaderTitle();
        ArrayList singleSectionItems = dataList.get(position).getAllItemInSection();
        holder.itemTitle.setText(sectionName);
        DataSectionAdapter adapter = new DataSectionAdapter(singleSectionItems, mContext);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);

    }


    @Override
    public int getItemCount() {
        if(dataList.size() > limit){
            return limit;
        }
        else
        {
            return dataList.size();
        }

    }

    public  class ItemRowHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView itemTitle;
        protected RecyclerView recyclerView;
        public Button btnMore;
        onMallListener onMallListener;

        public ItemRowHolder(View itemView, onMallListener onMallListener) {
            super(itemView);
            this.itemTitle = itemView.findViewById(R.id.itemTitle);
            this.recyclerView = itemView.findViewById(R.id.recycler_view_list);
            this.btnMore = itemView.findViewById(R.id.btnMore);
            this.onMallListener = onMallListener;

            btnMore.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            onMallListener.onMallClick(getAdapterPosition());

        }
    }
    public interface onMallListener{
        void onMallClick(int position);

    }
}
