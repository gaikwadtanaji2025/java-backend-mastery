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



Here is your complete **README.md** content.
You can copy this directly into your project.

---

# 📘 Java Regex Deep Dive – Complete Guide

## 📌 Overview

This document explains Regular Expressions (Regex) in Java from beginner to backend-production level, including:

* `String.matches()`
* `Pattern`
* `Matcher`
* Thread safety
* Performance considerations
* Real-world backend usage
* Internal working concepts
Pattern Matcher both are classes 
---

# 1️⃣ What Is Regex?

Regex (Regular Expression) is a pattern language used to:

* Validate input
* Search text
* Extract substrings
* Replace content

Example:

```
^[a-zA-Z]+$
```

Meaning:

* `^` → Start of string
* `[a-zA-Z]+` → One or more letters
* `$` → End of string

---

# 2️⃣ Three Ways Regex Works in Java

Java provides 3 main mechanisms:

| Level  | API                 | Description           |
| ------ | ------------------- | --------------------- |
| High   | `String.matches()`  | Simple validation     |
| Medium | `Pattern + Matcher` | Reusable & performant |
| Low    | Manual parsing      | No regex              |

---

# 3️⃣ String.matches() – High-Level API

Example:

```java
String upiId = "tanaji@upi";

if (upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$")) {
    System.out.println("Valid UPI");
}
```

### Important:

`matches()` checks the **entire string**.

Internally it behaves like:

```java
Pattern p = Pattern.compile(regex);
Matcher m = p.matcher(input);
return m.matches();
```

### ❗ Performance Note

Every call to `matches()`:

* Compiles regex again
* Creates new Pattern
* Creates new Matcher

Fine for small systems.
Not ideal for high-traffic backend systems.

---

# 4️⃣ Pattern – Compiled Regex

```java
Pattern pattern = Pattern.compile("^[a-z]+$");
```

### What It Does:

* Parses regex
* Builds internal finite automaton
* Stores compiled representation

### Key Properties:

* Immutable
* Thread-safe
* Can be reused

Think of Pattern as a **compiled blueprint**.

---

# 5️⃣ Matcher – Execution Engine

```java
Matcher matcher = pattern.matcher("tanaji");
```

Matcher:

* Holds input string
* Maintains current match state
* Executes matching operations

### Matcher Stores:

* Current index position
* Start & end of match
* Captured groups
* Backtracking state

### Important:

Matcher is **NOT thread-safe**.

Why?

Because it maintains mutable internal state during matching.

---

# 6️⃣ Difference Between matches() and find()

### `matches()`

* Entire string must match

```java
pattern.matcher("abc123").matches(); // false
```

---

### `find()`

* Substring match

```java
pattern.matcher("abc123").find(); // true
```

Why?

Because `"abc"` matches `[a-z]+`.

---

# 7️⃣ Anchors (^ and $)

```
^[pattern]$
```

* `^` → Start of string
* `$` → End of string

Without anchors:
Regex may match substrings.

Example:

```
[a-z]+@[a-z]+
```

Input:

```
###tanaji@upi123###
```

It will match substring `tanaji@upi`.

With anchors:

```
^[a-z]+@[a-z]+$
```

It will fail.

---

# 8️⃣ Why Pattern Is Thread-Safe

Pattern:

* Immutable
* Does not store matching state
* Safe to share across threads

Example:

```java
private static final Pattern UPI_PATTERN =
        Pattern.compile("regex");
```

---

# 9️⃣ Why Matcher Is NOT Thread-Safe

Matcher:

* Stores current position
* Stores last match boundaries
* Stores captured groups
* Updates internal state during execution

If two threads use same Matcher:

* Race condition
* Corrupted results
* Unpredictable behavior

---

# 1️⃣0️⃣ Why This Is Wrong

```java
private static final Matcher MATCHER =
    Pattern.compile("regex").matcher("");
```

This creates a shared Matcher.

If multiple threads call:

```java
MATCHER.reset(input);
MATCHER.matches();
```

It causes:

* Shared mutable state
* Race conditions

❌ Not safe
❌ Not production-grade

---

# 1️⃣1️⃣ Correct Production Pattern

```java
private static final Pattern UPI_PATTERN =
        Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");

public boolean isValid(String input) {
    return UPI_PATTERN.matcher(input).matches();
}

##public Matcher matcher(CharSequence input)----->gives mather obj.

```

Why this is safe:

* Pattern shared
* Matcher created per call
* No shared mutable state

---

# 1️⃣2️⃣ matcher("") Explained

```java
pattern.matcher("")
```

Creates a Matcher using:

* The compiled pattern
* Empty string as input

Matcher always needs an input string.

You can later change input using:

```java
matcher.reset("newInput");
```

---

# 1️⃣3️⃣ Performance Insight (Backend Level)

If:

* 10,000 requests per second
* Each request calls `String.matches()`

Then:

* Regex gets compiled 10,000 times per second

Wasteful.

Better:

* Compile Pattern once
* Reuse it

---

# 1️⃣4️⃣ Real Backend Usage (Spring Boot)

Best approach in modern applications:

### Option 1 – Precompiled Pattern

Used in services.

### Option 2 – Bean Validation

```java
@Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z]+$")
private String upiId;
```

Cleaner and declarative.

---

# 1️⃣5️⃣ Advanced Concepts

Java Regex Engine:

* Uses backtracking
* Based on NFA (Non-deterministic Finite Automaton)

Poorly written regex can cause:

* Catastrophic backtracking
* Performance attacks (ReDoS)

Example of dangerous regex:

```
(a+)+
```

Should be used carefully.

---

# 1️⃣6️⃣ Core Design Principle Learned

> Share immutable objects
> Do not share mutable objects

Examples:

| Immutable | Thread-Safe |
| --------- | ----------- |
| String    | Yes         |
| Pattern   | Yes         |

| Mutable       | Not Thread-Safe |
| ------------- | --------------- |
| StringBuilder | No              |
| Matcher       | No              |

---

# 1️⃣7️⃣ Interview-Level Summary

If asked:

### Why not reuse Matcher?

Because Matcher maintains mutable state during matching and is not thread-safe.

### Why use Pattern?

Because Pattern is immutable, thread-safe, and avoids repeated compilation.

### Does String.matches() check full string?

Yes. It performs full string matching internally.
