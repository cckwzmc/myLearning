package com.toney.core.utils;

import java.io.File;

/**
 *************************************************************** 
 * <p>
 * 
 * @DESCRIPTION :
 * @AUTHOR : wangmingjin
 * @DATE :2012-4-14 下午2:16:25
 *       </p>
 **************************************************************** 
 */
public class UploadPicUtils {

    // private static final XLogger LOGGER = XLoggerFactory.getXLogger(EIAddressManagerImpl.class);

    /**
     * 判断下上传地址是否存在
     */
    public static void isUploadPathExsit(String fileName) {

        File picFile = new File(fileName);

        String picParentPath = picFile.getParent();

        File picParentFile = new File(picParentPath);

        if (!picParentFile.exists()) {
            picParentFile.mkdirs();
        }
    }

    /**
     * 用户删除凭证
     */
    public static void deleteFile(String fileUrl) {
        File deleteFile = new File(fileUrl);
        if (deleteFile.exists()) {
            deleteFile.delete();
        }
    }

    /**
     * 用户删除凭证
     */
    public static String reSplice(final String fileUrl, final String proofs) {

        String proof = proofs;

        if (proof.endsWith(fileUrl)) {

            proof = proofs.replaceAll("(?:" + fileUrl + ")", "");
        } else {

            proof = proofs.replaceAll("(?:" + fileUrl + ";)", "");

        }

        return proof;
    }

    /**
     * @param upLoadFile
     * @throws Exception
     */
    public static String reFileName(String fileName, String remoteWritePath, String userId) {

        // String remoteWritePath = "c:/";
        // userId = "1000000049";
        StringBuilder sb = new StringBuilder();
        sb.append("/").append(userId).append("/").append(System.currentTimeMillis())
                .append(fileName.substring(fileName.lastIndexOf("."), fileName.length()));
        String wpath = remoteWritePath + sb.toString();
        isUploadPathExsit(wpath);
        // File destFile = new File(wpath);
        // copy(proofFile, destFile);
        return sb.toString();

    }

    public static String array2String(String[] arry, String proof) {
        String proofs = proof;

        if (arry != null) {
            for (String str : arry) {
                proofs = proofs + ";" + str;
            }
        }
        return proofs;
    }

    public static String[] string2Arr(String str) {

        String arr[] = null;

        if (str != null && !str.equals("")) {

            arr = str.split(";");
        }

        return arr;

    }

}
