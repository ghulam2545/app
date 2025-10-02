package com.ghulam.reportservice.service;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerInfoReport extends AbstractBaseReportService {

    @Value("${app.template.statement:info_template.ftl}")
    private String infoTemplate;

    protected CustomerInfoReport(Configuration freemarkerConfiguration) {
        super(freemarkerConfiguration);
    }

    @Override
    public String getTemplate() {
        return infoTemplate;
    }

    @Override
    public Map<String, Object> getVariables(Map<String, String> map) {
        Map<String, Object> data = new HashMap<>();
        data.put("currDate", currentDate());
        data.put("customerId", map.get("customerId"));
        data.put("firstName", map.get("firstName"));
        data.put("lastName", map.get("lastName"));
        return data;
    }

    public String currentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return today.format(formatter);
    }
}
