package edu.ijse.fx.orm.bo.custom;

import edu.ijse.fx.orm.bo.SuperBO;
import edu.ijse.fx.orm.dto.PaymentDTO;

import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO paymentDTO) throws Exception;
    boolean updatePayment(PaymentDTO paymentDTO) throws Exception;
    boolean deletePayment(String id) throws Exception;
    PaymentDTO searchPayment(String id) throws Exception;
    ArrayList<PaymentDTO> getAllPayments() throws Exception;
}
