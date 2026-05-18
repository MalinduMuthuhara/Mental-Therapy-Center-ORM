package edu.ijse.fx.orm.bo.custom.impl;

import edu.ijse.fx.orm.dao.DAOFactory;
import edu.ijse.fx.orm.bo.custom.PatientBO;
import edu.ijse.fx.orm.dao.custom.PatientDAO;
import edu.ijse.fx.orm.dto.PatientDTO;
import edu.ijse.fx.orm.entity.PatientEntity;

import java.util.ArrayList;

public class PatientBOImpl implements PatientBO {

    private final PatientDAO patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PATIENT);

    @Override
    public boolean savePatient(PatientDTO patientDTO) throws Exception {

        PatientEntity patientEntity = new PatientEntity(
                patientDTO.getPatientId(),
                patientDTO.getPatientName(),
                patientDTO.getGender(),
                patientDTO.getPatientPhone()
        );

        return patientDAO.save(patientEntity);
    }

    @Override
    public boolean updatePatient(PatientDTO patientDTO) throws Exception {

        PatientEntity patientEntity = new PatientEntity(
                patientDTO.getPatientId(),
                patientDTO.getPatientName(),
                patientDTO.getGender(),
                patientDTO.getPatientPhone()
        );

        return patientDAO.update(patientEntity);
    }

    @Override
    public boolean deletePatient(String id) throws Exception {
        return patientDAO.delete(id);
    }

    @Override
    public PatientDTO searchPatient(String id) throws Exception {

        PatientEntity patientEntity = patientDAO.search(id);

        if (patientEntity != null) {
            return new PatientDTO(
                    patientEntity.getPatientId(),
                    patientEntity.getPatientName(),
                    patientEntity.getGender(),
                    patientEntity.getPatientPhone()
            );
        }

        return null;
    }

    @Override
    public ArrayList<PatientDTO> getAllPatients() throws Exception {

        ArrayList<PatientEntity> patientEntities = patientDAO.getAll();
        ArrayList<PatientDTO> patientDTOS = new ArrayList<>();

        for (PatientEntity patientEntity : patientEntities) {
            patientDTOS.add(new PatientDTO(
                    patientEntity.getPatientId(),
                    patientEntity.getPatientName(),
                    patientEntity.getGender(),
                    patientEntity.getPatientPhone()
            ));
        }

        return patientDTOS;
    }
}