# Project 2: Nondeterministic Finite Automata (NFA)

* Author: Quinton Duce, Lauren Nutting
* Class: CS361 Section 001
* Semester: Fall 2025

## Overview

This project models an instance of a nondeterministic
finite automaton (NFA) and its behavior.

## Reflection

This project was fairly easy to complete, given that there was a lot of methods that crossed over from our DFA Project. There was a bit of a struggle in understanding where exactly NFAState.java would be placed in the packages, because it needed to read State.java still. With an import of fa.State, we were able to fix that issue. Aside from the methods that carried over, the newer method that was the most difficult was probably eClosure, given that it was the most integral piece to the NFA functionality.

To make our code easy to debug and modify, we used frequent commits to our GitHub repository. We did this so in the case the code broke, we could easily pin-point where that occurred, and be able to restore an older version if needed. We feel fairly solid on the concepts of NFA, and there is nothing that we necessarily would change about our design process, other than maybe an earlier start on the project. That's also something that we would tell ourselves about the project ahead of time.


## Compiling and Using

To compile test.nfa.NFATest on onyx from the top directory of these files:

    [you@onyx]$ javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java

To run test.nfa.NFATest on onyx type in one single line:

    [you@onyx]$ java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/hamcrest.jar org.junit.runner.JUnitCore test.dfa.DFATest

## Sources used

N/A

----------