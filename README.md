# java-streams-workshop
Materials for Java 8 streams API workshop

## Table of content
* [Introduction](#Introduction)
* [Topics in test classes](#Topics-in-test-classes-45-tests-in-total)
* [Repository structure](#Repository-structure)
* [Licence](#Licence)
* [Very important note](#Very-important-note)

## Introduction

This repository contains materials for Java Streams API workshops. This repository can be used either as private study or to organize workshops. In case you decide to use content of this repository to organize the workshops the following information are true. The aim of the workshop is to introduce Java developers to Java Streams API from Java 8. Materials contain:
 - Presentation with theoretical introduction
 - Cheat sheet
 - Pamphlet with model of objects used in exercises
 - Programming exercises (katas) to be solved during workshops.
 
Workshops can be organized either with normal or extended scope. Normal scope involves presentation + solving BasicTest class and would take about 4h. Extended scope involves presentation + solving BasicTest, AdvancedOneTest, AdvancedTwoTest classes and can take up to 7h. Normal scope should be enough for newcomers to Java Streams to tackle day-to-day problems. Extended scope involves topics on how to make using streams clearer. It is a bunch of nice-to-knows. While not critical for delivery using features from extended scope can improve code quality. Workshops should be limited to 8 people, excluding speaker.

### Topics in test classes (45 tests in total)

<details>
<summary>BasicTest (18 tests)</summary>
<ul>
<li>forEach</li>
<li>collectors(toList, toSet, toMap)</li>
<li>streams with map data structure </li>
<li>filter</li>
<li>map</li>
<li>flatMap</li>
<li>debug and code style</li>
<li>vertical processing</li>
<li>lazy</li>
<li>operation on closed streams</li>
</ul>
</details>

<details>
<summary>AdvancedOneTest (16 tests)</summary>
<p>
<ul>
<li>streams with arrays</li>
<li>joining, groupingBy, partitioningBy</li>
<li>skip, limit, distinct</li>
<li>allMatch, noneMatch, anyMatch, findFirst</li>
<li>sorted</li>
</ul>
</p>
</details>

<details><summary>AdvancedTwoTest (8 tests)</summary>
<p>
<ul>
<li>IntStream</li>
<li>count, sum</li>
<li>reduce</li>
<li>custom collector</li>
<li>parallel</li>
</ul>
</p>
</details>

<details><summary>OptionalTest (3 tests)</summary>
<p>
<ul>
<li>filter</li>
<li>map</li>
<li>flatMap</li>
</ul>
</p>
</details>

## Repository structure

<details><summary>Repository structure</summary>
<pre>
├───src
    │
    │
    ├───main
    │   ├───java
    │   │   └───t4upl
    │   │       └───model
    │   │           ├───optional (Classes used for optional exercises)
    │   │           │       BoxOfChocolates.java
    │   │           │       Cellar.java
    │   │           │       Chocolate.java
    │   │           │       House.java
    │   │           │
    │   │           └───stream (Classes used for stream exercises)
    │   │                   Continent.java
    │   │                   Nation.java
    │   │                   Person.java
    │   │
    │   └───resources (Resources to be given to participants during workshops)
    │           classModel.pdf (Model of java classes)
    │           java-8-streams-cheat-sheet.pdf (Helpful cheat sheet, not mine)
    │           presentation.pdf (Presentation with theory introduction)
    │        
    │       
    └───test
        └───java
            └───t4upl
                ├───pathtolambda (Used during presentation to explain lambda)
                │       PathToLambdaTest.java 
                │
                ├───practice (Exercises to be solved during workshop, initially all should fail) 
                │       AdvancedOneTest.java
                │       AdvancedTwoTest.java
                │       BasicTest.java
                │       OptionalTest.java
                │
                └───solution (Solutions to exercises, all should pass)
                        AdvancedOneTest.java
                        AdvancedTwoTest.java
                        BasicTest.java
                        OptionalTest.java
</pre>
</details>

## Licence

You may:
- Clone this repository and use it for individual study
- Organize workshops in your company using this repository as studying materials

You may not:
- Put this repository to any company or closed repository
- Modify any part of this repository
- Present content of this repository as your own work or work of your organization
- Use this repository to organize paid workshops

You must:
- Include link and information about author of this repository whenever you are using or building on contents of this repository

In case of questions feel free to contact me:
* [Linkedin](https://www.linkedin.com/in/patryk-drabi%C5%84ski-1a6209a6/)
* [Twitter](https://twitter.com/T4Upl)

All rights belong to Patryk Drabiński. I retain right to change licence of this repository at any time in any way.

## Very important note
<p>Stars and sharing are deeply appreciated &#128578; I didn't get paid for this.</p>

Please let me know if you find any errors.
