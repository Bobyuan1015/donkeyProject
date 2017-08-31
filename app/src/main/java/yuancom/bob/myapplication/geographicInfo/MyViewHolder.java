package yuancom.bob.myapplication.geographicInfo;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import yuancom.bob.myapplication.R;

/**
 * Created by bob on 31/08/2017.
 */


public  class MyViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView mView_address,mView_Postcode, mView_Latlng;
    public LinearLayout rowLayout;
    public ImageView choiceImage;
    public Destination destination;
    private OnItemListener mListener;
    public int selectedFlag = 0;
    public int position = -1;
    public MyViewHolder(View v, OnItemListener lisener) {
        super(v);
        mListener = lisener;
        mView_address = (TextView) v.findViewById(R.id.textView_address);
        mView_Postcode = (TextView) v.findViewById(R.id.textView_postcode);
        mView_Latlng = (TextView) v.findViewById(R.id.textView_latlng);
        choiceImage = (ImageView) v.findViewById(R.id.checkboximage);
        rowLayout = (LinearLayout) v.findViewById(R.id.rowLayout);
        rowLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                position = getPosition();
                Log.d("holder","Position="+position +"  selectedFlag="+selectedFlag);
                if( selectedFlag!= 1)
                {
                    selectedFlag = 1;
                    //rowLayout.setBackgroundColor(0x330000ff);
                    choiceImage.setImageResource(R.drawable.checkedbox);
                    mListener.onItemSelected( destination,position );
                    Log.d("holder","Position="+position +"  image checked=");
                }
                else{
                    selectedFlag = 0;
                    choiceImage.setImageResource(R.drawable.blankbox);
                    Log.d("holder","Position="+position +"  image non checked=");
                }

            }
        });
    }
}