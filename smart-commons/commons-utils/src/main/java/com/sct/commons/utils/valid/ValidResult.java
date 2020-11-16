package com.sct.commons.utils.valid;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 验证过程中的结果,保存的是报错的错误信息
 */
public class ValidResult {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //校验是否正常
    private boolean isNormal = true;
    //存放校验错误信息:程序中的错误信息
    private StringBuffer programAbnormalBufferInfo = null;
    //存放校验错误信息:对外用户可以展示的错误信息
    private StringBuffer zhAbnormalZhBufferInfo = null;

    public boolean isNormal() {
        return isNormal;
    }

    /**
     * 所有的提示语类型都添加该错误信息,在默认的位置
     *
     * @param abnormalInfo
     */
    public void appendAllTipType(String abnormalInfo) {
        appendAbnormalTipType(abnormalInfo, ValidTipType.program);
        appendAbnormalTipType(abnormalInfo, ValidTipType.zh);
    }

    /**
     * 所有的提示语类型都在最前面的位置上添加错误信息
     *
     * @param abnormalInfo
     */
    public void appendAllTipTypeByPosition(String abnormalInfo) {
        appendAllTipTypeByPosition(abnormalInfo, ValidAppendPositionType.pre);
    }

    /**
     * 所有的提示语类型都在指定位置上添加错误信息
     *
     * @param abnormalInfo
     * @param positionType
     */
    public void appendAllTipTypeByPosition(String abnormalInfo, ValidAppendPositionType positionType) {
        appendAbnormal(abnormalInfo, ValidTipType.program, positionType);
        appendAbnormal(abnormalInfo, ValidTipType.zh, positionType);
    }

    public void appendAbnormalTipType(String abnormalInfo, ValidTipType tipType) {
        appendAbnormal(abnormalInfo, tipType, getDefaultValidAppendPositionType());
    }

    public void appendAbnormalPosition(String abnormalInfo, ValidAppendPositionType positionType) {
        appendAbnormal(abnormalInfo, getDefaultValidTipType(), positionType);
    }

    public void appendAbnormal(String abnormalInfo, ValidTipType tipType, ValidAppendPositionType positionType) {
        appendTip(abnormalInfo, tipType, positionType);
    }

    public String getDefaultAbnormalInfo() {
        return getAbnormalInfo(getDefaultValidTipType());
    }

    public String getAbnormalInfo(ValidTipType tipType) {
        if (tipType == null) {
            tipType = getDefaultValidTipType();
        }

        String tip = null;
        try {
            readWriteLock.readLock().lock();
            switch (tipType) {
                case program:
                    tip = programAbnormalBufferInfo == null ? "" : programAbnormalBufferInfo.toString();
                    break;
                case zh:
                    tip = zhAbnormalZhBufferInfo == null ? "" : zhAbnormalZhBufferInfo.toString();
                    break;
                default:
                    throw new ValidException(String.format("错误提示类型"));
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
        return tip;
    }

    /**
     * 合并两个校验的结果
     *
     * @param validResult
     */
    public void mergeValidConfigResult(ValidResult validResult) {
        if (validResult == null || validResult.isNormal()) {
            return;
        }

        try {
            readWriteLock.writeLock().lock();
            this.appendTip(validResult.getAbnormalInfo(ValidTipType.program), ValidTipType.program, ValidAppendPositionType.normal);
            this.appendTip(validResult.getAbnormalInfo(ValidTipType.zh), ValidTipType.zh, ValidAppendPositionType.normal);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void clear() {
        try {
            readWriteLock.writeLock().lock();
            this.programAbnormalBufferInfo = null;
            this.zhAbnormalZhBufferInfo = null;
            this.isNormal = true;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private ValidTipType getDefaultValidTipType() {
        return ValidTipType.program;
    }

    private ValidAppendPositionType getDefaultValidAppendPositionType() {
        return ValidAppendPositionType.normal;
    }

    private void appendTip(String abnormalInfo, ValidTipType tipType, ValidAppendPositionType appendPositionType) {
        try {
            readWriteLock.writeLock().lock();
            if (tipType == null) {
                tipType = getDefaultValidTipType();
            }
            switch (tipType) {
                case program:
                    if (programAbnormalBufferInfo == null) programAbnormalBufferInfo = new StringBuffer();
                    appendPosition(abnormalInfo, programAbnormalBufferInfo, appendPositionType);
                    break;
                case zh:
                    if (zhAbnormalZhBufferInfo == null) zhAbnormalZhBufferInfo = new StringBuffer();
                    appendPosition(abnormalInfo, zhAbnormalZhBufferInfo, appendPositionType);
                    break;
                default:
                    throw new ValidException(String.format("错误提示类型"));
            }
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void appendPosition(String abnormalInfo, StringBuffer stringBuffer, ValidAppendPositionType appendPositionType) {
        if (appendPositionType == null) {
            appendPositionType = getDefaultValidAppendPositionType();
        }
        switch (appendPositionType) {
            case normal:
                appendPositionType_normal(stringBuffer, abnormalInfo);
                break;
            case pre:
                appendPositionType_pre(stringBuffer, abnormalInfo);
                break;
            default:
                throw new ValidException(String.format("错误提示追加位置类型"));
        }
    }

    private void appendPositionType_normal(StringBuffer sb, String abnormalInfo) {
        this.isNormal = false;
        if (sb.length() == 0) {
            sb.append(abnormalInfo);
        } else {
            sb.append(".\n").append(abnormalInfo);
        }
    }

    private void appendPositionType_pre(StringBuffer sb, String abnormalInfoZh) {
        this.isNormal = false;
        if (sb.length() == 0) {
            sb.append(abnormalInfoZh);
        } else {
            sb.insert(0, abnormalInfoZh + "\n");
        }
    }
}
