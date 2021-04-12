package com.yc.study.pojo;

import java.util.Map;

public class IDCartResult {

    private Long logId;
    private String imageStatus;

    private Integer direction;
    private String riskType;
    private String editTool;
    private Map<String,KeyDetail> wordsResult;
    private Integer wordsResultNum;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getImageStatus() {
        return imageStatus;
    }

    public void setImageStatus(String imageStatus) {
        this.imageStatus = imageStatus;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getEditTool() {
        return editTool;
    }

    public void setEditTool(String editTool) {
        this.editTool = editTool;
    }

    public Map<String, KeyDetail> getWordsResult() {
        return wordsResult;
    }

    public void setWordsResult(Map<String, KeyDetail> wordsResult) {
        this.wordsResult = wordsResult;
    }

    public Integer getWordsResultNum() {
        return wordsResultNum;
    }

    public void setWordsResultNum(Integer wordsResultNum) {
        this.wordsResultNum = wordsResultNum;
    }

    @Override
    public String toString() {
        return "IDCartResult{" +
                "logId=" + logId +
                ", imageStatus='" + imageStatus + '\'' +
                ", direction=" + direction +
                ", riskType='" + riskType + '\'' +
                ", editTool='" + editTool + '\'' +
                ", wordsResult=" + wordsResult +
                ", wordsResultNum=" + wordsResultNum +
                '}';
    }
}
