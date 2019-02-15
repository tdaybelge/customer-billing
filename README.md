customer-billing application is a simulation of a retail store where the following conditions are satisfied:

1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.

The application uses Maven as its build system. Run the following command in the root folder to build the application and run all unit tests.

mvn clean install

Jococo Maven plug-in is used to calculate the unit test coverage at the build. Its report can be found in the customer-billing/target/site/jococo directory.

UML diagram is automatically generated from the source code using ObjectAid UML Explorer Eclipse plug-in and can be found at customer-billing/uml.png.

SonarLint Eclipse plug-in is used to clean all static code analysis issues.

