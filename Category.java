public class Category
{
	String cat_id;
	String cat_name;
	String subcat_name;
	public Category(String cat_name,String subcat_name)
	{
		this.cat_name=cat_name;
		this.subcat_name=subcat_name;
	}
	public Category(String cat_id,String cat_name,String subcat_name)
	{
		this.cat_id=cat_id;
		this.cat_name=cat_name;
		this.subcat_name=subcat_name;
	}
	public Category(String subcat_name)
	{
		this.subcat_name=subcat_name;
	}
	public String getCat_name()
	{
		return cat_name;
	}
	public String getsubcat_name()
	{
		return subcat_name;
	}
	public String getcat_id()
	{
		return cat_id;
	}
}	