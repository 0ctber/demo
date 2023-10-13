package com.byd.demo.utils;

import com.alibaba.excel.EasyExcel;
import com.byd.demo.config.EasyExcelListener.EasyExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class EasyExcelUtils {

    /**
     * 根据自定义对象读取excel
     * @param <T>   实体类泛型
     * @param excel 文件流
     * @param clazz 实体类
     * @param sheetNum 读第几个sheet，从0开始
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readerExcel(MultipartFile excel, Integer sheetNum, Class<T> clazz) {
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            //excel导入监听器
            EasyExcelListener excelListener = new EasyExcelListener();
            EasyExcel.read(fileStream, clazz, excelListener)
                    .autoCloseStream(true)
                    .autoTrim(true)
                    .sheet(sheetNum)
                    .doRead();
            return excelListener.getSuccessDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
//            throw new RRException("导入失败, 请检查导入数据的准确性");
            throw new RuntimeException();
        }
    }

    /**
     * 根据自定义对象读取excel并指定读取起始行
     * @param <T>       实体类泛型
     * @param excel     文件流
     * @param rowNum    从第几行开始读，从0开始，解决多级复杂表头数据导入问题
     * @param clazz     实体类
     * @return 导入的数据集合
     */
    public static <T> ArrayList<T> readExcelWithRowNum(MultipartFile excel, Integer sheetNum,Integer rowNum, Class<T> clazz) throws Exception {
        if (excel==null||excel.isEmpty()) {
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            EasyExcelListener<T> excelListener = new EasyExcelListener<>();
            EasyExcel.read(fileStream, clazz, excelListener)
                    .autoTrim(true)
                    .autoCloseStream(true)
                    .sheet(sheetNum)
                    .headRowNumber(rowNum)
                    .doRead();
            return excelListener.getSuccessDatas();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
//            throw new RRException("导入失败, 请检查导入数据的准确性");
            throw new RuntimeException();
        }
    }

    /**
     * 根据自定义对象读取excel并指定读取起始行  复杂导入
     * @param <T>       实体类泛型
     * @param excel     文件流
     * @param rowNum    从第几行开始读，从0开始，解决多级复杂表头数据导入问题
     * @param clazz     实体类
     * @return 导入的数据集合
     */
    public static <T> List<LinkedHashMap> readComplexExcel(MultipartFile excel, Integer sheetNum, Integer rowNum, Class<T> clazz) throws Exception {
        if (excel==null||excel.isEmpty()) {
//            throw BydException.buildException(ErrorEnum_User.PARAM_EMPTY,"参数为空");
        }
        String fileName = excel.getOriginalFilename();
        log.info("Excel文件解析：文件名 = " + fileName);
        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
//            throw BydException.buildException(ErrorEnum_User.PARAM_EMPTY,"邮件格式错误");
        }
        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
            EasyExcelListener<T> excelListener = new EasyExcelListener<>();
            EasyExcel.read(fileStream, clazz, excelListener)
                    .autoTrim(true)
                    .autoCloseStream(true)
                    .sheet(sheetNum)
                    .headRowNumber(rowNum)
                    .doRead();
            return excelListener.getLinkedHashMap();
        } catch (Exception e) {
            log.error("导入失败, 请检查导入数据的准确性", e);
//            throw new RRException("导入失败, 请检查导入数据的准确性");
            throw new RuntimeException();
        }
    }

