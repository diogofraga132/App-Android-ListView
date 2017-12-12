package com.example.diogo.appcomlista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Diogo on 22/11/2017.
 */

public class AdapterDeAlunos extends BaseAdapter {
    public AdapterDeAlunos(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    List<Aluno> alunos;
    Context context;
    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override

    public View getView(int i, View view, ViewGroup parent) {
        View resultView = LayoutInflater.from(context).inflate(R.layout.item_aluno,parent,false);
        TextView tvNome = resultView.findViewById(R.id.tvNome);
        TextView tvCGU = resultView.findViewById(R.id.tvCGU);
        TextView tvId = resultView.findViewById(R.id.tvId);


        Aluno aluno=alunos.get(i);
        String position = String.valueOf(i);
        tvId.setText("ID: ".concat(String.valueOf(position)));
        tvNome.setText("NOME: ".concat(String.valueOf(aluno.getNome())));
        tvCGU.setText("CGU: ".concat(String.valueOf(aluno.getCgu())));
        return resultView;
    }

}
