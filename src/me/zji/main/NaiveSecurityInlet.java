package me.zji.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 程序入口
 * Created by imyu on 2017/3/12.
 */
public class NaiveSecurityInlet {
    public static void main(String[] args) {
        final EncryptWindow encryptWindow = new EncryptWindow("文件加密");
        final DecryptWindow decryptWindow = new DecryptWindow("文件解密");
        final EncryptNameWindow encryptNameWindow = new EncryptNameWindow("文件名加密");
        final DecryptNameWindow decryptNameWindow = new DecryptNameWindow("文件名解密");

        JFrame frame = new JFrame("文件加密解密工具");
        Toolkit tk = frame.getToolkit();
        Dimension dm = tk.getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(600, 350);
        frame.setLocation((int) (dm.getWidth() - 600) / 2, (int) (dm.getHeight() - 350) / 2);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JButton button1 = new JButton("文件加密");
        button1.setBounds(50, 75, 225, 70);
        frame.add(button1);
        JButton button2 = new JButton("文件解密");
        button2.setBounds(325, 75, 225, 70);
        frame.add(button2);
        JButton button3 = new JButton("文件名加密");
        button3.setBounds(50, 155, 225, 70);
        frame.add(button3);
        JButton button4 = new JButton("文件名解密");
        button4.setBounds(325, 155, 225, 70);
        frame.add(button4);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptWindow.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptWindow.setVisible(true);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptNameWindow.setVisible(true);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptNameWindow.setVisible(true);
            }
        });

    }
}