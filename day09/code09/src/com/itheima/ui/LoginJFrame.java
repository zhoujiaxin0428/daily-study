package com.itheima.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements ActionListener, MouseListener {
    //创建一个集合存储正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();
    // 用户名输入框
    private JTextField usernameField;
    // 密码输入框（建议后续改成 JPasswordField 更安全）
    private JTextField passwordField;
    // 验证码输入框
    private JTextField codeField;
    // 显示正确验证码的标签
    private JLabel rightCodeLabel;
    // 登录按钮
    private JButton loginBtn;
    //注册按钮
    private JButton registerbtn;

    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }


    public LoginJFrame() {
        //初始化界面
        initJFrame();

        //在这个界面中添加内容
        initView();

        //让当前界面显示出来
        this.setVisible(true);
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        usernameField = new JTextField();
        usernameField.setBounds(195, 134, 200, 30);
        this.getContentPane().add(usernameField);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        passwordField = new JTextField();
        passwordField.setBounds(195, 195, 200, 30);
        this.getContentPane().add(passwordField);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        codeField = new JTextField();
        codeField.setBounds(195, 256, 100, 30);
        this.getContentPane().add(codeField);

        String codeStr = CodeUtil.getCode();
         rightCodeLabel = new JLabel();
        //设置内容
        rightCodeLabel.setText(codeStr);
        //绑定鼠标监听事件
        rightCodeLabel.addMouseListener(this);
        //位置和宽高
        rightCodeLabel.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCodeLabel);

        //5.添加登录按钮
        loginBtn = new JButton();
        loginBtn.setBounds(123, 310, 128, 47);
        loginBtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\登录按钮.png"));
        //去除按钮的默认边框
        loginBtn.setBorderPainted(false);
        //去除按钮的默认背景
        loginBtn.setContentAreaFilled(false);
        // 给登录按钮绑定点击事件
        loginBtn.addActionListener(this);
        loginBtn.addMouseListener(this);
        this.getContentPane().add(loginBtn);

        //6.添加注册按钮
        registerbtn = new JButton();
        registerbtn.setBounds(256, 310, 128, 47);
        registerbtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\注册按钮.png"));
        //去除按钮的默认边框
        registerbtn.setBorderPainted(false);
        //去除按钮的默认背景
        registerbtn.setContentAreaFilled(false);
        registerbtn.addMouseListener(this);
        registerbtn.addActionListener(this);
        this.getContentPane().add(registerbtn);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }


    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }


    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content,SwingConstants.CENTER);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == loginBtn) {
            // 获取输入内容
            String inputUsername = usernameField.getText().trim();
            String inputPassword = passwordField.getText().trim();
            String inputCode = codeField.getText().trim();
            String rightCode = rightCodeLabel.getText().trim();

            // 第一步：校验验证码
            if (!inputCode.equalsIgnoreCase(rightCode)) {
                showJDialog("验证码错误");
                codeField.setText("");
                rightCodeLabel.setText(CodeUtil.getCode());
                return;
            }

            // 第二步：非空校验
            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                showJDialog("用户名或密码不能为空");
                rightCodeLabel.setText(CodeUtil.getCode());
                return;
            }

            // 第三步：分情况校验用户名和密码
            User findUser = null;
            for (User user : list) {
                if (user.getUsername().equals(inputUsername)) {
                    findUser = user;
                    break;
                }
            }

            if (findUser == null) {
                // 用户名不存在
                showJDialog("用户名不存在，请先注册");
                rightCodeLabel.setText(CodeUtil.getCode());
            } else if (!findUser.getPassword().equals(inputPassword)) {
                // 密码错误
                showJDialog("密码错误");
                rightCodeLabel.setText(CodeUtil.getCode());
            } else {
                // 登录成功
                this.dispose();
                new GameJFrame();
            }

        } else if (source == registerbtn) {
            // 点击注册按钮，打开注册窗口
            new RegisterJFrame();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj=e.getSource();
        if (obj==rightCodeLabel){
            rightCodeLabel.setText(CodeUtil.getCode());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj=e.getSource();
        if (obj==loginBtn){
            loginBtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\登录按下.png"));
        }else if (obj==registerbtn){
            registerbtn.setIcon(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}