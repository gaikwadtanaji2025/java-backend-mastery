# Spring Boot: Singleton with Prototype Bean using @Lookup

## Overview
This project demonstrates how to correctly inject a **prototype-scoped bean into a singleton bean** in a Spring Boot application using the `@Lookup` annotation.

By default, injecting a prototype bean into a singleton causes Spring to create only **one instance** of the prototype. This project shows how to fix that issue.

---

## Problem Statement
- Spring singleton beans are created once at startup
- Prototype beans are expected to be created per request
- Direct injection of prototype into singleton breaks prototype behavior

---

## Solution
Spring provides `@Lookup` to dynamically fetch a new prototype bean instance **each time it is needed**.

Spring internally:
- Creates a CGLIB proxy of the singleton bean
- Overrides the lookup method
- Calls `ApplicationContext.getBean()` on every invocation

---

## Architecture
used static and final variable to demonstrate every http request get prototype object new obj