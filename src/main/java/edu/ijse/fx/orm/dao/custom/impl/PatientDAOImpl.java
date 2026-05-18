package edu.ijse.fx.orm.dao.custom.impl;

import edu.ijse.fx.orm.config.FactoryConfiguration;
import edu.ijse.fx.orm.dao.custom.PatientDAO;
import edu.ijse.fx.orm.entity.PatientEntity;
import edu.ijse.fx.orm.entity.TherapistEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;


public class PatientDAOImpl implements PatientDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(PatientEntity patientEntity) throws SQLException {

        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try{
            session.persist(patientEntity);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;

        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(PatientEntity patientEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();

        try{
            PatientEntity oldPatient = session.find(PatientEntity.class, patientEntity.getPatientId());
            oldPatient.setPatientName(patientEntity.getPatientName());
            oldPatient.setGender(patientEntity.getGender());
            oldPatient.setPatientPhone(patientEntity.getPatientPhone());
            tx.commit();
            return true;


        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;

        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        try{
            PatientEntity patientEntity = session.find(PatientEntity.class, id);
            session.remove(patientEntity);
            tx.commit();
            return true;

        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
            return false;

        }finally {
            session.close();
        }
    }

    @Override
    public PatientEntity search(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            PatientEntity patientEntity = session.find(PatientEntity.class, id);
            transaction.commit();
            return patientEntity;

        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }

    }

    @Override
    public ArrayList<PatientEntity> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();
        try{
            return new ArrayList<>(session.createQuery("from PatientEntity", PatientEntity.class).list());
        }finally {
            session.close();
        }
    }
}
