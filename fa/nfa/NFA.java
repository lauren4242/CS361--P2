package fa.nfa;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fa.State;

public class NFA implements NFAInterface{

    // Private Fields
    private Set<Character> alphabet;
    private Set<NFAState> states;
    private Map<String, NFAState> stateMap;
    private NFAState startState;
    private Set<NFAState> finalStates;

    /**
     * Constructs a new and empty NFA. It contains no states, no alphabet,
     * and no transitions.
     */
    public NFA() {
        alphabet = new LinkedHashSet<>();
        states = new LinkedHashSet<>();
        stateMap = new HashMap<>();
        finalStates = new LinkedHashSet<>();
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
        if (!alphabet.contains(symbol)) {
            alphabet.add(symbol);
        }
    }

    @Override
    public boolean accepts(String s) {
        if (startState == null) {
            return false;
        }
        NFAState current = startState;
        for (char c : s.toCharArray()) {
            current = current.getNextState(c);
            if (current == null) {
                return false;
            }
        }
        return finalStates.contains(current);
    }

    @Override
    public Set<Character> getSigma() {
        return Collections.unmodifiableSet(alphabet);
    }

    @Override
    public State getState(String name) {
        return stateMap.get(name);
    }

    @Override
    public boolean isFinal(String name) {
        NFAState s = stateMap.get(name);
        return s != null && finalStates.contains(s);
    }

    @Override
    public boolean isStart(String name) {
        NFAState s = stateMap.get(name);
        return s != null && s.equals(startState);
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addTransition'");
    }

    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }

    
}