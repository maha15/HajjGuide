package com.example.maheen.projectsmd;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class Umrah extends Fragment {
    ArrayList<EssentialItem> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    RecyclerView RecyclerViewleftmenue;


    String [] EssentialItemDescription ;
    int  [] Images;

    int  Imagesleftmenue[] = {R.drawable.finalumrah,R.drawable.kaba,R.drawable.ibrahimi,R.drawable.tap_water,R.drawable.saee,R.drawable.finalscissors};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MySQLiteHelper db = new MySQLiteHelper(getContext());

        /**
         * CRUD Operations
         * */
        // add Books
        db.addUmrahitem(new Umrahclass("tawaf", "Enter Al-Haram gate on your right foot",R.drawable.masjidalharam),"umrah");
        db.addUmrahitem(new Umrahclass("tawaf", "Uncover right shoulder",R.drawable.uncoverrightshoulder),"umrah");
        db.addUmrahitem(new Umrahclass("tawaf", "Read dua \n بِسم الله،والصّلاة والسّلام على َرسول الله،الّلهُم افتَح لي أبوابَ رَحْمَتِك ",R.drawable.intention),"umrah");

        db.addUmrahitem(new Umrahclass("tawaf", "Start each round while touching or raising hands towards Hajr-e-aswad",R.drawable.hajreaswad1),"umrah");
        db.addUmrahitem(new Umrahclass("Nafal", "Behind maqam ibrahim pray two raka",R.drawable.makameibrahimif),"umrah");
        db.addUmrahitem(new Umrahclass("Nafal", "Recite surah Kaafiroon in first raka \n Recite surah Ikhlas in second raka ",R.drawable.nawafilatibrahimi),"umrah");

        db.addUmrahitem(new Umrahclass("Zamzam", "Drink zamzam after performing Raka",R.drawable.zamzamwater),"umrah");
        db.addUmrahitem(new Umrahclass("Zamzam", "Drink in 1 gulp",R.drawable.persondrinkingzamazam),"umrah");
        db.addUmrahitem(new Umrahclass("Zamzam", "recite \n   آللّهُمَ اِنِّىْ اَسْعَلُكَ عِلْماً نَّافَعِاً وَّرِزْقًا وَّاسِعاً وَشِفَائً مِّنْ كُلِ دَائً",R.drawable.intention),"umrah");

        db.addUmrahitem(new Umrahclass("Saaee", "While going for Saaee Raise hands towards Hajr-e-aswad and Recite \n بِسمِ اللّهِ اللّهُ اَكْبَر",R.drawable.hijreaswad),"umrah");
        db.addUmrahitem(new Umrahclass("Saaee", "Recite when reached saffa \n إِنَّ الصَّفَا وَالْمَرْوَةَ مِنْ شَعَا ئِرِاللّهِ\n أَبْدَأُ بِمَا بَدَأَاللّهُ بِه",R.drawable.safatomarwa),"umrah");
        db.addUmrahitem(new Umrahclass("Haircut", "Shave or trim hair ",R.drawable.scissorsf),"umrah");

        db.addUmrahitem(new Umrahclass("umrah-completion", "Your umrah has completed!",R.drawable.umrahcom),"umrah");

        // get all books
        List<Umrahclass> list = db.getAllUmrah("umrah");
        Log.d("length of array is","iaiia"+list.size());


        Log.d("valuee is ","aallal"+list.get(1).getDescription());

        EssentialItemDescription=new String[13];
        Images=new int[13];

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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_umrah, container, false);

        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);


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
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

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
                    if( id == R.drawable.detailinfo){

                        likeImageView.setTag(R.drawable.detailedinfo);
                        likeImageView.setImageResource(R.drawable.detailedinfo);

                        Toast.makeText(getActivity(),titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.detailinfo);
                        likeImageView.setImageResource(R.drawable.detailinfo);
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
