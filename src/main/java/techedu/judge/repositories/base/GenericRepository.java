package techedu.judge.repositories.base;

import techedu.judge.entities.base.Entity;

import java.util.List;

public interface GenericRepository<T extends Entity> {
	List<T> getAll ();

	List<T> getBy (String field, Object value);

	T create (T entity);
}