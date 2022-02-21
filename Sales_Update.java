import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;
public class Sales_Update extends JPanel  implements ActionListener
{
	JPanel pnlsales_update;
	JLabel lblHeading;
	JLabel lblinvoice,lblcustomer_name,lblinvoicedate,lblinvoice_no,lblinvoice_no2;
	JTextField txtinvoice,txtcustomer_name,txtinvoicedate;
	JButton btnsearch,btnupdate_invoice,btnmodify,btnrefresh;
	
	JScrollPane sp,spinvoice;
	DefaultTableModel dtm,dtminvoice;
	JTable jt,jtinvoice;
	
	ArrayList<Invoice>inv;
	ArrayList<Invoice>inv1;
	ArrayList<Product>pdt1;
	ArrayList<Product>pdt;
	ArrayList<Product>pdt7;
	
	public Sales_Update()
	{
		pnlsales_update=new JPanel();
		pnlsales_update.setLayout(null);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		pnlsales_update.setBackground(bgcolor);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		lblHeading = new JLabel("..:: Update Sale ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,10,450,50);
		lblHeading.setFont(myFont_head);
	    pnlsales_update.add(lblHeading);
		
		lblinvoice=new JLabel("Invoice No:");
		this.lblinvoice.setBounds(20,100,150,50);
		lblinvoice.setFont(myFont_lbl);
		pnlsales_update.add(lblinvoice);
		
		txtinvoice=new JTextField();
		this.txtinvoice.setBounds(160,99,110,50);
		pnlsales_update.add(txtinvoice);
		
		btnmodify=new JButton("Modify");
		this.btnmodify.setBounds(450,700,100,50);
		pnlsales_update.add(btnmodify);
		
		lblcustomer_name=new JLabel("Customer Name:");
		this.lblcustomer_name.setBounds(20,170,150,50);
		lblcustomer_name.setFont(myFont_lbl);
		pnlsales_update.add(lblcustomer_name);
		
		txtcustomer_name=new JTextField();
		this.txtcustomer_name.setBounds(180,169,100,50);
		pnlsales_update.add(txtcustomer_name);
		
		
		lblinvoicedate=new JLabel("Date:");
		this.lblinvoicedate.setBounds(300,170,100,50);
		lblinvoicedate.setFont(myFont_lbl);
		pnlsales_update.add(lblinvoicedate);
		
		txtinvoicedate=new JTextField();
		this.txtinvoicedate.setBounds(450,169,100,50);
		pnlsales_update.add(txtinvoicedate);
		
		
		lblinvoice_no=new JLabel("Invoice no:");
		this.lblinvoice_no.setBounds(570,170,100,50);
		lblinvoice_no.setFont(myFont_lbl);
		pnlsales_update.add(lblinvoice_no);
		
		
		
		lblinvoice_no2=new JLabel("");
		this.lblinvoice_no2.setBounds(700,170,100,50);
		lblinvoice_no2.setFont(myFont_lbl);
		pnlsales_update.add(lblinvoice_no2);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(300,100,100,50);
		pnlsales_update.add(btnsearch);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(900,700,150,50);
		pnlsales_update.add(btnrefresh);
		
		String [] col = {"Product ID","Product Name","Quantity","Price","Incoming Date","Sub-Catagory"};	
		dtm=new DefaultTableModel(col,0);
		
		
		dtm.setRowCount(0);
		pdt1=new ArrayList<Product>();
		Database db2=new Database();
		db2.getallProduct(pdt1);
		for(int i =0;i<pdt1.size();i++)                                                                                              
		{
						Object [] rd = new Object[6];
						rd[0] = pdt1.get(i).getpid();
						rd[1] = pdt1.get(i).getpname();
						rd[2] = pdt1.get(i).getquantity();
						rd[3]=pdt1.get(i).getselling_price();
						rd[4]=pdt1.get(i).getincoming_date();
						rd[5]=pdt1.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(850,300,400,400);
			jt.setDefaultEditor(Object.class,null);
			pnlsales_update.add(sp);
			
			String [] colinvoice = {"Product_ID","Product name","Quantity","Price","Total Price"};	
		dtminvoice=new DefaultTableModel(colinvoice,0);
				dtminvoice.setRowCount(0);
		
		
			this.jtinvoice=new JTable(dtminvoice);
			
			
			spinvoice=new JScrollPane(jtinvoice);
			spinvoice.setBounds(20,300,800,400);
			//jtinvoice.setDefaultEditor(Object.class,null);
			pnlsales_update.add(spinvoice);
			
			
			
			btnupdate_invoice=new JButton("Update Invoice");
		this.btnupdate_invoice.setBounds(600,700,200,50);
		pnlsales_update.add(btnupdate_invoice);
		
			btnsearch.addActionListener(this);
			btnupdate_invoice.addActionListener(this);
					btnmodify.addActionListener(this);
			btnrefresh.addActionListener(this);
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnsearch)
		{
			dtminvoice.setRowCount(0);
			inv=new ArrayList<Invoice>();
			Database db1=new Database();
			db1.getinvoice(inv,Integer.parseInt(txtinvoice.getText()));
			for(int i =0;i<inv.size();i++)                                                                                              
			{
						Object [] rd = new Object[5];
						rd[0] = inv.get(i).getpid();
						rd[1] = inv.get(i).getpname();
						rd[2] = inv.get(i).getbuying_quantity();
						rd[3]=inv.get(i).getprice();
						rd[4]=inv.get(i).gettotal_price();
						lblinvoice_no2.setText(inv.get(i).getinvoice_no());
						txtcustomer_name.setText(inv.get(i).getcustomer_name());
						txtinvoicedate.setText(inv.get(i).getinvoice_date());
						
						dtminvoice.addRow(rd);
			}
			
			
			
		}
		else if(e.getSource()==btnupdate_invoice)
		{
			
					int rows=jtinvoice.getRowCount();
					
					for(int i = 0; i<rows; i++)
						{
									String pid = (String)jtinvoice.getValueAt(i, 0);
									String pname = (String)jtinvoice.getValueAt(i, 1);
									String quantity = (String)jtinvoice.getValueAt(i, 2);
									String price = (String)jtinvoice.getValueAt(i, 3);
									String totalprice = (String)jtinvoice.getValueAt(i, 4);

									Database db5=new Database();
									
									inv1=new ArrayList<Invoice>();
								
									if(Integer.parseInt(inv.get(i).getbuying_quantity())<=Integer.parseInt(quantity))
									{
										db5.getinvoice_id(inv1,Integer.parseInt(lblinvoice_no2.getText()),Integer.parseInt(pid));
										int n=Integer.parseInt(inv1.get(0).getinvoiceid());
										db5.updatesale(txtcustomer_name.getText(),txtinvoicedate.getText(),Integer.parseInt(pid),pname,Integer.parseInt(quantity),Double.parseDouble(price),Double.parseDouble(totalprice),n);
										pdt7=new ArrayList<Product>();
										db5.getproductquantity(pdt7,Integer.parseInt(pid));
										int aa=Integer.parseInt(pdt7.get(0).getquantity());
										int a=Integer.parseInt(quantity)-Integer.parseInt(inv.get(i).getbuying_quantity());
										int aaa=aa-a;
										
										db5.subquantity(aaa,Integer.parseInt(pid));
										
									}
									else
									{
										db5.getinvoice_id(inv1,Integer.parseInt(lblinvoice_no2.getText()),Integer.parseInt(pid));
										int n=Integer.parseInt(inv1.get(0).getinvoiceid());
										db5.updatesale(txtcustomer_name.getText(),txtinvoicedate.getText(),Integer.parseInt(pid),pname,Integer.parseInt(quantity),Double.parseDouble(price),Double.parseDouble(totalprice),n);
										pdt7=new ArrayList<Product>();
										db5.getproductquantity(pdt7,Integer.parseInt(pid));
										int aa=Integer.parseInt(pdt7.get(0).getquantity());
										int a=Integer.parseInt(inv.get(i).getbuying_quantity())-Integer.parseInt(quantity);
										int aaa=aa+a;
										db5.subquantity(aaa,Integer.parseInt(pid));
										
									}
									JOptionPane.showMessageDialog(null,"Invoice Modified");
						}
						
						
			
		}
		else if(e.getSource()==btnmodify)
			{
				
				int n=jtinvoice.getRowCount();
				if(n>=1)
				{
					String quantity = (String)jtinvoice.getValueAt(n-1, 2);
					String pid = (String)jtinvoice.getValueAt(n-1, 0);
					dtminvoice.setRowCount(n-1);
					Database d1=new Database();
					inv1=new ArrayList<Invoice>();
					d1.getinvoice_id(inv1,Integer.parseInt(lblinvoice_no2.getText()),Integer.parseInt(pid));
					int nn=Integer.parseInt(inv1.get(0).getinvoiceid());
					d1.deleteinvoice(nn);
					pdt=new ArrayList<Product>();
					d1.searchProduct(pdt,Integer.parseInt(pid));
					int pquantity=Integer.parseInt(this.pdt.get(0).getquantity());
					int newquantity=Integer.parseInt(quantity);
					int totalquantity=pquantity+newquantity;
					d1.addquantity(totalquantity,Integer.parseInt(pid));
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Nothing to Modify");
				}
			}
			else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
		pdt1=new ArrayList<Product>();
		Database db2=new Database();
		db2.getallProduct(pdt1);
		for(int i =0;i<pdt1.size();i++)                                                                                              
		{
						Object [] rd = new Object[6];
						rd[0] = pdt1.get(i).getpid();
						rd[1] = pdt1.get(i).getpname();
						rd[2] = pdt1.get(i).getquantity();
						rd[3]=pdt1.get(i).getselling_price();
						rd[4]=pdt1.get(i).getincoming_date();
						rd[5]=pdt1.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
		}
	}
}