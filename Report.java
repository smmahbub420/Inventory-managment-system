import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;

public class Report extends JPanel implements ActionListener
{
	JPanel panel_report;
	JLabel lblto, lblfrom,lblHeading;
	JButton btnshow;
	JTextField txtto,txtfrom;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	public String [] category;
	public String [] subcatagory;
	ArrayList<Category> ins;
	ArrayList<Category> ins2;
	ArrayList<Product>pdt;
	ArrayList<Invoice>inv;
	
		JLabel lblyear;
	JTextField txtyear;
	JButton btnyear;

	public Report()
	{
		
		panel_report=new JPanel();
		panel_report.setLayout(null);
		
		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		panel_report.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Sales Report ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,20,400,50);
		lblHeading.setFont(myFont_head);
		panel_report.add(lblHeading);
		
		
		lblfrom=new JLabel("From:");
		this.lblfrom.setBounds(20,80,100,50);
		lblfrom.setFont(myFont_lbl);
		panel_report.add(lblfrom);
		
		txtfrom=new JTextField();
		this.txtfrom.setBounds(130,79,100,50);
		SimpleDateFormat datefrom = new SimpleDateFormat("yyyy/MM/dd");
		Date datefrm = new Date();
		txtfrom.setText(datefrom.format(datefrm)); 
		panel_report.add(txtfrom);
		
		
		lblto=new JLabel("To:");
		this.lblto.setBounds(250,80,100,50);
		lblto.setFont(myFont_lbl);
		panel_report.add(lblto);
		
		txtto=new JTextField();
		this.txtto.setBounds(360,79,100,50);
		SimpleDateFormat dateto = new SimpleDateFormat("yyyy/MM/dd");
		Date datetod = new Date();
		txtto.setText(dateto.format(datetod)); 
		panel_report.add(txtto);
		
		
		btnshow=new JButton("Show");
		this.btnshow.setBounds(480,80,150,50);
		panel_report.add(btnshow);
		
		String [] col = {"Product id","Product name","Total Quantity","Total Sale"};	
		dtm=new DefaultTableModel(col,0);
		dtm.setRowCount(0);
		this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(500,200,700,500);
			jt.setDefaultEditor(Object.class,null);
			panel_report.add(sp);
		
		btnshow.addActionListener(this);
		
		
		
		lblyear=new JLabel("Input Year:");
		this.lblyear.setBounds(20,150,150,50);
		lblyear.setFont(myFont_lbl);
		panel_report.add(lblyear);
		
		txtyear=new JTextField();
		this.txtyear.setBounds(180,149,100,50);
		panel_report.add(txtyear);
		
		btnyear=new JButton("Year Wise");
		this.btnyear.setBounds(300,150,150,50);
		panel_report.add(btnyear);
		
		btnyear.addActionListener(this);

		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnshow)
		{
			dtm.setRowCount(0);
			inv=new ArrayList<Invoice>();
			Database db=new Database();
			db.getreport(inv,txtfrom.getText(),txtto.getText());
			for(int i =0;i<inv.size();i++)                                                                                              
			{
						Object [] rd = new Object[4];
						rd[0] = inv.get(i).getpid();
						rd[1] = inv.get(i).getpname();
						rd[2] = inv.get(i).getbuying_quantity();
						rd[3]=inv.get(i).gettotal_price();
						
						dtm.addRow(rd);
			}
			double sum=0;
					for(int i=0;i<jt.getRowCount();i++)
					{
						sum=sum+Double.parseDouble(jt.getValueAt(i,3).toString());
					}
					String sumString = new Double(sum).toString(); 
					String [] totalrow={"","","Total=",sumString};
					dtm.addRow(totalrow);
		}
		else if(e.getSource()==btnyear)
		{
			
			dtm.setRowCount(0);
			inv=new ArrayList<Invoice>();
			Database db=new Database();
			String yearfrom=txtyear.getText() +"/01/01";
			String yearto=txtyear.getText() +"/12/31";
			db.getreport(inv,yearfrom,yearto);
			for(int i =0;i<inv.size();i++)                                                                                              
			{
						Object [] rd = new Object[4];
						rd[0] = inv.get(i).getpid();
						rd[1] = inv.get(i).getpname();
						rd[2] = inv.get(i).getbuying_quantity();
						rd[3]=inv.get(i).gettotal_price();
						
						dtm.addRow(rd);
						
			}
			double sum=0;
					for(int i=0;i<jt.getRowCount();i++)
					{
						sum=sum+Double.parseDouble(jt.getValueAt(i,3).toString());
					}
					String sumString = new Double(sum).toString(); 
					String [] totalrow={"","","Total=",sumString};
					dtm.addRow(totalrow);
	}
	
	}
}