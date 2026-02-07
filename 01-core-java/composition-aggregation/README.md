Inheritance = Behavior fixed at object creation
Composition = Behavior configurable at runtime

--Composition(Strong HAS A) -- Aggregation(Weak has a)

# Composition vs Aggregation in Java (Enterprise-Level Summary)

This document explains **Composition and Aggregation** in Java with **real enterprise application examples**, focusing on **Spring Boot, JPA, and interview expectations**.

---

## 1. Core Definition (Must Remember)

Both **Composition** and **Aggregation** represent **HAS-A relationships**.

The **only deciding factor** is **lifecycle dependency**.

> If parent object is destroyed, does the child also get destroyed?

- **Yes** → Composition  
- **No** → Aggregation  

---

## 2. Composition (Strong HAS-A)

### Meaning
- Parent **owns** the child
- Child **cannot exist independently**
- Parent controls **creation & destruction**

### Real-World Analogy
**Order → OrderItems**  
If order is deleted, items must be deleted.

---

### Enterprise JPA Example (Composition)

```java
@Entity
class Order {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
    }
}

@Entity
class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    private String productName;
    private int quantity;
}

## Why This Is Composition
OrderItem lifecycle depends on Order

cascade = ALL → parent controls child

orphanRemoval = true → child deleted automatically


Aggregation (Weak HAS-A)
Meaning

Parent uses the child

Child exists independently

No lifecycle ownership

Real-World Analogy

Order → Customer
Customer exists even if order is deleted.
@Entity
class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
}
@Entity
class Order {

    @ManyToOne
    private Customer customer;
}

### aggregation in service layer
Services have independent lifecycle
Easy mocking & testing
Loose coupling (microservice-friendly)

6. Why Composition Wins Over Inheritance (Key Insight)

Inheritance:

Behavior fixed at object creation

Tight coupling

Hard to change at runtime

Composition:

Behavior delegated

Can change behavior at runtime

Supports Strategy pattern
#Runtime behaviour
interface RetryStrategy {
    void retry();
}

class NoRetry implements RetryStrategy {
    public void retry() {
        System.out.println("No retry");
    }
}

class FastRetry implements RetryStrategy {
    public void retry() {
        System.out.println("Fast retry");
    }
}
class UpiPayment {

    private RetryStrategy retryStrategy;

    UpiPayment(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    void setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    void pay() {
        retryStrategy.retry();
    }
}
UpiPayment upi = new UpiPayment(new NoRetry());
upi.pay();          // No retry

upi.setRetryStrategy(new FastRetry());
upi.pay();          // Fast retry
