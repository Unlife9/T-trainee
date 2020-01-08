package sk.tsystems.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Comment;


@Component
@Transactional
public class CommentServiceJPA implements CommentService{
	
	@PersistenceContext
	private EntityManager entityManager;
 
	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}
 


	@SuppressWarnings("unchecked")
	public List<Comment> getComment(String game) {
		return (List<Comment>) entityManager
				.createQuery("select c from Comment c where c.game = :game order by c.content desc ")
				.setParameter("game", game).setMaxResults(15).getResultList();
	}
}
