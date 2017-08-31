package yuancom.bob.myapplication.geographicInfo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import yuancom.bob.myapplication.R;

/**
 * Created by bob on 31/08/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> implements OnItemListener{

    private ArrayList<Destination> mDataset;
    private OnItemListener mListener;
    public MyAdapter(ArrayList<Destination> mydata, OnItemListener listener){
        this.mDataset = mydata;
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.destination_row,parent,false);
        return new MyViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Destination destination = mDataset.get(position);
        holder.mView_address.setText( destination.getName());
        holder.mView_Postcode.setText( destination.getPostCode());
        holder.mView_Latlng.setText( "( "+destination.geLatitude()+ " , "+destination.getLongitude()+" )");

        holder.destination = destination;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public void onItemSelected(Destination item,int position) {
        mListener.onItemSelected(item, position);
    }
}
