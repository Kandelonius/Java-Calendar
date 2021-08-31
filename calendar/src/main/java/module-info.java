module com.kandel.calendar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kandel.calendar to javafx.fxml;
//    exports com.kandel.calendar;
    exports com.kandel;
    opens com.kandel to javafx.fxml;
}