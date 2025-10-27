package fa.nfa;

import java.util.*;

public class NFA implements NFAInterface {

    private final Set<NFAState> states;
    private final Set<Character> sigma;
    private NFAState startState;
    private final Set<NFAState> finalStates;
    private final Map<String, NFAState> stateMap; // For quick state lookup by name

    public NFA() {
        states = new HashSet<>();
        sigma = new HashSet<>();
        finalStates = new HashSet<>();
        stateMap = new HashMap<>();
    }

    @Override
    public boolean addState(String name) {
        if (stateMap.containsKey(name)) {
            return false;
        }
        NFAState state = new NFAState(name);
        states.add(state);
        stateMap.put(name, state);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        NFAState state = stateMap.get(name);
        if (state == null) {
            return false;
        }
        finalStates.add(state);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        NFAState state = stateMap.get(name);
        if (state == null) {
            return false;
        }
        startState = state;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        // Validate symbol is in alphabet (except epsilon)
        if (onSymb != 'e' && !sigma.contains(onSymb)) {
            return false;
        }

        // Get source state
        NFAState from = stateMap.get(fromState);
        if (from == null) {
            return false;
        }

        // Validate and get all target states
        Set<NFAState> toStateSet = new HashSet<>();
        for (String toStateName : toStates) {
            NFAState toState = stateMap.get(toStateName);
            if (toState == null) {
                return false;
            }
            toStateSet.add(toState);
        }

        // Add transitions
        for (NFAState to : toStateSet) {
            from.addTransition(onSymb, to);
        }
        return true;
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.getToStates(onSymb);
    }

    @Override
    public Set<Character> getSigma() {
        return new HashSet<>(sigma);
    }

    @Override
    public NFAState getState(String name) {
        return stateMap.get(name);
    }

    @Override
    public boolean isFinal(String name) {
        NFAState state = stateMap.get(name);
        return state != null && finalStates.contains(state);
    }

    @Override
    public boolean isStart(String name) {
        NFAState state = stateMap.get(name);
        return state != null && startState == state;
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> closure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();

        // Start DFS from the given state
        stack.push(s);
        closure.add(s);

        while (!stack.isEmpty()) {
            NFAState current = stack.pop();
            Set<NFAState> epsilonTransitions = current.getToStates('e');

            for (NFAState next : epsilonTransitions) {
                if (!closure.contains(next)) {
                    closure.add(next);
                    stack.push(next);
                }
            }
        }

        return closure;
    }

    @Override
    public boolean accepts(String s) {
        // Start with epsilon closure of start state
        Set<NFAState> currentStates = eClosure(startState);

        // Process each input symbol
        for (char c : s.toCharArray()) {
            if (!sigma.contains(c)) {
                return false; // Invalid input symbol
            }

            Set<NFAState> nextStates = new HashSet<>();

            // For each current state, get all possible transitions on current symbol
            for (NFAState state : currentStates) {
                Set<NFAState> transitions = getToState(state, c);
                for (NFAState next : transitions) {
                    // Add epsilon closure of each destination state
                    nextStates.addAll(eClosure(next));
                }
            }

            currentStates = nextStates;
        }

        // Check if any current state is final
        return currentStates.stream().anyMatch(finalStates::contains);
    }

    @Override
    public int maxCopies(String s) {
        int maxCopies = 1; // Start with 1 copy (start state)
        Set<NFAState> currentStates = eClosure(startState);
        maxCopies = Math.max(maxCopies, currentStates.size());

        // Process each input symbol
        for (char c : s.toCharArray()) {
            if (!sigma.contains(c)) {
                return maxCopies;
            }

            Set<NFAState> nextStates = new HashSet<>();

            // For each current state, get all possible transitions
            for (NFAState state : currentStates) {
                Set<NFAState> transitions = getToState(state, c);
                for (NFAState next : transitions) {
                    nextStates.addAll(eClosure(next));
                }
            }

            currentStates = nextStates;
            maxCopies = Math.max(maxCopies, currentStates.size());
        }

        return maxCopies;
    }

    @Override
    public boolean isDFA() {
        // Check each state's transitions
        for (NFAState state : states) {
            // Check for epsilon transitions
            if (!state.getToStates('e').isEmpty()) {
                return false;
            }

            // Check for each symbol in the alphabet
            for (char symbol : sigma) {
                Set<NFAState> transitions = state.getToStates(symbol);
                // DFA must have exactly one transition for each symbol
                if (transitions.size() != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
