package Game;

public enum Zone {
    
    BATTLEFIELD,
    COMMAND,
    EXILE,
    // face down exile is effectively a different zone for implementation purposes
    // cards target face down exile very differently
    EXILEFACEDOWN,
    GRAVEYARD,
    HAND,
    LIBRARY,
    STACK
}
