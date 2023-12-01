package com.example.demo.controller.general;

import static com.example.commons.GoblalConstants.API_SUCURSALES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Sucursal;
import com.example.demo.serviceImpl.SucursalImpl;

@RestController
@RequestMapping(API_SUCURSALES)
public class SucursalContoller {
	@Autowired
	private SucursalImpl sucursalServiceImpl;
	
	@GetMapping("/listarSucursales")
	public ResponseEntity<List<Sucursal>> listar() {
		try {
		      List<Sucursal> suc = sucursalServiceImpl.readAll();
		      if (suc.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(suc, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
