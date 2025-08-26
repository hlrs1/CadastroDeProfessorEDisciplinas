package main.java.control;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Disciplina;

public class DisciplinaService {

    private List<Disciplina> disciplinasDB = new ArrayList<>();

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        // Por enquanto, apenas adicionamos à lista.
        disciplinasDB.add(disciplina);
        return disciplina;
    }

    // Método para que o teste possa verificar o estado da lista.
    public List<Disciplina> getDisciplinasDB() {
        return disciplinasDB;
    }
}