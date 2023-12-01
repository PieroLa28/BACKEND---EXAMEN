package com.example.demo.controller.general;

import static com.example.commons.GoblalConstants.API_HOTELES;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hotel;
import com.example.demo.serviceImpl.HotelServiceImpl;
@RestController
@RequestMapping(API_HOTELES)
public class HotelController {
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@GetMapping("/listarHoteles")
	public ResponseEntity<List<Hotel>> listar() {
		try {
		      List<Hotel> hot = hotelServiceImpl.readAll();
		      if (hot.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(hot, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
}
