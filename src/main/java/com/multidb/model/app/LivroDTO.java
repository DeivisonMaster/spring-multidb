package com.multidb.model.app;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LivroDTO {
	
	private Long id;
	private String titulo;
	private String nomeUsuario;
	
}
