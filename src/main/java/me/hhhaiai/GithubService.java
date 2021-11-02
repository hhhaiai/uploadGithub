package me.hhhaiai;

import ff.jnezha.jnt.cs.GithubHelper;
import ff.jnezha.jnt.utils.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @Copyright © 2021 sanbo Inc. All rights reserved.
 * @Description: TODO
 * @Version: 1.0
 * @Create: 2021/11/2 12:58 下午
 * @author: sanbo
 */
public class GithubService {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            printHelp();
            return;
        }
        List<String> lists = Arrays.asList(args);
        if (lists == null) {
            return;
        }
        String owner = null, repo = null, dirName = null, pathName = null, fileName = null, token = null, contentNoBase64 = null, contentBase64 = null, commitMsg = null, auther = null, mail = null;
        for (int i = 0; i < lists.size(); i++) {
            try {

                String key = lists.get(i);
                if ("-owner".equals(key) || "-user".equals(key)) {
                    owner = lists.get(i + 1);
                } else if ("-repo".equals(key)) {
                    repo = lists.get(i + 1);
                } else if ("-target-dir-full-name".equals(key)) {
                    dirName = lists.get(i + 1);
                } else if ("-target-name".equals(key)) {
                    pathName = lists.get(i + 1);
                } else if ("-native-file".equals(key)) {
                    fileName = lists.get(i + 1);
                } else if ("-token".equals(key)) {
                    token = lists.get(i + 1);
                } else if ("-content-no-base64".equals(key)) {
                    contentNoBase64 = lists.get(i + 1);
                } else if ("-content-with-base64".equals(key)) {
                    contentBase64 = lists.get(i + 1);
                } else if ("-commit-messge".equals(key)) {
                    commitMsg = lists.get(i + 1);
                } else if ("-commit-auther".equals(key)) {
                    auther = lists.get(i + 1);
                } else if ("-commit-email".equals(key)) {
                    mail = lists.get(i + 1);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        String path = "";
//        dirName/pathName 互斥，同时只可以有一个，用于目标名称和目录
        if (isEmpty(dirName) && isEmpty(fileName)) {
            System.err.println("upload path  is null! please check -target-name  or -target-dir-full-name");
            return;
        }
        //上传文件名优先级：全路径>单文件
        if (isEmpty(dirName)) {
            path = pathName;
        } else {
            path = dirName;
        }

        path = "/" + path;

        // 上传优先级: 文件 > 未base64字符串 > base64字符串
        if (isEmpty(fileName)) {
            if (isEmpty(contentNoBase64)) {
                if (!isEmpty(contentBase64)) {
                    GithubHelper.createFile(false, owner, repo, path, token, contentBase64, commitMsg, auther, mail);
                } else {
                    System.err.println("无有效上传字符串，上传失败，请检测后再试！");
                    printHelp();
                    return;
                }
            } else {
                GithubHelper.createFile(true, owner, repo, path, token, contentNoBase64, commitMsg, auther, mail);
            }
        } else {
            GithubHelper.createFile(false, owner, repo, path, token, FileUtils.getBase64FromFile(new File(fileName)), commitMsg, auther, mail);
        }

    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    private static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("github 用法:").append("\r\n")
                .append("\t").append("-owner/-user:").append("\t").append("[用户]名字").append("\r\n")
                .append("\t").append("-repo:").append("\t").append("[项目]名称").append("\r\n")
                .append("\t").append("-target-name:").append("\t").append("上传[目标文件]名称").append("\r\n")
                .append("\t\t").append("仅用于确定上传的全路径文件名字").append("\t").append("\r\n\r\n")
                .append("\t").append("-target-dir-full-name:").append("\t").append("上传[上传全路径]名称").append("\r\n")
                .append("\t\t").append("仅用于确定上传的全路径文件名字，支持上传到特定路径").append("\r\n\r\n")
                .append("\t").append("-native-file:").append("\t").append("即将上传的全路径本地文件名").append("\r\n")
                .append("\t").append("-token:").append("\t").append("github 个人 token").append("\r\n")
                .append("\t").append("-content-no-base64:").append("\t").append("上传[未base64]内容").append("\r\n")
                .append("\t\t").append("上传文件的内容,有优先级,如有<b>-native-file</b>,则不会使用该部分内容").append("\r\n\r\n")
                .append("\t").append("-content-with-base64:").append("\t").append("上传[已base64]内容").append("\r\n")
                .append("\t\t").append("上传文件的内容,有优先级,如有<b>-native-file</b>,则不会使用该部分内容，同时-content-no-base64的优先级也高于该项").append("\r\n\r\n")
                .append("\t").append("-commit-messge:").append("\t").append("github上传commit内容").append("\r\n")
                .append("\t").append("-commit-auther:").append("\t").append("上传使用的用户名字(auther),可空").append("\r\n")
                .append("\t").append("-commit-email:").append("\t").append("上传使用的邮箱名称").append("\r\n")
        ;
        System.out.println(sb.toString());
    }
}
