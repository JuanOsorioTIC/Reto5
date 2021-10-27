package com.JuanOsorio.MINTIC.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JuanOsorio.MINTIC.model.Reservation;
import com.JuanOsorio.MINTIC.model.custom.CountClient;
import com.JuanOsorio.MINTIC.model.custom.StatusAmount;
import com.JuanOsorio.MINTIC.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public List<Reservation> getAll(){
		return (List<Reservation>)reservationRepository.getAll();
	}
	
	public Optional<Reservation> getReservation(int id){
		return reservationRepository.getReservation(id);
	}
	
	public Reservation save (Reservation reservation) {
		if(reservation.getIdReservation() == null) {
			return reservationRepository.save(reservation);
		}
		else {
			Optional<Reservation> reservationExists = reservationRepository.getReservation(reservation.getIdReservation());
			if(reservationExists.isEmpty()) {
				return reservationRepository.save(reservation);
			}else {
				return reservation;
			}
		}
	}
	
	public Reservation update(Reservation reservation) {
		if(reservation.getIdReservation()!=null) {
			Optional<Reservation> optional = reservationRepository.getReservation(reservation.getIdReservation());
			if(!optional.isEmpty()) {
				if(reservation.getStartDate()!=null) {
					optional.get().setStartDate(reservation.getStartDate());
				}
				if(reservation.getDevolutionDate()!=null) {
					optional.get().setDevolutionDate(reservation.getDevolutionDate());
				}
				return reservationRepository.save(optional.get());
			}
			
		}
		return reservation;
	}
	
	public boolean delete(int id) {
		Boolean flag = getReservation(id).map(reservation ->{
			reservationRepository.delete(reservation);
			return true;
		}).orElse(false);
		return flag;
	}
	
	
	public List<CountClient> getTopClient(){
		return reservationRepository.getTopReservation();
	}
	
	public StatusAmount getStatusReport() {
		List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
		List<Reservation> canceled = reservationRepository.getReservationByStatus("cancelled");
		
		return new StatusAmount(completed.size(), canceled.size());
	
	}
	
	public List<Reservation> getReservationPeriod(String d1, String d2){
		
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
		Date dateOne = new Date();
		Date dateTwo = new Date();
		try {
			dateOne = parser.parse(d1);
			dateTwo = parser.parse(d2);
			
		} catch (ParseException e) {}
		
		if(dateOne.before(dateTwo)) {
			return reservationRepository.getReservationPeriod(dateOne,dateTwo);
		}
		
		return new ArrayList<>();
		
		
		
		
	}
}
