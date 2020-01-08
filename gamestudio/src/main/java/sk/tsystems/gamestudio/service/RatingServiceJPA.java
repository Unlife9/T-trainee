package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.sql.Select;
import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Rating;

@Component // vytvor z tejto triedy object
@Transactional
public class RatingServiceJPA implements RatingService {

	private static final double Double = 0;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setRating(Rating rating) {
		try {
			Rating dbRating = (Rating) entityManager
					.createQuery("Select r from Rating r where r.username = :username and r.game = :game")
					.setParameter("username", rating.getUsername()).setParameter("game", rating.getGame())
					.getSingleResult();
			dbRating.setValue(rating.getValue());

		} catch (NoResultException e) {
			entityManager.persist(rating);
		}

	}

	@Override
	public double getAverageRating(String game) {
		try {
			Double x = (Double) entityManager.createQuery("Select round( Avg(r.value),2)from Rating r where r.game = :game")
					.setParameter("game", game).setParameter("game", game).getSingleResult();
			return x.doubleValue();
		} catch (Exception e) {
		}
		return Double;
	}

}
