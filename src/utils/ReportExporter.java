package utils;

import models.PaymentModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExporter {

    public static void exportPayments(List<PaymentModel> payments, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("تاريخ الدفع,المبلغ,طريقة الدفع,الحالة\n");

            for (PaymentModel payment : payments) {
                writer.write(String.format("%s,%.2f,%s,%s\n",
                        payment.paymentDateProperty().get(),
                        payment.amountProperty().get(),
                        payment.methodProperty().get(),
                        payment.statusProperty().get()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}