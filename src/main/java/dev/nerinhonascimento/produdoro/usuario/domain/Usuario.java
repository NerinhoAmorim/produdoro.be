package dev.nerinhonascimento.produdoro.usuario.domain;


import java.util.UUID;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpStatus;

import dev.nerinhonascimento.produdoro.handler.APIException;
import dev.nerinhonascimento.produdoro.pomodo.domain.ConfiguracaoPadrao;
import dev.nerinhonascimento.produdoro.usuario.aplication.api.UsuarioNovoRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Document(collection = "Usuario")
  	public class Usuario{
	@Id
	private UUID idUsuario;
	@Email
	@Indexed
	private String email;
	private ConfiguracaoUsuario configuracao;
	@Builder.Default
	private StatusUsuario status = StatusUsuario.FOCO;
	@Builder.Default
	private Integer quantidadePomodorosPausaCurta = 0;
	
	public Usuario(UsuarioNovoRequest usuarioNovo, ConfiguracaoPadrao configuracaoPadrao) {
        this.idUsuario = UUID.randomUUID();
        this.email = usuarioNovo.getEmail();
        this.status = StatusUsuario.FOCO;
        this.configuracao = new ConfiguracaoUsuario(configuracaoPadrao);
    }
	public void validaSeUsuarioJaEstaEmFoco() {
		if(this.status.equals(StatusUsuario.FOCO)) {
			throw APIException.build(HttpStatus.BAD_REQUEST, "Usuário já esta em FOCO!");
		}
	}
	public void mudaStatusParaFoco(UUID idUsuario) {
		validaUsuario(idUsuario);
		validaSeUsuarioJaEstaEmFoco();
		this.status = StatusUsuario.FOCO;
	}
    public void validaUsuario(UUID idUsuario) {
        log.info("[inicia] Usuario - validaUsuario");
        if (!this.idUsuario.equals(idUsuario)) {
        log.info("[finaliza] Usuario - validaUsuario");
            throw APIException.build(HttpStatus.UNAUTHORIZED, "Credencial de autenticação não é válida!");
        }
        log.info("[finaliza] Usuario - validaUsuario");
    }
	public void mudaStatusParaPausaLonga() {
		log.info("[inicia] Usuario - mudaStatusParaPausaLonga");
		this.status = StatusUsuario.PAUSA_LONGA;
		log.info("[finaliza] Usuario - mudaStatusParaPausaLonga");
		
	}
	public ConfiguracaoUsuario getConfiguracao() {
		return null;
	}
	public UUID getIdUsuario() {
		return null;
	}
	public String getEmail() {
		return null;
	}
	public StatusUsuario getStatus() {
		return null;
	}

}
