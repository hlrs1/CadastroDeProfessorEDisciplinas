package main.java.control;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
        
       //  Lógica para validar o formato do e-mail.
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!Pattern.compile(emailRegex).matcher(professor.getEmail()).matches()) {
            throw new IllegalArgumentException("Erro de validação no campo e-mail");
        }
        
       //  Lógica para validar se as senhas coincidem.
        if (!professor.getSenha().equals(confirmarSenha)) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }
        
        //  Lógica para validar a força da senha.
        // A expressão ".*[a-zA-Z].*" verifica se existe pelo menos uma letra.
        // A expressão ".*[0-9].*" verifica se existe pelo menos um número.
        if (!professor.getSenha().matches(".*[a-zA-Z].*") || !professor.getSenha().matches(".*[0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter letras e números");
        }
        
        //  Lógica para validar o nome.
        // A expressão ".*\\d.*" verifica se existe qualquer dígito na string.
        if (professor.getNome().matches(".*\\d.*")) {
             throw new IllegalArgumentException("Erro de validação no campo nome");
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
