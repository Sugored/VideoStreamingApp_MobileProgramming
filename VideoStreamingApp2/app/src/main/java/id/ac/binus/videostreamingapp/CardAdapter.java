package id.ac.binus.videostreamingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<Video> allVids;
    private Context context;

    // Constructor for CardAdapter
    public CardAdapter(Context ctx, List<Video> videos){
       this.context = ctx;
       this.allVids = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title.setText(allVids.get(position).getTitle());
        Picasso.get().load(allVids.get(position).getImgURL()).into(holder.vidImg);
        holder.uploader.setText(allVids.get(position).getAuthor());
        holder.views.setText(allVids.get(position).getViews() + " views");
        holder.upload_time.setText(allVids.get(position).getDate());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putSerializable("videoData", allVids.get(position));
                Intent i = new Intent(context, VideoPlayer.class);
                i.putExtras(b);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allVids.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vidImg;
        TextView title, uploader, views, upload_time;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vidImg = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            uploader = itemView.findViewById(R.id.uploader);
            views = itemView.findViewById(R.id.views);
            upload_time = itemView.findViewById(R.id.upload_time);

            view = itemView;
        }
    }
}
