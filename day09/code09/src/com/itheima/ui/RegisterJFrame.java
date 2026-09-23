package com.itheima.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements ActionListener, MouseListener {
    // 用户名输入框
    private JTextField usernameField;
    // 密码输入框（改为密文显示）
    private JPasswordField passwordField;
    private JPasswordField passwordField1;
    // 重置按钮
    private JButton replaceBtn;
    // 注册按钮
    private JButton registerbtn;

    public RegisterJFrame() {
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\注册用户名.png"));
        usernameText.setBounds(116, 135, 79, 14);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        usernameField = new JTextField();
        usernameField.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameField);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\注册密码.png"));
        passwordText.setBounds(116, 195, 64, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框（密文）
        passwordField = new JPasswordField();
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //再次输入密码
        JLabel passwordText1 = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\再次输入密码.png"));
        passwordText1.setBounds(116, 250, 96, 17);
        this.getContentPane().add(passwordText1);

        //确认密码输入框（密文）
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(220, 250, 170, 30);
        this.getContentPane().add(passwordField1);

        //5.添加重置按钮
        replaceBtn = new JButton();
        replaceBtn.setBounds(123, 310, 128, 47);
        replaceBtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\重置按钮.png"));
        //去除按钮的默认边框
        replaceBtn.setBorderPainted(false);
        //去除按钮的默认背景
        replaceBtn.setContentAreaFilled(false);
        // 绑定事件
        replaceBtn.addActionListener(this);
        replaceBtn.addMouseListener(this);
        this.getContentPane().add(replaceBtn);

        //6.添加注册按钮
        registerbtn = new JButton();
        registerbtn.setBounds(256, 310, 128, 47);
        registerbtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\注册按钮.png"));
        //去除按钮的默认边框
        registerbtn.setBorderPainted(false);
        //去除按钮的默认背景
        registerbtn.setContentAreaFilled(false);
        registerbtn.addMouseListener(this);
        registerbtn.addActionListener(this);
        this.getContentPane().add(registerbtn);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    public void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图游戏 V1.0 注册");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    // 通用提示弹框
    public void showJDialog(String content) {
        JDialog jDialog = new JDialog(this, "提示", true);
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(null);

        JLabel warning = new JLabel(content, SwingConstants.CENTER);
        warning.setBounds(0, 30, 200, 30);
        jDialog.getContentPane().add(warning);

        JButton okBtn = new JButton("确定");
        okBtn.setBounds(60, 70, 80, 30);
        okBtn.addActionListener(e -> jDialog.dispose());
        jDialog.getContentPane().add(okBtn);

        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == replaceBtn) {
            // 重置按钮：清空所有输入框，光标回到用户名
            usernameField.setText("");
            passwordField.setText("");
            passwordField1.setText("");
            usernameField.requestFocus();

        } else if (source == registerbtn) {
            // 获取输入内容
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String rePassword = new String(passwordField1.getPassword()).trim();

            // 1. 非空校验
            if (username.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                showJDialog("用户名和密码不能为空");
                return;
            }

            // 2. 两次密码一致性校验
            if (!password.equals(rePassword)) {
                showJDialog("两次输入的密码不一致");
                passwordField1.setText("");
                passwordField1.requestFocus();
                return;
            }

            // 3. 用户名重复校验（复用登录界面的用户集合，保证数据互通）
            for (User user : LoginJFrame.list) {
                if (user.getUsername().equals(username)) {
                    showJDialog("该用户名已被注册");
                    usernameField.setText("");
                    usernameField.requestFocus();
                    return;
                }
            }

            // 4. 注册成功：添加用户、提示、关闭注册窗、返回登录界面
            LoginJFrame.list.add(new User(username, password));
            showJDialog("注册成功！");
            this.dispose(); // 关闭注册窗口
            new LoginJFrame(); // 重新打开登录窗口
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == replaceBtn) {
            replaceBtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\重置按下.png"));
        } else if (obj == registerbtn) {
            registerbtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // 鼠标松开恢复按钮默认图标，修复按下不回弹
        Object obj = e.getSource();
        if (obj == replaceBtn) {
            replaceBtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\重置按钮.png"));
        } else if (obj == registerbtn) {
            registerbtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\register\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
