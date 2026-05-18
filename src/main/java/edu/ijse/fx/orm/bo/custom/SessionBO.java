package edu.ijse.fx.orm.bo.custom;

import edu.ijse.fx.orm.bo.SuperBO;
import edu.ijse.fx.orm.dto.SessionDTO;

import java.util.ArrayList;

public interface SessionBO extends SuperBO {
    boolean saveSession(SessionDTO sessionDTO) throws Exception;
    boolean updateSession(SessionDTO sessionDTO) throws Exception;
    boolean deleteSession(String id) throws Exception;
    SessionDTO searchSession(String id) throws Exception;
    ArrayList<SessionDTO> getAllSessions() throws Exception;
}
