package com.sslavik.dynamicfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sslavik.dynamicfragment.R;

public class FragmentB extends Fragment {
    public static final String TAG = "StaticFragment";
    private static final String SIZE = "TextSize";
    private static final String MESSAGE = "Message";
    private TextView tvText;

    /**
     * Metodo que se va a crear un objeto de la propia clase FragmentB garanitzando que se llama a setArguments inmediatamente despues de la Creación
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle){

        FragmentB fragmentB = new FragmentB();
        if (bundle != null){
            fragmentB.setArguments(bundle);
        }
        return fragmentB;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentb, container, false);
        Log.d(TAG, "FragmentB -> OnCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvText = view.findViewById(R.id.tvText);
        // OBTENEMOS LOS ARGUMENTOS
        Bundle bundle = getArguments();
        if(bundle != null)
            tvText.setText(bundle.getString("Message"));
    }

    // EVENTO QUE GUARDA EL ESTADO DE FORMA DINÁMICA PARA EL CAMBIO DE NUESTRA ACTIVITY ( SE GUARDA EN EL OBJETO BUNDLER )
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE, tvText.getText().toString());
    }
    // EVENTO QUE RESTAURA EL ESTADO DE FORMA DÍNAMICA PARA EL CAMBIO DE NUESTRA ACTIVITY ( OBTIENE LOS DATOS GUARDADOS DEL BUNDLER )
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            tvText.setText(savedInstanceState.getString(MESSAGE));
        }
    }

}
