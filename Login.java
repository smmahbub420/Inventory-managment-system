import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener
{
	JLabel lblHeading, lblUser, lblPass;
	JButton btnLogin, btnReset;
	JTextField txtUser;
	JPasswordField txtPassWord;
	JPanel panel;
	JFrame f;
	String uname;
	public Login()
	{
		super("Inventory Management System Login Panel");
		this.setSize(1282,801);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		


		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(null);


		Font myFont_head = new Font("Cambria", Font.ITALIC + Font.BOLD, 40);
		Font myFont_user = new Font("Cambria", Font.ITALIC, 30);
		Font myFont_txt = new Font("Cambria", Font.ITALIC, 30);
		Font myFont_btn = new Font("Cambria", Font.ITALIC, 20);
		Font myFont_wrning = new Font("Cambria", Font.ITALIC + Font.BOLD, 15);

		Color bgcolor = new Color(153, 204, 255);						//color

		panel.setBackground(bgcolor);

		lblHeading = new JLabel("Login Panel");							// Heading
		this.lblHeading.setBounds(520,150,300,100);
		lblHeading.setFont(myFont_head);
		//lblHeading.setForeground(Color.BLUE);
		panel.add(lblHeading);


		lblUser = new JLabel("UserName :");								//user Label
		this.lblUser.setBounds(400,300,150,50);
		lblUser.setFont(myFont_user);
		//lblUser.setForeground(Color.BLUE);
		panel.add(lblUser);


		lblPass = new JLabel("PassWord :");								//password Label
		this.lblPass.setBounds(400,400,150,50);
		lblPass.setFont(myFont_user);
		//lblPass.setForeground(Color.BLUE)
		panel.add(lblPass);
		

		txtUser = new JTextField("admin");										// user input
		this.txtUser.setBounds(580,305,209,44);
		txtUser.setFont(myFont_txt);
		txtUser.setToolTipText("Enter your username");
		panel.add(txtUser);

		txtPassWord = new JPasswordField("admin");								//password input
		this.txtPassWord.setBounds(580,405,209,44);
		txtPassWord.setFont(myFont_txt);
		txtPassWord.setToolTipText("Enter your password");
		panel.add(txtPassWord);


		btnLogin = new JButton("Login");								//login button
		this.btnLogin.setBounds(400,500,150,50);
		btnLogin.setFont(myFont_btn);
		btnLogin.setToolTipText("Login");
		panel.add(btnLogin);

		btnReset = new JButton("Reset");								//reset button
		this.btnReset.setBounds(640,500,150,50);
		btnReset.setFont(myFont_btn);
		btnReset.setToolTipText("Reset");
		panel.add(btnReset);



		this.add(panel);


		btnLogin.addActionListener(this);
		btnReset.addActionListener(this);
		


	}

	public void actionPerformed(ActionEvent e)
		{
			if (e.getSource()==btnLogin) {
				
					Database db = new Database();
					db.user_check(txtUser.getText(),txtPassWord.getText());
					if (db.flag==1) {
						this.setVisible(false);
						DashboardAdmin adm = new DashboardAdmin(txtUser.getText());
						
					}	
					else if (db.flag==2) {
						//JOptionPane.showMessageDialog(null,"Manager login success");
						this.setVisible(false);
						DashboardManager mgr = new DashboardManager(txtUser.getText());

					}

					else if (db.flag==3) {
						this.setVisible(false);
						DashboardSalesman slm=new DashboardSalesman(txtUser.getText());

					}
					
				else {
					JOptionPane.showMessageDialog(null,"Username or password is wrong");
					txtUser.setText("");
					txtPassWord.setText("");

				}
			}
			else if (e.getSource()==btnReset)
			{
				txtUser.setText("");
				txtPassWord.setText("");
			}
		}


			
		}