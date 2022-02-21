import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
public class Add_User_Manager extends JPanel implements ActionListener
{
	JPanel pnladd_user;
	JLabel lblusername,lblpassword,lbldeptno,lblHeading;
	JTextField txtusername,txtdeptno;
	JPasswordField ptxtpassword;
	JButton btnsubmit, btnreset,btnrefresh;
	JComboBox cmbdept;
	String [] dept;
	ArrayList<Dept>dpt;
	
	ArrayList<User>usr;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	
	public Add_User_Manager()
	{
		pnladd_user=new JPanel();
		pnladd_user.setLayout(null);
		
		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		Color bgcolor = new Color(204, 255, 204);								// color
		pnladd_user.setBackground(bgcolor);
		
		lblHeading = new JLabel("..:: Add User ::.."); 		// Label Heading
		this.lblHeading.setBounds(550,50,400,50);
		lblHeading.setFont(myFont_head);
		pnladd_user.add(lblHeading);
		
		lblusername=new JLabel("User Name");
		this.lblusername.setBounds(175,200,150,50);
		lblusername.setFont(myFont_lbl);
		pnladd_user.add(lblusername);
		
		txtusername=new JTextField();
		this.txtusername.setBounds(300,199,200,50);
		pnladd_user.add(txtusername);
		
		lblpassword=new JLabel("Password");
		this.lblpassword.setBounds(175,300,150,50);
		lblpassword.setFont(myFont_lbl);
		pnladd_user.add(lblpassword);
		
		ptxtpassword = new JPasswordField();
		this.ptxtpassword.setBounds(300,299,200,50);
		pnladd_user.add(ptxtpassword);
		
		lbldeptno = new JLabel("Department");
		this.lbldeptno.setBounds(175,400,150,50);
		lbldeptno.setFont(myFont_lbl);
		pnladd_user.add(lbldeptno);
		
		
		dept=new String[2];
		dept[0]="---Position---";
		dept[1]="salesman";
		
		
		cmbdept=new JComboBox(dept);
		this.cmbdept.setBounds(300,399,200,50);
		cmbdept.setFont(myFont_lbl);
		pnladd_user.add(cmbdept);
		
		btnsubmit = new JButton("Submit");							// Submit button
		this.btnsubmit.setBounds(250,500,150,50);
		pnladd_user.add(btnsubmit);

		btnreset = new JButton("Reset");							// Reset button
		this.btnreset.setBounds(460,500,150,50);
		pnladd_user.add(btnreset);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(950,100,150,50);
		pnladd_user.add(btnrefresh);
		
		String [] col = {"User Name","Position","User ID"};
		dtm=new DefaultTableModel(col,0);
		dtm.setRowCount(0);
		usr=new ArrayList<User>();
		Database d3=new Database();
		d3.getalluserformanager(usr);
		for(int i =0;i<usr.size();i++)                                                                                              
			{
						Object [] rd = new Object[3];
						rd[0] = usr.get(i).getusername();
						rd[1] = usr.get(i).getposition();
						rd[2] = usr.get(i).getuid();
						
						dtm.addRow(rd);
			}
			
			this.jt=new JTable(dtm);
			sp=new JScrollPane(jt);
			sp.setBounds(650,200,600,500);
			jt.setDefaultEditor(Object.class,null);
			pnladd_user.add(sp);
		
		
		btnsubmit.addActionListener(this);
		btnreset.addActionListener(this);
		btnrefresh.addActionListener(this);
		
		
		
	}
		public void actionPerformed(ActionEvent e)
		{
		if (e.getSource()==btnsubmit)
		{
			//save
			if(cmbdept.getSelectedItem().equals("salesman"))
			{
			Database dbadduser = new Database();
			dbadduser.adduser(txtusername.getText(),ptxtpassword.getText(),3);
			JOptionPane.showMessageDialog(null,"Data Inserted");
			txtusername.setText("");
			ptxtpassword.setText("");
			}
			
			

		}
		else if (e.getSource()==btnreset)
		{
			txtusername.setText("");
			ptxtpassword.setText("");
		}
		else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
		    usr=new ArrayList<User>();
		    Database d3=new Database();
		   d3.getalluserformanager(usr);
		   for(int i =0;i<usr.size();i++)                                                                                              
			{
						Object [] rd = new Object[3];
						rd[0] = usr.get(i).getusername();
						rd[1] = usr.get(i).getposition();
						rd[2] = usr.get(i).getuid();
						
						dtm.addRow(rd);
			}
		
		}
		
		
		}
}