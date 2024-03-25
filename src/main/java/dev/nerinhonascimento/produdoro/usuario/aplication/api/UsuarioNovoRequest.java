package dev.nerinhonascimento.produdoro.usuario.aplication.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UsuarioNovoRequest {
	@Email
	private final String email;
	@Size(min = 6)
	private final String senha;
	public @javax.validation.constraints.Email String getEmail() {
		return null;
	}
}