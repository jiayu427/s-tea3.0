package com.github.lmm.report;

import com.github.lmm.result.Result;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午3:47
 * To change this template use File | Settings | File Templates.
 */
public interface IReport {
    public void loadResult(Result result);
    public void formatHTMLReportToFile(String dirctory);
    public void formatXMLReportToFile(String dirctory);
    public void formatTXTReportToFile(String dirctory);
}
