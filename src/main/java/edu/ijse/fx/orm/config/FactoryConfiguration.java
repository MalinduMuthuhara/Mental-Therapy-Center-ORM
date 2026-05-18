package edu.ijse.fx.orm.config;

import edu.ijse.fx.orm.dto.TherapistDTO;
import edu.ijse.fx.orm.entity.PatientEntity;
import edu.ijse.fx.orm.entity.ProgramEntity;
import edu.ijse.fx.orm.entity.SessionEntity;
import edu.ijse.fx.orm.entity.TherapistEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        configuration.addAnnotatedClass(TherapistEntity.class);
        configuration.addAnnotatedClass(ProgramEntity.class);
        configuration.addAnnotatedClass(SessionEntity.class);
        configuration.addAnnotatedClass(PatientEntity.class);

        sessionFactory = configuration.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
