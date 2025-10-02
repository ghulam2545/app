#### Problem Statement:

We need to build a system to handle customer onboarding and report generation in a decoupled, event-driven manner. The workflow involves three services: Customer Service, Notification Service, and Report Service.

- **Customer Service** collects customer information and persists it into the database. After saving, it publishes an event to Kafka to notify that the customer has been registered.

- **Notification Service** consumes this event and sends an email to the customer confirming that their information has been received and that a report will be sent shortly.

- **Report Service** also consumes the customer registration event, generates a PDF report based on the customer data, and publishes another event to Kafka once the report is ready.

- **Notification Service** consumes the report-ready event and sends a second email to the customer with the generated PDF report attached.

The goal is to implement a reliable, asynchronous, and decoupled workflow using Kafka to handle event-driven communication between services.