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
	static JButton button1=new JButton("ȷ��");
	static JButton button2=new JButton("��ѯ");
	static JButton button3=new JButton("����");
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
	static Object name[]={"ѧ��","�ɼ�"};
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
					label1=new JLabel("ѧ�Ų���Ϊ��");
					panel1.add(label1);	
					pane.repaint();
				}
				else if((text2.getText().length())==0)
				{
					label1=new JLabel("�ɼ�����Ϊ��");
					panel1.add(label1);	
					pane.repaint();
				}	
				else
				{
					label1=new JLabel("����ɹ�");
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
							label1=new JLabel("ѧ��:"+s+"       "+"�ɼ�:"+student[i].score);
							panel2.add(label1);
							pane.setComponentAt(1,panel2);
							pane.repaint();
							flag2=1;
							break;
						}
						else if(flag2==1)
						{
							panel2.remove(label1);
							label1=new JLabel("ѧ��:"+s+"       "+"�ɼ�:"+student[i].score);
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
					label1=new JLabel("�Ҳ���ѧ��");
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
		pane.addTab("�ɼ�����",new JLabel("A"));
		pane.addTab("�ɼ���ѯ",new JLabel("B"));
		pane.addTab("�ɼ�����", new JLabel("C"));
		pane.setSelectedIndex(0);
		panel1.add(new JLabel("ѧ��"));
		panel1.add(text1);
		panel1.add(new JLabel("�ɼ�"));
		panel1.add(text2);
		panel1.add(button1);
		panel2.add(new JLabel("�ɼ���ѯ"));
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
