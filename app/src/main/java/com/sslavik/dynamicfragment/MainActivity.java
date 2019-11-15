package com.sslavik.dynamicfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.OnFragmentTextChange {

    // CAMPOS
    Fragment fragmenta;
    Fragment fragmentb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INSTANCIAMOS EL FRAGMENT MANAGER PARA PODER INSTANCIAR A UN FRAGMENT
        FragmentManager fragmentManager = getSupportFragmentManager();

        // UNA VEZ TENEMOS EL FRAGMENTMANAGER PODREMOS CONFIGURARLO PARA DECIRLE QUE EMPIECE UNA TRANSACCIÓN
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // INSTANCIAMOS FRAGMENT
        fragmenta = new FragmentA();
        // AÑADIMOS TRANSACCION
        fragmentTransaction.add(android.R.id.content, fragmenta, FragmentA.TAG);
        // TREMINAMOS LA TRANSACCION EN EL FRAGMENT
        fragmentTransaction.commit();

    }

    @Override
    public void onSetTextSize(int size) {
    }

    @Override
    public void onClickTextChange(String text) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        /**
        fragmentb = new FragmentB();
        // OBLIGATORIAMENTE DESPUÉS DE INICIALIZAR EL FRAGMENT TENEMOS QUE PASARLE LOS PARAMETROS NECESARIOS PARA SU INSTANCIA
        Bundle bundle = new Bundle();
        bundle.putString("Message", text);
        // PASO ARGUMENTOS
        fragmentb.setArguments(bundle);
         */
        // SUSTITUIMOS ESO POR
        Bundle bundle = new Bundle();
        bundle.putString("Message", text);
        FragmentB.newInstance(bundle);
        // AÑADIMOS TRANSACCION
        fragmentTransaction.add(android.R.id.content, fragmentb, FragmentB.TAG);
        // GUARDA EL ESTADO DE LA TRANSACCION EN LA PILA DEL FRAGMENT QUE GUARDA LA ACTIVITY
        fragmentTransaction.addToBackStack(null); // DE ESTA MANERA PODEMOS VOLVER ATRÁS DE UN CAMBIO EN EL FRAGMENT CON EL BOTON "BACK" DEL MOVIL
        // TERMINAMOS LA TRANSACCION EN EL FRAGMETN
        fragmentTransaction.commit();
    }
}
