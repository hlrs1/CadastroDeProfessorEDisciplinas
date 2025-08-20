package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfessorTest {

	private ProfessorService professor;
	
	@BeforeEach
    void configurar() {
        // Roda antes de cada teste, garantindo um ambiente limpo
        this.professor = new ProfessorService();
    }
	
	@Test
    // TC_001: Cadastro de professor com dados v�lidos
    void deveCadastrarProfessorComDadosValidos() {
        Professor prof = new Professor("Ana Silva", "123.456.789-00", "ana@exemplo.com", "Ana12345");
        professor.cadastrarProfessor(prof, "Ana12345");
        assertFalse(professor.getProfessoresDB().isEmpty());
    }

    @Test
    // TC_002: Cadastro com CPF j� existente
    void naoDeveCadastrarProfessorComCpfDuplicado() {
        Professor professorExistente = new Professor("Ana Silva", "123.456.789-00", "ana@exemplo.com", "Ana12345");
        professor.cadastrarProfessor(professorExistente, "Ana12345");
        Professor novoProfessor = new Professor("Luis Alberto", "123.456.789-00", "luis@exemplo.com", "Luis12345");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(novoProfessor, "Luis12345");
        });
        assertEquals("CPF j� cadastrado", exception.getMessage());
    }
    
    @Test
    // TC_003: Cadastro de professor com e-mail duplicado
    void naoDeveCadastrarProfessorComEmailDuplicado() {
        Professor professorExistente = new Professor("Ana Silva", "123.456.789-01", "ana@exemplo.com", "Ana12345");
        professor.cadastrarProfessor(professorExistente, "Ana12345");
        Professor novoProfessor = new Professor("Jo�o Pereira", "456.789.123-55", "ana@exemplo.com", "Joao1234");
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(novoProfessor, "Joao1234");
        });
        assertEquals("E-mail j� cadastrado", exception.getMessage());
    }
    
    @Test
    // TC_004: Cadastro com e-mail em formato inv�lido
    void naoDeveCadastrarProfessorComEmailInvalido() {
        Professor prof = new Professor("Jo�o Pereira", "456.789.123-55", "joaoexemplo.com", "Joao1234");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(prof, "Joao1234");
        });
        assertEquals("Erro de valida��o no campo e-mail", exception.getMessage());
    }
    
    @Test
    // TC_005: Senhas n�o coincidem
    void naoDeveCadastrarProfessorComSenhasDiferentes() {
        Professor prof= new Professor("Jo�o Pereira", "456.789.123-55", "joao@exemplo.com", "Joao12
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(prof, "Joao4321");
        });
        assertEquals("Senhas n�o coincidem", exception.getMessage());
    }
    
    @Test
    // TC_006: Senha sem n�mero (fraca)
    void naoDeveCadastrarProfessorComSenhaSemNumero() {
        Professor prof = new Professor("Lucas Mendes", "234.567.890-11", "lucas@exemplo.com", "seguranca");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(prof, "seguranca");
        });
        assertEquals("A senha deve conter letras e n�meros", exception.getMessage());
    }
    
    @Test
    // TC_007: Nome com caracteres inv�lidos
    void naoDeveCadastrarProfessorComNomeInvalido() {
        Professor prof = new Professor("Lucas123", "234.567.890-11", "lucas@exemplo.com", "Lucas1234");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            professor.cadastrarProfessor(prof, "Lucas1234");
        });
        assertEquals("Erro de valida��o no campo nome", exception.getMessage());
    }
	
	
 }


