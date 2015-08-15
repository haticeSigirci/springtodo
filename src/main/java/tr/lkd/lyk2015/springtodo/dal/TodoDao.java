package tr.lkd.lyk2015.springtodo.dal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tr.lkd.lyk2015.springtodo.model.Todo;

/**
 * Created by destan on 23.07.2015.
 */
@Repository
public class TodoDao {

	@Autowired			//bununla pull dan cikariyorum
	protected SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public Long create(final Todo todo) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(todo);
	}

	//orm sorgu
	
	public Todo getById(final Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Todo) session.get(Todo.class, id);
	}

	public Todo update(final Todo todo) {
		final Session session = sessionFactory.getCurrentSession();
		return (Todo) session.merge(todo);
	}

	@SuppressWarnings("unchecked")
	public List<Todo> getAll() {

		final Session session = sessionFactory.getCurrentSession();
		final Criteria criteria = session.createCriteria(Todo.class);
		
//		final Criteria criteria = session.createCriteria(User.class);
//		criteria.add(Restrictions.idEq(13L)); //where id == 13
//		criteria.setFetchMode("todos", FethcMode.JOIN);		//eager Loading		
//				
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setFetchMode("*", FetchMode.JOIN);

		return criteria.list();
	}

	public void hardDelete(final Todo t) {

		final Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

}
