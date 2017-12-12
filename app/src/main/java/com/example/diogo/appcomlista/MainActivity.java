package com.example.diogo.appcomlista;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    Button botaoBaludo;
    Button botaoDelet;
    Button botaoEdit;
    ListView listViewBaludo;
    EditText etDelete;
    EditText etNomeEdit;
    EditText etCguEdit;
    List<Aluno> listaDeAlunos= RepositorioDeAlunos.getAlunos();
    AdapterDeAlunos adapter = new AdapterDeAlunos(listaDeAlunos, this);

    private Aluno getAlunoEdit(){
        Aluno aluno= new Aluno();
        aluno.setNome(etNomeEdit.getText().toString());
        aluno.setCgu(etCguEdit.getText().toString());
        return aluno;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // etNomeEdit = (EditText).(Dialog alert). findViewById(R.id.etNomeEdit);

        etCguEdit = (EditText) findViewById(R.id.etCguEdit);


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
                    Toast.makeText(MainActivity.this, "Deletado !", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Opssss");
                    alertDialog.setMessage("Ocorreu um erro ao deletar, verifique e tente novamente ");

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                     alertDialog.show();
                }

            }});



        botaoEdit=(Button) findViewById(R.id.botaoEdit);

        botaoEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                try {
                    View v = getLayoutInflater().inflate(R.layout.layout_edt, null);
                    final AlertDialog alert = new AlertDialog.Builder(MainActivity.this).create();
                    alert.setView(v);

                    alert.setTitle("Editar");
                    alert.setButton(AlertDialog.BUTTON_POSITIVE,"Editar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            try{

                                etNomeEdit = ((Dialog) arg0).findViewById(R.id.etNomeEdit);
                                etCguEdit= ((Dialog) arg0).findViewById(R.id.etCguEdit);
                                //etNomeEdit.setText(RepositorioDeAlunos.getAlunos().get(Integer.parseInt(etDelete.getText().toString())).getNome());
                                //etCguEdit.setText(RepositorioDeAlunos.getAlunos().get(Integer.parseInt(etDelete.getText().toString())).getCgu());

                                if((etNomeEdit.getText().length() == 0)||(etCguEdit.getText().length() != 9)){
                                    alert.dismiss();
                                    Toast.makeText(MainActivity.this, "Verifique os campos!", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    RepositorioDeAlunos.getAlunos().remove(Integer.parseInt(etDelete.getText().toString()));
                                    RepositorioDeAlunos.getAlunos().add(Integer.parseInt(etDelete.getText().toString()), getAlunoEdit());
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(MainActivity.this, "Editado !", Toast.LENGTH_SHORT).show();
                                }

                            }catch (Exception E){
                                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                alertDialog.setTitle("Opssss");
                                alertDialog.setMessage("Ocorreu um erro ao editar, verifique e tente novamente ");

                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        }
                    });
                    alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alert.dismiss();
                        }
                    });
                    AlertDialog alerta = alert;
                    alerta.show();

                }catch (Exception e){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Opssss");
                    alertDialog.setMessage("Ocorreu um erro ao editar, verifique e tente novamente ");

                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Editar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
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
