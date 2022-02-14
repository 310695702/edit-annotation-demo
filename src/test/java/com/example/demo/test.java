package com.example.demo;

import com.example.demo.comment.Constant;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author: HanYuXing
 * @date: 2021-12-16 17:18
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo1Application.class)
public class test {

    @Test
    public void test() {
//        File file = new File("D:\\FeiQ\\Recv Files\\1.png");
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("D:\\test");
//        tesseract.setLanguage("chi_sim");
//        long startTime = System.nanoTime();
//        try {
//            String result = tesseract.doOCR(file);
//            System.out.println(result);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        long endTime = System.nanoTime();
//        System.out.println((endTime - startTime)/1000000000.0);
        Constant.CONTRACT_KIND.forEach((k, v) -> {
            System.out.println("key" + k + " ,value" + v);
        });
    }
}
