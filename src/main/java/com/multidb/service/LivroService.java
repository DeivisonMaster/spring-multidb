package com.multidb.service;

import java.util.List;

import com.multidb.model.app.Livro;
import com.multidb.model.app.LivroDTO;

import org.springframework.stereotype.Service;

@Service
public interface LivroService {

	public List<Livro> obterLivros();
	public List<LivroDTO> obterLivrosDTO();
	public Livro criar(Livro Livro);

}
