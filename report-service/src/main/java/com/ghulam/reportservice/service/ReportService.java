package com.ghulam.reportservice.service;

import com.ghulam.reportservice.kafka.ReportProducer;
import com.ghulam.reportservice.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class ReportService {

    private final CustomerInfoReport customerInfoReport;
    private final ReportProducer reportProducer;

    public ReportService(CustomerInfoReport customerInfoReport, ReportProducer reportProducer) {
        this.customerInfoReport = customerInfoReport;
        this.reportProducer = reportProducer;
    }

    public void saveReport(Map<String, String> map) {
        try {
            System.out.println("Waiting for 10 seconds...");
            Thread.sleep(10000); // 10000 milliseconds = 10 seconds
            System.out.println("Done waiting!");
        } catch (InterruptedException e) {
            log.error("InterruptedException", e);
        }

        byte[] report = customerInfoReport.getReport(map);

        String dirPath = "reports"; // can be absolute or relative
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = "report" + ".pdf";
        File file = new File(dir, fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(report);
            fos.flush();
            System.out.println("Report saved at: " + file.getAbsolutePath());

            reportProducer.sendAsync(map, Constants.REPORT_GENERATION_TOPIC);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
