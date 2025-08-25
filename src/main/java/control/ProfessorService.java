package main.java.control;

import java.util.ArrayList;
import java.util.List;

import main.java.model.Professor;

public class ProfessorService {

    // Lista para simular o banco de dados.
    private List<Professor> professoresDB = new ArrayList<>();

    // Método `cadastrarProfessor`.
    public Professor cadastrarProfessor(Professor professor, String confirmarSenha) {
        
        // Lógica para a validação do CPF.
        if (professoresDB.stream().anyMatch(p -> p.getCpf().equals(professor.getCpf()))) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        
       //  Lógica para validar o e-mail duplicado.
        if (professoresDB.stream().anyMatch(p -> p.getEmail().equals(professor.getEmail()))) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        
        //Adiciona o professor ao banco de dados.
        professoresDB.add(professor);
        return professor;
    }
    
    // Método auxiliar para o teste poder verificar o estado da lista.
    public List<Professor> getProfessoresDB() {
        return professoresDB;
    }
}
