package com.JuanOsorio.MINTIC.model.custom;

import com.JuanOsorio.MINTIC.model.Client;

import lombok.Data;

@Data
public class CountClient {

	private Long total;
	private Client client;
	

	public CountClient(Long total, Client client) {
		this.total=total;
		this.client=client;
	
	}

}
