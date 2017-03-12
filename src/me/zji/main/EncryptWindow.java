package me.zji.main;

import me.zji.service.SecurityService;
import me.zji.service.impl.SecurityServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 加密用户界面
 * Created by imyu on 2017/3/12.
 */
public class EncryptWindow extends JFrame {
    SecurityService securityService = new SecurityServiceImpl();

    EncryptWindow(String title) {
        Toolkit tk = getToolkit();
        Dimension dm = tk.getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(600, 350);
        setLocation((int) (dm.getWidth() - 600) / 2, (int) (dm.getHeight() - 350) / 2);
        setLayout(null);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JLabel label1 = new JLabel("加密文件输入路径:");
        label1.setBounds(40, 20, 120, 50);
        add(label1);
        final JLabel label2 = new JLabel("加密文件输出路径:");
        label2.setBounds(40, 70, 120, 50);
        add(label2);
        final JLabel label3 = new JLabel("请输入加密的密钥:");
        label3.setBounds(40, 120, 120, 50);
        add(label3);
        final JLabel label4 = new JLabel();
        label4.setForeground(Color.red);
        label4.setBounds(40, 170, 420, 50);
        add(label4);

        final JTextField textField1 = new JTextField();
        textField1.setBounds(170, 30, 370, 30);
        add(textField1);
        final JTextField textField2 = new JTextField();
        textField2.setBounds(170, 80, 370, 30);
        add(textField2);
        final JTextField textField3 = new JTextField();
        textField3.setBounds(170, 130, 370, 30);
        add(textField3);

        final JButton button = new JButton("进行加密");
        button.setBounds(100, 220, 400, 50);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validate = true;
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                String text3 = textField3.getText();
                String text4 = "";
                if ("".equals(text1)) {
                    text4 += "加密文件输入路径不能为空。";
                    validate = false;
                }
                if ("".equals(text2)) {
                    text4 += "加密文件输出路径不能为空。";
                    validate = false;
                }
                if (text1.equals(text2)) {
                    text4 += "输入输出路径不能一样。";
                    validate = false;
                }
                if (text3.length() < 8) {
                    text4 += "加密密钥格式不合法。";
                    validate = false;
                }
                label4.setText(text4);
                if (validate) {
                    label4.setText("正在加密...");
                    text1 = text1.replace("\\", "/");
                    text2 = text2.replace("\\", "/");
                    if (securityService.encryptFile(text1, text2, text3)) {
                        label4.setText("加密成功...");
                    } else {
                        label4.setText("加密失败...");
                    }
                }
            }
        });
    }
}
