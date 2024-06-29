package com.example.lab07;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imgCuadro;
    private TextView txtAutor;
    private TextView txtTitulo;
    private TextView txtTecnica;
    private TextView txtCategoria;
    private TextView txtDescripcion;
    private TextView txtAnio;

    public DetalleFragment() {
        // Required empty public constructor
    }

    public static DetalleFragment newInstance(String param1, String param2) {
        DetalleFragment fragment = new DetalleFragment();
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
        View view = inflater.inflate(R.layout.fragment_detalle, container, false);

        imgCuadro = view.findViewById(R.id.imgCuadro);
        txtAutor = view.findViewById(R.id.txtAutor);
        txtTitulo = view.findViewById(R.id.txtTitulo);
        txtTecnica = view.findViewById(R.id.txtTecnica);
        txtCategoria = view.findViewById(R.id.txtCategoria);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtAnio = view.findViewById(R.id.txtAnio);

        cargarDatos();

        return view;
    }
    void cargarDatos(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SmartGallery", Context.MODE_PRIVATE);
        Bitmap imagen = null;
        Cuadro cuadro = null;
        File file = new File(getContext().getFilesDir(), "cuadro.data");
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            cuadro = (Cuadro) in.readObject();
            in.close();
            Log.d("TAG", "Objeto cargado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            imagen = MediaStore.Images.Media.getBitmap(
                    getContext().getContentResolver(), cuadro.getImagen());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        imgCuadro.setImageBitmap(imagen);
        txtAutor.setText(cuadro.getAutor());
        txtTitulo.setText(cuadro.getTitulo());
        txtTecnica.setText(cuadro.getTecnica());
        txtCategoria.setText(cuadro.getCategoria());
        txtDescripcion.setText(cuadro.getDescripcion());
        txtAnio.setText(cuadro.getAnio() + "");
    }
}