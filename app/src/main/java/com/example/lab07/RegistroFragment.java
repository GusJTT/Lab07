package com.example.lab07;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Uri uriImagen;

    //Inicializando elementos de la interfaz
    private ImageView imgPrevImagen;
    private EditText edtAutor;
    private EditText edtTitulo;
    private EditText edtTecnica;
    private EditText edtCategoria;
    private EditText edtDescripcion;
    private EditText edtAnio;

    public RegistroFragment() {
        // Required empty public constructor
    }
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
        Button btnCargar = view.findViewById(R.id.btnCargarimagen);
        Button btnRegistro = view.findViewById(R.id.btnRegistro);
        imgPrevImagen = view.findViewById(R.id.imgPrevImagen);
        edtAutor = view.findViewById(R.id.edtAutor);
        edtTitulo = view.findViewById(R.id.edtTitulo);
        edtTecnica = view.findViewById(R.id.edtTecnica);
        edtCategoria = view.findViewById(R.id.edtCategoria);
        edtDescripcion = view.findViewById(R.id.edtDescripcion);
        edtAnio = view.findViewById(R.id.edtAnio);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarImagen();
            }
        });

        return view;
    }

    void guardarDatos() {
        Cuadro cuadro = new Cuadro();
        cuadro.setImagen(uriImagen);
        cuadro.setTitulo(edtTitulo.getText().toString());
        cuadro.setAutor(edtAutor.getText().toString());
        cuadro.setTecnica(edtTecnica.getText().toString());
        cuadro.setCategoria(edtCategoria.getText().toString());
        cuadro.setDescripcion(edtDescripcion.getText().toString());
        cuadro.setAnio(Integer.parseInt(edtAnio.getText().toString()));

        File file = new File(getContext().getFilesDir(), "cuadro.data");
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(cuadro);
            out.close();
            Log.d("TAG", "Objeto guardado correctamente en almacenamiento interno");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void seleccionarImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        lanzarActivity.launch(intent);
    }
    ActivityResultLauncher<Intent> lanzarActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.getData() != null) {
                        uriImagen = data.getData();
                        Bitmap imagen = null;
                        try {
                            imagen = MediaStore.Images.Media.getBitmap(
                                    getContext().getContentResolver(), uriImagen);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        imgPrevImagen.setImageBitmap(imagen);
                    }
                }
            });
}