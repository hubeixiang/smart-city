package com.sct.service.core.web.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码的工具类
 */
public final class CaptchaCode {
    private int width = 100;// 生成验证码图片的宽度
    private int height = 50;// 生成验证码图片的高度
    private String[] fontNames = {"宋体", "楷体", "隶书", "微软雅黑"};
    private Color bgColor = new Color(255, 255, 255);// 定义验证码图片的背景颜色为白色
    private Random random = new Random();
    private String codes = "23456789abcdefghjkmnprstuvwxyzABCDEFGHJKLMNPRSTUVWXYZ";

    public CaptchaCodeInfo createVerifyCodeInfo() {
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            g2.setColor(randomColor());
            g2.setFont(randomFont());
            float x = i * width * 1.0f / 4;
            g2.drawString(s, x, height - 15);
        }
        String text = sb.toString().toLowerCase();
        drawLine(image);
        CaptchaCodeInfo captchaCodeInfo = new CaptchaCodeInfo();
        captchaCodeInfo.setBufferedImage(image);
        captchaCodeInfo.setCode(text);
        captchaCodeInfo.setKey(text);
        return captchaCodeInfo;
    }

    /**
     * 获取一个随意颜色
     *
     * @return
     */
    private Color randomColor() {
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     * 获取一个随机字体
     *
     * @return
     */
    private Font randomFont() {
        String name = fontNames[random.nextInt(fontNames.length)];
        int style = random.nextInt(4);
        int size = random.nextInt(5) + 24;
        return new Font(name, style, size);
    }

    /**
     * 获取一个随机字符
     *
     * @return
     */
    private char randomChar() {
        return codes.charAt(random.nextInt(codes.length()));
    }

    /**
     * 创建一个空白的BufferedImage对象
     *
     * @return
     */
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(bgColor);// 设置验证码图片的背景颜色
        g2.fillRect(0, 0, width, height);
        return image;
    }


    /**
     * 绘制干扰线
     *
     * @param image
     */
    private void drawLine(BufferedImage image) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        int num = 2;
        for (int i = 0; i < num; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawLine(x1, y1, x2, y2);
        }
    }

}
