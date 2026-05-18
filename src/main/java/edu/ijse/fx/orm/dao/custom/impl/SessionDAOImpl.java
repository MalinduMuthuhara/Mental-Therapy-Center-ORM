package edu.ijse.fx.orm.dao.custom.impl;

import edu.ijse.fx.orm.config.FactoryConfiguration;
import edu.ijse.fx.orm.dao.custom.SessionDAO;
import edu.ijse.fx.orm.entity.SessionEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class SessionDAOImpl implements SessionDAO {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(SessionEntity sessionEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(sessionEntity);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(SessionEntity sessionEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            SessionEntity oldSession = session.find(SessionEntity.class, sessionEntity.getSessionId());

            if (oldSession != null) {
                oldSession.setTherapistEntity(sessionEntity.getTherapistEntity());
                oldSession.setPatientEntity(sessionEntity.getPatientEntity());
                oldSession.setDate(sessionEntity.getDate());
                oldSession.setTime(sessionEntity.getTime());
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            SessionEntity sessionEntity = session.find(SessionEntity.class, id);
            if (sessionEntity != null) {
                session.remove(sessionEntity);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public SessionEntity search(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(SessionEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<SessionEntity> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            return new ArrayList<>(session.createQuery("from SessionEntity", SessionEntity.class).list());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}