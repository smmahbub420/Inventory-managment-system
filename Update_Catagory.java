import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;


public class Update_Catagory extends JPanel implements ActionListener
{
	JLabel lblcatid,lblcatid1,lblcat_id;
	JLabel lblCategory; 
	JLabel lblSubCategory,lblHeading;
	JTextField txtcatid;
	JButton btnupdate,btnReset,btnsearch,btnrefresh;
	
	JTextField txtcat_name,txtsubcat_name;
	
	JPanel pnl_update_catagory;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	ArrayList<Category> cat;
	
	public Update_Catagory()
	{
		pnl_update_catagory = new JPanel();
		pnl_update_catagory.setLayout(null);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);

		Color bgcolor = new Color(204, 255, 204);				// color
		pnl_update_catagory.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Update Catagory ::.."); 		// Label Heading
		this.lblHeading.setBounds(650,50,400,50);
		lblHeading.setFont(myFont_head);
		pnl_update_catagory.add(lblHeading);
		
		lblcatid = new JLabel("Category ID:");						// Catagory Label
		this.lblcatid.setBounds(100,48,150,50);
		lblcatid.setFont(myFont_lbl);
		pnl_update_catagory.add(lblcatid);

		txtcatid=new JTextField();
		this.txtcatid.setBounds(275,47,150,44);
		txtcatid.setFont(myFont_lbl);
		pnl_update_catagory.add(txtcatid);
		
		btnsearch=new JButton("Search");
		this.btnsearch.setBounds(500,48,100,50);
		pnl_update_catagory.add(btnsearch);

		lblcatid1=new JLabel("Category ID:");
		this.lblcatid1.setBounds(100,125,200,50);
		lblcatid1.setFont(myFont_lbl);
		pnl_update_catagory.add(lblcatid1);
		
		lblcat_id=new JLabel();
		this.lblcat_id.setBounds(275,124,200,50);
		lblcat_id.setFont(myFont_lbl);
		pnl_update_catagory.add(lblcat_id);
		
		lblCategory=new JLabel("Category Name:");
		this.lblCategory.setBounds(100,202,200,50);
		lblCategory.setFont(myFont_lbl);
		pnl_update_catagory.add(lblCategory);
		
		txtcat_name=new JTextField();
		this.txtcat_name.setBounds(275,203,200,50);
		txtcat_name.setFont(myFont_lbl);
		pnl_update_catagory.add(txtcat_name);
		
		lblSubCategory=new JLabel("SubCategory Name:");
		this.lblSubCategory.setBounds(100,280,200,50);
		lblSubCategory.setFont(myFont_lbl);
		pnl_update_catagory.add(lblSubCategory);
		
		txtsubcat_name=new JTextField();
		this.txtsubcat_name.setBounds(275,279,200,50);
		txtsubcat_name.setFont(myFont_lbl);
		pnl_update_catagory.add(txtsubcat_name);
		
		
		btnupdate=new JButton("update");
		this.btnupdate.setBounds(150,350,100,50);
		pnl_update_catagory.add(btnupdate);
		
		btnReset=new JButton("Reset");
		this.btnReset.setBounds(280,350,100,50);
		pnl_update_catagory.add(btnReset);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(950,100,150,50);
		pnl_update_catagory.add(btnrefresh);
		
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
			pnl_update_catagory.add(sp);
			
			btnsearch.addActionListener(this);
			btnReset.addActionListener(this);
		    btnrefresh.addActionListener(this);
			btnupdate.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e){
		
		if (e.getSource()==btnsearch)
			{
			
			lblcat_id.setText("");
			txtcat_name.setText("");
			txtsubcat_name.setText("");
			
			cat=new ArrayList<Category>();
			Database db=new Database();
			db.getselectedcategory(cat,Integer.parseInt(txtcatid.getText()));
			
			lblcat_id.setText(cat.get(0).getcat_id());
			txtcat_name.setText(cat.get(0).getCat_name());
			txtsubcat_name.setText(cat.get(0).getsubcat_name());
			
			txtcatid.setText("");
		}
		
		else if (e.getSource()==btnReset) {
				lblcat_id.setText("");
			txtcat_name.setText("");
			txtsubcat_name.setText("");
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
		else if(e.getSource()==btnupdate)
		{
			Database db10=new Database();
			db10.updatecategory(Integer.parseInt(lblcat_id.getText()),txtcat_name.getText(),txtsubcat_name.getText());
			lblcat_id.setText("");
			txtcat_name.setText("");
			txtsubcat_name.setText("");
			txtcatid.setText("");
		}
	}
}