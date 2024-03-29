package com.JuanOsorio.MINTIC.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.JuanOsorio.MINTIC.model.Client;
import com.JuanOsorio.MINTIC.service.ClientService;


@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins="*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping("/all")
	public List<Client> getClients(){
		return clientService.getAll();
	}
	
	@GetMapping("/{idClient}")
	public Optional<Client> getClient(@PathVariable("id") int id){
		return clientService.getClient(id);		
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Client save(@RequestBody Client client) {
		return clientService.save(client);		
	}
	
	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public Client update(@RequestBody Client client) {
		return clientService.update(client);
	}
	
	@DeleteMapping("/{idClient}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public boolean deleteClient(@PathVariable("idClient") int id) {
		return clientService.delete(id);
	}
	
}