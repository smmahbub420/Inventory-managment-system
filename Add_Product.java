import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;


public class Add_Product extends JPanel implements ActionListener
{
	JLabel lblName, lblSubCatagory, lblQuantity,lblIncomingDate,lblBuyingPrice,lblSellingPrice,lblProDetails,lblHeading;
	JTextField txtName, txtQuantity, txtIncomingDate, txtBuyingPrice, txtSellingPrice;
	JButton btnSubmit, btnReset,btnrefresh,btnselectSubcat;
	JComboBox cmbCatagory, cmbSubCatagory;
	JPanel panel1;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	public String [] category;
	public String [] subcatagory;
	ArrayList<Category> ins;
	ArrayList<Category> ins2;
	ArrayList<Product>pdt;
	
	public Add_Product()
	{ 
		panel1 = new JPanel();
		panel1.setLayout(null);


		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		Font myFont_subHead = new Font("Cambria", Font.ITALIC + Font.BOLD, 25);

		Color bgcolor = new Color(204, 255, 204);				// color
		panel1.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Add Product ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,20,400,50);
		lblHeading.setFont(myFont_head);
		panel1.add(lblHeading);
		
		
		lblName = new JLabel("Product Name: ");
		this.lblName.setBounds(100,98,150,50);
		lblName.setFont(myFont_lbl);
		panel1.add(lblName);

		txtName = new JTextField();
		this.txtName.setBounds(275,97,200,44);
		panel1.add(txtName);


		lblQuantity = new JLabel("Product Quantity: ");
		this.lblQuantity.setBounds(100,175,200,50);
		lblQuantity.setFont(myFont_lbl);
		panel1.add(lblQuantity);

		txtQuantity = new JTextField();
		this.txtQuantity.setBounds(275,174,200,50);
		panel1.add(txtQuantity);

		lblIncomingDate = new JLabel("Product Incoming Date: ");
		this.lblIncomingDate.setBounds(100,252,200,50);
		lblIncomingDate.setFont(myFont_lbl);
		panel1.add(lblIncomingDate);

		txtIncomingDate = new JTextField();
		this.txtIncomingDate.setBounds(275,251,200,50);
		SimpleDateFormat dateFor = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		txtIncomingDate.setText(dateFor.format(date)); 
		panel1.add(txtIncomingDate);

		lblBuyingPrice = new JLabel("Product Buying Price: ");
		this.lblBuyingPrice.setBounds(100,330,200,50);
		lblBuyingPrice.setFont(myFont_lbl);
		panel1.add(lblBuyingPrice);

		txtBuyingPrice = new JTextField();
		this.txtBuyingPrice.setBounds(275,329,200,50);
		panel1.add(txtBuyingPrice);

		lblSellingPrice = new JLabel("Product Selling Price: ");
		this.lblSellingPrice.setBounds(100,408,200,50);
		lblSellingPrice.setFont(myFont_lbl);
		panel1.add(lblSellingPrice);

		txtSellingPrice = new JTextField();
		this.txtSellingPrice.setBounds(275,407,200,50);
		panel1.add(txtSellingPrice);

		lblSubCatagory = new JLabel("Product Sub-Catagory: ");
		this.lblSubCatagory.setBounds(100,486,200,50);
		lblSubCatagory.setFont(myFont_lbl);
		panel1.add(lblSubCatagory);

	
		
		btnSubmit = new JButton("Submit");							// Submit button
		this.btnSubmit.setBounds(130,550,150,50);
		panel1.add(btnSubmit);

		btnReset = new JButton("Reset");							// Reset button
		this.btnReset.setBounds(300,550,150,50);
		panel1.add(btnReset);


		lblProDetails = new JLabel("Product Details");				// Product details label
		this.lblProDetails.setBounds(900,95,300,50);
		lblProDetails.setFont(myFont_subHead);
		panel1.add(lblProDetails);


		Font myFont_cmb = new Font("Cambria",+ Font.BOLD, 15);
		
		Database db2=new Database();                                    //combo box category
		
		
		btnrefresh=new JButton("Refresh");
		btnrefresh.setBounds(800,150,170,40);
		btnrefresh.setFont(myFont_cmb);
		panel1.add(btnrefresh);
		
		
		ins2=new ArrayList<Category>();
		db2.getAddProductsubcategory(ins2);
		int k=ins2.size();                                                                                              //new combo box sub category
		subcatagory =new String [k+1];
		subcatagory[0]="---Select Sub-Category---";
		for(int i=1;i<k+1;i++)
		{
			subcatagory[i]=ins2.get(i-1).getsubcat_name();
		}
		cmbSubCatagory=new JComboBox(subcatagory);
		this.cmbSubCatagory.setBounds(275,486,200,50);                                       //new combo box sub category
		cmbSubCatagory.setFont(myFont_cmb);                                     
		panel1.add(cmbSubCatagory);
	
	
	
	
	String [] col = {"Product id","Product name","Quantity","Buying Price","Selling Price","Incoming Date","Sub-Catagory"};	
	dtm=new DefaultTableModel(col,0);
	
	dtm.setRowCount(0);
			pdt=new ArrayList<Product>();
			Database db3=new Database();
			db3.getallProduct(pdt);
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
			sp.setBounds(550,200,700,500);
			jt.setDefaultEditor(Object.class,null);
			panel1.add(sp);
	
	
	
	
	
	
		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);
		btnrefresh.addActionListener(this);
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==btnSubmit)
		{
			//save
			try{
			String a = (String)cmbSubCatagory.getSelectedItem();
			Database db1 = new Database();
			db1.addProduct(txtName.getText(),Integer.parseInt(txtQuantity.getText()),Double.parseDouble(txtBuyingPrice.getText()),Double.parseDouble(txtSellingPrice.getText()),txtIncomingDate.getText(),a);

			JOptionPane.showMessageDialog(null,"Data Inserted");
			txtName.setText("");
			txtQuantity.setText("");
			SimpleDateFormat dateFor = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		txtIncomingDate.setText(dateFor.format(date)); 
			txtBuyingPrice.setText("");
			txtSellingPrice.setText("");
			}
			catch(Exception ie)
			{
				JOptionPane.showMessageDialog(null,"Something Worng");
			}

		}
		else if (e.getSource()==btnReset)
		{
			txtName.setText("");
			
			txtQuantity.setText("");
			SimpleDateFormat dateFor = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		txtIncomingDate.setText(dateFor.format(date)); 
			txtBuyingPrice.setText("");
			txtSellingPrice.setText("");
		}
		else if(e.getSource()==btnrefresh)
		{
			try{
				dtm.setRowCount(0);
			pdt=new ArrayList<Product>();
			Database db3=new Database();
			db3.getallProduct(pdt);
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
			catch(Exception ex)
			{
					System.out.println(ex.getMessage());
			}
		}
	}
	
}