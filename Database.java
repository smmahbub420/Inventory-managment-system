import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Database
{
	Connection con;
	String query;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	final String driver = "com.mysql.jdbc.Driver";
	final String url = "jdbc:mysql://localhost:3306/inventory";
	final String user = "root";
	final String pass = "";
	int flag;

	public Database()
	{
		try{
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url,user,pass);
			query = null;
			psmt = null;
			stmt =null;
			rs =null;

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	public void user_check(String uname,String pass)
	{
		try{
			System.out.println(uname);
			System.out.println(pass);
		query = "SELECT `deptno` FROM `user` WHERE `username`= ? AND `password`= ?";
		psmt = con.prepareStatement(query);
		psmt.setString(1,uname);
		psmt.setString(2,pass);
		rs = psmt.executeQuery();
		if (rs.next()) {
				
			if (rs.getString("deptno").equals("1")) {
					flag=1;
				}
			else if (rs.getString("deptno").equals("2")) {
					flag =2;
			}
			else if (rs.getString("deptno").equals("3")) {
					flag =3;
			}	
			
		}

	} 
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	}

	public void addProduct(String pname,int quantity,Double buying_price,Double selling_price,String incoming_date,String subcat_name) 
	{
		try{
			
			query = "INSERT INTO `product` (`pname`, `quantity`, `buying_price`, `selling_price`, `incoming_date`, `subcat_name`) VALUES (?, ?, ?, ?, ?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1,pname);
			psmt.setInt(2,quantity);
			psmt.setDouble(3,buying_price);                                               //add product
			psmt.setDouble(4,selling_price);
			psmt.setString(5,incoming_date);
			psmt.setString(6,subcat_name);
			
			psmt.executeUpdate();
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	
	
	public void getcategory(ArrayList<Category> ins)
	{
		try{
			query="SELECT DISTINCT `cat_name` FROM category";
				
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String cat_name = rs.getString("cat_name");
				Category c=new Category(cat_name);
				ins.add(c);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void getsubcategory(ArrayList<Category> ins2,String cmb)
	{
		try{
			query="SELECT DISTINCT `subcat_name` FROM category Where cat_name= ?";
				
			psmt = con.prepareStatement(query);
			psmt.setString(1,cmb);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				String subcat_name = rs.getString("subcat_name");
				Category sc=new Category(subcat_name);
				ins2.add(sc);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	public void getAddProductsubcategory(ArrayList<Category> ins2)
	{
		try{
			query="SELECT DISTINCT `subcat_name` FROM category";
				
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())                                                                                               
			{
				String subcat_name = rs.getString("subcat_name");
				Category sc=new Category(subcat_name);
				ins2.add(sc);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	 
	public void adduser(String username,String password,int deptno)
	 {
		 try{
			 query="INSERT INTO `user` (`username`, `password`, `deptno`) VALUES (?, MD5(?), ?)"; 
			psmt = con.prepareStatement(query);                                                            //add user to database
			psmt.setString(1,username);
			psmt.setString(2,password);
			psmt.setInt(3,deptno);
			psmt.executeUpdate();
		}
		catch(SQLException e){
				JOptionPane.showMessageDialog(null,"Enter Unique User Name");
			}
	 }
	 public void getProduct(ArrayList<Product> pdt,String cmb)
	 {
		 try{
			 query="select * from product where `subcat_name` in (SELECT `subcat_name` FROM category Where `cat_name`= ?)";
			 psmt = con.prepareStatement(query);
			psmt.setString(1,cmb);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				String pid = rs.getString("pid");
				String pname=rs.getString("pname");
				String quantity=rs.getString("quantity");                                                                         //product return for table and scroll panel
				String buying_price=rs.getString("buying_price");
				String selling_price=rs.getString("selling_price");
				String incoming_date=rs.getString("incoming_date");
				String subcat_name=rs.getString("subcat_name");
				Product pt=new Product(pid,pname,quantity,buying_price,selling_price,incoming_date,subcat_name);
				
				pdt.add(pt);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public int getdept(int uid)
	{
		try
		{
			query="SELECT `deptno`  FROM user where uid=?";
				
			psmt = con.prepareStatement(query);
			psmt.setInt(1,uid);
			rs = psmt.executeQuery();
			if (rs.next())
			{				
				if (rs.getString("deptno").equals("1")) 
				{
					return 1;
				}
				else if (rs.getString("deptno").equals("2")) 
				{
					return 2;
				}
				else if (rs.getString("deptno").equals("3"))
				{
					return 3;
				}
			}				
		}
    
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		return 0;
	}
	public void deleteuser(int uid)
	{
		try{
			query="DELETE FROM `user` WHERE `uid` = ?";
			psmt = con.prepareStatement(query);                                                            //delete user to database
			psmt.setInt(1,uid);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				System.out.println(e.getMessage());
			}
	}
	public void deleteproduct(int pid)
	{
		try{
			query="DELETE FROM `product` WHERE `pid` = ?";
			psmt = con.prepareStatement(query);                                                            //delete PRODUCT to database
			psmt.setInt(1,pid);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				System.out.println(e.getMessage());
			}
	}
	public void addcategory(String cat_name,String subcat_name)
	{
		try{
			query="INSERT INTO `category` (`cat_name`, `subcat_name`) VALUES (?, ?)";
			psmt = con.prepareStatement(query);                                                            //add category to database
			psmt.setString(1,cat_name);
			psmt.setString(2,subcat_name);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				System.out.println(e.getMessage());
			}
		
	}
	public void deletecategory(int cat_id)
	{
		try{
			query="DELETE FROM `category` WHERE `category`.`cat_id` = ?";
			psmt = con.prepareStatement(query);                                                            //delete cat_name from database
			psmt.setInt(1,cat_id);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				System.out.println(e.getMessage());
			}
	}
	
	public void updatecategory(int cat_id,String cat_name,String subcat_name)
	{
		try{
			query="UPDATE `category` SET `cat_name` = ?, `subcat_name`=?  WHERE `cat_id` = ?";
			psmt = con.prepareStatement(query);                                                            //updtae cat_name from database
			psmt.setString(1,cat_name);
			psmt.setString(2,subcat_name);
			psmt.setInt(3,cat_id);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				System.out.println(e.getMessage());
			}
	}

	public void updateProduct(int pid,String pname,int quantity,Double buying_price,Double selling_price,String incoming_date,String subcat_name) 
	{
		try{
			
			query = "UPDATE `product` SET  `pname` = ?, `quantity` = ?, `buying_price` = ?, `selling_price` = ?, `incoming_date` = ?, `subcat_name` = ? WHERE `product`.`pid` = ? ";
			psmt = con.prepareStatement(query);
			psmt.setInt(7,pid);
			psmt.setString(1,pname);
			psmt.setInt(2,quantity);
			psmt.setDouble(3,buying_price);                                               //update product
			psmt.setDouble(4,selling_price);
			psmt.setString(5,incoming_date);
			psmt.setString(6,subcat_name);
			
			psmt.executeUpdate();
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	
	public void searchProduct(ArrayList<Product> pdt,int pid1)
	 {
		 try{
			 query="select * from product where `pid` = ?";
			 psmt = con.prepareStatement(query);
			psmt.setInt(1,pid1);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				String pid = rs.getString("pid");
				String pname=rs.getString("pname");
				String quantity=rs.getString("quantity");                                                                         //product return for search
				String buying_price=rs.getString("buying_price");
				String selling_price=rs.getString("selling_price");
				String incoming_date=rs.getString("incoming_date");
				String subcat_name=rs.getString("subcat_name");
				Product pt=new Product(pid,pname,quantity,buying_price,selling_price,incoming_date,subcat_name);
				pdt.add(pt);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void getallProduct(ArrayList<Product> pdt1)
	 {
		 try{
			 query="select * from product ";
			 stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String pid = rs.getString("pid");
				String pname=rs.getString("pname");
				String quantity=rs.getString("quantity");                                                                         //
				String buying_price=rs.getString("buying_price");
				String selling_price=rs.getString("selling_price");
				String incoming_date=rs.getString("incoming_date");
				String subcat_name=rs.getString("subcat_name");
				Product pt=new Product(pid,pname,quantity,buying_price,selling_price,incoming_date,subcat_name);
				
				pdt1.add(pt);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void addinvoice(String customer_name,String invoice_date,int invoice_no,int pid,String pname,int buying_quantity,double price,double total_price)
	{
		try{
			query="INSERT INTO `invoice` (`customer_name`, `invoice_date`, `invoice_no`, `pid`, `pname`, `buying_quantity`, `price`, `total_price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);                                                            //add invoice
			psmt.setString(1,customer_name);
			psmt.setString(2,invoice_date);
			psmt.setInt(3,invoice_no);
			psmt.setInt(4,pid);
			psmt.setString(5,pname);
			psmt.setInt(6,buying_quantity);
			psmt.setDouble(7,price);
			psmt.setDouble(8,total_price);
			
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
				JOptionPane.showMessageDialog(null,"Duplicate Invoice No");
			}
		
	}
	
	public void subquantity(int quantity,int pid)
	{
		try{
			query=query = "UPDATE `product` SET  `quantity` = ? WHERE `product`.`pid` = ? ";
			psmt = con.prepareStatement(query);                                                            //sub  quantity
			psmt.setInt(1,quantity);
			psmt.setInt(2,pid);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
		}
	}
	
	public void getinvoice(ArrayList<Invoice>inv,int invoice_no)
	{
		try{
			 query="SELECT * FROM `invoice` WHERE `invoice_no`=?";
			 psmt = con.prepareStatement(query);
			psmt.setInt(1,invoice_no);
			rs = psmt.executeQuery();
			while(rs.next())
			{
				String customer_name = rs.getString("customer_name");
				String invoice_date=rs.getString("invoice_date");
				String invoice_no1=rs.getString("invoice_no");                                                                         //product return for search
				String pid=rs.getString("pid");
				String pname=rs.getString("pname");
				String buying_quantity=rs.getString("buying_quantity");
				String price=rs.getString("price");
				String total_price=rs.getString("total_price");
				String invoice_id=rs.getString("invoice_id");
				Invoice in=new Invoice(customer_name,invoice_date,invoice_no1,pid,pname,buying_quantity,price,total_price,invoice_id);
				inv.add(in);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
			
	}
	
	public void updatesale(String customer_name,String invoice_date,int pid,String pname,int buying_quantity,Double price,Double total_price,int invoice_id) 
	{
		try{
			
			query =" UPDATE `invoice` SET `customer_name` = ?, `invoice_date` = ?, `pid` = ?, `pname` = ?, `buying_quantity` = ?, `price` = ?, `total_price` = ? WHERE `invoice`.`invoice_id` = ?";
			psmt = con.prepareStatement(query);
			
			psmt.setString(1,customer_name);
			psmt.setString(2,invoice_date);
			psmt.setInt(3,pid);
			psmt.setString(4,pname);
			psmt.setInt(5,buying_quantity);
			psmt.setDouble(6,price);                                               //
			psmt.setDouble(7,total_price);
			
			
			psmt.setInt(8,invoice_id);
			
			psmt.executeUpdate();
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	public void getinvoice_id(ArrayList<Invoice>inv1,int invoice_no,int pid1)
	{
		try{
			query="SELECT * FROM `invoice` WHERE `invoice_no`=? and`pid`=?";
			psmt = con.prepareStatement(query);
				psmt.setInt(1,invoice_no);
			psmt.setInt(2,pid1);
		
			rs = psmt.executeQuery();
			while(rs.next())
			{
				String customer_name = rs.getString("customer_name");
				String invoice_date=rs.getString("invoice_date");
				String invoice_no1=rs.getString("invoice_no");                                                                        
				String pid=rs.getString("pid");
				String pname=rs.getString("pname");
				String buying_quantity=rs.getString("buying_quantity");
				String price=rs.getString("price");
				String total_price=rs.getString("total_price");
				String invoice_id=rs.getString("invoice_id");
				Invoice in=new Invoice(customer_name,invoice_date,invoice_no1,pid,pname,buying_quantity,price,total_price,invoice_id);
				inv1.add(in);
			}
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	
	public void deleteinvoice(int invoice_id)
	{
		try{
			query="DELETE FROM `invoice` WHERE `invoice`.`invoice_id` = ?";
			psmt = con.prepareStatement(query);
			
			psmt.setInt(1,invoice_id);			
			psmt.executeUpdate();
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	
	public void addquantity(int quantity,int pid)
	{
		try{
			query=query = "UPDATE `product` SET  `quantity` = ? WHERE `product`.`pid` = ? ";
			psmt = con.prepareStatement(query);                                                            //add  quantity
			psmt.setInt(1,quantity);
			psmt.setInt(2,pid);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
		}
	}
	public void getproductquantity(ArrayList<Product>pdt5,int pid)
	{
		try{
			query="select quantity from product where `pid`=?";
			psmt = con.prepareStatement(query);
				psmt.setInt(1,pid);
		
			rs = psmt.executeQuery();
			while(rs.next())
			{
				                                                                    
				String quantity=rs.getString("quantity");
				Product pt=new Product(quantity);
				pdt5.add(pt);
			}
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
	}
	public void getalluser(ArrayList<User>usr)
	{
		try{
			query="select user.username,dept.position,user.uid from user , dept where user.deptno=dept.deptno ";
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String username = rs.getString("username");
				String position=rs.getString("position");                                                                         //
				String uid=rs.getString("uid");
				User ur=new User(username,position,uid);
				
				usr.add(ur);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
		public void getalluserformanager(ArrayList<User>usr)
	{
		try{
			query="select user.username,dept.position,user.uid from user , dept where user.deptno=dept.deptno and user.deptno=3 ";
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String username = rs.getString("username");
				String position=rs.getString("position");                                                                         //
				String uid=rs.getString("uid");
				User ur=new User(username,position,uid);
				
				usr.add(ur);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void getreport(ArrayList<Invoice>inv,String fromdate,String todate)
	{
		try{
			query="select pid,pname,sum(buying_quantity) as buying_quantity,sum(total_price) as total_price from invoice where invoice_date between ? and ? group by pid ";
			psmt = con.prepareStatement(query);
				psmt.setString(1,fromdate);
			psmt.setString(2,todate);
		
			rs = psmt.executeQuery();
			while(rs.next())
			{                                                                 
				String pid=rs.getString("pid");
				String pname=rs.getString("pname");
				String buying_quantity=rs.getString("buying_quantity");
				String total_price=rs.getString("total_price");
				Invoice in=new Invoice(pid,pname,buying_quantity,total_price);
				inv.add(in);
			}
		}
		catch(SQLException e){
				System.out.println(e.getMessage());
			}
			
	}
	public void getallcategory(ArrayList<Category>cat)
	{
		try{
			query="Select * from category";
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String cat_id = rs.getString("cat_id");
				String cat_name=rs.getString("cat_name");
				String subcat_name=rs.getString("subcat_name");
				Category ct=new Category(cat_id,cat_name,subcat_name);
				
				cat.add(ct);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void getselectedcategory(ArrayList<Category>cat,int cat_id1)
	{
		try{
			query="Select * from category where cat_id=?";
			psmt = con.prepareStatement(query);
			psmt.setInt(1,cat_id1);
			rs = psmt.executeQuery();
			
		while(rs.next())
			{
				String cat_id = rs.getString("cat_id");
				String cat_name=rs.getString("cat_name");
				String subcat_name=rs.getString("subcat_name");
				Category ct=new Category(cat_id,cat_name,subcat_name);
				
				cat.add(ct);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public void getallinvoice(ArrayList<Invoice>inv)
	{
		try{
			 query="SELECT * FROM `invoice` ";
			 stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.getResultSet();
			while(rs.next())
			{
				String customer_name = rs.getString("customer_name");
				String invoice_date=rs.getString("invoice_date");
				String invoice_no1=rs.getString("invoice_no");                                                                         //product return for search
				String pid=rs.getString("pid");
				String pname=rs.getString("pname");
				String buying_quantity=rs.getString("buying_quantity");
				String price=rs.getString("price");
				String total_price=rs.getString("total_price");
				String invoice_id=rs.getString("invoice_id");
				Invoice in=new Invoice(customer_name,invoice_date,invoice_no1,pid,pname,buying_quantity,price,total_price,invoice_id);
				inv.add(in);
			}
        }
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
			
	}
	
}