package com.example.lab07;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BottomNavigationView menu = findViewById(R.id.menuInferior);
        menu.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            if(item.getItemId() == R.id.menu_registro){
                fragment = new RegistroFragment();
            } else if (item.getItemId() == R.id.menu_detalle){
                fragment = new DetalleFragment();
            } else {
                return false;
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, fragment)
                    .commit();
            return true;
        });
    }
}