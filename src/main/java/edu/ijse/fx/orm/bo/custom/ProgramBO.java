package edu.ijse.fx.orm.bo.custom;

import edu.ijse.fx.orm.bo.SuperBO;
import edu.ijse.fx.orm.dto.ProgramDTO;

import java.util.ArrayList;

public interface ProgramBO extends SuperBO {
    boolean saveProgram(ProgramDTO programDTO) throws Exception;
    boolean updateProgram(ProgramDTO programDTO) throws Exception;
    boolean deleteProgram(String id) throws Exception;
    ProgramDTO searchProgram(String id) throws Exception;
    ArrayList<ProgramDTO> getAllPrograms() throws Exception;
}
