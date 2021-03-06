package org.jboss.seam.reports.jasperreports;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import org.jboss.seam.reports.Report;
import org.jboss.seam.reports.ReportException;

public class JasperSeamReport implements Report<JasperSeamReportDataSource, JasperSeamReportInstance> {

    private JasperReport compiledReport;

    public JasperSeamReport(JasperReport compiledReport) {
        super();
        this.compiledReport = compiledReport;
    }

    public JasperReport getCompiledReport() {
        return compiledReport;
    }

    @Override
    public JasperSeamReportInstance fill(JasperSeamReportDataSource dataSource, Map<String, Object> parameters)
            throws ReportException {
        try {
            JRDataSource ds = dataSource.getDataSource();
            JasperPrint filledReport = JasperFillManager.fillReport(getCompiledReport(), parameters,ds);
            return new JasperSeamReportInstance(filledReport);
        } catch (JRException e) {
            throw new ReportException(e);
        }
    }

}
