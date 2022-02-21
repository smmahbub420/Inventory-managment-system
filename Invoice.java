import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.ArrayList;
public class Invoice
{
	String customer_name;
	String invoice_date;
	String invoice_no;
	String pid;
	String pname;
	String buying_quantity;
	String price;
	String total_price;
	String invoice_id;
	public Invoice(String customer_name,String invoice_date,String invoice_no,String pid,String pname,String buying_quantity,String price,String total_price,String invoice_id)
	{
		this.customer_name=customer_name;
		this.invoice_date=invoice_date;
		this.invoice_no=invoice_no;
		this.pid=pid;
		this.pname=pname;
		this.buying_quantity=buying_quantity;
		this.price=price;
		this.total_price=total_price;
		this.invoice_id=invoice_id;
		
	}
	
	public Invoice(String pid,String pname,String buying_quantity,String total_price)
	{
		this.pid=pid;
		this.pname=pname;
		this.buying_quantity=buying_quantity;
		this.total_price=total_price;
	}
	public String getcustomer_name()
	{
		return this.customer_name;
		
	}
	public String getinvoice_date()
	{
		return this.invoice_date;
	}
	public String getinvoice_no()
	{
		return this.invoice_no;
		
	}
	public String getpid(){
		return this.pid;
	}
	public String getpname()
	{
		return this.pname;
	}
	public String getbuying_quantity()
	{
		return this.buying_quantity;
	}
	public String getprice()
	{
		return this.price;
	}
	public String gettotal_price()
	{
		return this.total_price;
	}
	public String getinvoiceid()
	{
		return this.invoice_id;
	}
	
}