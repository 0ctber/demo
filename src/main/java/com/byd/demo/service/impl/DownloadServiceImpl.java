package com.byd.demo.service.impl;

import cn.afterturn.easypoi.csv.CsvExportUtil;
import cn.afterturn.easypoi.csv.entity.CsvExportParams;
import com.byd.demo.entity.ErrorMessage;
import com.byd.demo.service.DownloadService;
import com.byd.demo.utils.DownloadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class DownloadServiceImpl  implements DownloadService {


    @Override
    public ResponseEntity<byte[]> downloadErrorCsv(List<String> errorMessages) {
//        final String errorDir = CommonUtils.getBaseUrl(Constant.ERROR_BASE_DIR);
        File outFile;
        String fileName;
        try {
            fileName = "ERROR_" + System.currentTimeMillis() + ".csv";
            List<ErrorMessage> collect = errorMessages.stream().map(s -> {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorMessage(s);
                return errorMessage;
            }).collect(Collectors.toList());
            // 保存
            File saveFile = new File("/error");
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            outFile = new File(saveFile + "/" + fileName);
            FileOutputStream fos = new FileOutputStream(outFile);
            CsvExportParams csvExportParams = new CsvExportParams();
            csvExportParams.setEncoding(CsvExportParams.GBK);
            CsvExportUtil.exportCsv(csvExportParams, ErrorMessage.class, collect, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return DownloadUtils.downloadFile(outFile, fileName);
    }

    @Override
    public ResponseEntity<byte[]> downloadMessageCsv(List<String> errorMessages) {
        return null;
    }

    @Override
    public ResponseEntity<byte[]> downloadFileByUrl(String url) {
        return null;
    }

    public Map getFtpConfigRedis() {
        return null;
    }
}
