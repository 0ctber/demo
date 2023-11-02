package com.byd.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DownloadService {
    /**
     * 描述：下载错误文件的方法 <br>
     * @param  errorList： 错误信息列表
     *
     * @return csv文件
     */
    ResponseEntity<byte[]> downloadErrorCsv(List<String> errorList);


    /**
     * 描述：下载错误文件的方法 <br>
     * @param  errorList： 错误信息列表
     *
     * @return csv文件
     */
    ResponseEntity<byte[]> downloadMessageCsv(List<String> errorList);


    /**
     * 描述：根据路径下载文件 <br>
     * @param url ： 文件路径
     *
     * @return 文件
     */
    ResponseEntity<byte[]> downloadFileByUrl(String url);
}
