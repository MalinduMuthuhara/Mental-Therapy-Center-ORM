package edu.ijse.fx.orm.bo.custom.impl;

import edu.ijse.fx.orm.dao.DAOFactory;
import edu.ijse.fx.orm.bo.custom.PaymentBO;
import edu.ijse.fx.orm.dao.custom.PaymentDAO;
import edu.ijse.fx.orm.dao.custom.SessionDAO;
import edu.ijse.fx.orm.dto.PaymentDTO;
import edu.ijse.fx.orm.entity.PaymentEntity;
import edu.ijse.fx.orm.entity.SessionEntity;

import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {

    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private final SessionDAO sessionDAO = (SessionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SESSION);

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {

        SessionEntity sessionEntity = sessionDAO.search(paymentDTO.getSessionId());

        PaymentEntity paymentEntity = new PaymentEntity(
                paymentDTO.getPaymentId(),
                sessionEntity,
                paymentDTO.getDate()
        );

        return paymentDAO.save(paymentEntity);
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {

        SessionEntity sessionEntity = sessionDAO.search(paymentDTO.getSessionId());

        PaymentEntity paymentEntity = new PaymentEntity(
                paymentDTO.getPaymentId(),
                sessionEntity,
                paymentDTO.getDate()
        );

        return paymentDAO.update(paymentEntity);
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public PaymentDTO searchPayment(String id) throws Exception {

        PaymentEntity paymentEntity = paymentDAO.search(id);

        if (paymentEntity != null) {
            return new PaymentDTO(
                    paymentEntity.getPaymentId(),
                    paymentEntity.getSessionEntity() != null ? paymentEntity.getSessionEntity().getSessionId() : null,
                    paymentEntity.getDate()
            );
        }

        return null;
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {

        ArrayList<PaymentEntity> paymentEntities = paymentDAO.getAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();

        for (PaymentEntity paymentEntity : paymentEntities) {
            paymentDTOS.add(new PaymentDTO(
                    paymentEntity.getPaymentId(),
                    paymentEntity.getSessionEntity() != null ? paymentEntity.getSessionEntity().getSessionId() : null,
                    paymentEntity.getDate()
            ));
        }

        return paymentDTOS;
    }
}