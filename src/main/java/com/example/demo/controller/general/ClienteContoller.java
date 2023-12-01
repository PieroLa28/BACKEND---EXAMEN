package com.example.demo.controller.general;

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

import com.example.demo.entity.Cliente;
import com.example.demo.serviceImpl.ClienteServiceImpl;

import jakarta.validation.Valid;

import static com.example.commons.GoblalConstants.API_CLIENTES;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(API_CLIENTES)
public class ClienteContoller {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/listarClientes")
	public ResponseEntity<List<Cliente>> listar() {
		try {
		      List<Cliente> cli = clienteServiceImpl.readAll();
		      if (cli.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cli, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarClientes")
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente){
        try {
        	Cliente cli = clienteServiceImpl.create(cliente);
            return new ResponseEntity<Cliente>(cli, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarCliente/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id){
		Optional<Cliente> cliData = clienteServiceImpl.read(id);
	    if (cliData.isPresent()) {
	      return new ResponseEntity<Cliente>(cliData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@DeleteMapping("eliminarCliente/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") Long id){
		try {
			clienteServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("editarCliente/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente){
		Optional<Cliente> cliData = clienteServiceImpl.read(id);
	      if (cliData.isPresent()) {
	        Cliente dbCliente = cliData.get();
	        dbCliente.setDni(cliente.getDni());
	        dbCliente.setNombres(cliente.getNombres());
	        dbCliente.setApellidos(cliente.getApellidos());
	        dbCliente.setTelefono(cliente.getTelefono());
	        dbCliente.setEmail(cliente.getEmail());
	        return new ResponseEntity<Cliente>(clienteServiceImpl.update(dbCliente), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
