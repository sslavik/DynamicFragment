package com.sslavik.dynamicfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sslavik.dynamicfragment.R;

public class FragmentA extends Fragment {
    public static final String TAG = "StaticFragment";
    private EditText edText;
    private SeekBar sbText;
    private Button btText;
    private OnFragmentTextChange onFragmentTextChange;

    // INTERFAZ
    public interface OnFragmentTextChange{
        void onSetTextSize(int size);
        void onClickTextChange(String text);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onFragmentTextChange = (OnFragmentTextChange)context;
        }
        catch (ClassCastException e){
            Log.d("ERR", e.getMessage() );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentTextChange = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenta, container, false);
        Log.d(TAG, "FragmentA -> OnCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // INSTANCIAMOS
        edText = view.findViewById(R.id.editText);
        sbText = view.findViewById(R.id.sbTamanio);
        btText = view.findViewById(R.id.btTamanio);


        // EVENTOS
        sbText.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onFragmentTextChange.onSetTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentTextChange.onClickTextChange(edText.getText().toString());
            }
        });

    }
}
