
1️⃣ JVM Memory Overview

When a Java program runs, the JVM (Java Virtual Machine) divides memory into different runtime areas.

JVM Process
│
├── Heap (Shared)
│     ├── Young Generation
│     │     ├── Eden
│     │     ├── Survivor S0
│     │     └── Survivor S1
│     └── Old Generation (Tenured)
│
├── Metaspace (Native Memory)
│
└── Thread Memory (Per Thread)
      ├── Stack
      ├── Program Counter (PC Register)
      └── Native Method Stack
2️⃣ Heap Memory

#1)Heap : is shared among all threads.
Stores:
Objects
Arrays
Instance variables
String Pool (since Java 7)
Static variable values (inside Class object)
- When a class is loaded, the JVM creates a special object called a Class object in the heap.
- This java.lang.Class object represents the loaded class.
- The static fields of that class are stored inside this object’s memory structure.
- That means the values of static variables live in the heap, tied to the Class object.
- Number of loaded classes = Number of Class objects in the heap.
- Each Class object represents a single loaded class and holds:
- A reference to its metadata (in Metaspace).
- Its static fields (values stored in the heap).
- Information about methods, constructors, and annotations

##Generational Design (Based on Generational Hypothesis)
Most objects die young.
Young Generation:
New objects created in Eden
Surviving objects move to Survivor spaces
Minor GC runs here
##Old Generation:
Long-living objects promoted here
Cleaned during Major / Full GC

#2)7️⃣ Thread Memory (Per Thread)
- Stack memory is the runtime memory area used for method execution.
- Each thread running in the JVM has its own stack.
- It stores frames for method calls, along with local variables, partial results, and references to objects in the heap.
- It is LIFO (Last-In, First-Out) — when a method is invoked, a new frame is pushed; when the method completes, the frame is popped.

once stack is free no obj ref stored in stack and  once gc runs it clears unreferenced objects from heap.
Each thread has:
Stack
Stores:
Local variables
Primitive values
Object references
Method call frames

#3)
4️⃣ Metaspace (Major Change in Java 8)

Before Java 8 → PermGen(permanat generation)
Java 8 → Metaspace
Stores:
constants
Class metadata
Method metadata
Method bytecode
Runtime constant pool
Important:
Stored in Native Memory
Not part of Heap
Grows dynamically

5️⃣ Runtime Constant Pool(Class loading step)
#
- When you compile Java code, each .class file contains a constant pool table(from .class file) (part of the class file structure).-->JVM created Runtime constant pool for each class when it loads the class 
- This table stores symbolic references (like "Hello", MyClass, myMethod) rather than direct memory addresses.
- At runtime, the JVM creates a Runtime Constant Pool for each loaded class.
- The JVM uses this pool as a lookup table to resolve those symbolic references into actual memory addresses in the heap or metaspace

Each class/interface  has its own Runtime Constant Pool.- It’s created when the class is loaded into memory.
Stored in:
→ Metaspace
Contains:
class definations
String literals
Class/iterface references(only ref not obj)
Method references(symbolic ref of methos obj.callmethod)+Method meta data
Field references(variablle name)+field meta data
  Demo d = new Demo();   // symbolic ref "Demo" → resolved to Class object in Metaspace
  - When the JVM loads a class (like Demo), it creates a java.lang.Class object to represent that class at runtime.
- These Class objects are stored in Metaspace, not in the regular Java heap.

Symbolic references resolved at runtime(Resolution step of class loading)


#4)PC Register
Stores address of current instruction.
#5)Native Method Stack
Used for JNI calls.

###############

What Garbage Collection is
- A process: It runs inside the JVM to free memory occupied by objects that are no longer reachable there ref cleared from stack memory.
 supporting classes in the - java.lang.ref.* → Provides reference objects (WeakReference, SoftReference)that interact with GC behavior.
 - System.gc() → A method to request GC, but the JVM may ignore it.

1)Strong Reference :it is variable in stack references object in heap
2)WeakReference(class): var in stack referecing obj in heap but when gc runs it will deleted from memory even though this same var is referecing obj in heap.
WeakReference<Person> weakObj =new WeakReference<Person>(new Person);//if it tried to access this weak ef post garbage collection Runs it will get null value in stack for this variable 
3)SoftReference(class):it is type of weakref but when there is memory shortage then only it delete this obj from heap

##Garbage Collection Algorithms
1)Mark -Sweep
whenever new obj created it goes in Eden first,lets say garbage collection Runs then unref obj are marked in sweep step I) first remove unref obj
          II)move rest of survior obj in survior space and add age to objects.now gc runs again this called minor gc 
          III)long living obj promoted to old generation
- Cons: Leaves memory fragmentation (holes in heap).(for deleted objs)

2)Mark-Sweep-Compact
- Same as Mark-Sweep, but after sweeping, compacts memory to eliminate fragmentation.
- once GC runs remaing surviors obj stored in sequencial memory block leaving another sequential block free to store objects
- Pros: Prevents fragmentation.
- Cons: Compaction adds overhead (longer pauses).

##Types of Garbage Collector

1)Serial GC
-Algorithm
Young → Copying:before:Eden: [Live][Dead][Live][Dead] after copying algorithm :Survivor: [Live][Live]
Old → Mark-Compact
Single-threaded
Full Stop-The-World--once GC runs all thread pause 
Small applications only
2)Parallel GC (Java 8 Default)
Algorithm:
Young → Parallel Copying
Old → Parallel Mark-Compact
Characteristics:
Multi-threaded
Focuses on throughput
Stop-The-World for all phases
Best For:
Batch jobs
CPU-heavy applications
3. CMS (Concurrent Mark Sweep)
Algorithm:
Old Gen → Mark-Sweep (no compaction)
Advantages:
Low pause time
Problems:
Memory fragmentation
No memory compaction
Complex tuning
Removed in Java 14

4)4. G1 GC (Java 9+ Default)
G1 = Garbage First
Core Idea:
Heap divided into small regions (1–32MB).
No fixed Young/Old physically.
Phases:
Initial Mark
Concurrent Mark
Remark
Cleanup
Evacuation (Copying live objects)
Algorithm Used:
Region-based
Copying + Mark-Compact hybrid
Key Feature:
Collects regions with most garbage first.
Advantages:
Predictable pause time
Good for large heaps
Performs compaction
Handles fragmentation

5)5. Z-GC (Java 11+)
Designed for:
Ultra-low latency
Algorithm Type:
Concurrent Mark-Compact

6)6. Shenandoah GC
Similar goal to ZGC:
Low latency
Algorithm:
Concurrent Mark-Compact
write:Tracks object references modified during GC.(G1,ZGC,Shenandoah)
Uses read/write barriers
Suitable For:
Microservices
Cloud-native systems


##Deffalut GC
Java 8 - Parallel GC(default) Availble - Serial GC ,CMS(Concurrent Mark Sweep),G1 GC
java 9 ,10 - G1 GC(default) Available- Serial GC ,Parallel GC,CMS
java 11 -G1 GC Availble - Serial GC ,Parallel GC,CMS,Z-GC
java 12 ,13 -G1 GC -Available -Serial GC ,Parallel GC,CMS,Z-GC,Shenandoah
java 14 -G1 GC -Availble -Except CMS all
java 15 16 17 -G1 GC-Availble -Except CMS all
#####
Small app → Serial
Batch processing → Parallel
Typical Spring Boot app → G1
Ultra-low latency trading → ZGC
Cloud-native microservices → Shenandoah