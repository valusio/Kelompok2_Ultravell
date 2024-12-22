package com.example.ultravel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements RecyclerViewInterface{

    ViewPager2 viewPager2;
    ArrayList<ViewPager_promoModel> viewPagerItem_promoArrayList;
    ViewPagerAdapter_promo viewPagerAdapter_promo;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager2 = view.findViewById(R.id.viewPagerSlider_promo);
        int[] images = {R.drawable.pagaruyung, R.drawable.batusangkar, R.drawable.bukittinggi};
        String[] titles = {"Pagaruyung", "Batusangkar", "Bukittinggi"};

        viewPagerItem_promoArrayList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {
            ViewPager_promoModel viewPagerItem_promo = new ViewPager_promoModel(images[i], titles[i]);
            viewPagerItem_promoArrayList.add(viewPagerItem_promo);
        }

        viewPagerAdapter_promo = new ViewPagerAdapter_promo(viewPagerItem_promoArrayList, this);
        viewPager2.setAdapter(viewPagerAdapter_promo);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(2);
        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);

        intent.putExtra("image", viewPagerItem_promoArrayList.get(position).getImageID());
        intent.putExtra("title", viewPagerItem_promoArrayList.get(position).getTitle());

        startActivity(intent);
    }
}