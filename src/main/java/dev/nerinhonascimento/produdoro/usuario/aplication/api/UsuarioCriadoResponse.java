package dev.nerinhonascimento.produdoro.usuario.aplication.api;

import java.util.UUID;

import dev.nerinhonascimento.produdoro.usuario.domain.StatusUsuario;
import dev.nerinhonascimento.produdoro.usuario.domain.Usuario;
import lombok.Value;
@Value
public class UsuarioCriadoResponse {
	private final UUID idUsuario;
	private final String email;
	private final ConfiguracaoUsuarioResponse configuracao;
	private final StatusUsuario status;
	private final Integer quantidadePomodorosPausaCurta = 0;

	public UsuarioCriadoResponse(Usuario usuario) {
		this.idUsuario = usuario.getIdUsuario();
		this.email = usuario.getEmail();
		this.configuracao = new ConfiguracaoUsuarioResponse(usuario.getConfiguracao());
		this.status = usuario.getStatus();
	}
}

}
