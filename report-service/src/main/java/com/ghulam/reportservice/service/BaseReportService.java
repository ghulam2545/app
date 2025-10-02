package com.ghulam.reportservice.service;

import java.util.Map;

public interface BaseReportService {
    byte[] getReport(Map<String, String> map);
    String getTemplate();
    Map<String, Object> getVariables(Map<String, String> map);
}
