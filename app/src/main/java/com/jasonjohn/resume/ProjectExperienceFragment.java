package com.jasonjohn.resume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProjectExperienceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProjectExperienceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectExperienceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listView;
    private ArrayList<ProjExpObject> listData;
    private ProjectExpAdapter listAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectExperienceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectExperienceFragment newInstance(String param1, String param2) {
        ProjectExperienceFragment fragment = new ProjectExperienceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectExperienceFragment() {
        // Required empty public constructor
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
        View view =  inflater.inflate(R.layout.fragment_work_experience, container, false);
        listView = (ListView) view.findViewById(R.id.listview);

        listAdapter = new ProjectExpAdapter(getActivity().getApplicationContext());

        ProjExpObject beam = new ProjExpObject("Beam", "August 2015 - September 2015", R.drawable.space, R.string.beam);
        ProjExpObject unchained = new ProjExpObject("Unchained", "September 2015 - Present", R.drawable.space, R.string.unchained);
        ProjExpObject answerme = new ProjExpObject("AnswerMe", "Dec 2014", R.drawable.space, R.string.answerme);
        ProjExpObject wardrobe = new ProjExpObject("Wardrobe", "Jan 2015 - Present", R.drawable.space, R.string.wardrobe);

        listData = new ArrayList<>();
        listData.addAll(Arrays.asList(beam, unchained, answerme, wardrobe));
        listAdapter.addAll(listData);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProjExpObject peo = listAdapter.getItem(i);
                Intent intent = new Intent(getActivity().getApplicationContext(), DetailActivity.class);
                intent.putExtra("obj", peo);
                startActivity(intent);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class ProjectExpAdapter extends ArrayAdapter<ProjExpObject> {
        private Context mContext;

        public ProjectExpAdapter(Context context) {
            super(context, R.layout.workexp_element);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ProjExpObject projExpObject = getItem(position);
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View listItemView = vi.inflate(R.layout.projexp_element, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) listItemView.findViewById(R.id.title);
            viewHolder.company = (TextView) listItemView.findViewById(R.id.date);
            viewHolder.bg = (ImageView) listItemView.findViewById(R.id.bgImg);

            viewHolder.title.setText(projExpObject.getTitle());
            viewHolder.company.setText(projExpObject.getDate());
            Picasso.with(mContext).load(projExpObject.getBgResId()).into(viewHolder.bg);

            return listItemView;
        }

        private class ViewHolder {
            private TextView title, company;
            private ImageView bg;
        }
    }

}
