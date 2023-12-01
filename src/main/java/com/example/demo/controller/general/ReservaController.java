package com.example.demo.controller.general;

import static com.example.commons.GoblalConstants.API_RESERVAS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Reserva;
import com.example.demo.serviceImpl.ReservaServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping(API_RESERVAS)
public class ReservaController {
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@GetMapping("/listarReservas")
	public ResponseEntity<List<Reserva>> listar() {
		try {
		      List<Reserva> re = reservaServiceImpl.readAll();
		      if (re.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(re, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarReserva")
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva){
        try {
        	Reserva re = reservaServiceImpl.create(reserva);
            return new ResponseEntity<Reserva>(re, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarReserva/{id}")
	public ResponseEntity<Reserva> getReservaById(@PathVariable("id") Long id){
		Optional<Reserva> reData = reservaServiceImpl.read(id);
	    if (reData.isPresent()) {
	      return new ResponseEntity<Reserva>(reData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarReserva/{id}")
	public ResponseEntity<Reserva> delete(@PathVariable("id") Long id){
		try {
			reservaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarReserva/{id}")
	public ResponseEntity<?> updateReserva(@PathVariable("id") Long id, @Valid @RequestBody Reserva reserva){
		Optional<Reserva> reData = reservaServiceImpl.read(id);
	      if (reData.isPresent()) {
	        Reserva dbReserva = reData.get();
	        dbReserva.setClase(reserva.getClase());
	        dbReserva.setVuelo(reserva.getVuelo());
	        dbReserva.setCliente(reserva.getCliente());
	        dbReserva.setHotel(reserva.getHotel());
	        dbReserva.setSucursal(reserva.getSucursal());
	        return new ResponseEntity<Reserva>(reservaServiceImpl.update(dbReserva), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
