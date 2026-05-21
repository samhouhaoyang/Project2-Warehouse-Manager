package enums;

/**
 * Represents the result of attempting to move a forklift.
 */
public enum MovementResult {
    /** The forklift moved successfully. */
    MOVED,

    /** The forklift attempted to move into a wall. */
    WALL_HIT,

    /** The forklift attempted to move into a restricted cell. */
    RESTRICTED_HIT,

    /** The movement input was not recognised. */
    INVALID_INPUT
}
