package com.example.myfirstapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.database.CampiComuni;
import com.example.myfirstapp.database.DBManager;
import com.example.myfirstapp.database.TabellaRazza;
import com.example.myfirstapp.domain.Arma;
import com.example.myfirstapp.domain.Armatura;
import com.example.myfirstapp.domain.Equipaggiamento;

import java.util.List;

public class CreateNewItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String nomecamp;
    private String nomeg;

    private DBManager dbManager;
    private EditText editText;
    private Spinner typeSpinner;
    private Spinner subtypeSpinner;
    private final String SELEZIONAT = "Seleziona tipo";
    private final String SELEZIONAST = "Seleziona sottotipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_item_temporaneo);

        this.estraiIntent();
        this.setSpinner();
    }

    public void estraiIntent() {
        Intent intent = getIntent();
        nomecamp = intent.getStringExtra("nomecamp");
        nomeg = intent.getStringExtra("nomeg");
    }

    public void setSpinner() {
        List<String> typelist = Equipaggiamento.getTipobase();
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        typeSpinnerAdapter.add(SELEZIONAT);
        typeSpinnerAdapter.addAll(typelist);
        typeSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        typeSpinner = findViewById(R.id.create_item_type_selection_spinner);
        typeSpinner.setAdapter(typeSpinnerAdapter);
        typeSpinner.setOnItemSelectedListener(this);

        List<String> subtypelist = Equipaggiamento.getSubtipobase();
        ArrayAdapter<String> subtypeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_custom_item);
        subtypeSpinnerAdapter.add(SELEZIONAST);
        subtypeSpinnerAdapter.addAll(subtypelist);
        subtypeSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_custom_item);
        subtypeSpinner = findViewById(R.id.create_item_subtype_selection_spinner);
        subtypeSpinner.setAdapter(subtypeSpinnerAdapter);
        subtypeSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void createNewItem(View view) {
        dbManager = new DBManager(this);

        editText = findViewById(R.id.create_item_name);
        String nome = editText.getText().toString();

        editText = findViewById(R.id.create_item_desc);
        StringBuffer desc = new StringBuffer();
        desc.append(editText.getText().toString());

        editText = findViewById(R.id.create_item_price);
        String temp = editText.getText().toString();
        int costo = 0;
        if (!temp.equals(""))
            costo = Integer.parseInt(temp);

        editText = findViewById(R.id.create_item_weight);
        temp = editText.getText().toString();
        int peso = 0;
        if (!temp.equals(""))
            peso = Integer.parseInt(temp);

        editText = findViewById(R.id.create_item_capacity);
        temp = editText.getText().toString();
        int capacita = 0;
        if (!temp.equals(""))
            capacita = Integer.parseInt(editText.getText().toString());

        String tipo = typeSpinner.getSelectedItem().toString();

        if (tipo.equals(SELEZIONAT)) {
            Toast.makeText(this, "selezionare un tipo", Toast.LENGTH_LONG).show();
            return;
        }

        String subtipo = subtypeSpinner.getSelectedItem().toString();
        if (subtipo.equals(SELEZIONAST))
            subtipo = "";

        switch (tipo) {
            case "arma":
                this.createWeapon(nome, desc, tipo, costo, peso, capacita, subtipo);
                break;
            case "armatura":
            case "scudo":
                this.createArmor(nome, desc, tipo, costo, peso, capacita, subtipo);
                break;
            default:
                this.createEquipment(nome, desc, tipo, costo, peso, capacita, subtipo);
                break;
        }
    }

    public void createEquipment(String nome, StringBuffer desc, String tipo, int costo, int peso, int capacita, String subtipo) {
        Equipaggiamento nuovoEquipaggiamento = new Equipaggiamento(nome, desc, tipo, costo, peso, capacita, subtipo);

        if (!dbManager.aggiungiEquipaggiamento(nuovoEquipaggiamento))
            Toast.makeText(this, "inserimeto fallito", Toast.LENGTH_LONG).show();
        else
            onBackPressed();
    }

    public void createWeapon(String nome, StringBuffer desc, String tipo, int costo, int peso, int capacita, String subtipo) {
        editText = findViewById(R.id.create_item_weapon_damage);
        String danno = editText.getText().toString();

        editText = findViewById(R.id.create_item_weapon_property);
        String proprieta = editText.getText().toString();

        Arma nuovaArma = new Arma(nome, desc, tipo, costo, capacita, peso, danno, proprieta, subtipo);

        if (!dbManager.aggiungiArma(nuovaArma))
            Toast.makeText(this, "inserimeto fallito", Toast.LENGTH_LONG).show();
        else
            onBackPressed();
    }

    public void createArmor(String nome, StringBuffer desc, String tipo, int costo, int peso, int capacita, String subtipo) {

        boolean nonFurtiva = !((CheckBox) findViewById(R.id.create_item_armor_stealth)).isChecked();

        editText = findViewById(R.id.create_item_armor_CA);
        String temp = editText.getText().toString();
        int modCA = 0;
        if (!temp.equals(""))
            modCA = Integer.parseInt(editText.getText().toString());

        editText = findViewById(R.id.create_item_armor_time_off);
        String tempoTogliere = editText.getText().toString();

        editText = findViewById(R.id.create_item_armor_time_on);
        String tempoIndossare = editText.getText().toString();

        editText = findViewById(R.id.create_item_armor_streight_needed);
        String forzaNecessaria = editText.getText().toString();

        Armatura nuovaArmatura = new Armatura(nome, desc, tipo, costo, peso, capacita, nonFurtiva, modCA, tempoTogliere, tempoIndossare, forzaNecessaria, subtipo);

        if (!dbManager.aggiungiArmatura(nuovaArmatura))
            Toast.makeText(this, "inserimeto fallito", Toast.LENGTH_LONG).show();
        else
            onBackPressed();
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, CharacterBagActivity.class);
        intent.putExtra("nomecamp", nomecamp);
        intent.putExtra("nomeg", nomeg);
        startActivity(intent);
        finish();
    }
}