# How to run the project
via IntelliJ:
1) Import the project in IntelliJ
2) Open "MainApp" class
3) Click run on the "main" function

via CLI:
1) Run "./mvnw package"
2) Run "./mvnw java:exec"

# How to run the test suite 
Run "./mvnw test" from the command line

# Solution
All comments related to the solution can be found in the 
ArmyBuilder class. In a production use case I would not 
use this many comments.

However, concretely in this case it is better to put 
the comments in the comments, rather than in the README.

# Notes
- See the "MainApp" class for how the class is used
- The key class of interest in ArmyBuilder
- A custom Hamcrest matches is supplied to check the validity of the generated army
- The tests are in ArmyBuilderTest class

The solution runs in O(num_army_units) time. 
In practice, however, due to domain constraints the number of unit types 
is less than a 1000 for sure, and in most games less than 10.

Given this domain constraint, we can see that there is an upper-bound
and in practice the runtime will be bounded and therefore O(1)

