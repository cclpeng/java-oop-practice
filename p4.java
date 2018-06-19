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

class Plus
{
	//static methods because we will call plus() without creating
	//any Plus objects (only made the class to work with Elements)
	public static Element plus(EBoolean a, EBoolean b) 
	{ 
		int num = (a.value || b.value) ? 1 : 0;  //true=1, false=0
		EBoolean c = new EBoolean(num);
		return c; 
	}

	public static Element plus(EInteger a, EInteger b) 
	{ 
		EInteger c = new EInteger(a.value + b.value);
		return c; 
	}

	public static Element plus(EString a, EString b) 
	{
		EString c = new EString(a.value + b.value);
		return c; 
	}

	

}

public class p4
{
	public static void main(String[] args) 
	{
		EString a = new EString("happyb");
		EBoolean b = new EBoolean(1);     //true
		EBoolean z = new EBoolean(0);     //false
		EInteger c = new EInteger(50);
		
		Element d = Plus.plus(a, a);      //should get "happybhappyb"
		System.out.println("estring+estring:  " + d);

		Element e = Plus.plus(b, z);      //should get true || false == true
		System.out.println("ebool+ebool:  " + e);

		Element f = Plus.plus(c, c);      //should get 50+50==100
		System.out.println("eint+eint:  " + f);	
	}
}