public class User
{
	String username;
	String position;
	String uid;
	public User(String username,String position,String uid)
	{
		this.username=username;
		this.position=position;
		this.uid=uid;
	}
	public String getusername()
	{
		return this.username;
	}
	public String getposition()
	{
		return this.position;
	}
	public String getuid()
	{
		return this.uid;
	}
}