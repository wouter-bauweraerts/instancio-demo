# Instancio Demo

This is not part of the official documentation. 
It is just a small demo to trigger your interest in using 
the Instancio library to generate your test data.

See the [official site](https://www.instancio.org/) for a more detailed overview of the possibilities!

## Instantiating objects with Instancio
Instancio is a library to create objects and populate all members with random values.
The most trivial example is to instantiate an object like this:
```java
DemoPojo pojo = Instancio.create(DemoPojo.class);
```

This example gives us a new (random) instance of the ```DemoPojo``` class.

## Controlling the values of the instantiated object
We can also control the actual values of the members of the generated object. 
In the previous example, all values would be random. 
There are multiple scenarios where we don't want every value to be random, 
especially in testing.
We could instantiate an object like this:

```java
DemoPojo pojo = Instancio.of(DemoPojo.class)
        .set(field(DemoPojo::anInt), -25)
        .create();
```

In this example a random instance is created, but the value of the ```anInt``` member will be ```-25```.
This makes the library a very powerful and effective tool to setup test data quickly.

There are some downsides in this approach.
- The test class is aware of the fact that we are using Instancio
- We will have a lot of code duplication
  - especially when the value of some member has some rules that should be applied to it
  - The syntax is quite verbose at the moment.

## Using Models
Instancio has a solution for this problem. There is a concept of ```Model<T>```.
A model is a description of how Instancio should generate an instance of a certain class.
We can describe what rules the generator should follow when creating a new random object.
Some examples:
- We can define the min and/or max values of numeric values
- We can define that a date must be in the past or in the future
- We can describe the format that should be followed. E.g.: we can describe a phone number format

Creating a model is simple:

```java
Model<DemoPojo> model = Instancio.of(DemoPojo.class)
        .generate(allInts(), gen -> gen.ints().min(1))
        // ...
        .toModel();
```

For full model creation, see following files:
- [DemoPojo](https://github.com/wouter-bauweraerts/instancio-demo/blob/a5d7648a2352a175049f7a3c49a8c6a7dfdb4332/src/test/java/com/example/wouterbauweraerts/instancio/demo/fixtures/DemoPojoFixtures.java)
- [NestedPojo](https://github.com/wouter-bauweraerts/instancio-demo/blob/a5d7648a2352a175049f7a3c49a8c6a7dfdb4332/src/test/java/com/example/wouterbauweraerts/instancio/demo/fixtures/NestedPojoFixtures.java)

## Isolating Instancio from our test code
To avoid cluttering our tests with Instancio object creation, we can separate this into separate classes. 
I like to call these Fixtures.
In a Fixture class, we define the model and add some methods to create instances.
- By adding multiple methods with a different set of parameters
- By adding something I like to call a FixtureBuilder (see below)

Generally, I prefer to add a factory method for a completely random instance only.
In some cases I also add a method with one or two parameters. 
Especially for creating instances of entities (with an ID or something similar).

By moving all the Instancio code to fixture classes, we keep our tests as clean as possible.
Our tests are not aware of Instancio, so if at some point we decide to abandon it, we only need to update our fixtures.

## Using FixtureBuilder
When working with complex objects - having a sh*tload of members - 
it's simply not possible to add a factory method for every combination.

Together with my team, we created an elegant solution, inspired by [the builder pattern](https://www.tutorialspoint.com/design_pattern/builder_pattern.htm).
This FixtureBuilder helps us to create objects where we make sure we have full control. 
We can choose to set the value of some (or all) members to a specific value.

While working with this FixtureBuilder, we noticed that there was a lot of duplication.
Back to the drawing board!
We engineered a solution that allows us to move all common code to an abstract super class [```AbstractFixtureBuilder```](https://github.com/wouter-bauweraerts/instancio-demo/blob/a5d7648a2352a175049f7a3c49a8c6a7dfdb4332/src/test/java/com/example/wouterbauweraerts/instancio/demo/fixtures/AbstractFixtureBuilder.java)

## Summary
This is how we use Instancio! Make sure to also check how this is used in the tests.