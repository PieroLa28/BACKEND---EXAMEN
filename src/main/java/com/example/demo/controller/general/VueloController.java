package com.example.demo.controller.general;

import static com.example.commons.GoblalConstants.API_VUELOS;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Vuelo;
import com.example.demo.serviceImpl.VueloServiceImpl;

@RestController
@RequestMapping(API_VUELOS)
public class VueloController {
	@Autowired
	private VueloServiceImpl vueloServiceImpl;
	
	@GetMapping("/listarVuelos")
	public ResponseEntity<List<Vuelo>> listar() {
		try {
		      List<Vuelo> vue = vueloServiceImpl.readAll();
		      if (vue.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(vue, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
}
