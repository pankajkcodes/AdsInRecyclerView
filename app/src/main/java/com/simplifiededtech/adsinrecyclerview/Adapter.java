package com.simplifiededtech.adsinrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> recyclerItems = new ArrayList<>();
    private Context context;

    private static final int item_data = 0;
    private static final int item_banner = 1;

    public Adapter(List<Object> recycleritems, Context context) {
        this.recyclerItems = recycleritems;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case item_data:
                View dataView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                return new itemViewHolder(dataView);

            case item_banner:
            default:
                View bannerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_layout, parent, false);
                return new bannerAdViewHolder(bannerView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viwtype = getItemViewType(position);

        switch (viwtype) {
            case item_data:
                itemViewHolder mvh = (itemViewHolder) holder;
                Model model = (Model) recyclerItems.get(position);
                mvh.t1.setText(model.getHeader());
                mvh.t2.setText(model.getDesc());
                break;

            case item_banner:
            default:
                bannerAdViewHolder bvh = (bannerAdViewHolder) holder;
                AdView adView = (AdView) recyclerItems.get(position);

                ViewGroup adCardView = (ViewGroup) bvh.itemView;

                if (adCardView.getChildCount() > 0)
                    adCardView.removeAllViews();
                if (adCardView.getParent() != null)
                    ((ViewGroup) adView.getParent()).removeView(adView);

                adCardView.addView(adView);
        }
    }

    @Override
    public int getItemCount() {
        return recyclerItems.size();
    }


    public int getItemViewType(int position) {
        if (position % MainActivity.ITEM_PER_AD == 0)
            return item_banner;
        else
            return item_data;
    }

    public static class itemViewHolder extends RecyclerView.ViewHolder {
        TextView t1, t2;

        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.t1);
            t2 = (TextView) itemView.findViewById(R.id.t2);
        }
    }

    public static class bannerAdViewHolder extends RecyclerView.ViewHolder {
        public bannerAdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
