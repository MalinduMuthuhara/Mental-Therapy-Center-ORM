package edu.ijse.fx.orm.dao.custom.impl;

import edu.ijse.fx.orm.config.FactoryConfiguration;
import edu.ijse.fx.orm.dao.custom.PaymentDAO;
import edu.ijse.fx.orm.entity.PaymentEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(PaymentEntity paymentEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(paymentEntity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }

    }

    @Override
    public boolean update(PaymentEntity paymentEntity) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            PaymentEntity oldPayment = session.find(PaymentEntity.class, paymentEntity.getPaymentId());
            oldPayment.setSessionEntity(paymentEntity.getSessionEntity());
            oldPayment.setDate(paymentEntity.getDate());
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            PaymentEntity paymentEntity = session.find(PaymentEntity.class, id);
            session.remove(paymentEntity);
            transaction.commit();
            return true;

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
            return false;

        } finally {
            session.close();
        }
    }

    @Override
    public PaymentEntity search(String id) throws SQLException {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();

        try {

            PaymentEntity paymentEntity = session.find(PaymentEntity.class, id);
            transaction.commit();
            return paymentEntity;

        } catch (Exception e) {

            e.printStackTrace();
            transaction.rollback();
            return null;

        } finally {
            session.close();
        }
    }

    @Override
    public ArrayList<PaymentEntity> getAll() throws SQLException {
        Session session = factoryConfiguration.getSession();

        try {
            return new ArrayList<>(session.createQuery("from PaymentEntity", PaymentEntity.class).list());
        } finally {
            session.close();
        }
    }
}
