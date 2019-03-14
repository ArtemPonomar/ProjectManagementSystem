package service;

import entities.Developer;
import entities.Project;
import entities.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DAOHibernate<T> {
    private String className;
    private Class entityClass;

    public DAOHibernate(Class entityClass) {
        this.className = entityClass.getSimpleName();
        this.entityClass = entityClass;
    }

    public void save(T object) {
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T findBy(String fieldName, String value){
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()) {
            return (T) session.createQuery("from Project where " + fieldName + "  = '" + value + "'")
                    .getResultStream().findFirst().get();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void delete (int id){
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            T objToDelete = (T)session.get(entityClass, id);
            session.delete(objToDelete);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update (int id_old, T obj_new){
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            T objToDelete = (T) session.get(entityClass, id_old);
            session.delete(objToDelete);
            session.save(obj_new);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        try(Session session = HibernateFactory.getInstance().getSessionFactory().openSession()) {
            return session.createQuery("from " + className).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public T getById(long id) {
        try (Session session = HibernateFactory.getInstance().getSessionFactory().openSession()) {
            return (T) session.get(entityClass, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public int getTotalDevelopersSalaryOfProject(Project project) throws SQLException {
//        int totalSalary = 0;
//        for (Developer developer : project.getDevsOnProject()){
//            totalSalary += developer.getSalary();
//        }
//        return totalSalary;
//    }
}
