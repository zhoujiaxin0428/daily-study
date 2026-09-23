package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏主界面
    //创建一个二维数组
    //加载图片会根据二维数组的数据进行加载
    int[][] data=new int[4][4];
    //空白方块在二维数组中的位置
    int x=0,y=0;
    //定义一个变量，记录当前图片的路径
    String path="D:\\java基础学习\\学习\\day09\\code09\\image\\animal\\animal3\\";
    //定义一个二维数组，存储正确的数据
    int[][]win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //统计步数
    int step=0;

    //创建选项下面的条目对象
    JMenuItem replayItem =new JMenuItem("重新游戏");
    JMenuItem reLoginItem =new JMenuItem("重新登录");
    JMenuItem closeItem =new JMenuItem("关闭游戏");
    JMenuItem beautyItem =new JMenuItem("美女");
    JMenuItem animalItem =new JMenuItem("动物");
    JMenuItem sportItem =new JMenuItem("运动");

    JMenuItem accountItem =new JMenuItem("公众号");
    public GameJFrame(){
        //初始化页面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据（打乱）
        initData();
        //初始化图片
        initImage();
        //显示出来,写在最后
        this.setVisible(true);
    }


    private void initData() {
        //定义一维数组
        int[]tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //打乱顺序
        Random r=new Random();
        for (int i = 0; i <tempArr.length ; i++) {
            int index=r.nextInt(tempArr.length);
            //拿着遍历到的数据和临时索引进行交换
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }
        //给数组添加数据
        for (int i = 0; i <tempArr.length ; i++) {
            if (tempArr[i]==0){
                x=i/4;
                y=i%4;
            }
                data[i/4][i%4]=tempArr[i];

        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if (victory()){
            JLabel winJLabel=new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i <4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取当前要加载图片的序号
                int num =data[i][j];
                //创建一个图片ImageIcon对象
                ImageIcon icon=new ImageIcon(path+num+".jpg");
                //创建一个JLabel的对象（管理容器）
                JLabel jLabel=new JLabel(icon);
                //指定图片位置
                jLabel.setBounds(105*j+83,105*i+134,105,105);
                //给图片加边框
                //0：图片凸起来，1：图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));

                //把容器添加到界面
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
            }

        }
        //添加背景图片
        ImageIcon bg=new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\background.png");
        JLabel background=new JLabel(bg);
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        //把背景图片添加到界面当中
        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar jMenuBar=new JMenuBar();

        //创建菜单上面的两个选项的对象（功能 关于我们）
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于我们");
        JMenu replace_image_JMenu =new JMenu("更换图片");

        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(replace_image_JMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        replace_image_JMenu.add(beautyItem);
        replace_image_JMenu.add(animalItem);
        replace_image_JMenu.add(sportItem);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        beautyItem.addActionListener(this);
        sportItem.addActionListener(this);
        animalItem.addActionListener(this);

        //将菜单里面的两个选项添加到菜单
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高，并显示出来
        this.setSize(603,680);
        //标题
        this.setTitle("拼图单机版 v1.0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置，只有取消了才会按照xy轴的形式放置
        this.setLayout(null);
        //给整个界面添加键盘监听
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all=new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            ImageIcon bg=new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\background.png");
            JLabel background=new JLabel(bg);
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利
        if (victory()){
            return;
        }
        //对上下左右进行判断
        //左37右38上39下40
        int code=e.getKeyCode();
        if (code==37){
            System.out.println("向左移动");
            if(y==3){
                return;
            }
            //把空白方块右面的数字向左移动
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            //每移动一次计数器自增一次
            step++;
            initImage();

        }else if (code==38){
            System.out.println("向上移动");
            if (x==3){
                //空白图片在最下面一张
                return;
            }
            //把空白方块下面的数字向上移动
            //x，y表示空白方块，x+1，y表示方块下面的数字
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            //每移动一次计数器自增一次
            step++;
            initImage();

        }else if (code==39){
            System.out.println("向右移动");
            if (y==0){
                return;
            }
            //把空白方块左面的数字向右移动
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            //每移动一次计数器自增一次
            step++;
            initImage();

        }else if (code==40){
            System.out.println("向下移动");
            if (x==0){
                return;
            }
            //把空白方块上面的数字向下移动
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            //每移动一次计数器自增一次
            step++;
            initImage();
            }else if (code==65){
            initImage();
        }else if (code==87){
            data=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    public Boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if (obj==replayItem){
            System.out.println("重新游戏");

            //计步器清理
            step=0;
            //再次打乱二维数组的数据
            initData();
            //加载图片
            initImage();

        }else if (obj==reLoginItem){
            System.out.println("重新登录");
            //关闭当前界面
            this.setVisible(false);
            //打开登录页面
            new LoginJFrame();

        }else if (obj==closeItem){
            System.out.println("关闭游戏");
            //直接关闭虚拟机即可
            System.exit(0);

        }else if (obj==accountItem){
            System.out.println("公众号");

            //创建一个弹框对象
            JDialog jDialog=new JDialog();
            //创建一个管理图片的容器对象
            JLabel jLabel=new JLabel(new ImageIcon("D:\\java基础学习\\学习\\day09\\code09\\image\\about.png"));
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
        else if(obj==beautyItem){
            //随机选择图片
            Random random=new Random();
            int num= random.nextInt(13)+1;
            //改变路径
            path="D:\\java基础学习\\学习\\day09\\code09\\image\\girl\\girl"+num+"\\";
            //加载所有小图片
            step=0;
            initData();
            initImage();
        }else if(obj==sportItem){
            //随机选择图片
            Random random=new Random();
            int num= random.nextInt(10)+1;
            //改变路径
            path="D:\\java基础学习\\学习\\day09\\code09\\image\\sport\\sport"+num+"\\";
            //加载所有小图片
            step=0;
            initData();
            initImage();
        }else if(obj==animalItem){
            //随机选择图片
            Random random=new Random();
            int num= random.nextInt(8)+1;
            //改变路径
            path="D:\\java基础学习\\学习\\day09\\code09\\image\\animal\\animal"+num+"\\";
            //加载所有小图片
            step=0;
            initData();
            initImage();
            //按A是选择之后
        }
    }
}


