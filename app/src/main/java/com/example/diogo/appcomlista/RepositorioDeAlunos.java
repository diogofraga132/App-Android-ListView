package com.example.diogo.appcomlista;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diogo on 22/11/2017.
 */

public class RepositorioDeAlunos {
    private static List<Aluno> alunos = new ArrayList<>();
    public static List<Aluno> getAlunos(){
        return alunos;
    }


}
