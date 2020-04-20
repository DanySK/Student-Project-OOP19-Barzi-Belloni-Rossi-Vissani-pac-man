package model;

/**
 * The Interface Ghost.
 */
public interface Ghost extends Entity {

    /**
     * Creates the Ghost.
     */
    void create();

    /**
     * Sets the eatable.
     *
     * @param eatable the new eatable
     */
    void setEatable(boolean eatable);

    /**
     * Gets the name.
     *
     * @return the name
     */
    Ghosts getName();

    /**
     *Notify Blinky's death.
     *
     */
    void blinkyIsDead();

}
