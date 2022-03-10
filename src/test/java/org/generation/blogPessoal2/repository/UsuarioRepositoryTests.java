package org.generation.blogPessoal2.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal2.model.Usuario;
import org.generation.blogPessoal2.service.UsuarioService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTests {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@BeforeAll
	void start() {
		usuarioRepository.save(new Usuario(0L, "Ramon Santos", "Ramonzito@clovis.com", "123456789",
				"https://www.einerd.com.br/curiosidades-seu-madruga-ramon-valdez/"));
		usuarioRepository.save(new Usuario(0L, "Paolo Bracho Santos", "ataldausurpadora@yahoo.com", "carlosdaniel",
				"https://i.imgur.com/FETvs2O.jpg"));
		usuarioRepository.save(new Usuario(0L, "Robson Carmo Santos", "robsonbruxinho@carmo.com", "mago123",
				"https://pt.wikipedia.org/wiki/Paola_Bracho"));
	}

	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() {
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("Ramonzito@clovis.com");
		assertTrue(usuario.get().getUsuario().equals("Ramonzito@clovis.com"));

	}

	@Test
	@DisplayName("Retorna 3 usuarios")

	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ramon Daniel Santos"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Robson Carmo Santos"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Paolo Bracho Santos"));
	}

	@AfterAll

	public void end() {
		usuarioRepository.deleteAll();

	}

}
