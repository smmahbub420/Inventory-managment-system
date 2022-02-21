import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;

public class Add_Catagory extends JPanel implements ActionListener
{
	JLabel lblCatagory, lblSubCatagory,lblHeading;
	JTextField txtCatagory, txtSubCatagory;
	JButton btnSubmit, btnReset,btnrefresh;
	JPanel pnl_add_catagory;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	ArrayList<Category> cat;

	public Add_Catagory()
	{
		pnl_add_catagory = new JPanel();									
		pnl_add_catagory.setLayout(null);

		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);

		Color bgcolor = new Color(204, 255, 204);				// color
		pnl_add_catagory.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Add Category ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,50,400,50);
		lblHeading.setFont(myFont_head);
		pnl_add_catagory.add(lblHeading);
		
		lblCatagory = new JLabel("Category :");				// Catagory Label
		this.lblCatagory.setBounds(175,200,150,50);
		lblCatagory.setFont(myFont_lbl);
		pnl_add_catagory.add(lblCatagory);

		txtCatagory = new JTextField();						// TextBox Catagory
		this.txtCatagory.setBounds(300,199,200,50);
		txtCatagory.setFont(myFont_lbl);
		pnl_add_catagory.add(txtCatagory);


		lblSubCatagory = new JLabel("Sub-Category :");		// Sub-Catagory Label
		this.lblSubCatagory.setBounds(175,300,150,50);
		lblSubCatagory.setFont(myFont_lbl);
		pnl_add_catagory.add(lblSubCatagory);

		txtSubCatagory = new JTextField();					// TextBox Sub-Catagory
		this.txtSubCatagory.setBounds(300,299,200,50);
		txtCatagory.setFont(myFont_lbl);
		pnl_add_catagory.add(txtSubCatagory);

		btnSubmit = new JButton("Submit");					// Submit button
		this.btnSubmit.setBounds(250,400,150,50);
		pnl_add_catagory.add(btnSubmit);

		btnReset = new JButton("Reset");					// Reset button
		this.btnReset.setBounds(420,400,150,50);
		pnl_add_catagory.add(btnReset);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(950,100,150,50);
		pnl_add_catagory.add(btnrefresh);
		
		

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
			pnl_add_catagory.add(sp);
	


		btnSubmit.addActionListener(this);
		btnReset.addActionListener(this);
		btnrefresh.addActionListener(this);
		

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==btnSubmit) {
			Database db=new Database();
			db.addcategory(txtCatagory.getText(),txtSubCatagory.getText());
			JOptionPane.showMessageDialog(null,"Category added");
			txtCatagory.setText("");
			txtSubCatagory.setText("");
			
		}
		else if (e.getSource()==btnReset) 
		{
			txtCatagory.setText("");
			txtSubCatagory.setText("");
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
	}

}