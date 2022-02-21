import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
public class Product
{
	String pid;
	String pname;
	String quantity;
	String buying_price;
	String selling_price;
	String incoming_date;
	String subcat_name;
	public Product(String pid,String pname,String quantity,String buying_price,String selling_price,String incoming_date,String subcat_name)
	{
		this.pid=pid;
		this.pname=pname;
		this.quantity=quantity;
		this.buying_price=buying_price;
		this.selling_price=selling_price;
		this.incoming_date=incoming_date;
		this.subcat_name=subcat_name;
	}
	public Product(String quantity)
	{
		this.quantity=quantity;
	}
	public String getpid()
	{
		return this.pid;
	}
	public String  getpname()
	{
		return this.pname;
	}
	public String getquantity()
	{
		return this.quantity;
	}
	public String getbuying_price()
	{
		return this.buying_price;
	}
	public String getselling_price()
	{
		return this.selling_price;
	}
	public String  getincoming_date()
	{
		return this.incoming_date;
	}
	public String  getsubcat_name()
	{
		return this.subcat_name;
	}
}