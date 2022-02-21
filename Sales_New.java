import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;

public class Sales_New extends JPanel implements ActionListener
{
	JPanel panelS;
	JTextField txtCname;
	JTextField txtinvoice, txtDate;
	JLabel lblCname,lblinvoice,lblDate,lblHeading,lblinvoice_no;
	JLabel pidlbl;
	JTextField txtpid;
	JButton btnsearch,btnrefresh;
	JLabel lblpid1,lblpname1,lblprice1,lblquantity,lblsubcat1;
	JLabel lblpid2,lblpname2,lblprice2,lblsubcat2;
	JTextField txtquantity;
	JButton btnadd,btnreset,btngenerate,btninvoicenum,btnmodify,btnnew_sale;
	
	ArrayList<Product>pdt;
	ArrayList<Product>pdt1;
	ArrayList<Product>pdt5;
	
	JScrollPane sp,spinvoice;
	DefaultTableModel dtm,dtminvoice;
	JTable jt,jtinvoice;
	public int genaratecount;
	
	public Sales_New()
	{
		this.genaratecount=0;
		panelS=new JPanel();
		panelS.setLayout(null);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		panelS.setBackground(bgcolor);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		lblHeading = new JLabel("..:: New Sale ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,10,450,50);
		lblHeading.setFont(myFont_head);
	    panelS.add(lblHeading);
		
		lblCname=new JLabel("Customer Name");
		this.lblCname.setBounds(20,20,150,50);
		lblCname.setFont(myFont_lbl);
		panelS.add(lblCname);
		
		txtCname=new JTextField();
		this.txtCname.setBounds(151,29,100,40);
		panelS.add(txtCname);
		
		lblDate=new JLabel("Date");
		this.lblDate.setBounds(280,30,80,40);
		lblDate.setFont(myFont_lbl);
		panelS.add(lblDate);
		
		txtDate=new JTextField();
		this.txtDate.setBounds(351,29,100,50);
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		txtDate.setText(dateFor.format(date)); 
		panelS.add(txtDate);

		
		lblinvoice=new JLabel("Invoice No:");
		this.lblinvoice.setBounds(20,100,150,50);
		lblinvoice.setFont(myFont_lbl);
		panelS.add(lblinvoice);
		
	
		
		lblinvoice_no=new JLabel();
		this.lblinvoice_no.setBounds(150,99,100,50);
		panelS.add(lblinvoice_no);
		
		Random rand = new Random();
			int n = rand.nextInt(10000000);
			String sI = Integer.toString(n);
			lblinvoice_no.setText(sI);
		
		pidlbl=new JLabel("Product_ID");
		this.pidlbl.setBounds(20,170,100,50);
		pidlbl.setFont(myFont_lbl);
		panelS.add(pidlbl);
		
		txtpid=new JTextField();
		this.txtpid.setBounds(150,169,100,50);
		panelS.add(txtpid);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(300,170,100,50);
		panelS.add(btnsearch);
		
		lblpid1=new JLabel("Product ID:");
		this.lblpid1.setBounds(20,240,100,50);
		lblpid1.setFont(myFont_lbl);
		panelS.add(lblpid1);
		
		lblpid2=new JLabel();
		this.lblpid2.setBounds(150,240,80,50);
		panelS.add(lblpid2);
		
		lblpname1=new JLabel("Product Name:");
		this.lblpname1.setBounds(230,240,120,50);
		lblpname1.setFont(myFont_lbl);
		panelS.add(lblpname1);
		
		lblpname2=new JLabel();
		this.lblpname2.setBounds(360,240,100,50);
		panelS.add(lblpname2);

		lblprice1=new JLabel("Price:");
		this.lblprice1.setBounds(490,240,100,50);
		lblprice1.setFont(myFont_lbl);
		panelS.add(lblprice1);
		
		lblprice2=new JLabel();
		this.lblprice2.setBounds(600,240,100,50);
		panelS.add(lblprice2);

		lblquantity=new JLabel("Quantity:");
		this.lblquantity.setBounds(730,240,100,50);
		lblquantity.setFont(myFont_lbl);
		panelS.add(lblquantity);
		
		txtquantity=new JTextField();
		this.txtquantity.setBounds(850,239,100,50);
		panelS.add(txtquantity);
		
		lblsubcat1=new JLabel("Sub-Category:");
		this.lblsubcat1.setBounds(1000,240,100,50);
		lblsubcat1.setFont(myFont_lbl);
		panelS.add(lblsubcat1);
		
		lblsubcat2=new JLabel();
		this.lblsubcat2.setBounds(1120,240,150,50);
		panelS.add(lblsubcat2);
		
		btnadd=new JButton("ADD Product");
		this.btnadd.setBounds(850,170,150,50);
		panelS.add(btnadd);
		
		btnreset=new JButton("Reset");
		this.btnreset.setBounds(250,700,150,50);
		panelS.add(btnreset);
		
		btnmodify=new JButton("Modify");
		this.btnmodify.setBounds(450,700,100,50);
		panelS.add(btnmodify);
		
		btngenerate=new JButton("Generate Invoice");
		this.btngenerate.setBounds(600,700,200,50);
		panelS.add(btngenerate);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(900,700,150,50);
		panelS.add(btnrefresh);
		
		btnnew_sale=new JButton("New Sales");
		this.btnnew_sale.setBounds(900,50,200,100);
		panelS.add(btnnew_sale);
		
		
		
		
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
			panelS.add(sp);
		
		
		
		
		String [] colinvoice = {"Product_ID","Product name","Quantity","Price","Total Price"};	
		dtminvoice=new DefaultTableModel(colinvoice,0);
				dtminvoice.setRowCount(0);
		
		
			this.jtinvoice=new JTable(dtminvoice);
			spinvoice=new JScrollPane(jtinvoice);
			spinvoice.setBounds(20,300,800,400);
			jtinvoice.setDefaultEditor(Object.class,null);
			panelS.add(spinvoice);
		
		
		
		
		
		
		
		btnsearch.addActionListener(this);
		btnadd.addActionListener(this);
		btnreset.addActionListener(this);
		btngenerate.addActionListener(this);
		btnmodify.addActionListener(this);
		btnnew_sale.addActionListener(this);
		btnrefresh.addActionListener(this);
	
		
		
	}
		public void actionPerformed(ActionEvent e)
		{
			
			if(e.getSource()== btnsearch)
			{ 
		try{
				lblpid2.setText("");
				lblpname2.setText("");
				txtquantity.setText("");
				lblprice2.setText("");
				lblsubcat2.setText("");
				
			pdt=new ArrayList<Product>();
			Database db=new Database();
			db.searchProduct(pdt,Integer.parseInt(txtpid.getText()));
			lblpname2.setText(pdt.get(0).getpname());
			lblpid2.setText(pdt.get(0).getpid());
			lblprice2.setText(pdt.get(0).getselling_price());
			lblsubcat2.setText(pdt.get(0).getsubcat_name());
			
			txtpid.setText("");
			}
			catch(Exception ie)
			{
				JOptionPane.showMessageDialog(null,"Product Not Found");
			}				
			}
			else if(e.getSource()== btnadd)
			{
				try{
				int productquantity=Integer.parseInt(pdt.get(0).getquantity());
				int buyingquantity=Integer.parseInt(txtquantity.getText());
				if(productquantity>=buyingquantity)
				{
					String pname=lblpname2.getText();
					String pid=lblpid2.getText();
					String price=lblprice2.getText();
					String quantity=txtquantity.getText();
					Double totalprice=Double.parseDouble(lblprice2.getText())*Integer.parseInt(txtquantity.getText());
					String totalprices = new Double(totalprice).toString(); 
					String []row={pid,pname,quantity,price,totalprices};
					dtminvoice.addRow(row);
					
					
					

					
				lblpid2.setText("");
				lblpname2.setText("");
				txtquantity.setText("");
				lblprice2.setText("");
				lblsubcat2.setText("");
					
				}
				else{
					JOptionPane.showMessageDialog(null,"Insufficient Product");
				}
				}catch(Exception ie)
				{
					JOptionPane.showMessageDialog(null,"Product is not added");
				}
				
			}
			else if(e.getSource()== btnreset)
			{
								lblpid2.setText("");
				lblpname2.setText("");
				txtquantity.setText("");
				lblprice2.setText("");
				lblsubcat2.setText("");
				
				dtminvoice.setRowCount(0);
				
			}
			else if(e.getSource()== btngenerate)
			{
				try{
				this.genaratecount++;
				if(this.genaratecount==1)
				{
									double sum=0;
					for(int i=0;i<jtinvoice.getRowCount();i++)
					{
						sum=sum+Double.parseDouble(jtinvoice.getValueAt(i,4).toString());
					}
					String sumString = new Double(sum).toString(); 
					String [] totalrow={"","","","Total=",sumString};
					dtminvoice.addRow(totalrow);
					
					
					int rows=jtinvoice.getRowCount();
					Database db5=new Database();
					for(int i = 0; i<rows; i++)
						{
									String pid = (String)jtinvoice.getValueAt(i, 0);
									String pname = (String)jtinvoice.getValueAt(i, 1);
									String quantity = (String)jtinvoice.getValueAt(i, 2);
									String price = (String)jtinvoice.getValueAt(i, 3);
									String totalprice = (String)jtinvoice.getValueAt(i, 4);

									
									db5.addinvoice(txtCname.getText(),txtDate.getText(),Integer.parseInt(lblinvoice_no.getText()),Integer.parseInt(pid),pname,Integer.parseInt(quantity),Double.parseDouble(price),Double.parseDouble(totalprice));
									
									pdt5=new ArrayList<Product>();
									db5.getproductquantity(pdt5,Integer.parseInt(pid));
									int totalquantity=Integer.parseInt(pdt5.get(0).getquantity());
									int buyquantity=Integer.parseInt(quantity);
									
									int remaningquantity=totalquantity-buyquantity;
									db5.subquantity(remaningquantity,Integer.parseInt(pid));	
						}
						this.genaratecount=0;
				}
				}catch(Exception ie)
				{
					//JOptionPane.showMessageDialog(null,"Invoice Already Genarated");
				}
	
	
			}
			
			
			else if(e.getSource()==btnmodify)
			{
				try{
				int n=jtinvoice.getRowCount();
				dtminvoice.setRowCount(n-1);
				}catch (Exception ie)
				{
					JOptionPane.showMessageDialog(null,"Nothing to Modify");
				}
			}
			
			else if(e.getSource()==btnnew_sale)
			{
				txtCname.setText("");
				lblpid2.setText("");
				lblpname2.setText("");
				txtquantity.setText("");
				lblprice2.setText("");
				lblsubcat2.setText("");
				
				dtminvoice.setRowCount(0);
				Random rand = new Random();
				int n = rand.nextInt(10000000);
				String sI = Integer.toString(n);
				lblinvoice_no.setText(sI);
				
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
			
			this.genaratecount=0;
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