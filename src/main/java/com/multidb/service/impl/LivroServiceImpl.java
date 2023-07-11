package com.multidb.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.multidb.model.app.Livro;
import com.multidb.model.app.LivroDTO;
import com.multidb.model.auth.Usuario;
import com.multidb.repository.app.LivroRepository;
import com.multidb.repository.auth.UsuarioRepository;
import com.multidb.service.LivroService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LivroServiceImpl implements LivroService{

	private final LivroRepository repository;
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public List<Livro> obterLivros() {
		return repository.findAll();
	}
	
	public List<LivroDTO> obterLivrosDTO() {
		return this.obterLivros().stream()
				.map(livro -> LivroDTO.builder().id(livro.getId())
						.titulo(livro.getTitulo())
						.nomeUsuario(livro.getNomeUsuario())
						.build()).collect(Collectors.toList());
	}

	@Override
	public Livro criar(Livro livro) {
		livro.setNomeUsuario(obtemNomeUsuarioPorUsuarioPesquisa(livro));
		return repository.save(livro);
	}

	private String obtemNomeUsuarioPorUsuarioPesquisa(Livro livro) {
		return usuarioRepository
				.findById(livro.getIdUsuario())
				.map(Usuario::getNome)
				.orElseThrow(() -> new IllegalArgumentException("Usuario não encontrado."));
	}

}
