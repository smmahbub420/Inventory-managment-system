import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;

public class Logs extends JPanel implements ActionListener
{
	JPanel panel_logs;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	JButton btnrefresh;
	JLabel lblHeading;
	
	ArrayList<Invoice>inv;
	
	public Logs()
	{
		panel_logs=new JPanel();
		panel_logs.setLayout(null);


		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		Font myFont_subHead = new Font("Cambria", Font.ITALIC + Font.BOLD, 25);

		Color bgcolor = new Color(204, 255, 204);				// color
		panel_logs.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Sales Logs ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,20,400,50);
		lblHeading.setFont(myFont_head);
		panel_logs.add(lblHeading);
		
		btnrefresh=new JButton("Refresh");
		btnrefresh.setBounds(100,30,100,40);
		
		panel_logs.add(btnrefresh);
		
		String [] col = {"Customer Name","Invoice date","Invoice no","Product ID","Product Name","Quantity","Price","Total Price","Invoice Id"};	
			dtm=new DefaultTableModel(col,0);
	
			dtm.setRowCount(0);
			inv=new ArrayList<Invoice>();
			Database db3=new Database();
			db3.getallinvoice(inv);
			for(int i =0;i<inv.size();i++)                                                                                              
			{
						Object [] rd = new Object[9];
						rd[0]=inv.get(i).getcustomer_name();
						rd[1] = inv.get(i).getinvoice_date();
						rd[2] = inv.get(i).getinvoice_no();
						rd[3] = inv.get(i).getpid();
						rd[4] = inv.get(i).getpname();
						rd[5] = inv.get(i).getbuying_quantity();
						rd[6]=inv.get(i).getprice();
						rd[7]=inv.get(i).gettotal_price();
						rd[8]=inv.get(i).getinvoiceid();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(100,100,1000,600);
			jt.setDefaultEditor(Object.class,null);
			panel_logs.add(sp);
			
			
			btnrefresh.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
			inv=new ArrayList<Invoice>();
			Database db2=new Database();
			db2.getallinvoice(inv);
			for(int i =0;i<inv.size();i++)                                                                                              
			{
						Object [] rd = new Object[9];
						rd[0]=inv.get(i).getcustomer_name();
						rd[1] = inv.get(i).getinvoice_date();
						rd[2] = inv.get(i).getinvoice_no();
						rd[3] = inv.get(i).getpid();
						rd[4] = inv.get(i).getpname();
						rd[5] = inv.get(i).getbuying_quantity();
						rd[6]=inv.get(i).getprice();
						rd[7]=inv.get(i).gettotal_price();
						rd[8]=inv.get(i).getinvoiceid();
						
						dtm.addRow(rd);
			}
		}
	}
}