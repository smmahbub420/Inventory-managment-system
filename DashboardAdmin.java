import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardAdmin extends JPanel implements ActionListener,MouseListener
{
	JMenu product,catagory, report,sales,logname,user,log;
	JMenuItem p_add, p_remove, p_update,c_add, c_remove, c_update,u_add,u_remove,s_new,s_update,logout;
	JPanel  pnl_product, pnl_catagory,pnl_report, p,pnl_user;
	JLabel lblHeading;
	JFrame f;
	String uname;
	public DashboardAdmin(String uname)
	{
		this.uname=uname;
		f = new JFrame("Admin Dashboard");

		f.setSize(1080,720);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//f.setUndecorated(true);
		f.setVisible(true);
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		
	
		JPanel p = new JPanel();
		p.setLayout(null);
		
		Color bgcolor = new Color(204, 255, 204);				// color
		p.setBackground(bgcolor);

		JMenuBar mb = new JMenuBar();
		product = new JMenu("Product");
		catagory = new JMenu("Catagory");
		report = new JMenu("Report");
		//
		sales=new JMenu("Sales");
		user=new JMenu("User");
		logname=new JMenu(this.uname);
		log=new JMenu("Logs");

		
		
		
		
		
		p_add = new JMenuItem("Add");
		p_remove = new JMenuItem("Remove");
		p_update = new JMenuItem("Update");

		c_add = new JMenuItem("Add");
		c_remove = new JMenuItem("Remove");
		c_update = new JMenuItem("Update");

		u_add=new JMenuItem("Add");
		u_remove=new JMenuItem("Remove");
		
		s_new = new JMenuItem("New");
		s_update = new JMenuItem("Update");
		logout=new JMenuItem("Log Out");
		
		logname.add(logout);
		user.add(u_add);user.add(u_remove);
		product.add(p_add); product.add(p_remove); product.add(p_update);
		catagory.add(c_add); catagory.add(c_remove); catagory.add(c_update);
		sales.add(s_new); sales.add(s_update);

		mb.add(product); mb.add(catagory); mb.add(user);mb.add(sales);mb.add(report);mb.add(log);
		mb.add(Box.createHorizontalGlue());
		mb.add(logname);
		
		f.setJMenuBar(mb);
		
		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 30);

	



		lblHeading = new JLabel("Welcome to Admin Panel");
		lblHeading.setFont(myFont_head);
		this.lblHeading.setBounds(520,50,1000,600);
			

		p.add(lblHeading);
		f.getContentPane().add(p);
		f.revalidate();
		
		//f.add(p);
		
		p_add.addActionListener(this);
		p_remove.addActionListener(this);
		u_add.addActionListener(this);
		logout.addActionListener(this);
		u_remove.addActionListener(this);
		c_add.addActionListener(this);
		c_remove.addActionListener(this);
		c_update.addActionListener(this);
		p_update.addActionListener(this);
		s_new.addActionListener(this);
		s_update.addActionListener(this);
		report.addMouseListener(this);
		log.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==p_add) {
			//p.setVisible(false);
			Add_Product pr = new Add_Product();
			f.getContentPane().removeAll();
			f.getContentPane().add(pr.panel1);
			f.revalidate();
			//pr.panel1.setVisible(true);
			//f.add(pr.panel1);
		
		}
		else if (e.getSource()==p_remove) 
		{
			Remove_product rp=new Remove_product();
			f.getContentPane().removeAll();
			f.getContentPane().add(rp.pnlremove_product);
			f.revalidate();
		}
		else if(e.getSource()==u_add)
		{
			Add_User ur=new Add_User();
			f.getContentPane().removeAll();
			f.getContentPane().add(ur.pnladd_user);
			f.revalidate();
			f.setVisible(true);
			f.setResizable(false);
			f.setLocationRelativeTo(null);
		}
		else if(e.getSource()== u_remove)
		{
			Remove_User ru=new Remove_User();
			f.getContentPane().removeAll();
			f.getContentPane().add(ru.pnlremove_user);
			f.revalidate();
		}
		else if(e.getSource()== c_add)
		{
			Add_Catagory ac=new Add_Catagory();
			f.getContentPane().removeAll();
			f.getContentPane().add(ac.pnl_add_catagory);
			f.revalidate();
		}
		else if(e.getSource()== c_remove)
		{
			Remove_Catagory rc=new Remove_Catagory();
			f.getContentPane().removeAll();
			f.getContentPane().add(rc.pnl_remove_catagory);
			f.revalidate();
		}
		else if(e.getSource()== c_update)
		{
			Update_Catagory uc=new Update_Catagory();
			f.getContentPane().removeAll();
			f.getContentPane().add(uc.pnl_update_catagory);
			f.revalidate();
		}
		else if(e.getSource()== p_update)
		{
			Update_product pu=new Update_product();
			f.getContentPane().removeAll();
			f.getContentPane().add(pu.pnl_update_product);
			f.revalidate();
		}
		else if (e.getSource()==s_new)
		{
			
			Sales_New ps = new Sales_New();
			f.getContentPane().removeAll();
			f.getContentPane().add(ps.panelS);
			f.revalidate();
		}
		else if (e.getSource()==s_update)
		{
			
			Sales_Update uds = new Sales_Update();
			f.getContentPane().removeAll();
			f.getContentPane().add(uds.pnlsales_update);
			f.revalidate();
		}
		else if (e.getSource() == logout) {
			
			int flag = JOptionPane.showConfirmDialog(null,"Are you want to logout?", "Logout Confirm",JOptionPane.YES_NO_OPTION);
			if (flag==0) {
				f.setVisible(false);
				Login ln = new Login();
				ln.setVisible(true);
			}
			else
			{
				f.setVisible(true);
			}
		}
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource()==report)
		{
			Report rp=new Report();
			f.getContentPane().removeAll();
			f.getContentPane().add(rp.panel_report);
			f.revalidate();
		}
		else if(e.getSource()==log)
		{
			Logs lg=new Logs();
			f.getContentPane().removeAll();
			f.getContentPane().add(lg.panel_logs);
			f.revalidate();
		}

	}

	public void mousePressed(MouseEvent e){

	}
	public void mouseReleased(MouseEvent e){

	}

	public void mouseEntered(MouseEvent e)
	{
		
	}
	public void mouseExited(MouseEvent e)
	{
		
	}




}