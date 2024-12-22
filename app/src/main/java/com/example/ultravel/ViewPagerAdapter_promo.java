package com.example.ultravel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewPagerAdapter_promo extends RecyclerView.Adapter<ViewPagerAdapter_promo.ViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;

    ArrayList<ViewPager_promoModel> viewPagerItemPromo_ArrayList;

    public ViewPagerAdapter_promo(ArrayList<ViewPager_promoModel> viewPagerItemPromo_ArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.viewPagerItemPromo_ArrayList = viewPagerItemPromo_ArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewPagerAdapter_promo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_fragment_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter_promo.ViewHolder holder, int position) {
        ViewPager_promoModel viewPagerItem_promo = viewPagerItemPromo_ArrayList.get(position);

        holder.ivPromo.setImageResource(viewPagerItem_promo.imageID);
    }

    @Override
    public int getItemCount() {
        return viewPagerItemPromo_ArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPromo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPromo = itemView.findViewById(R.id.imageView1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
