import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;

public class Update_product extends JPanel implements ActionListener
{
	JLabel pidlbl;
	JButton btnsearch;
	JTextField pidtxt;
	JTextField txtsub;
	JLabel lblpid1,lblHeading;
	JLabel lblName, lblSubCatagory, lblQuantity,lblIncomingDate,lblBuyingPrice,lblSellingPrice,lblProDetails,lblpid;
	JTextField txtName, txtQuantity, txtIncomingDate, txtBuyingPrice, txtSellingPrice;
	JButton btnUpdate, btnReset,btnselectCat,btnselectSubcat;
	JButton btnrefresh;
	JPanel pnl_update_product;
	ArrayList<Product>pdt;
	
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	
	public Update_product()
	{
		
		pnl_update_product = new JPanel();
		pnl_update_product.setLayout(null);


		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		pnl_update_product.setBackground(bgcolor);
		
		lblHeading= new JLabel("..:: Update Product ::..");							// Label Heading
		this.lblHeading.setBounds(650,48,500,50);
		lblHeading.setFont(myFont_head);
		pnl_update_product.add(lblHeading);
		

		pidlbl=new JLabel("Product ID");
		this.pidlbl.setBounds(100,48,150,50);
		pidlbl.setFont(myFont_lbl);
		pnl_update_product.add(pidlbl);
		
		pidtxt=new JTextField();
		this.pidtxt.setBounds(275,47,200,44);
		pnl_update_product.add(pidtxt);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(500,48,100,50);
		pnl_update_product.add(btnsearch);
		
		lblName = new JLabel("Product Name: ");
		this.lblName.setBounds(100,125,200,50);
		lblName.setFont(myFont_lbl);
		pnl_update_product.add(lblName);

		txtName = new JTextField();
		this.txtName.setBounds(275,124,200,50);
		pnl_update_product.add(txtName);


		lblpid = new JLabel("Product ID: ");
 		this.lblpid.setBounds(100,202,200,50);
		lblpid.setFont(myFont_lbl);
		pnl_update_product.add(lblpid);
		
		lblpid1 = new JLabel();
		this.lblpid1.setBounds(275,203,200,50);
		pnl_update_product.add(lblpid1);

		lblQuantity = new JLabel("Product Quantity: ");
		this.lblQuantity.setBounds(100,280,200,50);
		lblQuantity.setFont(myFont_lbl);
		pnl_update_product.add(lblQuantity);

		txtQuantity = new JTextField();
		this.txtQuantity.setBounds(275,279,200,50);
		pnl_update_product.add(txtQuantity);

		lblIncomingDate = new JLabel("Product Incoming Date: ");
		this.lblIncomingDate.setBounds(100,358,200,50);
		lblIncomingDate.setFont(myFont_lbl);
		pnl_update_product.add(lblIncomingDate);

		txtIncomingDate = new JTextField();
		this.txtIncomingDate.setBounds(275,357,200,50);
		pnl_update_product.add(txtIncomingDate);

		lblBuyingPrice = new JLabel("Product Buying Price: ");
		this.lblBuyingPrice.setBounds(100,436,200,50);
		lblBuyingPrice.setFont(myFont_lbl);
		pnl_update_product.add(lblBuyingPrice);

		txtBuyingPrice = new JTextField();
		this.txtBuyingPrice.setBounds(275,435,200,50);
		pnl_update_product.add(txtBuyingPrice);

		lblSellingPrice = new JLabel("Product Selling Price: ");
		this.lblSellingPrice.setBounds(100,514,200,50);
		lblSellingPrice.setFont(myFont_lbl);
		pnl_update_product.add(lblSellingPrice);

		txtSellingPrice = new JTextField();
		this.txtSellingPrice.setBounds(275,513,200,50);
		pnl_update_product.add(txtSellingPrice);

		lblSubCatagory = new JLabel("Product Sub-Catagory: ");
		this.lblSubCatagory.setBounds(100,592,200,50);
		lblSubCatagory.setFont(myFont_lbl);
		pnl_update_product.add(lblSubCatagory);

		txtsub=new JTextField();
		this.txtsub.setBounds(275,591,200,50);
		pnl_update_product.add(txtsub);
		
		
		btnrefresh=new JButton("Refresh");
		btnrefresh.setBounds(800,150,170,40);
		pnl_update_product.add(btnrefresh);
		btnrefresh.addActionListener(this);
	
		
		btnUpdate = new JButton("Update");							// Submit button
		this.btnUpdate.setBounds(100,669,150,50);
		pnl_update_product.add(btnUpdate);

		btnReset = new JButton("Reset");							// Reset button
		this.btnReset.setBounds(275,669,150,50);
		pnl_update_product.add(btnReset);
		
		String [] col = {"Product id","Product name","Quantity","Buying Price","Selling Price","Incoming Date","Sub-Catagory"};	
	dtm=new DefaultTableModel(col,0);
		
		
		dtm.setRowCount(0);
			pdt=new ArrayList<Product>();
			Database db2=new Database();
			db2.getallProduct(pdt);
			for(int i =0;i<pdt.size();i++)                                                                                              
			{
						Object [] rd = new Object[7];
						rd[0] = pdt.get(i).getpid();
						rd[1] = pdt.get(i).getpname();
						rd[2] = pdt.get(i).getquantity();
						rd[3] = pdt.get(i).getbuying_price();
						rd[4]=pdt.get(i).getselling_price();
						rd[5]=pdt.get(i).getincoming_date();
						rd[6]=pdt.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(500,200,700,500);
			jt.setDefaultEditor(Object.class,null);
			pnl_update_product.add(sp);
		
		btnsearch.addActionListener(this);
		btnReset.addActionListener(this);
		btnUpdate.addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==btnsearch)
		{
			pdt=new ArrayList<Product>();
			Database db=new Database();
			db.searchProduct(pdt,Integer.parseInt(pidtxt.getText()));
			txtName.setText(pdt.get(0).getpname());
			lblpid1.setText(pdt.get(0).getpid());
			txtQuantity.setText(pdt.get(0).getquantity());
			txtIncomingDate.setText(pdt.get(0).getincoming_date());
			txtBuyingPrice.setText(pdt.get(0).getbuying_price());
			txtSellingPrice.setText(pdt.get(0).getselling_price());
			txtsub.setText(pdt.get(0).getsubcat_name());
			
			pidtxt.setText("");
			
		}
		else if(e.getSource()==btnReset)
		{
			pidtxt.setText("");
			txtName.setText("");
			lblpid1.setText("");
			txtQuantity.setText("");
			txtIncomingDate.setText("");
			txtBuyingPrice.setText("");
			txtSellingPrice.setText("");
			txtsub.setText("");
		}
		else if(e.getSource()==btnUpdate)
		{
			try{
			Database db1=new Database();
			db1.updateProduct(Integer.parseInt(lblpid1.getText()),txtName.getText(),Integer.parseInt(txtQuantity.getText()),Double.parseDouble(txtBuyingPrice.getText()),Double.parseDouble(txtSellingPrice.getText()),txtIncomingDate.getText(),txtsub.getText());
			JOptionPane.showMessageDialog(null,"Data Updated");
			pidtxt.setText("");
			txtName.setText("");
			lblpid1.setText("");
			txtQuantity.setText("");
			txtIncomingDate.setText("");
			txtBuyingPrice.setText("");
			txtSellingPrice.setText("");
			txtsub.setText("");
			}catch(Exception ie)
			{
				JOptionPane.showMessageDialog(null,"Something Worng");
			}
		}
		else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
			pdt=new ArrayList<Product>();
			Database db2=new Database();
			db2.getallProduct(pdt);
			for(int i =0;i<pdt.size();i++)                                                                                              
			{
						Object [] rd = new Object[7];
						rd[0] = pdt.get(i).getpid();
						rd[1] = pdt.get(i).getpname();
						rd[2] = pdt.get(i).getquantity();
						rd[3] = pdt.get(i).getbuying_price();
						rd[4]=pdt.get(i).getselling_price();
						rd[5]=pdt.get(i).getincoming_date();
						rd[6]=pdt.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
		}
	}
}