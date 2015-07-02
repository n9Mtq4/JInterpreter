<b>JInterpreter</b>
===================


##What is it?
JInterpreter is a java library that allows for interpreting java without compiling it. Currently the syntax is different of that of java, however it does access java libraries.

------------------------

##How it works
JInterpreter works by using the [java.lang.Reflect](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/package-summary.html) api. If you aren't familiar with it, basically it allows calling constructors, methods, and getting and settings fields based of off their name.

------------------------

##Some commands / what you can do

###Argument Parser
In the following examples, args... represents passing arguments. The options for these arguments are as follows
 - An Integer, a number without decimals ex: 12
 - A Double, a number with decimals ex: 12.5
 - A Boolean, true or false ex: false
 - A String, text surrounded by quotes ex: "Hello World"
 - Another variable, variable name surrounded with { and } ex: {frm}
 - null (note: only works with setfields and invoking. not constructors)

###Setting a variable
```
set varname classname args...
#example
set frm javax.swing.JFrame "Hello World"
```
this is equivalent to 
```java
classname varname = new classname(args...);
//example
JFrame frm = new JFrame("Hello World");
```

###Getting a variable
```
get varname
#example
get frm
```
this is equvalant to
```java
System.out.println(Arrays.toString(varname));
//or, depending on if the varname is an array or a Collection
System.out.println(varname.toString());
//example
System.out.println(frm.toString());
```

###Removing a variable
```
unset varname
#example
unset frm
```
this is equivalent to
```java
//there is no equvalant to this in java
```

###Calling a method
```
invoke varname methodname args...
#example
invoke frm setVisible true
```
this is equvalant to
```java
varname.methodname(args...);
//example
frm.setVisible(true);
```

###Calling a static method
```
invokestatic classname method args...
#example
invokestatic java.lang.System getProperty "os.name"
```
this is equivalent to
```java
classname.method(args...);
System.getProperty("os.name");
```

###Setting a field
```
setfield varname fieldname newvalue
```
this is equivalent to
```java
varname.fieldname = newvalue;
```

###Getting a field
```
getfield varname fieldname
```
this is equivalent to
```java
System.out.println(varname.fieldname.toString());
```

###Setting a static field
```
setstaticfield classname fieldname newvalue
#example
setstaticfield com.n9mtq4.jpswing.JPSwing instance null
```
this is equivalent to
```java
classname.fieldname = newvalue;
//example
com.n9mtq4.jpswing.JPSwing.instance = null
```

###Getting a static field
```
getstaticfield classname fieldname
#example
getstaticfield java.lang.System out
```
this is equivalent to
```java
System.out.println(classname.fieldname.toString());
//example
System.out.println(java.lang.System.out.toString());
```
