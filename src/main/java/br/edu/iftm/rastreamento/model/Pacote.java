package br.edu.iftm.rastreamento.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "rastreamentos")
@Entity
@ToString(exclude = "rastreamentos")

public class Pacote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String idUnico;
	private String destinatario;
	@ManyToOne
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;
	private String status;
	@OneToMany

	private List<Rastreamento> rastreamentos = new ArrayList<>();

	public void atualizarStatus(String novoStatus, Date dataHora, String localizacao) {
		this.status = novoStatus;
		Rastreamento rastreamento = new Rastreamento();
		rastreamento.setDataHora(dataHora);
		rastreamento.setLocalizacao(localizacao);
		rastreamento.setStatus(novoStatus);
		rastreamentos.add(rastreamento);
	}

	public String consultarInformacoes() {
		StringBuilder sb = new StringBuilder();
		for (Rastreamento r : rastreamentos) {
			sb.append(r.getResumo());
			sb.append("\n");
		}
		return "Pacote [id=" + id + ", idUnico=" + idUnico + ", destinatario=" + destinatario + ", endereco=" + endereco
				+ ", status=" + status + ", rastreamentos=" + sb.toString() + "]";
	}

}
