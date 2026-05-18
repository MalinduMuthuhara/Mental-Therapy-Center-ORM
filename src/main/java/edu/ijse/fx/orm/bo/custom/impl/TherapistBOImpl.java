package edu.ijse.fx.orm.bo.custom.impl;

import edu.ijse.fx.orm.dao.DAOFactory;
import edu.ijse.fx.orm.bo.custom.TherapistBO;
import edu.ijse.fx.orm.dao.custom.ProgramDAO;
import edu.ijse.fx.orm.dao.custom.TherapistDAO;
import edu.ijse.fx.orm.dto.TherapistDTO;
import edu.ijse.fx.orm.entity.ProgramEntity;
import edu.ijse.fx.orm.entity.TherapistEntity;

import java.util.ArrayList;

public class TherapistBOImpl implements TherapistBO {

    private final TherapistDAO therapistDAO = (TherapistDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.THERAPIST);
    private final ProgramDAO programDAO = (ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public boolean saveTherapist(TherapistDTO therapistDTO) throws Exception {

        ProgramEntity programEntity = programDAO.search(therapistDTO.getProgramId());

        TherapistEntity therapistEntity = new TherapistEntity(
                therapistDTO.getTherapistId(),
                programEntity,
                therapistDTO.getTherapistName(),
                therapistDTO.getTherapistPhone()
        );

        return therapistDAO.save(therapistEntity);
    }

    @Override
    public boolean updateTherapist(TherapistDTO therapistDTO) throws Exception {

        ProgramEntity programEntity = programDAO.search(therapistDTO.getProgramId());

        TherapistEntity therapistEntity = new TherapistEntity(
                therapistDTO.getTherapistId(),
                programEntity,
                therapistDTO.getTherapistName(),
                therapistDTO.getTherapistPhone()
        );

        return therapistDAO.update(therapistEntity);
    }

    @Override
    public boolean deleteTherapist(String id) throws Exception {
        return therapistDAO.delete(id);
    }

    @Override
    public TherapistDTO searchTherapist(String id) throws Exception {

        TherapistEntity therapistEntity = therapistDAO.search(id);

        if (therapistEntity != null) {
            return new TherapistDTO(
                    therapistEntity.getTherapistId(),
                    therapistEntity.getProgramEntity() != null ? therapistEntity.getProgramEntity().getProgramId() : null,
                    therapistEntity.getTherapistName(),
                    therapistEntity.getTherapistPhone()
            );
        }

        return null;
    }

    @Override
    public ArrayList<TherapistDTO> getAllTherapists() throws Exception {

        ArrayList<TherapistEntity> therapistEntities = therapistDAO.getAll();
        ArrayList<TherapistDTO> therapistDTOS = new ArrayList<>();

        for (TherapistEntity therapistEntity : therapistEntities) {
            therapistDTOS.add(new TherapistDTO(
                    therapistEntity.getTherapistId(),
                    therapistEntity.getProgramEntity() != null ? therapistEntity.getProgramEntity().getProgramId() : null,
                    therapistEntity.getTherapistName(),
                    therapistEntity.getTherapistPhone()
            ));
        }

        return therapistDTOS;
    }
}