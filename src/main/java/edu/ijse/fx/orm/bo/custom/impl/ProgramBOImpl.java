package edu.ijse.fx.orm.bo.custom.impl;

import edu.ijse.fx.orm.dao.DAOFactory;
import edu.ijse.fx.orm.bo.custom.ProgramBO;
import edu.ijse.fx.orm.dao.custom.ProgramDAO;
import edu.ijse.fx.orm.dto.ProgramDTO;
import edu.ijse.fx.orm.entity.ProgramEntity;

import java.util.ArrayList;

public class ProgramBOImpl implements ProgramBO {

    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean saveProgram(ProgramDTO programDTO) throws Exception {

        ProgramEntity programEntity = new ProgramEntity(
                programDTO.getProgramId(),
                programDTO.getProgramName()
        );

        return programDAO.save(programEntity);
    }

    @Override
    public boolean updateProgram(ProgramDTO programDTO) throws Exception {

        ProgramEntity programEntity = new ProgramEntity(
                programDTO.getProgramId(),
                programDTO.getProgramName()
        );

        return programDAO.update(programEntity);
    }

    @Override
    public boolean deleteProgram(String id) throws Exception {
        return programDAO.delete(id);
    }

    @Override
    public ProgramDTO searchProgram(String id) throws Exception {

        ProgramEntity programEntity = programDAO.search(id);

        if (programEntity != null) {
            return new ProgramDTO(
                    programEntity.getProgramId(),
                    programEntity.getProgramName()
            );
        }

        return null;
    }

    @Override
    public ArrayList<ProgramDTO> getAllPrograms() throws Exception {

        ArrayList<ProgramEntity> programEntities = programDAO.getAll();
        ArrayList<ProgramDTO> programDTOS = new ArrayList<>();

        for (ProgramEntity programEntity : programEntities) {
            programDTOS.add(new ProgramDTO(
                    programEntity.getProgramId(),
                    programEntity.getProgramName()
            ));
        }

        return programDTOS;
    }
}