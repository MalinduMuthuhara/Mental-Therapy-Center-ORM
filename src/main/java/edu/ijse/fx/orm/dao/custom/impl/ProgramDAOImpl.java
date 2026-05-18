package edu.ijse.fx.orm.dao.custom.impl;

import edu.ijse.fx.orm.config.FactoryConfiguration;
import edu.ijse.fx.orm.dao.custom.ProgramDAO;
import edu.ijse.fx.orm.entity.ProgramEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramDAOImpl implements ProgramDAO {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(ProgramEntity programEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(programEntity);
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
    public boolean update(ProgramEntity programEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try {
            ProgramEntity oldProgram = session.find(ProgramEntity.class, programEntity.getProgramId());
            if (oldProgram != null) {
                oldProgram.setProgramName(programEntity.getProgramName());
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
            ProgramEntity programEntity = session.find(ProgramEntity.class, id);
            if (programEntity != null) {
                session.remove(programEntity);
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
    public ProgramEntity search(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            return session.find(ProgramEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<ProgramEntity> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try {
            return new ArrayList<>(session.createQuery("from ProgramEntity", ProgramEntity.class).list());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            session.close();
        }
    }
}