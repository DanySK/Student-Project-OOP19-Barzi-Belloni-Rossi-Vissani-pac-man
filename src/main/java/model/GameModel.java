package model;

import java.util.Map;
import java.util.Set;

/**
 * This interface represents the main class of the model.
 * Its purpose is to manage all the logic of the game, such as the position of the entities, the scores, etc.
 */
public interface GameModel {
    /**
     * @param direction of Pac-Man
     * sets the direction of Pac-Man
     */
    void setPacManDirection(Directions direction);
    /**
     * @return a Set containing all the entities of the game
     */
    Set<Entity> getEntities();
    /**
     * @return a Map with entities as keys and their positions as values
     */
    Map<Entity, Pair<Integer, Integer>> getEntitiesPosition();
    /*
     * Moves each mobile entity to its next position;
     */
    void moveEntitiesNextPosition();
    /**
     * @param entity type
     * @param position of the entity
     */
    void addEntity(Entities entity, Pair<Integer, Integer> position);
    /**
     * @param entity to remove
     */
    void removeEntity(Entity entity);
    /*
     * Increments the level number
     */
    void incLevelTime();
    /**
     * @return the scores of the current game
     */
    int getScores();
    /**
     * @return the current level time
     */
    int getLevelTime();
    /**
     * @return the current level number
     */
    int getLevelNumber();
    /**
     * @return the remaining lives of Pac-Man
     */
    int getPacManLives();
}
