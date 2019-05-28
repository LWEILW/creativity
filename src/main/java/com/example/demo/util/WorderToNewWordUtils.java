package com.example.demo.util;


import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * created by LW  2019/3/5
 */
public class WorderToNewWordUtils {
    /**
     * 导出word
     * <p>第一步生成替换后的word文件，只支持docx</p>
     * <p>第二步下载生成的文件</p>
     * <p>第三步删除生成的临时文件</p>
     * 模版变量中变量格式：{{foo}}
     *
     * @param templatePath word模板地址
     * @param temDir       生成临时文件存放地址
     * @param fileName     文件名
     * @param params       替换的参数
     * @param request      HttpServletRequest
     * @param response     HttpServletResponse
     */
    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {

        Assert.notNull(templatePath, "模板路径不能为空");
        Assert.notNull(temDir, "临时文件路径不能为空");
        Assert.notNull(fileName, "导出文件名不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
        if (!temDir.endsWith("/")) {
            temDir = temDir + File.separator;
        }
        File dir = new File(temDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            }
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);

            // 获取word文档信息
            List<XWPFTable> tableList = doc.getTables();
            // 获取第一张表格
            XWPFTable table = tableList.get(0);



            String tmpPath = temDir + fileName;
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            delAllFile(temDir);//这一步看具体需求，要不要删
        }

    }


    // 添加行,复制样式
    private static void copy(XWPFTable table, XWPFTableRow sourceRow, int rowIndex) {
        //在表格指定位置新增一行
        XWPFTableRow targetRow = table.insertNewTableRow(rowIndex);
        //复制行属性
        targetRow.getCtRow().setTrPr(sourceRow.getCtRow().getTrPr());
        List<XWPFTableCell> cellList = sourceRow.getTableCells();
        if (null == cellList) {
            return;
        }
        //复制列及其属性和内容
        XWPFTableCell targetCell = null;
        for (XWPFTableCell sourceCell : cellList) {
            targetCell = targetRow.addNewTableCell();
            //列属性
            targetCell.getCTTc().setTcPr(sourceCell.getCTTc().getTcPr());
            //段落属性
            if (sourceCell.getParagraphs() != null && sourceCell.getParagraphs().size() > 0) {
                targetCell.getParagraphs().get(0).getCTP().setPPr(sourceCell.getParagraphs().get(0).getCTP().getPPr());
                if (sourceCell.getParagraphs().get(0).getRuns() != null && sourceCell.getParagraphs().get(0).getRuns().size() > 0) {
                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                    cellR.setText(sourceCell.getText());
                    cellR.setBold(sourceCell.getParagraphs().get(0).getRuns().get(0).isBold());
                } else {
                    targetCell.setText(sourceCell.getText());
                }
            } else {
                targetCell.setText(sourceCell.getText());
            }
        }
    }

}
