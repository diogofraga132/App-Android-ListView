package com.example.diogo.appcomlista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    EditText etNome;
    EditText etCGU;
    Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etNome=(EditText) findViewById(R.id.etNome);
        etCGU=(EditText) findViewById(R.id.etCGU);
        btnCadastrar= (Button) findViewById(R.id.idBtnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((etNome.getText().length() == 0)||(etCGU.getText().length() != 9)){

                    Toast.makeText(CadastroActivity.this, "Verifique os campos !", Toast.LENGTH_SHORT).show();
                }
                else {
                    RepositorioDeAlunos.getAlunos().add(getAluno());
                    CadastroActivity.this.finish();
                }

            }
        });
    }
    private Aluno getAluno(){
        Aluno aluno= new Aluno();
        aluno.setNome(etNome.getText().toString());
        aluno.setCgu(etCGU.getText().toString());
        return aluno;
    }

}
