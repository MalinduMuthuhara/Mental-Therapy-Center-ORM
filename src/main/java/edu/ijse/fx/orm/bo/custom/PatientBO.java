package edu.ijse.fx.orm.bo.custom;

import edu.ijse.fx.orm.bo.SuperBO;
import edu.ijse.fx.orm.dto.PatientDTO;

import java.util.ArrayList;

public interface PatientBO extends SuperBO {
    boolean savePatient(PatientDTO patientDTO) throws Exception;
    boolean updatePatient(PatientDTO patientDTO) throws Exception;
    boolean deletePatient(String id) throws Exception;
    PatientDTO searchPatient(String id) throws Exception;
    ArrayList<PatientDTO> getAllPatients() throws Exception;
}
