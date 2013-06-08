package com.hackathon.hopi.dao.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.hackathon.hopi.dao.RideDao;
import com.hackathon.hopi.model.Ride;

@Repository("rideDao")
public class RideDaoJpa extends GenericDaoJpa<Ride, Long> implements RideDao {

	public RideDaoJpa() {
		super(Ride.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ride> search(Float fromLat, Float fromLng, Float toLat,
			Float toLng, int radius) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ride> search(String userName) {
		return getEntityManager()
				.createQuery(
						"select obj from Ride obj where obj.userName>=:username or " +
						"obj.userName<=:username or")
				.setParameter("username", userName).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ride> search(Date startTime) {
		return getEntityManager()
				.createQuery(
						"select obj from Ride obj where obj.startTime <=:date")
				.setParameter("date", startTime).getResultList();
	}

}
