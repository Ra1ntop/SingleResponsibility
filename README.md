# Single Responsibility Principle — Java Demo (without Spring)

This repository demonstrates the **Single Responsibility Principle (SRP)** using simple Java examples **without Spring**.

The goal is to compare two approaches:

| Module | Description |
|--------|------------|
| `wrong-without-spring` | SRP is violated — one class handles business logic, email template building, and email sending |
| `right-without-spring` | Correct SRP — responsibilities are separated into services, a template registry, and an email sender |

---

## Structure & Idea

### ❌ `wrong-without-spring`
- A single “God-class” controls user actions (register, charge, block, delete)
- Builds email templates inside business logic
- Sends emails directly
- Hard to test, change, and maintain

Used to **show the problem** in the presentation.

### ✅ `without-spring`
- `TemplateRegistry` stores templates and returns them by type  
- `NotificationService` selects templates and triggers email sending  
- `MailService` interface abstracts the sending mechanism  
- Business services (`RegistrationService`, `BillingService`, etc.) are clean and testable

Used to **show the solution**.

---

## Key Concepts Demonstrated

- SRP in service design
- Avoiding “God classes”
- Dependency injection *without* a framework
- Reusable email templates
- Replaceable email sender (SMTP → mock → console)
- Unit-testable design

---

## Running the Examples

> Open modules in your IDE and run `Main` classes manually  
> (Or add Maven/Gradle wrapper — PR welcome)

---

## Presentation

The accompanying **PowerPoint slides** explain:

- SRP theory
- What goes wrong in `wrong-without-spring`
- Architecture for `right-without-spring`
- Code examples side-by-side

**Slides location:** `https://docs.google.com/presentation/d/1ToZN6CSHzbO4WTCOPCxQnwiWI9P6w8V1XlkNhFTEbG8/edit?usp=sharing`

---

## Next Steps / Contributions

Possible improvements:

- Add build script (Gradle or Maven)
- Add unit tests (JUnit + Mockito)
- Add more real-world email examples (HTML templates, attachments)
- Convert console mail sender to SMTP

PRs and feedback are welcome!
