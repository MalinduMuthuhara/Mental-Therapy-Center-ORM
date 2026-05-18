package edu.ijse.fx.orm.dao;

import edu.ijse.fx.orm.dao.custom.ProgramDAO;
import edu.ijse.fx.orm.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public  enum DAOTypes{
        PROGRAM,PATIENT,PAYMENT,SESSION,THERAPIST
    }
    public Object getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case PROGRAM:
                return new ProgramDAOImpl();
            case PATIENT:
                return new PatientDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SESSION:
                return new SessionDAOImpl();
            case THERAPIST:
                return new TherapistDAOImpl();
            default:
                return null;
        }
    }
}
