/*
so notice file name is p4.java, but theres no "class p4."
If you do public class ___, then there can only be one public class ___ in
the file, and the file's name must be ___.java.
Otherwise you can just do a bunch of class A{}, class B{}, they will just be
private
*/

abstract class Element 
{
	static int count = 0;
	public Element(){ count++; }
	public abstract int hash(); //"abstract" means the func can't have ANY
	//	implementation. no hash() {return 0; }

	public int absvalue(int i)  // since has no abstract, not penalized
				//even tho this func NOT implemented in the other classes
	{  //returns the absolute value of an integer
		if(i < 0)
			return i * -1;
		else
			return i;
	} // absvalue()

	public static int getCount() { return count; }
}

class EBoolean extends Element
{
	static int count = 0; //can only have 1 count within this class
	protected boolean value;

	public EBoolean(int v)
	{ 
		count++;
		if(v == 0) 
			value = false; 
		else 
			value = true; 
	}// constructor EBoolean()

	public static int getCount() { return count; }

	@Override       // @Override is just a tag, reminds us we overrode it
	public String toString()
	{
		if(value == true)			//in java, boolean val written as [true]
			return "$true$";		//not [True] or ["true"]/["True"]
		else
			return "$false$";
	} // toString()

	@Override 
	public int hash()
	{
		if(value == true)
			return 1;
		else  		// 0 for false
			return 0; 
	} // hash()
} // class EBoolean

class EString extends Element
{
	static int count = 0;
	protected String value;			//String is part of standard library...dont 
							//need to import anything new above
	
	// in java, dont need stringcpy, assignment by = sign
	public EString(String v) 
	{ 
		count++;
		if(v.length() > 24)	
			v = v.substring(0, 23);  //trim string if length > 24
		value = v; 
	} 

	protected String getString() { return value; } //protected works...
	public static int getCount() { return count; }

	@Override
	public String toString() { return value; }
	
	@Override 
	public int hash() { return value.length() % 17; }
	
}

class EInteger extends Element
{
	static int count = 0;
	protected int value;
	public EInteger(int v) 
	{ 
		count++;
		value = v; 
	}

	@Override
	// public String toString() { return "" + value; }    OR
	public String toString() { return String.valueOf(value); } //capital "O"

	@Override 
	public int hash() { return absvalue(value) % 17; }

	public static int getCount() { return count; }
}

public class p4
{
	public static void main(String[] args) 
	{
		EString a = new EString("happybirthday45678901234567");
		//testing if estring's string gets truncated to 24 letters max
		// System.out.println( a.getString() );	
		// System.out.println( a.hash() );  //test EString hash func

		EBoolean b = new EBoolean(1);
		//testing eboolean's toString() func
		// System.out.println(b.toString());
		// System.out.println( b.hash() );   //test hash func of EBoolean

		EInteger c = new EInteger(50);
		//testing EInteger's toString method
		// System.out.println(c.toString());
		// System.out.println( c.hash() );		//test hash func of EInteger

		//test getCount
		System.out.println("Number of elements: " + Element.getCount() );
		System.out.println("Number of estrings: " + a.getCount() );
		System.out.println("Number of ebooleans: " + b.getCount() );
		System.out.println("Number of eintegers: " + c.getCount() );
	}
}