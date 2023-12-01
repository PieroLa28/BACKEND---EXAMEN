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
@Table(name="HOTEL")
public class Hotel {
	@Id
	@Column(name = "ID_HOTEL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqHoteles")
    @SequenceGenerator(name = "seqHoteles", allocationSize = 1, sequenceName = "SEQ_HOTELES")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")	
	private String nombre;
	
	@Column(name = "DIRECCION")	
    private String direccion;
	
	@Column(name = "LOCALIDAD")	
    private String localidad;
	
	@Column(name = "PROVINCIA")	
    private String provincia;
	
	@Column(name = "TELEFONO")	
    private String telefono;
	
	@Column(name = "NUMERO_ESTRELLAS")	
    private Integer numeroEstrellas;
	
	//HACIA RESERVA:
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "hotel")
	@JsonIgnore
	private Set<Reserva> reservas;
}
