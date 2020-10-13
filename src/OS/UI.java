package OS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.regex.Pattern;

class Label implements Runnable{
    private JLabel[] label1 = new JLabel[10];
    private JLabel[] label2 = new JLabel[10];
    private JLabel[] label3 = new JLabel[10];
    private JLabel[] label4 = new JLabel[10];
    private JLabel[] label5 = new JLabel[10];
    private JLabel[] label6 = new JLabel[10];
    private static Pcb pp = new Pcb();

    public Label() {

    }
    public Label(JLabel[] id,JLabel[] name,JLabel[] status,JLabel[] pri, JLabel[] time,JLabel[] next) {
        this.label1 = id;
        this.label2 = name;
        this.label3 = status;
        this.label4 = pri;
        this.label5 = time;
        this.label6 = next;
    }

    public static void setPP(Pcb p) {
        pp = p;
    }
    @Override
    public void run() {
        // TODO 自动生成的方法存根
        while(true) {
            int i = pp.getId();

            label2[i].setText(String.valueOf(pp.getName()));
            label4[i].setText(String.valueOf(pp.getPri()));
            label5[i].setText(String.valueOf(pp.getTime()));
            label6[i].setText(String.valueOf(pp.getNext()));

            if(pp.getStatus() == 0) {
                label3[i].setText("就绪");
                label3[i].setForeground(Color.BLACK);
            }else if(pp.getStatus() == 1) {
                label3[i].setText("运行");
                label3[i].setForeground(Color.BLUE);
            }else {
                label3[i].setText("终止");
                label3[i].setForeground(Color.RED);
            }

            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class UI extends JFrame {
    static JTextField text;

    JButton btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08,btn09,btn10,btn11,btn12,btn13,btn14;

    JLabel label, label1;

    public Font font = new Font("Dialog",Font.BOLD,18);
    JLabel[] idLabel = new JLabel[10];
    JLabel[] nameLabel = new JLabel[10];
    JLabel[] statusLabel = new JLabel[10];
    JLabel[] priLabel = new JLabel[10];
    JLabel[] timeLabel = new JLabel[10];
    JLabel[] nextLabel = new JLabel[10];
    int key = 0;
    Container pane;
    static JPanel panel4 = new JPanel(null);
    static JPanel panel5 = new JPanel(null);
    static JPanel panel6 = new JPanel(null);
    public UI() {
    }

    public UI(String s,int x,int y,int w,int h) {
        setTitle(s);
        setLocation(x,y);
        setSize(w, h);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);
        //initSwitchPanel();
        btn01 = new JButton("开始模拟");
        btn01.setLocation(50,50);
        btn01.setSize(200, 100);
        btn01.setFont(font);
        btn01.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        int n = SchedulingMethod.getN();
                        switch(key) {
                            case 1:
                                SchedulingMethod.RR(SchedulingMethod.createProcess(n), n);
                                break;
                            case 2:
                                SchedulingMethod.PSA(SchedulingMethod.createProcess(n), n);
                                break;
                            case 3:
                                SchedulingMethod.SPN(SchedulingMethod.createProcess(n), n);
                                break;
                            case 4:
                                SchedulingMethod.SPT(SchedulingMethod.createProcess(n), n);
                                break;
                        }

                    }

                }).start();
            }
        });
        panel.add(btn01);
        btn02 = new JButton("清空");
        btn02.setLocation(50,200);
        btn02.setSize(200, 100);
        btn02.setFont(font);
        btn02.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根

                        for(int i = 0; i < 10; i++) {
                            Pcb p= new Pcb();
                            p.setId(i);
                            Label.setPP(p);
                            try {
                                Thread.sleep(1000);
                            }catch(InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
        panel.add(btn02);
        btn03 = new JButton("退出");
        btn03.setLocation(50,350);
        btn03.setSize(200, 100);
        btn03.setFont(font);
        btn03.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        System.exit(0);
                    }
                }).start();

            }
        });
        panel.add(btn03);

        //initSchedulingMethod()
        JPanel panel2 = new JPanel(null);
        label = new JLabel("申请几个进程：");
        label.setLocation(350,45);
        label.setSize(150,60);
        label.setFont(font);
        panel2.add(label);
        text = new JTextField(10);
        text.setLocation(500,60);
        text.setSize(100,30);
        text.setFont(font);

        panel2.add(text);
        btn04 = new JButton("确认");
        btn04.setLocation(605,60);
        btn04.setSize(75,30);
        btn04.setFont(font);
        btn04.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        boolean error = false;
                        if((Pattern.compile("[0-9]*")).matcher(text.getText()).matches())
                            if(Integer.parseInt(text.getText()) >= 0 && Integer.parseInt(text.getText()) <= 10)
                                SchedulingMethod.setN(Integer.parseInt(text.getText()));
                            else
                                error = true;
                        else
                            error = true;

                        if(error) {
                            JFrame newJFrame = new JFrame("非法输入");

                            newJFrame.setSize(500, 250);
                            newJFrame.setLocationRelativeTo(pane);
                            newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            newJFrame.setResizable(false);

                            JPanel panel = new JPanel(new GridLayout(1, 1));
                            JLabel label = new JLabel("n为0-10的正整数");
                            label.setFont(new Font(null, Font.PLAIN, 25));
                            label.setFont(font);
                            label.setHorizontalAlignment(SwingConstants.CENTER);
                            label.setVerticalAlignment(SwingConstants.CENTER);
                            panel.add(label);

                            newJFrame.setContentPane(panel);
                            newJFrame.setVisible(true);
                        }
                    }
                }).start();

            }
        });
        panel2.add(btn04);


        label1 = new JLabel("请选择进程调度方法...");
        label1.setLocation(350,105);
        label1.setSize(500,50);
        label1.setFont(font);
        label1.setForeground(Color.BLUE);
        panel2.add(label1);
        btn05 = new JButton("时间片轮转调度");
        btn05.setLocation(350,155);
        btn05.setSize(200,30);
        btn05.setFont(font);

        btn05.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        key = 1;
                        label1.setText("当前选择的是时间片轮转调度");
                    }
                }).start();




            }
        });
        panel2.add(btn05);
        btn06 = new JButton("优先数调度");
        btn06.setLocation(550,155);
        btn06.setSize(200,30);
        btn06.setFont(font);
        btn06.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        key = 2;
                        label1.setText("当前选择的是优先数调度");
                    }
                }).start();




            }
        });
        panel2.add(btn06);
        btn07 = new JButton("最短进程优先");
        btn07.setLocation(750,155);
        btn07.setSize(200,30);
        btn07.setFont(font);
        btn07.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        key = 3;
                        label1.setText("当前选择的是最短进程优先调度");
                    }
                }).start();




            }
        });
        panel2.add(btn07);
        btn08 = new JButton("最短剩余时间优先");
        btn08.setLocation(950,155);
        btn08.setSize(200,30);
        btn08.setFont(font);
        btn08.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO 自动生成的方法存根
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        key = 4;
                        label1.setText("当前选择的是最短剩余时间优先调度");
                    }
                }).start();




            }
        });
        panel2.add(btn08);




        btn09 = new JButton("进程名称");
        btn09.setLocation(0, 0);
        btn09.setSize(150,30);
        btn09.setFont(font);
        panel4.add(btn09);
        btn10 = new JButton("进程标识符");
        btn10.setLocation(150, 0);
        btn10.setSize(150,30);
        btn10.setFont(font);
        panel4.add(btn10);
        btn11 = new JButton("进程状态");
        btn11.setLocation(300, 0);
        btn11.setSize(150,30);
        btn11.setFont(font);
        panel4.add(btn11);
        btn12 = new JButton("进程优先数");
        btn12.setLocation(450, 0);
        btn12.setSize(150,30);
        btn12.setFont(font);
        panel4.add(btn12);
        btn13 = new JButton("剩余运行时间");
        btn13.setLocation(600, 0);
        btn13.setSize(150,30);
        btn13.setFont(font);
        panel4.add(btn13);
        btn14 = new JButton("下一个进程");
        btn14.setLocation(750, 0);
        btn14.setSize(150,30);
        btn14.setFont(font);
        panel4.add(btn14);

        panel4.setBackground(Color.white);

        for(int i = 0; i < 10; i++) {
            idLabel[i] = new JLabel("进程"+i);
            idLabel[i].setLocation(50,30+i*50);
            idLabel[i].setSize(200, 50);
            idLabel[i].setFont(font);
            panel4.add(idLabel[i]);
        }

        for(int i = 0; i < 10; i++) {
            nameLabel[i] = new JLabel("0");
            nameLabel[i].setLocation(220,30+i*50);
            nameLabel[i].setSize(200, 50);
            nameLabel[i].setFont(font);
            panel4.add(nameLabel[i]);
        }

        for(int i = 0; i < 10; i++) {
            statusLabel[i] = new JLabel("就绪");
            statusLabel[i].setLocation(360,30+i*50);
            statusLabel[i].setSize(200, 50);
            statusLabel[i].setFont(font);
            panel4.add(statusLabel[i]);
        }

        for(int i = 0; i < 10; i++) {
            priLabel[i] = new JLabel("0");
            priLabel[i].setLocation(510,30+i*50);
            priLabel[i].setSize(200, 50);
            priLabel[i].setFont(font);
            panel4.add(priLabel[i]);
        }

        for(int i = 0; i < 10; i++) {
            timeLabel[i] = new JLabel("0");
            timeLabel[i].setLocation(670,30+i*50);
            timeLabel[i].setSize(200, 50);
            timeLabel[i].setFont(font);
            panel4.add(timeLabel[i]);
        }

        for(int i = 0; i < 10; i++) {
            nextLabel[i] = new JLabel("0");
            nextLabel[i].setLocation(820,30+i*50);
            nextLabel[i].setSize(200, 50);
            nextLabel[i].setFont(font);
            panel4.add(nextLabel[i]);
        }

        new Thread(new Label(idLabel,nameLabel,statusLabel,priLabel,timeLabel,nextLabel)).start();

        pane = getContentPane();
        pane.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(350, 100));
        panel2.setPreferredSize(new Dimension(0, 200));
        panel4.setPreferredSize(new Dimension(110, 1000));
        panel5.setPreferredSize(new Dimension(130, 100));
        panel6.setPreferredSize(new Dimension(0, 30));
        pane.add(panel2,BorderLayout.NORTH);
        pane.add(panel,BorderLayout.WEST);
        pane.add(panel4,BorderLayout.CENTER);
        pane.add(panel5,BorderLayout.EAST);
        pane.add(panel6,BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setVisible(true);
    }

}


