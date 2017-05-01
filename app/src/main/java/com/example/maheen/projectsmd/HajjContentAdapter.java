package com.example.maheen.projectsmd;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HajjContentAdapter extends RecyclerView.Adapter<HajjContentAdapter.SimpleViewHolder> {

    private static Context mContext;
    private List<EssentialItem> mData;

    public HajjContentAdapter(Context context, ArrayList<EssentialItem>  data) {
        mContext = context;
        if (data != null)
            mData = new ArrayList<EssentialItem>(data);
        else mData = new ArrayList<EssentialItem>();
    }



    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        final View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_items, parent, false);
        return new SimpleViewHolder(view);
    }


    public void add(EssentialItem s,int position) {
        position = position == -1 ? getItemCount()  : position;
        mData.add(position,s);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
       /* public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.simple_text);
        }

*/


        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public SimpleViewHolder(View v) {
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

                        Toast.makeText(mContext,titleTextView.getText()+" added to favourites",Toast.LENGTH_SHORT).show();

                    }else{

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(mContext,titleTextView.getText()+" removed from favourites",Toast.LENGTH_SHORT).show();


                    }

                }
            });



            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {






                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                            "://" + mContext.getResources().getResourcePackageName(coverImageView.getId())
                            + '/' + "drawable" + '/' +  mContext.getResources().getResourceEntryName((int)coverImageView.getTag()));


                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM,imageUri);
                    shareIntent.setType("image/jpeg");
                    mContext.startActivity(Intent.createChooser(shareIntent,  mContext.getResources().getText(R.string.send_to)));



                }
            });



        }





    }


    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {

        holder.titleTextView.setText(mData.get(position).getCardName());
        holder.coverImageView.setImageResource(mData.get(position).getImageResourceId());
        holder.coverImageView.setTag(mData.get(position).getImageResourceId());
        holder.likeImageView.setTag(R.drawable.ic_like);


        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Position ="+position,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}