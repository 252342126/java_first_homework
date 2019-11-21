package ex1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Arrays;
import javax.swing.*;


public class ex1 {
	static JFrame frame=new JFrame("123");
	static JTabbedPane pane=new JTabbedPane();
	static JPanel panel1=new JPanel();
	static JPanel panel2=new JPanel();
	static JPanel panel3=new JPanel();
	static JPanel pane3=new JPanel();
	static JScrollPane excel;
	static JTable table;
	static JButton button1=new JButton("确定");
	static JButton button2=new JButton("查询");
	static JButton button3=new JButton("排序");
	static JTextField text1=new JTextField(10);
	static JTextField text2=new JTextField(10);
	static JTextField text3=new JTextField(10);
	static stu student[]=new stu[1005];
	static stu sss=new stu();
	static int cnt=0;
	static int flag1=0,flag2=0,flag3=0;
	static JLabel label1;
	static JLabel label2;
	static Object a[][];
	static Object name[]={"学号","成绩"};
	static public class stu
	{
		public String student_number;
		public int score;
	}
	static class cmp implements Comparator<stu>
	{
			public int compare(stu o1,stu o2)
		{
			return o1.score > o2.score ? -1:1;
		}
	}
	
	static class mylistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==button1)
			{
				
				if(flag1==1)panel1.remove(label1);
				else flag1=1;
				if((text1.getText().length())==0)
				{
					label1=new JLabel("学号不能为空");
					panel1.add(label1);	
					pane.repaint();
				}
				else if((text2.getText().length())==0)
				{
					label1=new JLabel("成绩不能为空");
					panel1.add(label1);	
					pane.repaint();
				}	
				else
				{
					label1=new JLabel("加入成功");
					panel1.add(label1);	
					student[cnt]=new stu();
					student[cnt].student_number=text1.getText();
					student[cnt].score=Integer.parseInt(text2.getText());
					cnt++;
					if(flag3==0)
					{
						flag3=1;
					}
					else
					{
						panel3.remove(excel);
					}
					a=new Object[cnt][2];
					for(int i=0;i<cnt;i++)
					{
						a[i][0]=student[i].student_number;
						a[i][1]=student[i].score;
					}
					table=new JTable(a,name);
					excel=new JScrollPane(table);
					panel3.add(excel);
					pane.repaint();
				}
			}
			else if(e.getSource()==button2)
			{
				String s=text3.getText();
				int inlist=0;
				for(int i=0;i<cnt;i++)
				{
					if(student[i].student_number.equals(s))
					{
						inlist=1;
						if(flag2==0)
						{
							label1=new JLabel("学号:"+s+"       "+"成绩:"+student[i].score);
							panel2.add(label1);
							pane.setComponentAt(1,panel2);
							pane.repaint();
							flag2=1;
							break;
						}
						else if(flag2==1)
						{
							panel2.remove(label1);
							label1=new JLabel("学号:"+s+"       "+"成绩:"+student[i].score);
							panel2.add(label1);
							pane.setComponentAt(1,panel2);
							pane.repaint();
							break;
						}
					}
				}
				if(inlist==0)
				{
					if(flag2==1)panel2.remove(label1);
					label1=new JLabel("找不到学号");
					panel2.add(label1);
					pane.setComponentAt(1,panel2);
					pane.repaint();
				}
			}
			else if(e.getSource()==button3)
			{
				if(flag3==0)
				{
					flag3=1;
				}
				else
				{
					panel3.remove(excel);
				}
				Arrays.sort(student,0,cnt,new cmp());
				a=new Object[cnt][2];
				for(int i=0;i<cnt;i++)
				{
					a[i][0]=student[i].student_number;
					a[i][1]=student[i].score;
				}
				table=new JTable(a,name);
				excel=new JScrollPane(table);
				panel3.add(excel);
				pane.repaint();
			}
		}

		
	}
	public static void main(String[] args) {
		frame.setBounds(0, 0, 1000, 500);
		pane.addTab("成绩输入",new JLabel("A"));
		pane.addTab("成绩查询",new JLabel("B"));
		pane.addTab("成绩排序", new JLabel("C"));
		pane.setSelectedIndex(0);
		panel1.add(new JLabel("学号"));
		panel1.add(text1);
		panel1.add(new JLabel("成绩"));
		panel1.add(text2);
		panel1.add(button1);
		panel2.add(new JLabel("成绩查询"));
		panel2.add(text3);
		panel2.add(button2);	
		JScrollPane pane2=new JScrollPane(table);
		pane3.add(pane2);
		panel3.add(button3);
		pane.setComponentAt(0,panel1);
		pane.setComponentAt(1,panel2);
		pane.setComponentAt(2, panel3);
		button1.addActionListener(new mylistener());
		button2.addActionListener(new mylistener());
		button3.addActionListener(new mylistener());
		frame.add(pane);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
