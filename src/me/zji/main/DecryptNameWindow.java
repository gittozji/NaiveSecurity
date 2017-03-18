package me.zji.main;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import me.zji.service.SecurityService;
import me.zji.service.impl.SecurityServiceImpl;

import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 文件名解密
 * Created by imyu on 2017/3/18.
 */
public class DecryptNameWindow extends JFrame {
    SecurityService securityService = new SecurityServiceImpl();

    DecryptNameWindow(String title) {
        Toolkit tk = getToolkit();
        Dimension dm = tk.getScreenSize();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setSize(600, 350);
        setLocation((int) (dm.getWidth() - 600) / 2, (int) (dm.getHeight() - 350) / 2);
        setLayout(null);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        final JLabel label1 = new JLabel("输入需要解密的文本:");
        label1.setBounds(40, 20, 120, 50);
        add(label1);
        final JLabel label2 = new JLabel("输出已经解密的文本:");
        label2.setBounds(40, 70, 120, 50);
        add(label2);
        final JLabel label3 = new JLabel("请输入解密的密钥:");
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

        final JButton button = new JButton("进行解密");
        button.setBounds(100, 220, 400, 50);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validate = true;
                String text1 = textField1.getText();
                String text3 = textField3.getText();
                String text4 = "";
                if ("".equals(text1)) {
                    text4 += "输入需要解密的文本不能为空。";
                    validate = false;
                }
                if (text3.length() < 8) {
                    text4 += "解密密钥格式不合法。";
                    validate = false;
                }
                label4.setText(text4);
                if (validate) {
                    String result = "";
                    label4.setText("正在解密...");
                    try {
                        result = securityService.doFinal(Cipher.DECRYPT_MODE, text1, text3);
                        result = new String(Base64.decode(result));
                    } catch (Exception exception) {
                        result = "解密内部算法出错！！！";
                    }
                    textField2.setText(result);
                    label4.setText("解密完成...");
                }
            }
        });
    }
}
