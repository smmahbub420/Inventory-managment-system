import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;

public class Remove_product extends JPanel implements ActionListener
{
	JPanel pnlremove_product;
	JLabel pidlbl;
	JButton btnsearch;
	JButton btnrefresh;
	JTextField pidtxt;
	JLabel lblHeading;
	JLabel lblName, lblSubCatagory, lblQuantity,lblIncomingDate,lblBuyingPrice,lblSellingPrice,lblProDetails,lblpid;
	JLabel lblpid2,lblName2, lblQuantity2, lblIncomingDate2, lblBuyingPrice2, lblSellingPrice2,lblSubCatagory2;
	JButton btndelete, btnReset;
	ArrayList<Product>pdt;
	ArrayList<Product>pdt1;
	
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	
	public Remove_product()
	{
		pnlremove_product=new JPanel();
		pnlremove_product.setLayout(null);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		pnlremove_product.setBackground(bgcolor);
		
		lblHeading= new JLabel("..:: Remove Product ::..");							// Label Heading
		this.lblHeading.setBounds(650,48,500,50);
		lblHeading.setFont(myFont_head);
		pnlremove_product.add(lblHeading);
		

		pidlbl=new JLabel("Product ID");
		this.pidlbl.setBounds(100,48,150,50);
		pidlbl.setFont(myFont_lbl);
		pnlremove_product.add(pidlbl);
		
		pidtxt=new JTextField();
		this.pidtxt.setBounds(275,47,200,44);
		pnlremove_product.add(pidtxt);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(500,48,100,50);
		pnlremove_product.add(btnsearch);
		
		lblName = new JLabel("Product Name: ");
		this.lblName.setBounds(100,125,200,50);
		lblName.setFont(myFont_lbl);
		pnlremove_product.add(lblName);

		lblName2 = new JLabel();
		this.lblName2.setBounds(275,124,200,50);
		pnlremove_product.add(lblName2);


		lblpid = new JLabel("Product ID: ");
 		this.lblpid.setBounds(100,202,200,50);
		lblpid.setFont(myFont_lbl);
		pnlremove_product.add(lblpid);
		
		lblpid2 = new JLabel();
		this.lblpid2.setBounds(275,203,200,50);
		pnlremove_product.add(lblpid2);

		lblQuantity = new JLabel("Product Quantity: ");
		this.lblQuantity.setBounds(100,280,200,50);
		lblQuantity.setFont(myFont_lbl);
		pnlremove_product.add(lblQuantity);

		lblQuantity2 = new JLabel();
		this.lblQuantity2.setBounds(275,279,200,50);
		pnlremove_product.add(lblQuantity2);

		lblIncomingDate = new JLabel("Product Incoming Date: ");
		this.lblIncomingDate.setBounds(100,358,200,50);
		lblIncomingDate.setFont(myFont_lbl);
		pnlremove_product.add(lblIncomingDate);

		lblIncomingDate2 = new JLabel();
		this.lblIncomingDate2.setBounds(275,357,200,50);
		pnlremove_product.add(lblIncomingDate2);

		lblBuyingPrice = new JLabel("Product Buying Price: ");
		this.lblBuyingPrice.setBounds(100,436,200,50);
		lblBuyingPrice.setFont(myFont_lbl);
		pnlremove_product.add(lblBuyingPrice);

		lblBuyingPrice2 = new JLabel();
		this.lblBuyingPrice2.setBounds(275,435,200,50);
		pnlremove_product.add(lblBuyingPrice2);

		lblSellingPrice = new JLabel("Product Selling Price: ");
		this.lblSellingPrice.setBounds(100,514,200,50);
		lblSellingPrice.setFont(myFont_lbl);
		pnlremove_product.add(lblSellingPrice);

		lblSellingPrice2 = new JLabel();
		this.lblSellingPrice2.setBounds(275,513,200,50);
		pnlremove_product.add(lblSellingPrice2);

		lblSubCatagory = new JLabel("Product Sub-Catagory: ");
		this.lblSubCatagory.setBounds(100,592,200,50);
		lblSubCatagory.setFont(myFont_lbl);
		pnlremove_product.add(lblSubCatagory);

		lblSubCatagory2=new JLabel();
		this.lblSubCatagory2.setBounds(275,591,200,50);
		pnlremove_product.add(lblSubCatagory2);
		
	
		
		btndelete= new JButton("Delete");							// Submit button
		this.btndelete.setBounds(100,669,150,50);
		pnlremove_product.add(btndelete);

		btnReset = new JButton("Reset");							// Reset button
		this.btnReset.setBounds(275,669,150,50);
		pnlremove_product.add(btnReset);
		
		btnrefresh=new JButton("Refresh");
		btnrefresh.setBounds(800,150,170,40);
		pnlremove_product.add(btnrefresh);
		
		
		String [] col = {"Product id","Product name","Quantity","Buying Price","Selling Price","Incoming Date","Sub-Catagory"};	
		dtm=new DefaultTableModel(col,0);
		
		
		dtm.setRowCount(0);
			pdt1=new ArrayList<Product>();
			Database db2=new Database();
			db2.getallProduct(pdt1);
			for(int i =0;i<pdt1.size();i++)                                                                                              
			{
						Object [] rd = new Object[7];
						rd[0] = pdt1.get(i).getpid();
						rd[1] = pdt1.get(i).getpname();
						rd[2] = pdt1.get(i).getquantity();
						rd[3] = pdt1.get(i).getbuying_price();
						rd[4]=pdt1.get(i).getselling_price();
						rd[5]=pdt1.get(i).getincoming_date();
						rd[6]=pdt1.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(500,200,700,500);
			jt.setDefaultEditor(Object.class,null);
			pnlremove_product.add(sp);
		
		
		
		btnsearch.addActionListener(this);
		btnReset.addActionListener(this);
		btndelete.addActionListener(this);
		btnrefresh.addActionListener(this);
				
	}
	public void actionPerformed(ActionEvent e)
	{
		
		if (e.getSource()==btnsearch)
		{
			lblName2.setText("");
			lblpid2.setText("");
			lblQuantity2.setText("");
			lblIncomingDate2.setText("");
			lblBuyingPrice2.setText("");
			lblSellingPrice2.setText("");
			lblSubCatagory2.setText("");
			
			
			pdt=new ArrayList<Product>();
			Database db=new Database();
			db.searchProduct(pdt,Integer.parseInt(pidtxt.getText()));
			lblName2.setText(pdt.get(0).getpname());
			lblpid2.setText(pdt.get(0).getpid());
			lblQuantity2.setText(pdt.get(0).getquantity());
			lblIncomingDate2.setText(pdt.get(0).getincoming_date());
			lblBuyingPrice2.setText(pdt.get(0).getbuying_price());
			lblSellingPrice2.setText(pdt.get(0).getselling_price());
			lblSubCatagory2.setText(pdt.get(0).getsubcat_name());
			
			pidtxt.setText("");
			
		}
		else if(e.getSource()==btndelete)
		{
			try{
			Database db=new Database();
			db.deleteproduct(Integer.parseInt(lblpid2.getText()));
			JOptionPane.showMessageDialog(null,"Product removed");
			pidtxt.setText("");
			lblName2.setText("");
			lblpid2.setText("");
			lblQuantity2.setText("");
			lblIncomingDate2.setText("");
			lblBuyingPrice2.setText("");
			lblSellingPrice2.setText("");
			lblSubCatagory2.setText("");
			}catch(Exception ie)
			{
				JOptionPane.showMessageDialog(null,"Something Worng");
			}
		}
		else if(e.getSource()==btnReset)
		{
			pidtxt.setText("");
			lblName2.setText("");
			lblpid2.setText("");
			lblQuantity2.setText("");
			lblIncomingDate2.setText("");
			lblBuyingPrice2.setText("");
			lblSellingPrice2.setText("");
			lblSubCatagory2.setText("");
		}
		else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
			pdt1=new ArrayList<Product>();
			Database db2=new Database();
			db2.getallProduct(pdt1);
			for(int i =0;i<pdt1.size();i++)                                                                                              
			{
						Object [] rd = new Object[7];
						rd[0] = pdt1.get(i).getpid();
						rd[1] = pdt1.get(i).getpname();
						rd[2] = pdt1.get(i).getquantity();
						rd[3] = pdt1.get(i).getbuying_price();
						rd[4]=pdt1.get(i).getselling_price();
						rd[5]=pdt1.get(i).getincoming_date();
						rd[6]=pdt1.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
		}
	}
			
}