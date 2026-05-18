module edu.ijse.fx.orm {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires mysql.connector.j;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;

    opens edu.ijse.fx.orm to javafx.fxml;
    opens edu.ijse.fx.orm.controller to javafx.fxml;
    opens edu.ijse.fx.orm.entity to org.hibernate.orm.core;

    exports edu.ijse.fx.orm;
}