package edu.ijse.fx.orm.bo.custom;

import edu.ijse.fx.orm.bo.SuperBO;
import edu.ijse.fx.orm.dto.TherapistDTO;

import java.util.ArrayList;

public interface TherapistBO extends SuperBO {
    boolean saveTherapist(TherapistDTO therapistDTO) throws Exception ;
    boolean updateTherapist(TherapistDTO therapistDTO) throws Exception ;
    boolean deleteTherapist(String id) throws Exception ;
    TherapistDTO searchTherapist(String id) throws Exception ;
    ArrayList<TherapistDTO> getAllTherapists() throws Exception ;
}
