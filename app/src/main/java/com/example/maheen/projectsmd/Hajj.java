package com.example.maheen.projectsmd;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Hajj extends Fragment {

    ArrayList<EssentialItem> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    RecyclerView RecyclerViewleftmenue;


    String [] EssentialItemDescription ;
    int  [] Images;
    //  String EssentialItemDescription[] = {"Bath and put on Ihram","Read the dua to make intention","Here on contineously read talbiyah","Go to minnah","Pray qasr prayers"};
   // int  Images[] = {R.drawable.ihram_man_women,R.drawable.intention,R.drawable.talbiyah,R.drawable.minnah,R.drawable.namaz};

    int  Imagesleftmenue[] = {R.drawable.zulhijjah,R.drawable.zillhajj8,R.drawable.zillhajj9,R.drawable.zillhajj10,R.drawable.zillhajj11,R.drawable.zillhajj12,R.drawable.zillhajj13, R.drawable.dos};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySQLiteHelper db2 = new MySQLiteHelper(getContext());
        db2.addUmrahitem(new Umrahclass("8Zilhajj", "Bath and put on Ihram",R.drawable.ihram_man_women),"hajj");
        db2.addUmrahitem(new Umrahclass("8Zilhajj", "Read the dua to make intention",R.drawable.intention),"hajj");

        db2.addUmrahitem(new Umrahclass("8Zilhajj", "Here on contineously read talbiyah",R.drawable.talbiyah),"hajj");
        db2.addUmrahitem(new Umrahclass("8Zilhajj", "Go to minnah",R.drawable.minnah),"hajj");

        db2.addUmrahitem(new Umrahclass("8Zilhajj", "Pray qasr prayers",R.drawable.namaz),"hajj");

        List<Umrahclass> list = db2.getAllUmrah("hajj");
        Log.d("length of array is","iaiia"+list.size());


        Log.d("valuee is ","aallal"+list.get(1).getDescription());


        EssentialItemDescription=new String[5];
        Images=new int[5];

        for (int i = 0; i <EssentialItemDescription .length; i++)
        {
            EssentialItemDescription[i]=(list.get(i).getDescription());
        }
        for (int i = 0; i <Images .length; i++)
        {
            Images[i]=(list.get(i).getImagename());
        }

        initializeList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hajj, container, false);

        // Right Panel
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        SimpleAdapter mAdapter = null;
        if (listitems.size() > 0 & MyRecyclerView != null) {
            //MyRecyclerView.setAdapter(new MyAdapter(listitems));
            mAdapter = new SimpleAdapter( getContext(), listitems);
            MyRecyclerView.setAdapter(mAdapter);
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        MyRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));


        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<SimpleSectionedRecyclerViewAdapter.Section>();//!!!!!!!!!!!

        //Sections
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(0,"ZillHajj 8"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(1,"ZillHajj 9"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(2,"ZillHajj 10"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(3,"ZillHajj 11"));
        sections.add(new SimpleSectionedRecyclerViewAdapter.Section(4,"ZillHajj 12"));

        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerViewAdapter.Section[] dummy = new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];
        SimpleSectionedRecyclerViewAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerViewAdapter(getContext(),R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //Apply this adapter to the RecyclerView
        MyRecyclerView.setAdapter(mSectionedAdapter);



        //-------------------------------------------------------------------------------------------------------
        // left menue

        RecyclerViewleftmenue = (RecyclerView) view.findViewById(R.id.leftmenue_rv);
        RecyclerViewleftmenue.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager2 = new LinearLayoutManager(getActivity());
        MyLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & RecyclerViewleftmenue != null) {
            RecyclerViewleftmenue.setAdapter(new MyAdapter2(Imagesleftmenue));
        }
        RecyclerViewleftmenue.setLayoutManager(MyLayoutManager2);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public interface OnFragmentInteractionListener {
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<EssentialItem> list;

        public MyAdapter(ArrayList<EssentialItem> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
            holder.likeImageView.setTag(R.drawable.ic_like);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }





    public class MyAdapter2 extends RecyclerView.Adapter<MenueViewHolder> {
        private int list[];

        public MyAdapter2(int Data[]) {
            list = Data;
        }

        @Override
        public MenueViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.leftmenu_recycle_items, parent, false);
            MenueViewHolder holder = new MenueViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MenueViewHolder holder, int position) {

            holder.buttonImage.setImageResource(list[position]);


        }

        @Override
        public int getItemCount() {
            return list.length;
        }
    }




    public class MenueViewHolder extends RecyclerView.ViewHolder {

        public ImageView buttonImage;


        public MenueViewHolder(View v) {
            super(v);
            buttonImage = (ImageButton) v.findViewById(R.id.iconButton);
            buttonImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(),R.id.iconButton +" clicked",Toast.LENGTH_SHORT).show();



                }
            });



        }
    }











    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);
            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    int id = (int)likeImageView.getTag();
                    if( id == R.drawable.ic_like){

                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(getActivity(),titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                    }

                }
            });



            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {






                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' + getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.send_to)));



                }
            });



        }
    }

    public void initializeList() {
        listitems.clear();

        for (int i = 0; i < Images.length; i++) {

            EssentialItem item = new EssentialItem();
            item.setCardName(EssentialItemDescription[i]);
            item.setImageResourceId(Images[i]);
            item.setIsfav(0);
            item.setIsturned(0);
            listitems.add(item);

        }
    }
}
