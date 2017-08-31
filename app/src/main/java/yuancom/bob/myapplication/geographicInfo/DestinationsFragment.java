package yuancom.bob.myapplication.geographicInfo;

import android.content.Context;

import android.net.Uri;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import yuancom.bob.myapplication.R;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DestinationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DestinationsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationsFragment extends Fragment implements OnItemListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final String Tag = "DestFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private ListView destinationsListView ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayManager;
    private Button  delete;
    private ArrayList<Destination> destinationsDelete;



    public DestinationsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DestinationsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DestinationsFragment newInstance(String param1, String param2) {
        DestinationsFragment fragment = new DestinationsFragment();
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
        View view = inflater.inflate(R.layout.fragment_destinations, container, false);
        destinationsDelete = new ArrayList<Destination>();

        delete = (Button) view.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( destinationsDelete.size()> 0 ){

                    for( Destination destination: destinationsDelete)
                    {
                        TestDestinations.getInstance().removeDestination(destination);
                    }
                    recyclerViewAdapter.notifyDataSetChanged();
                    Log.d(Tag,"Delete sucessfully");
                }
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerViewLayManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerViewLayManager);


        recyclerViewAdapter = new MyAdapter( TestDestinations.getInstance().getDestinationsInfo(), this );

        recyclerView.setAdapter(recyclerViewAdapter);



//         destinationsListView = (ListView) view.findViewById(R.id.destinationsList);
//
//         destinationsListAdapter = new ArrayAdapter<Destination>(getActivity(),
//                android.R.layout.simple_list_item_1,TestDestinations.getInstance().getDestinationsInfo());
//
//        destinationsListView.setAdapter(destinationsListAdapter);
//
//        destinationsListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(Tag,"onItemLongClick( position="+position+" id="+id+" )");
//;
//
//                TestDestinations.getInstance().removeDestination(position);
//                destinationsListAdapter.notifyDataSetChanged();
//
//                return false;
//            }
//        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(Destination item, int position) {
        destinationsDelete.add(item);
        Log.d(Tag,"onItemSelected ("+item+",index="+position+")");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
