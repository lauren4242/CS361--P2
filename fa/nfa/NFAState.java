package fa.nfa;

import fa.State;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NFAState extends State {

    private final Map<Character, Set<NFAState>> transitions;

    /**
     * Default constructor
     */
    public NFAState() {
        super();
        transitions = new HashMap<>();
    }

    /**
     * Constructor with name parameter
     *
     * @param name the name of the state
     */
    public NFAState(String name) {
        super(name);
        transitions = new HashMap<>();
    }

    /**
     * Adds a transition from this state to a target state on a given symbol
     *
     * @param onSymb the symbol for this transition
     * @param toState the target state
     */
    public void addTransition(char onSymb, NFAState toState) {
        transitions.computeIfAbsent(onSymb, k -> new HashSet<>()).add(toState);
    }

    /**
     * Gets all states that can be reached from this state on the given symbol
     *
     * @param onSymb the symbol to transition on
     * @return set of states reachable on the given symbol, empty set if none
     */
    public Set<NFAState> getToStates(char onSymb) {
        return transitions.getOrDefault(onSymb, new HashSet<>());
    }

    /**
     * Gets all transition symbols from this state
     *
     * @return set of all symbols that have transitions from this state
     */
    public Set<Character> getTransitionSymbols() {
        return transitions.keySet();
    }
}
