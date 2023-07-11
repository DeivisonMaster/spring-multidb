package com.multidb.controller;

import java.util.List;

import com.multidb.model.app.Livro;
import com.multidb.model.app.LivroDTO;
import com.multidb.service.LivroService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@Log4j2
public class LivroController {
	
	private final LivroService livroService;

	@GetMapping
	public List<LivroDTO> obterLivros(){
		return this.livroService.obterLivrosDTO();
	}
	
	@PostMapping
	public ResponseEntity<LivroDTO> criar(@RequestBody Livro livro){
		try {
			return new ResponseEntity<>(getLivroDTO(this.livroService.criar(livro)), HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			log.error(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	private LivroDTO getLivroDTO(Livro livro) {
		return LivroDTO.builder().id(livro.getId())
				.titulo(livro.getTitulo())
				.nomeUsuario(livro.getNomeUsuario())
				.build();
	}
	
}
