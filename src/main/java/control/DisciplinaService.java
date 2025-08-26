package main.java.control;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Disciplina;

public class DisciplinaService {

    private List<Disciplina> disciplinasDB = new ArrayList<>();

    public Disciplina cadastrarDisciplina(Disciplina disciplina) {
        
    	// L�gica para validar c�digo duplicado.
        if (disciplinasDB.stream().anyMatch(d -> d.getCodigo().equals(disciplina.getCodigo()))) {
            throw new IllegalArgumentException("C�digo de disciplina j� existente");
        }
        
        // L�gica para validar o formato do c�digo.
        // A express�o "[a-zA-Z0-9]+" verifica se a string cont�m apenas letras (mai�sculas/min�sculas) e n�meros.
        if (!disciplina.getCodigo().matches("[a-zA-Z0-9]+")) {
             throw new IllegalArgumentException("O c�digo da disciplina deve conter apenas letras e n�meros");
        }
        
        // L�gica para validar se a carga hor�ria � maior que 0.
        if (disciplina.getCargaHoraria()<=0) {
             throw new IllegalArgumentException("Carga hor�ria deve ser maior que 0");
        }
        
        disciplinasDB.add(disciplina);
        return disciplina;
    }

    // M�todo para que o teste possa verificar o estado da lista.
    public List<Disciplina> getDisciplinasDB() {
        return disciplinasDB;
    }
}