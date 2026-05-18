package edu.ijse.fx.orm.bo;

import edu.ijse.fx.orm.bo.custom.impl.*;
import edu.ijse.fx.orm.dao.custom.TherapistDAO;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        THERAPIST, PROGRAM,SESSION,PAYMENT,PATIENT,
    }
    public Object getBO(BOTypes boType) throws Exception {
        switch (boType) {
            case THERAPIST:
                return new TherapistBOImpl();
            case PROGRAM:
                return new ProgramBOImpl();
            case SESSION:
                return new SessionBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PATIENT:
                return new PatientBOImpl();
            default:
                return null;
        }
    }
}
