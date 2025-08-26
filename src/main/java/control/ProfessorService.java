package main.java.control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import main.java.model.Professor;

public class ProfessorService {

    // Lista para simular o banco de dados.
    private List<Professor> professoresDB = new ArrayList<>();

    // M�todo `cadastrarProfessor`.
    public Professor cadastrarProfessor(Professor professor, String confirmarSenha) {
        
        // L�gica para a valida��o do CPF.
        if (professoresDB.stream().anyMatch(p -> p.getCpf().equals(professor.getCpf()))) {
            throw new IllegalArgumentException("CPF j� cadastrado");
        }
        
       //  L�gica para validar o e-mail duplicado.
        if (professoresDB.stream().anyMatch(p -> p.getEmail().equals(professor.getEmail()))) {
            throw new IllegalArgumentException("E-mail j� cadastrado");
        }
        
       //  L�gica para validar o formato do e-mail.
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!Pattern.compile(emailRegex).matcher(professor.getEmail()).matches()) {
            throw new IllegalArgumentException("Erro de valida��o no campo e-mail");
        }
        
        //Adiciona o professor ao banco de dados.
        professoresDB.add(professor);
        return professor;
    }
    
    // M�todo auxiliar para o teste poder verificar o estado da lista.
    public List<Professor> getProfessoresDB() {
        return professoresDB;
    }
}
