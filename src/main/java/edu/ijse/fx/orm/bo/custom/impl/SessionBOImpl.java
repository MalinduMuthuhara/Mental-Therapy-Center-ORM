package edu.ijse.fx.orm.bo.custom.impl;

import edu.ijse.fx.orm.dao.DAOFactory;
import edu.ijse.fx.orm.bo.custom.SessionBO;
import edu.ijse.fx.orm.dao.custom.PatientDAO;
import edu.ijse.fx.orm.dao.custom.SessionDAO;
import edu.ijse.fx.orm.dao.custom.TherapistDAO;
import edu.ijse.fx.orm.dto.SessionDTO;
import edu.ijse.fx.orm.entity.PatientEntity;
import edu.ijse.fx.orm.entity.SessionEntity;
import edu.ijse.fx.orm.entity.TherapistEntity;

import java.util.ArrayList;

public class SessionBOImpl implements SessionBO {

    private final SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SESSION);
    private final TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    private final PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean saveSession(SessionDTO sessionDTO) throws Exception {

        TherapistEntity therapistEntity = therapistDAO.search(sessionDTO.getTherapistId());
        PatientEntity patientEntity = patientDAO.search(sessionDTO.getPatientId());

        SessionEntity sessionEntity = new SessionEntity(
                sessionDTO.getSessionId(),
                therapistEntity,
                patientEntity,
                sessionDTO.getDate(),
                sessionDTO.getTime()
        );

        return sessionDAO.save(sessionEntity);
    }

    @Override
    public boolean updateSession(SessionDTO sessionDTO) throws Exception {

        TherapistEntity therapistEntity = therapistDAO.search(sessionDTO.getTherapistId());
        PatientEntity patientEntity = patientDAO.search(sessionDTO.getPatientId());

        SessionEntity sessionEntity = new SessionEntity(
                sessionDTO.getSessionId(),
                therapistEntity,
                patientEntity,
                sessionDTO.getDate(),
                sessionDTO.getTime()
        );

        return sessionDAO.update(sessionEntity);
    }

    @Override
    public boolean deleteSession(String id) throws Exception {
        return sessionDAO.delete(id);
    }

    @Override
    public SessionDTO searchSession(String id) throws Exception {

        SessionEntity sessionEntity = sessionDAO.search(id);

        if (sessionEntity != null) {
            return new SessionDTO(
                    sessionEntity.getSessionId(),
                    sessionEntity.getTherapistEntity() != null ? sessionEntity.getTherapistEntity().getTherapistId() : null,
                    sessionEntity.getPatientEntity() != null ? sessionEntity.getPatientEntity().getPatientId() : null,
                    sessionEntity.getDate(),
                    sessionEntity.getTime()
            );
        }

        return null;
    }

    @Override
    public ArrayList<SessionDTO> getAllSessions() throws Exception {

        ArrayList<SessionEntity> sessionEntities = sessionDAO.getAll();
        ArrayList<SessionDTO> sessionDTOS = new ArrayList<>();

        for (SessionEntity sessionEntity : sessionEntities) {
            sessionDTOS.add(new SessionDTO(
                    sessionEntity.getSessionId(),
                    sessionEntity.getTherapistEntity() != null ? sessionEntity.getTherapistEntity().getTherapistId() : null,
                    sessionEntity.getPatientEntity() != null ? sessionEntity.getPatientEntity().getPatientId() : null,
                    sessionEntity.getDate(),
                    sessionEntity.getTime()
            ));
        }

        return sessionDTOS;
    }
}