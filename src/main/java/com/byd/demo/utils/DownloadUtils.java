package com.byd.demo.utils;

import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class DownloadUtils {

    public static <T> ResponseEntity<byte[]> downLoadCsv(List<T> data, Class<T> clazz, String fileName) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        HttpHeaders headers = new HttpHeaders();
        String valueUTF8=fileName;
        try {
            valueUTF8= URLEncoder.encode(fileName, "UTF-8");
        }catch (Exception e ){
            log.error(fileName+"转码失败");
        }
        headers.setContentDispositionFormData("attachment", valueUTF8);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        CsvExportParams params = new CsvExportParams();
        params.setEncoding(CsvExportParams.GBK);
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        CsvExportUtil.exportCsv(params, clazz, data, outByteStream);
        outByteStream.flush();
        outByteStream.close();
        log.info("exportCsv 写入耗时: {} 毫秒", Duration.between(start, LocalDateTime.now()).toMillis());
        return new ResponseEntity<>(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 方法描述：下载excel方法<br>
     *
     * @param fileName : 名称
     * @param workbook : 工作表
     * 创建日期： 2018/9/11 <br>
     * 版本： V1.0<br>
     */
    public static ResponseEntity<byte[]> downLoadExcel1(String fileName, Workbook workbook) {
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            //HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

            workbook.write(outByteStream);
        } catch (IOException e) {
            log.info("", e);
            e.printStackTrace();
        }
        log.info("downLoadExcel结束...");
        return new ResponseEntity<>(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }

    public static ResponseEntity<byte[]> downLoadExcel(String fileName, Workbook workbook)  {
        LocalDateTime start = LocalDateTime.now();
        HttpHeaders headers = new HttpHeaders();
        String valueUTF8=fileName;
        try {
            valueUTF8= URLEncoder.encode(fileName, "UTF-8");
        }catch (Exception e ){
            log.error(fileName+"转码失败");
        }
        headers.setContentDispositionFormData("attachment", valueUTF8);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        try {
            workbook.write(outByteStream);
        } catch (IOException e) {
            log.info("", e);
            e.printStackTrace();
        }
        log.info("downLoadExcel Workbook写入ByteArrayOutputStream耗时: {} 毫秒", Duration.between(LocalDateTime.now(), start).toMillis());
        log.info("downLoadExcel结束...");
        return new ResponseEntity<>(outByteStream.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 方法描述：下载方法<br>
     *
     * @param file : 文件
     * @return 输出浏览器文件流
     * 创建日期： 2018/9/11 <br>
     * 版本： V1.0<br>
     */
    public static ResponseEntity<byte[]> downloadFile(File file, String host, String username, String password) {
        log.info("下载方法 file exists check {}", file.exists());
        log.info("下载方法 file length {}", file.length());
        if (!file.exists() || file.length() == 0) {
            log.info("file is not exists..."+file.getPath());
            log.info("host:{},username:{},password:{}",host,username,password);

//    		SftpUtils sftp = null;
//	        // 本地存放地址
//	        String localPath = file.getParent()+ "/";;
//	        // Sftp下载路径
//	        String sftpPath =file.getParent()+ "/";;
//	        try {
//	            sftp = new SftpUtils(host,username,password);
//	            sftp.connect();
//	            // 下载
//	            sftp.downloadFile(sftpPath,file.getName(), localPath, file.getName());
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        } finally {
//	            sftp.disconnect();
//	        }

        }
        InputStream fileInputStream;
        HttpHeaders headers;
        ByteArrayOutputStream bos;
        try {
            fileInputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
            bos = new ByteArrayOutputStream();
            int data = -1;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
            bis.close();
            bos.close();
            headers = new HttpHeaders();
            headers.setContentLength(bos.toByteArray().length);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //headers.set(Constant.FILE_URL, URLEncoder.encode(file.toString(), "UTF-8"));
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 方法描述：下载方法<br>
     *
     * @param inputStream : 文件
     * @param fileName    : 文件名
     * @return 输出浏览器文件流
     * 创建日期： 2018/9/11 <br>
     * 版本： V1.0<br>
     */
    public static ResponseEntity<byte[]> downloadFile(InputStream inputStream, String fileName) {
        ByteArrayOutputStream bos;
        HttpHeaders headers;
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bos = new ByteArrayOutputStream();
            int data = -1;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
            bis.close();
            bos.close();
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);
    }

    /**
     * 方法描述：下载方法<br>
     *
     * @param file : 文件
     * @param fileName    : 文件名
     * @return 输出浏览器文件流
     * 创建日期： 2018/9/11 <br>
     * 版本： V1.0<br>
     */
    public static ResponseEntity<byte[]> downloadFile(File file, String fileName) {
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            return downloadFile(fileInputStream, fileName);
        } catch (Exception e) {
            log.error("", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static <T> ResponseEntity<byte[]> createExcelAndDownloadFile(List<T> data, Class<T> clazz, String fileName) {
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams(), clazz, data);
        return downLoadExcel(fileName, sheets);
    }

    /**
     * 描述: easypoi导出大表格
     * @since: 2021/12/8 15:40
     * @param data：
     * @param clazz：
     * @param fileName :
     * @return : org.springframework.http.ResponseEntity<byte[]>
     */
    public static <T> ResponseEntity<byte[]> createBigExcelAndDownloadFile(List<T> data, Class<T> clazz, String fileName) {
        Workbook sheets = null;
        List<T> tempList = new ArrayList<>();
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < data.size(); i++) {
            tempList.add(data.get(i));
            if(tempList.size() == 5000 || i == data.size() - 1){
                sheets = ExcelExportUtil.exportBigExcel(new ExportParams(), clazz, data);
                tempList.clear();
            }
        }
        log.info("createExcelAndDownloadBigFile 构建Workbook耗时: {} 毫秒", Duration.between(LocalDateTime.now(), start).toMillis());
        ExcelExportUtil.closeExportBigExcel();
        return downLoadExcel(fileName, sheets);
    }


    public static <T> ResponseEntity<byte[]> createExcelAndDownloadFile1(List<T> data, Class<T> clazz, String fileName) {
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams(), clazz, data);
        return downLoadExcel1(fileName, sheets);
    }

    public static <T> ResponseEntity<byte[]> create07ExcelAndDownloadFile(List<T> data, Class<T> clazz, String fileName) {
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, clazz, data);
        return downLoadExcel(fileName, sheets);
    }

    public static <T> ResponseEntity<byte[]> create07ExcelAndDownloadFile1(List<T> data, Class<T> clazz, String fileName) {
        ExportParams exportParams = new ExportParams();
        exportParams.setType(ExcelType.XSSF);
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, clazz, data);
        return downLoadExcel1(fileName, sheets);
    }
    public static <T> ResponseEntity<byte[]> createExcelAndDownloadMultiSheet(List<Map<String, Object>> data, String fileName) {
        Workbook workbook = ExcelExportUtil.exportExcel(data,ExcelType.XSSF);
        return downLoadExcel1(fileName, workbook);
    }

}
