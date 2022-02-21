import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class Remove_Catagory extends JPanel implements ActionListener
{
	JLabel lblcatid,lblcatid1,lblcatid2;
	JLabel lblCategory,lblCategory2; 
	JLabel lblSubCategory,lblSubCategory2,lblHeading;
	JTextField txtcatid;
	JButton btnremove,btnReset,btnsearch,btnrefresh;
	JPanel pnl_remove_catagory;
	
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	ArrayList<Category> cat;


	public Remove_Catagory()
	{
		pnl_remove_catagory = new JPanel();
		pnl_remove_catagory.setLayout(null);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		pnl_remove_catagory.setBackground(bgcolor);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		
		lblHeading = new JLabel("..:: Remove Category ::.."); 		// Label Heading
		this.lblHeading.setBounds(650,50,400,50);
		lblHeading.setFont(myFont_head);
		pnl_remove_catagory.add(lblHeading);
		
		lblcatid = new JLabel("Category ID:");						// Catagory Label
		this.lblcatid.setBounds(100,48,150,50);
		lblcatid.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblcatid);

		txtcatid=new JTextField();
		this.txtcatid.setBounds(275,47,150,44);
		txtcatid.setFont(myFont_lbl);
		pnl_remove_catagory.add(txtcatid);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(500,48,100,50);
		pnl_remove_catagory.add(btnsearch);

		lblcatid1=new JLabel("Category ID:");
		this.lblcatid1.setBounds(100,125,200,50);
		lblcatid1.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblcatid1);
		
		lblcatid2=new JLabel();
		this.lblcatid2.setBounds(275,124,200,50);
		lblcatid2.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblcatid2);
		
		lblCategory=new JLabel("Category Name:");
		this.lblCategory.setBounds(100,202,200,50);
		lblCategory.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblCategory);
		
		lblCategory2=new JLabel();
		this.lblCategory2.setBounds(275,203,200,50);
		lblCategory2.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblCategory2);
		
		lblSubCategory=new JLabel("SubCategory Name:");
		this.lblSubCategory.setBounds(100,280,200,50);
		lblSubCategory.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblSubCategory);
		
		lblSubCategory2=new JLabel();
		this.lblSubCategory2.setBounds(275,279,200,50);
		lblSubCategory2.setFont(myFont_lbl);
		pnl_remove_catagory.add(lblSubCategory2);
		
		
		btnremove=new JButton("Remove");
		this.btnremove.setBounds(150,350,100,50);
		pnl_remove_catagory.add(btnremove);
		
		btnReset=new JButton("Reset");
		this.btnReset.setBounds(280,350,100,50);
		pnl_remove_catagory.add(btnReset);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(950,100,150,50);
		pnl_remove_catagory.add(btnrefresh);
		
		String [] col = {"Category id","Category name","Sub-Category name"};	
	    dtm=new DefaultTableModel(col,0);
	
	       dtm.setRowCount(0);
			cat=new ArrayList<Category>();
			Database db3=new Database();
			db3.getallcategory(cat);
			for(int i =0;i<cat.size();i++)                                                                                              
			{
						Object [] rd = new Object[3];
						rd[0] = cat.get(i).getcat_id();
						rd[1] = cat.get(i).getCat_name();
						rd[2] = cat.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(650,200,600,500);
			jt.setDefaultEditor(Object.class,null);
			pnl_remove_catagory.add(sp);
			
			btnsearch.addActionListener(this);
			btnReset.addActionListener(this);
		    btnrefresh.addActionListener(this);
			btnremove.addActionListener(this);
	
	}


	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==btnsearch)
			{
			
			lblcatid2.setText("");
			lblCategory2.setText("");
			lblSubCategory2.setText("");
			
			cat=new ArrayList<Category>();
			Database db=new Database();
			db.getselectedcategory(cat,Integer.parseInt(txtcatid.getText()));
			
			lblcatid2.setText(cat.get(0).getcat_id());
			lblCategory2.setText(cat.get(0).getCat_name());
			lblSubCategory2.setText(cat.get(0).getsubcat_name());
			
			txtcatid.setText("");
		}
		
		else if (e.getSource()==btnReset) {
				lblcatid2.setText("");
			lblCategory2.setText("");
			lblSubCategory2.setText("");
			txtcatid.setText("");
			
		}
		
		else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
			cat=new ArrayList<Category>();
			Database db3=new Database();
			db3.getallcategory(cat);
			for(int i =0;i<cat.size();i++)                                                                                              
			{
						Object [] rd = new Object[3];
						rd[0] = cat.get(i).getcat_id();
						rd[1] = cat.get(i).getCat_name();
						rd[2] = cat.get(i).getsubcat_name();
						
						dtm.addRow(rd);
			}
		}
		else if(e.getSource()==btnremove)
		{
			Database db10=new Database();
			db10.deletecategory(Integer.parseInt(lblcatid2.getText()));
			lblcatid2.setText("");
			lblCategory2.setText("");
			lblSubCategory2.setText("");
			txtcatid.setText("");
		}
	}
}