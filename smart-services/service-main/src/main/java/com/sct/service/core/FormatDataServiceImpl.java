package com.sct.service.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sct.commons.file.FileConstants;
import com.sct.commons.file.excel.writeexcel.WriteExcelUtil;
import com.sct.commons.file.fileutil.ZipFileUtil;
import com.sct.commons.file.location.FileLocation;
import com.sct.commons.file.location.FileLocationManager;
import com.sct.commons.utils.id.IDGenerator;
import com.sct.service.core.api.service.file.ServiceFileLocationApi;
import com.sct.service.database.condition.ExportExcelCondition;
import com.sct.service.database.condition.ExportExcelConditionUtil;
import com.sct.service.database.condition.FieldMapping;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 对象数据,各种结果数据格式化的方法
 */
@Service
public class FormatDataServiceImpl extends AbstractFormatDataService {
    protected static TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<List<Map<String, Object>>>() {
    };
    private static Logger logger = LoggerFactory.getLogger(FormatDataServiceImpl.class);

    @Autowired
    private ServiceFileLocationApi serviceFileLocationApi;

    @Autowired
    private FormatDataServiceImpl formatDataService;

    /**
     * 将查询出来的对象list数据转换为二维数组
     *
     * @param datas
     * @param exportExcelCondition
     * @param <T>
     * @return
     */
    public <T> List<List<String>> format2TwoDimensionalArray(List<T> datas, ExportExcelCondition exportExcelCondition) {
        return format2TwoDimensionalArray(datas, exportExcelCondition, datas == null ? 0 : datas.size());
    }

    public <T> List<List<String>> format2TwoDimensionalArray(List<T> datas, ExportExcelCondition exportExcelCondition, int group) {
        if (CollectionUtils.isEmpty(datas) || group == 0) {
            return null;
        }
        boolean hasFilterFieldMapping = ExportExcelConditionUtil.filterFieldMapping(exportExcelCondition);
        Assert.isTrue(hasFilterFieldMapping, "ExportExcelCondition Must have field mapping");
        int size = datas.size();
        if (size < group) {
            group = size;
        }
        List<List<T>> partitionDatas = ListUtils.partition(datas, group);
        List<List<String>> result = new ArrayList<>();
        for (List<T> data : partitionDatas) {
            List<Map<String, Object>> list = format2TwoDimensionalArrayInternal(data);
            if (list == null) {
                continue;
            }
            for (Map<String, Object> map : list) {
                List<String> line = format2Line(map, exportExcelCondition.getMappings());
                if (line != null) {
                    result.add(line);
                }
            }
        }
        return result;
    }

    public <E> FileLocation export2FileLocation(ExportExcelCondition exportExcelCondition, Supplier<List<E>> supplier) {
        List<E> datas = supplier.get();
        List<List<String>> xdatas = format2TwoDimensionalArray(datas, exportExcelCondition, 1000);
        return write2Excel(exportExcelCondition, xdatas);
    }

    /**
     * 将二维数据写入excel文件中,并返回文件的定位信息
     *
     * @param exportExcelCondition
     * @param datas
     * @return
     */
    public FileLocation write2Excel(ExportExcelCondition exportExcelCondition, List<List<String>> datas) {
        String dir = serviceFileLocationApi.makeSureLocationTemporaryDailyDir();
//        String dir = "E:\\01IntelliJ\\04GitHubIdeaSelf\\smart-city\\logs";
        String uuid = IDGenerator.UUID.generate();
        String fileName = WriteExcelUtil.checkExcelFileName(uuid);
        String sheetName = StringUtils.isEmpty(exportExcelCondition.getSheetName()) ? "Sheet1" : exportExcelCondition.getSheetName();
        List<String> headInfo = new ArrayList<>();
        for (FieldMapping fieldMapping : exportExcelCondition.getMappings()) {
            headInfo.add(fieldMapping.getFieldTitle());
        }
        String excelFileName = null;
        try {
            String excelDir = null;
            if (exportExcelCondition.isZip()) {
                String time2idName = serviceFileLocationApi.currentDayDir();
                excelDir = dir + File.separator + "zip_sub" + File.separator + time2idName;
                fileName = WriteExcelUtil.checkExcelFileName(sheetName);
                serviceFileLocationApi.makeSureDirAlreadyExists(excelDir);
            } else {
                excelDir = dir;
            }
            excelFileName = WriteExcelUtil.writeSimpleExcel(excelDir, fileName, sheetName, headInfo, null, datas, exportExcelCondition.isMultiSheet());
            if (excelFileName != null) {
                if (exportExcelCondition.isZip()) {
                    String fileUuid = uuid + FileConstants.FILE_SUFFIX_ZIP;
                    List<File> files = new ArrayList<>();
                    files.add(new File(excelDir + File.separator + excelFileName));
                    File zipFile = ZipFileUtil.compressFiles2Zip(files, dir + File.separator + fileUuid);
                    serviceFileLocationApi.delete(excelDir);
                    FileLocation fileLocation = FileLocationManager.getInstance().parserFileLocalion(zipFile);
                    return fileLocation;
                } else {
                    File excelFile = new File(excelDir + File.separator + excelFileName);
                    FileLocation fileLocation = FileLocationManager.getInstance().parserFileLocalion(excelFile);
                    return fileLocation;
                }
            }
        } catch (IOException e) {
            String error = String.format("writeSimpleExcel(%s/%s) exception:%s", dir, fileName, e.getMessage());
            logger.error(error, e);
            throw new RuntimeException(error);
        }
        String error = String.format("writeSimpleExcel(%s/%s) create file is null", dir, fileName);
        logger.error(error);
        return null;
    }

}
