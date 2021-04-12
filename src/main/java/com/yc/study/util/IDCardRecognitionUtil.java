package com.yc.study.util;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.yc.study.pojo.IDCartResult;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * 身份证识别工具类
 */
public class IDCardRecognitionUtil {

    //设置相关参数 https://console.bce.baidu.com/
    public static String appId;
    public static String apiKey;
    public static String secretKey;

    static {
        try {
            Properties properties = new Properties();
            // 设置配置文件路径
            String path = IDCardRecognitionUtil.class.getClassLoader().getResource("card.properties").getPath();
            properties.load(new FileInputStream(path));

            appId = properties.getProperty("APP_ID_IDCARD");
            apiKey = properties.getProperty("API_KEY_IDCARD");
            secretKey = properties.getProperty("SECRET_KEY_IDCARD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param detectDirectionFlag   是否提示图片方向信息
     * @param detectRiskFlag        是否提示复印、翻拍等风险信息
     * @param idCardSide            身份证图片为正面（front）还是反面（back）
     * @param imagePath             图片的全路径字符串
     * @return
     */
    public static IDCartResult readIDCard(boolean detectDirectionFlag, boolean detectRiskFlag, String idCardSide, String imagePath) {
//        System.out.println("**********APP_ID_IDCARD:" + appId);
//        System.out.println("**********API_KEY_IDCARD:" + apiKey);
//        System.out.println("**********SECRET_KEY_IDCARD:" + secretKey);
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(appId, apiKey, secretKey);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "" + detectDirectionFlag);
        options.put("detect_risk", "" + detectRiskFlag);
        if (ValidateUtil.isEmpty(idCardSide) || (!"front".equals(idCardSide) && !"back".equals(idCardSide))) {
            System.out.println("身份证正反面参数idCardSide传入不正确!");
            return null;
        }

        JSONObject res = client.idcard(imagePath, idCardSide, options);
        IDCartResult idCardResult = JSON.parseObject(res.toString(), IDCartResult.class);
        return idCardResult;
    }

    public static String getMessage(IDCartResult idCardResult) {
        StringBuilder sb = new StringBuilder();
        //image_status
        if ("normal".equals(idCardResult.getImageStatus())) {
            sb.append("识别正常;");
        } else if ("reversed_side".equals(idCardResult.getImageStatus())) {
            sb.append("未摆正身份证;");
        } else if ("non_idcard".equals(idCardResult.getImageStatus())) {
            sb.append("上传的图片中不包含身份证;");
        } else if ("blurred".equals(idCardResult.getImageStatus())) {
            sb.append("身份证模糊;");
        } else if ("over_exposure".equals(idCardResult.getImageStatus())) {
            sb.append("身份证关键字段反光或过曝;");
        } else if ("unknown".equals(idCardResult.getImageStatus())) {
            sb.append("未知状态;");
        }

        //risk_type
        if (!ValidateUtil.isEmpty(idCardResult.getRiskType())) {
            if ("normal".equals(idCardResult.getRiskType())) {
                sb.append("正常身份证;");
            } else if ("copy".equals(idCardResult.getRiskType())) {
                sb.append("复印件;");
            } else if ("temporary".equals(idCardResult.getRiskType())) {
                sb.append("临时身份证;");
            } else if ("screen".equals(idCardResult.getRiskType())) {
                sb.append("翻拍;");
            } else if ("unknown".equals(idCardResult.getRiskType())) {
                sb.append("其他未知情况;");
            }
        }
        if (!ValidateUtil.isEmpty(idCardResult.getEditTool())) {
            sb.append("该身份证被图片处理软件" + idCardResult.getEditTool() + "编辑处理过;");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------------正面身份证-----------------------------------------------------------");
        // 正面
        String frontImagePath = "C:\\Users\\Administrator\\Pictures\\IDCard\\front.jpg";
        IDCartResult frontIdCardResult = IDCardRecognitionUtil.readIDCard(true, true, "front", frontImagePath);
        System.out.println("***********: " + frontIdCardResult);
        System.out.println("***********: " + IDCardRecognitionUtil.getMessage(frontIdCardResult));

        System.out.println("-------------------------------------------------------反面身份证-----------------------------------------------------------");
        // 反面
        String backImagePath = "C:\\Users\\Administrator\\Pictures\\IDCard\\back.jpg";
        IDCartResult backIdCardResult = IDCardRecognitionUtil.readIDCard(true, true, "back", backImagePath);
        System.out.println("***********: " + backIdCardResult);
        System.out.println("***********: " + IDCardRecognitionUtil.getMessage(backIdCardResult));
    }

}
