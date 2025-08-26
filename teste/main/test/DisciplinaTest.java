package main.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.control.DisciplinaService;
import main.java.model.Disciplina;
import main.java.model.Professor;

class DisciplinaTest {

	private DisciplinaService disciplina;
    private Professor professorPadrao;

    @BeforeEach
    void configurar() {
        this.disciplina = new DisciplinaService();
        professorPadrao = new Professor("Professor Teste", "987.654.321-00", "prof@teste.com", "Prof123");
        professorPadrao.setId(1L);
    }

    @Test
    // TC_008: Cadastro de disciplina com dados v�lidos
    void deveCadastrarDisciplinaComDadosValidos() {
        Disciplina disc = new Disciplina("LPO001", "LPOO", 60, professorPadrao);
        disciplina.cadastrarDisciplina(disc);
        assertFalse(disciplina.getDisciplinasDB().isEmpty());
    }
    
    @Test
    // TC_009: C�digo de disciplina duplicado
    void naoDeveCadastrarDisciplinaComCodigoDuplicado() {
        disciplina.cadastrarDisciplina(new Disciplina("LPO001", "LPOO", 60, professorPadrao));
        Disciplina novaDisciplina = new Disciplina("LPO001", "LDD", 60, professorPadrao);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(novaDisciplina);
        });
        assertEquals("C�digo de disciplina j� existente", exception.getMessage());
    }
    
    @Test
    // TC_010: C�digo com caracteres especiais
    void naoDeveCadastrarDisciplinaComCodigoComCaracteresEspeciais() {
        Disciplina disc = new Disciplina("MAT#202", "Matem�tica II", 60, professorPadrao);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("O c�digo da disciplina deve conter apenas letras e n�meros", exception.getMessage());
    }
    
    @Test
    // TC_011: Carga hor�ria inv�lida (zero)
    void naoDeveCadastrarDisciplinaComCargaHorariaInvalida() {
        Disciplina disc = new Disciplina("MAT202", "Matem�tica II", 0, professorPadrao);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("Carga hor�ria deve ser maior que 0", exception.getMessage());
    }
    
    @Test
    // TC_012: Cadastro sem professor
    void naoDeveCadastrarDisciplinaSemProfessor() {
        Disciplina disc = new Disciplina("MAT202", "Matem�tica II", 60, null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("Informe o professor", exception.getMessage());
    }
    
    @Test
    // TC_013: C�digo com menos de 5 caracteres
    void naoDeveCadastrarDisciplinaComCodigoCurto() {
        Disciplina disc = new Disciplina("MAT", "Matem�tica II", 60, professorPadrao);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("Erro de valida��o do c�digo - C�digo muito curto", exception.getMessage());
    }
    
    @Test
    // TC_014: Descri��o maior que 500 caracteres
    void naoDeveCadastrarDisciplinaComDescricaoLonga() {
        Disciplina disc = new Disciplina("WEB301", "Web 3", 80, professorPadrao);
        disciplina.setDescricao("A".repeat(501));
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("Descri��o excede 500 caracteres", exception.getMessage());
    }
    
    @Test
    // TC_015: Cadastro sem nome
    void naoDeveCadastrarDisciplinaSemNome() {
        Disciplina disc = new Disciplina("ARQS01", " ", 60, professorPadrao);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            disciplina.cadastrarDisciplina(disc);
        });
        assertEquals("Nome � obrigat�rio", exception.getMessage());
    }

}
