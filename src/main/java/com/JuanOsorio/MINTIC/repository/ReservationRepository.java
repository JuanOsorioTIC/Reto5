package com.JuanOsorio.MINTIC.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.JuanOsorio.MINTIC.model.Client;
import com.JuanOsorio.MINTIC.model.Reservation;
import com.JuanOsorio.MINTIC.model.custom.CountClient;
import com.JuanOsorio.MINTIC.repository.crud.ReservationCrudRepository;

@Repository
public class ReservationRepository {
	
	@Autowired
	private ReservationCrudRepository reservationCrudRepository;

	public List<Reservation> getAll(){
		return (List<Reservation>)reservationCrudRepository.findAll();
	}
	
	
	public Optional<Reservation> getReservation(Integer id){
		return reservationCrudRepository.findById(id);
	}
	
	public Reservation save(Reservation reservation) {
		return reservationCrudRepository.save(reservation);
	}
	
	public void delete(Reservation reservation) {
		reservationCrudRepository.delete(reservation);
	}
	
	public List<Reservation> getReservationByStatus(String status){
		return reservationCrudRepository.findAllByStatus(status);
	}
	
	public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
		return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);		
	}
	
	public List<CountClient> getTopReservation(){
		List<CountClient> res = new ArrayList<>();
		
		List<Object[]> report = reservationCrudRepository.countTotalReservationsByClient();
		for (int i=0; i<report.size();i++) {
			/*Reservation r = (Reservation)report.get(i)[0];
			Integer cantidad = (Integer)report.get(i)[1];
			res.add(new CountReservation(r,cantidad));
			*/
			res.add(new CountClient((Long)report.get(i)[1],(Client)report.get(i)[0]));
		}
		
		return res;
	}
	
}
