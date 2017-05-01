package com.example.maheen.projectsmd;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Hajj extends Fragment {

    // The complete items to be displayed
    ArrayList<EssentialItem> listitems = new ArrayList<>();

    // The two widgets on screen
    RecyclerView MyRecyclerView;
    RecyclerView RecyclerViewleftmenue;

<<<<<<< HEAD
    // Adapter for the content Recycler View
    HajjContentAdapter mAdapter = null;


    // TODO: Populate these from DB
    String EssentialItemDescription[] = {"Bath and put on Ihram","Read the dua to make intention","Here on contineously read talbiyah","Go to minnah","Pray qasr prayers"};
    int  Images[] = {R.drawable.ihram_man_women,R.drawable.intention,R.drawable.talbiyah,R.drawable.minnah,R.drawable.namaz};
=======

    String [] EssentialItemDescription ;
    int  [] Images;
    //  String EssentialItemDescription[] = {"Bath and put on Ihram","Read the dua to make intention","Here on contineously read talbiyah","Go to minnah","Pray qasr prayers"};
   // int  Images[] = {R.drawable.ihram_man_women,R.drawable.intention,R.drawable.talbiyah,R.drawable.minnah,R.drawable.namaz};
>>>>>>> e619cc0c6750135230f855c588331e17674ce4d5

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


        EssentialItemDescription=new String[list.size()];
        Images=new int[list.size()];

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

        // Right Panel.. Content about Hajj Essentials
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (listitems.size() > 0 & MyRecyclerView != null) {
            //MyRecyclerView.setAdapter(new MyAdapter(listitems));
            mAdapter = new HajjContentAdapter( getContext(), listitems);
            MyRecyclerView.setAdapter(mAdapter);
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);
        MyRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));


        //This is the code to provide a sectioned list
        List<HajjContentSectionedRecyclerViewAdapter.Section> sections = new ArrayList<HajjContentSectionedRecyclerViewAdapter.Section>();

        //Sections
        sections.add(new HajjContentSectionedRecyclerViewAdapter.Section(0,"ZillHajj 8")); // PARAM 1 =  number of items in first category
        //sections.add(new HajjContentSectionedRecyclerViewAdapter.Section(1,"ZillHajj 9"));// PARAM 1 =  number of items in second category ...
       // sections.add(new HajjContentSectionedRecyclerViewAdapter.Section(2,"ZillHajj 10"));
       // sections.add(new HajjContentSectionedRecyclerViewAdapter.Section(3,"ZillHajj 11"));
         sections.add(new HajjContentSectionedRecyclerViewAdapter.Section(4,"ZillHajj 12"));

        //Add  adapter to the sectionAdapter
        HajjContentSectionedRecyclerViewAdapter.Section[] sectionsAdapter = new HajjContentSectionedRecyclerViewAdapter.Section[sections.size()];
        HajjContentSectionedRecyclerViewAdapter mSectionedAdapter = new
                HajjContentSectionedRecyclerViewAdapter(getContext(),R.layout.section,R.id.section_text,mAdapter);
        mSectionedAdapter.setSections(sections.toArray(sectionsAdapter));

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


    // Adpater class for left menue in Hajj tab
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

                /*    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());//((LinearLayoutManager) MyRecyclerView.getLayoutManager());
                    layoutManager.scrollToPositionWithOffset(4, 0);
                    MyRecycler
                    View.setLayoutManager(layoutManager);
                    Toast.makeText(getActivity()," clicked",Toast.LENGTH_SHORT).show();*/


                 //   HajjContentAdapter section = new HajjContentAdapter(sectionTag, getString(R.string.group_title, randomNumber));

                  //  sectionAdapter.addSection(sectionTag, section);

                   // int sectionPos = mAdapter.   (sectionTag);

//                    mAdapter.notifyItemInserted(sectionPos);

                  /*  MyRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            //MyRecyclerView.smoothScrollToPosition(listitems.size()-2);
                           View setNow = MyRecyclerView.getChildAt(1);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                            layoutManager.scrollToPositionWithOffset(4, 0);
                            mAdapter.notifyDataSetChanged();
                            // layoutManager.attachView(setNow);
                            // or do mRecyclerView.scrollTo(0, scrolled);
                        }
                    });*/
                    /*new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            MyRecyclerView.getLayoutManager().scrollToPosition(listitems.size()-1);

                        }
                    }, 200);*/
                    //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    //Toast.makeText(getActivity(),MyRecyclerView.getAdapterPosition()+ " clicked",Toast.LENGTH_SHORT).show();

                  /*  Toast.makeText(getActivity(), mAdapter.getItemCount() + " clicked " + mAdapter.getItemId(0),Toast.LENGTH_SHORT).show();
                    for(int i = 0 ; i < 5; i++)
                        Log.d("***",String.valueOf(mAdapter.getItemId(i)));*/

                }
            });



        }
    }


// Fills the EssentialItem list with required data
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
