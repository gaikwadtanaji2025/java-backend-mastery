1. What is String in Java (Practically)

String is immutable → once created, it cannot be changed

Any modification creates a new object

Used heavily in:

API requests / responses

Logging

Configuration values

JSON, XML, SQL queries

String s = "hello";
s.concat(" world");   // original string unchanged


Why it matters:

Thread-safe by default

Prevents accidental data modification

2. String Pool (Why it exists)
String a = "java";
String b = "java";


Both a and b point to same object in String Pool

Saves memory in large applications

String x = new String("java");


Forces new object → avoids pool

Real use:

Pool is used automatically for literals

new String() mostly avoided in real projects

3. == vs equals() (VERY IMPORTANT)
String a = "test";
String b = new String("test");

a == b        // false → compares references
a.equals(b)   // true  → compares content


Real-world rule:

Always use equals() for string comparison

== is used only when checking same object reference (rare)

4. Commonly Used String Methods (Project-Focused)
length()
username.length() > 8


Used for:

Validation (password, username)

charAt(int index)
char firstChar = name.charAt(0);


Used for:

Parsing

Masking data (e.g., ****1234)

substring()
String year = date.substring(0, 4);


Used for:

Extracting tokens from input

Parsing IDs, dates, codes

contains()
if (email.contains("@")) { }


Used for:

Basic validations

Keyword detection

startsWith() / endsWith()
file.endsWith(".pdf")


Used for:

File handling

URL checks

Content-type validation

toUpperCase() / toLowerCase()
role.toUpperCase()


Used for:

Case-insensitive comparison

Normalizing user input

trim()
input.trim()


Used for:

Removing accidental spaces from user input

Preventing validation bugs

replace()
phone.replace("-", "")


Used for:

Data cleaning

Formatting inputs

5. isEmpty() vs isBlank() (Java 11+)
str.isEmpty();  // length == 0
str.isBlank();  // empty OR only spaces


Real rule:

Use isBlank() for user input

Use isEmpty() for internal logic

6. split()
String[] parts = csv.split(",");


Used for:

CSV parsing

Headers

Simple tokenization

Avoid for:

Complex parsing → use regex or libraries

7. String Concatenation (Performance Rule)

❌ Bad in loops:

for (...) {
  result = result + value;
}


✅ Correct:

StringBuilder sb = new StringBuilder();
sb.append(value);


Why:

Avoids creating multiple objects

Improves performance in loops

8. StringBuilder vs StringBuffer
Feature	StringBuilder	StringBuffer
Thread-safe	❌	✅
Performance	Fast	Slower
Usage	Most apps	Rare (legacy)

Rule:

Use StringBuilder unless thread safety is required

9. Null-safe String comparison (REAL BUG FIX)

❌ Risky:

input.equals("YES");


✅ Safe:

"YES".equals(input);


Why:

Prevents NullPointerException

Common production bug prevention

10. Interview One-Liners (Quick Recall)

String is immutable for security + thread safety

Use equals(), not ==

Use StringBuilder in loops

Avoid new String() unless required

Normalize input using trim() and case conversion

11. Where This Appears in Real Projects

REST APIs (request validation)

Logging and debugging

SQL query building (careful!)

File processing

Data transformation

📌 Status

✔ Practiced
✔ Interview-ready
✔ Production-relevant

I