package com.sct.webtools.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel("Excel文件内容描述")
public class ExcelContext implements Serializable {
    private static final long serialVersionUID = 8922904426219606765L;
    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件Sheet")
    private List<SheetContext> sheets = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SheetContext> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetContext> sheets) {
        this.sheets = sheets;
    }

    public static class SheetContext implements Serializable {
        private static final long serialVersionUID = 1235793168400471076L;
        @ApiModelProperty(value = "Sheet名称")
        @JsonProperty(value = "name")
        private String name;

        @ApiModelProperty(value = "是否合并列标题")
        @JsonProperty(value = "merged_header", defaultValue = "0")
        private Integer mergedHeader;

        @ApiModelProperty(value = "列标题")
        @JsonProperty(value = "header")
        private List<Object> header;

        @ApiModelProperty(value = "内容")
        @JsonProperty(value = "content")
        private List<List<String>> content;

        public SheetContext() {
            this.mergedHeader = 0;
        }

        public SheetContext(String name) {
            this();
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMergedHeader() {
            return mergedHeader;
        }

        public void setMergedHeader(Integer mergedHeader) {
            this.mergedHeader = mergedHeader;
        }

        public List<Object> getHeader() {
            return header;
        }

        public void setHeader(List<Object> header) {
            this.header = header;
        }

        public List<List<String>> getContent() {
            return content;
        }

        public void setContent(List<List<String>> content) {
            this.content = content;
        }
    }
}
