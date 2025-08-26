package main.java.control;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Disciplina;

public class DisciplinaService {

    private List<Disciplina> disciplinasDB = new ArrayList<>();

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        
    	// Lógica para validar código duplicado.
        if (disciplinasDB.stream().anyMatch(d -> d.getCodigo().equals(disciplina.getCodigo()))) {
            throw new IllegalArgumentException("Código de disciplina já existente");
        }
        
        // Lógica para validar o formato do código.
        // A expressão "[a-zA-Z0-9]+" verifica se a string contém apenas letras (maiúsculas/minúsculas) e números.
        if (!disciplina.getCodigo().matches("[a-zA-Z0-9]+")) {
             throw new IllegalArgumentException("O código da disciplina deve conter apenas letras e números");
        }
        
        // Lógica para validar se a carga horária é maior que 0.
        if (disciplina.getCargaHoraria()<=0) {
             throw new IllegalArgumentException("Carga horária deve ser maior que 0");
        }
        
        disciplinasDB.add(disciplina);
        return disciplina;
    }

    // Método para que o teste possa verificar o estado da lista.
    public List<Disciplina> getDisciplinasDB() {
        return disciplinasDB;
    }
}