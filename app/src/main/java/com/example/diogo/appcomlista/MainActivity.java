package com.example.diogo.appcomlista;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    Button botaoBaludo;
    Button botaoDelet;
    ListView listViewBaludo;
    EditText etDelete;
    List<Aluno> listaDeAlunos= RepositorioDeAlunos.getAlunos();
    AdapterDeAlunos adapter = new AdapterDeAlunos(listaDeAlunos, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        botaoBaludo= (Button) findViewById(R.id.botaoBaludo);
        listViewBaludo= (ListView) findViewById(R.id.listViewBaluda);
        listViewBaludo.setAdapter(adapter);


        botaoBaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencao = new Intent(MainActivity.this, CadastroActivity.class);
                MainActivity.this.startActivity(intencao);
            }
        });

        etDelete = (EditText) findViewById(R.id.etDelete);
        botaoDelet=(Button) findViewById(R.id.botaoDelet);
        botaoDelet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                try {
                    listaDeAlunos.remove(Integer.parseInt(etDelete.getText().toString()));
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Opssss");
                    alertDialog.setMessage("Ocorreu um erro ao deletar, verifique e tente novamente ");
                    // Alert dialog button
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();// use dismiss to cancel alert dialog
                                }
                            });
                    alertDialog.show();
                }

            }});

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }


}
