package com.JuanOsorio.MINTIC.repository.crud;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.JuanOsorio.MINTIC.model.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
	
	@Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c group by c.client order by COUNT(c.client) desc")
	public List<Object[]> countTotalReservationsByClient();
	
	public List<Reservation>findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
	
	public List<Reservation>findAllByStatus(String status);

}
