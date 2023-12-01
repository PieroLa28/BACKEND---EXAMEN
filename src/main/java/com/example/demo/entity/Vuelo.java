package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="VUELO")
public class Vuelo {
	@Id
	@Column(name = "ID_VUELO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVuelos")
    @SequenceGenerator(name = "seqVuelos", allocationSize = 1, sequenceName = "SEQ_Vuelos")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "FECHA_SALIDA")	
	private String fechasa;
	
	@Column(name = "HORA_SALIDA")	
    private String horasa;
	
	@Column(name = "FECHA_LLEGADA")	
	private String fechalle;
	
	@Column(name = "HORA_LLEGADA")	
    private String horalle;
	
	@Column(name = "ORIGEN")	
    private String origen;
	
	@Column(name = "DESTINO")	
    private String destino;
	
	@Column(name = "NUMERO_PLAZAS")	
    private Integer numeroPlazas;
	
	//HACIA RESERVA
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vuelo")
	@JsonIgnore
	private Set<Reserva> reservas;
	
	
}
