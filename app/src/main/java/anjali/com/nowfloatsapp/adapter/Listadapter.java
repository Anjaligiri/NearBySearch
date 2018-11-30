package anjali.com.nowfloatsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import anjali.com.nowfloatsapp.R;
import anjali.com.nowfloatsapp.activity.DetailsActivity;
import anjali.com.nowfloatsapp.activity.FavActivity;
import anjali.com.nowfloatsapp.database.DatabaseHandler;
import anjali.com.nowfloatsapp.model.Resturent;
import anjali.com.nowfloatsapp.activity.MainActivity;


public class Listadapter extends RecyclerView.Adapter<Listadapter.MyViewHolder> {

        ArrayList<Resturent> resturents;
        ArrayList<Resturent> arraylist;
        Context context;

        public Listadapter(MainActivity allCategoryProfile, ArrayList<Resturent> resturents) {
            context=allCategoryProfile;
            this.resturents=resturents;
            this.arraylist = new ArrayList<>();
            this.arraylist.addAll(resturents);
        }

    public Listadapter(FavActivity favActivity, ArrayList<Resturent> resturents) {
        context=favActivity;
        this.resturents=resturents;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(resturents);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView name;
            Button fav;

            public MyViewHolder(View view) {
                super(view);

                name = (TextView) view.findViewById(R.id.name);
                fav = (Button) view.findViewById(R.id.fav);

            }
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent,
                    false);
            itemView.setTag(viewType);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            final Resturent resturent = resturents.get(position);
            holder.name.setText(resturent.getName());
holder.fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseHandler databaseHandler=new DatabaseHandler(context);
        databaseHandler.addFav(resturent);
    }
});
holder.name.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(context,DetailsActivity.class);
        intent.putExtra("Details","");
        intent.putExtra("lan","");
        ((Activity)context).startActivity(intent);
    }
});



        }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            resturents.clear();
            System.out.println("kmcckdv" + arraylist);
            if (charText.length() == 0) {
                resturents.addAll(arraylist);
                System.out.println("searchItem"+resturents);
            } else {
                for (Resturent wp : arraylist) {
                    if (wp.getName()
                            .contains(charText)) {
                        resturents.add(wp);
                        System.out.println("SearchData"+resturents);
                    }
                }
            }
            System.out.println("chhaya"+ resturents);
            notifyDataSetChanged();
        }
        @Override
        public int getItemCount() {
            return resturents.size();
        }
    }



