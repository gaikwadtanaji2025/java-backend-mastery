class StringMethodsEx{
    public static void main(String[] args) {
        String str = "  Hello, World!  ";
        //String Creation
        String s1="Hello Java";//String Literal stored in String Pool
        String s2=new String("Hello Java");//String Object stored in Heap Memory
        System.out.println(s1==s2) ; //false
        System.out.println(s1.equals(s2)) ; //true
        System.out.println("String length: " + s1.length());
        System.out.println("charAt(4): " + s1.charAt(4));
        
        //equals() method equalsIgnoreCase() method
        String a ="java";
        String b="JAVA";
        System.out.println(a.equals(b)); //false
        System.out.println(a.equalsIgnoreCase(b)); //true

        // Length of the string
        int length = str.length();
        System.out.println("Length: " + length);

        // Convert to uppercase
        String upperStr = str.toUpperCase();
        System.out.println("Uppercase: " + upperStr);

        // Convert to lowercase
        String lowerStr = str.toLowerCase();
        System.out.println("Lowercase: " + lowerStr);

        // Trim whitespace
        String trimmedStr = str.trim();
        System.out.println("Trimmed: '" + trimmedStr + "'");

        // Substring
        String subStr = str.substring(2, 7);
        System.out.println("Substring (2,7): '" + subStr + "'");
        String subStrFromIndex = str.substring(7);
        System.out.println("Substring from index 7: '" + subStrFromIndex + "'");

        // Replace characters
        String replacedStr = str.replace('o', '0');
        System.out.println("Replaced 'o' with '0': " + replacedStr);

        // Check if string contains a substring
        boolean containsWorld = str.contains("World");
        System.out.println("Contains 'World': " + containsWorld);

        //startsWith() and endsWith() methods lastIndexOf() method indexOf() method
        String sample = "Hello, welcome to Java programming.";
        System.out.println("index of "+"java" + sample.indexOf("java")); //-1
        System.out.println("Starts with 'Hello': " + sample.startsWith("Hello")); //true
        System.out.println("Ends with 'programming.': " + sample.endsWith("programming.")); //true
        System.out.println("Last index of 'o': " + sample.lastIndexOf('o')); //22


        // Split the string
        String[] parts = str.trim().split(", ");
        System.out.println("Split by ', ':");
        for (String part : parts) {
            System.out.println(part);
        }

        //replace() method replaceAll() method
        String replaceExample = "foo bar foo baz";
        String replacedAllStr = replaceExample.replaceAll("foo", "qux");
        System.out.println("Replaced all 'foo' with 'qux': " + replacedAllStr);
        String replaceAllExample = "The rain in Spain stays mainly in the plain.";
        String replacedAllExampleStr = replaceAllExample.replaceAll("ain", "ane");
        String replaceEx =replaceAllExample.replace("ain", "ane");
        System.out.println("Replaced  'ain' with 'ane': " + replaceEx);

        //isEmpty() method isBlank() method java 11 ++
        String emptyStr = "";
        String blankStr = "   ";
        System.out.println("Is empty string empty(): " + emptyStr.isEmpty()); //true
        System.out.println("Is blank string isBlank(): " + blankStr.isBlank()); //true      
        System.out.println("Is blank string empty(): " + blankStr.isEmpty()); //false

        //String Concatination
        String firstName = "John";
        String lastName = "Doe";
        //compile time concatination as it envolves constants
        String greeting = "Hello, " + "World!";
        System.out.println(greeting);

        //runtime concatination as it envolves variables
        String fullName1 = firstName + lastName;//Using + operator createss new String Object and it becomes inefficient if used multiple times in code and new StringBuilder.append(firstName).append(lastName).toString() is preferred
        System.out.println("Full Name: " + fullName1);
        String fullName = firstName.concat(" ").concat(lastName);
        System.out.println("Full Name: " + fullName);

        int salary = 50000;
        String salaryStr =String.valueOf(salary);
        System.out.println("Salary String: " + salaryStr);

        //tocharArray() method
        String sampleStr = "Sample";   
        char[] charArray = sampleStr.toCharArray();
        for (char c : charArray) {
            System.out.println(c);
        }
        //compareTo() method
        String str1 = "apple";
        String str2 = "banana";
        System.out.println("Comparing 'apple' and 'banana': " + str1.compareTo(str2)); //negative value less than 
        System.out.println("java".compareTo("java")); //0
        System.out.println("orange".compareTo("apple")); //positive value

        //itern()
          String p1 = new String("CoreJava");
        String p2 = p1.intern();

        System.out.println(p1 == p2); // false
        System.out.println(p2 == "CoreJava"); // true
        //null safe beest practice for equals() method
        String nullable = null;

        System.out.println("Java".equals(nullable)); // false (no NPE)
    }
}