import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class Remove_User_Manager extends JPanel implements ActionListener
{
	JPanel pnlremove_user;
	JLabel lblusername,lblHeading;
	JTextField txtusername;
	JButton btndelete,btnrefresh;
	
	ArrayList<User>usr;
	JScrollPane sp;
	DefaultTableModel dtm;
	JTable jt;
	
	public Remove_User_Manager()
	{
		pnlremove_user=new JPanel();
		pnlremove_user.setLayout(null);
		
		Font myFont_lbl = new Font("Cambria",+ Font.BOLD, 15);
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);

		Color bgcolor = new Color(204, 255, 204);				// color
		pnlremove_user.setBackground(bgcolor);

		lblHeading= new JLabel("..:: Remove User ::..");							// Label Heading
		this.lblHeading.setBounds(550,50,400,50);
		lblHeading.setFont(myFont_head);
		pnlremove_user.add(lblHeading);
		
		lblusername=new JLabel("User ID");
		this.lblusername.setBounds(125,300,150,50);
		lblusername.setFont(myFont_lbl);
		pnlremove_user.add(lblusername);
		
		txtusername=new JTextField();
		this.txtusername.setBounds(320,299,200,50);
		pnlremove_user.add(txtusername);
		
		btndelete=new JButton("Remove User");
		this.btndelete.setBounds(550,299,150,50);
		pnlremove_user.add(btndelete);
		
		btnrefresh=new JButton("Refresh");
		this.btnrefresh.setBounds(950,100,150,50);
		pnlremove_user.add(btnrefresh);
		
		String [] col = {"User Name","Position","User ID"};
		dtm=new DefaultTableModel(col,0);
		dtm.setRowCount(0);
		usr=new ArrayList<User>();
		Database d2=new Database();
		d2.getalluserformanager(usr);
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
			sp.setBounds(750,200,500,500);
			jt.setDefaultEditor(Object.class,null);
			pnlremove_user.add(sp);
		
		
		
		btndelete.addActionListener(this);
		btnrefresh.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btndelete)
		{
			Database db=new Database();
			int n=db.getdept(Integer.parseInt(txtusername.getText()));
			if(n==1)
			{
				JOptionPane.showMessageDialog(null,"Can Not remove this User");
				txtusername.setText("");
			}
			else if(n==2)
			{
				JOptionPane.showMessageDialog(null,"Can Not remove this User");
				txtusername.setText("");
			}
			else{
				db.deleteuser(Integer.parseInt(txtusername.getText()));
				JOptionPane.showMessageDialog(null,"User removed");
				txtusername.setText("");
			}
		}
		else if(e.getSource()==btnrefresh)
		{
			dtm.setRowCount(0);
		    usr=new ArrayList<User>();
		    Database d2=new Database();
		    d2.getalluserformanager(usr);
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