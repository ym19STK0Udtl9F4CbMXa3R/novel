package com.nines.novel.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: NovelSpiderUtil
 * @Description: 自定义普工具类
 * @author: Nines
 * @date: 2020年03月31日 18:18
 */
public final class NovelSpiderUtil {

    private NovelSpiderUtil() {
    }

    /**
     * 分割字符串 返回后面部分
     * @param str 目标字符串
     * @param regex 分割字符串
     * @return String
     */
    public static String splitLast(String str, String regex){
        int index = str.indexOf(regex);
        if (index < 0 || index == str.length()){
            return str;
        }
        return str.substring(index+1);
    }

    /**
     * 分割字符串 返回后面部分
     * @param str 目标字符串
     * @param regex 分割字符串
     * @return String
     */
    public static String splitFirst(String str, String regex){
        int index = str.indexOf(regex);
        if (index < 0 || index == str.length()){
            return str;
        }
        return str.substring(0, index);
    }

    /**
     * 多个文件合并为一个文件，合并规则：按文件名分割排序
     * @param path 基础目录，该目录下的所有文本文件合并到 mergeToFile
     * @param mergeToFile 被合并的文本文件，这个参数可以为null，合并后的文件保存在path/merge.text
     */
    public static void multiFileMerge(String path, String mergeToFile, boolean deleteBranch){
        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()){
                    return false;
                }
                return file.getName().endsWith(".txt");
            }
        });
        // 判断文件列表是否为空
        if (files != null){
            // 按照横杠前数组排序
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    int o1Num = Integer.parseInt(o1.getName().substring(0, o1.getName().indexOf("-")));
                    int o2Num = Integer.parseInt(o2.getName().substring(0, o2.getName().indexOf("-")));
                    return Integer.compare(o1Num, o2Num);
                }
            });
            // 合并
            try (PrintWriter out = new PrintWriter(mergeToFile, "UTF-8");){
                for (File file : files) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        out.println(line);
                    }
                    bufferedReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 合并完成后删除分支
            if (deleteBranch){
                for (File file : files) {
                    boolean rs = file.delete();
                    if (!rs){
                        System.err.println(file + ",删除失败！");
                    }
                }
            }
        }else {
            throw new RuntimeException("目录中无符合要求文件");
        }
    }

    /**
     * 压缩成zip文件
     * @param path 原文件路径
     * @param outPath 输出文件路径
     */
    public static void compressZipFile(String path, String outPath){
        outPath = outPath == null ? path.replace(".txt", ".zip") : outPath;
        File file = new File(path);
        try (// 文件输入流
             FileInputStream inputStream = new FileInputStream(file);
             // 压缩对象流
             ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(new File(outPath)));) {
            // 设置ZipEntry对象
            zipOut.putNextEntry(new ZipEntry(file.getName()));
            int temp;
            while ((temp = inputStream.read()) != -1){
                zipOut.write(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