//    /**
//     * 根据自定义对象读取excel并指定sheet列表读取
//     * sheet列表数据格式必须一样
//     * @param excel     excel文件
//     * @param sheetNums sheet集合
//     * @param clazz     实体类
//     * @param <T>       实体类泛型
//     * @return 导入的数据集合
//     */
//    public static <T> ArrayList<T> readExcelWithSheets(MultipartFile excel, List<Integer> sheetNums, Class<T> clazz) throws Exception {
//
//        String fileName = excel.getOriginalFilename();
//        log.info("Excel文件解析：文件名 = " + fileName);
//        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
//        }
//        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
//            GeneraExcelListener<T> excelListener = new GeneraExcelListener<>();
//            ExcelReader excelReader = EasyExcel.read(fileStream,clazz,excelListener).autoTrim(true).build();
//            List<ReadSheet> readSheets = new ArrayList<>();
//            for (Integer sheetNum : sheetNums) {
//                ReadSheet readSheet = EasyExcel.readSheet(sheetNum).build();
//                readSheets.add(readSheet);
//            }
//            excelReader.read(readSheets);
//            excelReader.finish();//必须要有，因为会自动关闭流
//            return excelListener.getSuccessDatas();
//        } catch (Exception e) {
//            log.error("导入失败, 请检查导入数据的准确性", e);
//            throw new RuntimeException("导入失败, 请检查导入数据的准确性");
//        }
//    }
//
//    /**
//     * 根据自定义对象读取excel并自定义sheets读取
//     * sheet列表数据格式必须一样
//     * @param excel excel文件
//     * @param sheets 自定义sheet
//     * @param clazz 实体类
//     * @param <T>   实体类泛型
//     * @return 导入的数据集合
//     */
//    public static <T> ArrayList<T> readExcelWithCustomSheet(MultipartFile excel, List<ReadSheet> sheets, Class<T> clazz) throws Exception {
//        if (excel==null||excel.isEmpty()) {
//            throw BydException.buildException(BusinessErrorEnum.PARAM_EMPTY,"参数为空");
//        }
//        String fileName = excel.getOriginalFilename();
//        log.info("Excel文件解析：文件名 = " + fileName);
//        if (fileName == null || (!fileName.toLowerCase().endsWith(".xls") && !fileName.toLowerCase().endsWith(".xlsx"))) {
//            throw BydException.buildException(BusinessErrorEnum.PARAM_EMPTY,"邮件格式错误");
//        }
//        if (sheets == null){
//            throw new Exception("请指定sheet");
//        }
//        try(InputStream fileStream = new BufferedInputStream(excel.getInputStream())) {
//            GeneraExcelListener<T> excelListener = new GeneraExcelListener<>();
//            ExcelReader excelReader = EasyExcel.read(fileStream,clazz,excelListener).autoTrim(true).build();
//            excelReader.read(sheets);
//            excelReader.finish();
//            return excelListener.getSuccessDatas();
//        } catch (Exception e) {
//            log.error("导入失败, 请检查导入数据的准确性", e);
//            throw new RuntimeException("导入失败, 请检查导入数据的准确性");
//        }
//    }
//
//
//
//    /**
//     * @description excel导出，没有模板的
//     *
//     * @params [response, list, fileNames, sheetName]
//     * @return void
//     * @author xiaoyinchuan
//     * @date 2020/11/27 0027 11:42
//     **/
//    public static<T> void downloadFailedUsingJson(List<?> list, String fileNames, String sheetName) throws IOException, IOException {
//        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//        try {
//            String userAgent = request.getHeader("User-Agent");
//
//            // 针对IE或者以IE为内核的浏览器：
//            fileNames = URLEncoder.encode(fileNames, "UTF-8");
//
//            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileNames + ".xlsx"));
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("Cache-Control", "no-cache");
//            response.setCharacterEncoding("utf-8");
//        } catch (UnsupportedEncodingException e1) {
//            log.error("导出excel未知编码异常", e1);
//        }
//        try {
//            EasyExcel.write(response.getOutputStream(), list.get(0).getClass())
//                    .autoCloseStream(Boolean.TRUE)
//                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
////                    ///合并单元格
////                    .registerWriteHandler(new ExcelFillCellLineMergeHandler(row,cells))
//                    //设置导出表头
//                    .registerWriteHandler(getCellCenterStyle())
//                    .sheet(sheetName)
//                    .doWrite(list);
//        } catch (IOException e) {
//            log.error("导出excel文件异常", e);
//        }
//    }
//
//    /**
//     * @description 导出到本地，有模板的
//     *
//     * @params [list, path, fileNames, sheetName]
//     * @return void
//     * @author xiaoyinchuan
//     * @date 2020/11/27 0027 11:42
//     **/
//    public static void downloadLocal(List<?> list, String path, String fileNames, String sheetName,int[] cells,int row) {
//        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//
//        if (CollectionUtils.isEmpty(list)) {
//            throw new RuntimeException();
//        }
//        if (StrUtil.isEmpty(fileNames)) {
//            fileNames = new Date().toString();
//        }
//        if (StrUtil.isEmpty(sheetName)) {
//            sheetName = "Sheet1";
//        }
//        try {
//            String userAgent = request.getHeader("User-Agent");
//            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
//                fileNames = URLEncoder.encode(fileNames, "UTF-8");
//            } else {
//                fileNames = new String(fileNames.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
//            }
//            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileNames + ".xlsx"));
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", -1);
//        } catch (UnsupportedEncodingException e1) {
//            log.error("导出excel未知编码异常", e1);
//        }
//        try{
//            EasyExcel.write(response.getOutputStream(), list.get(0).getClass())
//                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
//                    .withTemplate(path)
//                    .sheet(0, sheetName)
//                    .doWrite(list);
//        }catch (IOException e){
//            log.error("导出excel文件异常", e);
//        }
//
//    }
//
//    /**
//     * 设置导出头格式
//     * @return
//     */
//    public static HorizontalCellStyleStrategy getCellCenterStyle(){
//        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
//        //设置背景颜色
//        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
//        //设置头字体
//        WriteFont headWriteFont = new WriteFont();
//        headWriteFont.setFontHeightInPoints((short)13);
//        headWriteFont.setBold(true);
//        headWriteCellStyle.setWriteFont(headWriteFont);
//        //设置头居中
//        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//
//        //内容策略
//        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
//        //设置 水平居中
//        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
//        //设置 垂直居中
//        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
//        return horizontalCellStyleStrategy;
//    }
//
//    /**
//     * 下载excel模板
//     * @param filePath 文件地址，加上文件名，获取resource下面的文件
//     * @param fileName 文件名
//     */
//    public static void downLoadExcel(String filePath,String fileName) throws IOException {
//
//        FileInputStream  inputStream = null;
//        OutputStream outputStream = null;
//        HttpServletResponse response = SessionUtil.getResponse();
//        try {
//
//            response.setHeader("Content-disposition","attachment;fileName="+
//                    URLEncoder.encode(fileName,"utf-8").replaceAll("\\+","%20"));
//            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//            response.setCharacterEncoding("utf-8");
//            String file = FileUtil.class.getResource("/template/"+fileName).getPath();
//            file = URLDecoder.decode(file,"utf-8");
//            inputStream = new FileInputStream(file);
//            outputStream = response.getOutputStream();
//            byte[] buff = new byte[1000];
//            int length;
//            while ((length=inputStream.read(buff))>0){
//                outputStream.write(buff,0,length);
//            }
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw BydException.buildException(BusinessErrorEnum.PARAM_EMPTY,"下载错误");
//        }finally {
//            outputStream.close();
//        }
//    }

}
