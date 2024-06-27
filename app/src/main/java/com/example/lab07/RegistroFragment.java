package com.example.lab07;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SmartGallery", Context.MODE_PRIVATE);
        EditText edtImagen = view.findViewById(R.id.edtImagen);
        EditText edtAutor = view.findViewById(R.id.edtAutor);
        EditText edtTitulo = view.findViewById(R.id.edtTitulo);
        EditText edtTecnica = view.findViewById(R.id.edtTecnica);
        EditText edtCategoria = view.findViewById(R.id.edtCategoria);
        EditText edtDescripcion = view.findViewById(R.id.edtDescripcion);
        EditText edtAnio = view.findViewById(R.id.edtAnio);
        Button btnRegistro = view.findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Imagen", edtImagen.getText().toString());
                editor.putString("Autor", edtAutor.getText().toString());
                editor.putString("Titulo", edtTitulo.getText().toString());
                editor.putString("Técnica", edtTecnica.getText().toString());
                editor.putString("Categoria", edtCategoria.getText().toString());
                editor.putString("Descripción", edtDescripcion.getText().toString());
                editor.putInt("Año", Integer.parseInt(edtAnio.getText().toString()));
                editor.apply();
            }
        });
        return view;
    }
}