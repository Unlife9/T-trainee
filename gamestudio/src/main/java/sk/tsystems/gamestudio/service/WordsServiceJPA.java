package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Words;

@Component
@Transactional
public class WordsServiceJPA implements WordsService{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addWords(Words words) {
		entityManager.persist(words);
	}

	@SuppressWarnings("unchecked")
	public List<Words> listAllWords() {
		return (List<Words>) entityManager.createQuery("select w from Words w").getResultList();
	}

	public Words getWords(String slovak) {
	try {
		return (Words) entityManager.createQuery("select w from Words w where w.slovak = :slovak").setParameter("slovak", slovak).getSingleResult();
		} catch (NoResultException e) {
		return null;
	}

}
}
