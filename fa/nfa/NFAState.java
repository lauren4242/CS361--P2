package fa.nfa;
import java.util.HashMap;
import java.util.Map;

import fa.State;

public class NFAState extends State {

    /**
     * Maps input symbols to their corresponding destination states
     */
    private Map<Character, NFAState> transitions;
    
    /**
     * Constructs a new NFA state with the given name.
     * 
     * @param name The name/label of the state
     */
    public NFAState(String name) {
        super(name);
        transitions = new HashMap<>();
    }
    
    /**
     * Gets the next state based on the input symbol.
     *
     * @param symbol The input symbol to follow
     * @return The destination state for the given symbol, or null if no
     * transition exists
     */
    public NFAState getNextState(char symbol) {
        return transitions.get(symbol);
    }
}