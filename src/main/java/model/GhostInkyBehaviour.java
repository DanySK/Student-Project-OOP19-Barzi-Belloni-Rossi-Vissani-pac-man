package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
* this class implements the Inky behaviour.
*
*/
public class GhostInkyBehaviour extends GhostAbstractBehaviour {

    private final Set<Pair<Integer, Integer>> setWall;
    private Pair<Integer, Integer> chaseTarget;
    private final List<Pair<Integer, Integer>> ghostHouse;

    public GhostInkyBehaviour(final Set<Pair<Integer, Integer>> setWall, final Set<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize, final Pair<Integer, Integer> relaxTarget) {
        super(setWall, xMapSize, yMapSize);
        this.setWall = setWall;
        this.setRelaxTarget(relaxTarget);
        this.ghostHouse = new ArrayList<>(ghostHouse);
        this.setStartPosition(this.ghostHouse.get(3));
    }

    private void targetPosition(final PacMan pacMan, final Optional<Pair<Integer, Integer>> blinkyPosition) {
        final Pair<Integer, Integer> pacManPosition = pacMan.getPosition();
        Pair<Integer, Integer> appo;
        int targetX;
        int targetY;
        final Directions pacManDirection = pacMan.getDirection();
        if (pacManDirection.equals(Directions.UP)) {
            appo = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() + 2);
        } else if (pacManDirection.equals(Directions.RIGHT)) {
            appo = new PairImpl<>(pacManPosition.getX() + 2, pacManPosition.getY());
        } else if (pacManDirection.equals(Directions.DOWN)) {
            appo = new PairImpl<>(pacManPosition.getX(), pacManPosition.getY() - 2);
        } else {
            appo = new PairImpl<>(pacManPosition.getX() - 2, pacManPosition.getY());
        }
        if (blinkyPosition.get().getX() <= appo.getX()) {
            targetX = blinkyPosition.get().getX() + (Math.abs(appo.getX() - blinkyPosition.get().getX()) * 2);
        } else {
            targetX = blinkyPosition.get().getX() - (Math.abs(appo.getX() - blinkyPosition.get().getX()) * 2);
        }
        if (blinkyPosition.get().getY() <= appo.getY()) {
            targetY = blinkyPosition.get().getY() + (Math.abs(appo.getY() - blinkyPosition.get().getY()) * 2);
        } else {
            targetY = blinkyPosition.get().getY() - (Math.abs(appo.getY() - blinkyPosition.get().getY()) * 2);
        }
        this.chaseTarget = new PairImpl<>(targetX, targetY);
        if (this.chaseTarget.getX() >= getxMapSize()) {
            this.chaseTarget = new PairImpl<>(getxMapSize() - 1, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getX() < 0) {
            this.chaseTarget = new PairImpl<>(0, this.chaseTarget.getY());
        }
        if (this.chaseTarget.getY() >= getyMapSize()) {
            this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), getyMapSize() - 1);
        }
        if (this.chaseTarget.getY() < 0) {
            this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), 0);
        }
        if (this.setWall.contains(this.chaseTarget) || this.getCurrentPosition().equals(this.chaseTarget)) {
            if (this.chaseTarget.getY() + 1 < this.getyMapSize() && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() + 1);
            } else if (this.chaseTarget.getX() + 1 < this.getxMapSize() && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY()))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() + 1, this.chaseTarget.getY());
            } else if (this.chaseTarget.getX() - 1 >= 0 && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY()))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX() - 1, this.chaseTarget.getY());
            } else if (this.chaseTarget.getY() - 1 >= 0 && !this.setWall.contains(new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1))) {
                this.chaseTarget = new PairImpl<>(this.chaseTarget.getX(), this.chaseTarget.getY() - 1);
            }
        }
    }

    @Override
    public final void chase(final PacMan pacMan, final Optional<Pair<Integer, Integer>> blinkyPosition) {
        if (!moveIfStuck()) {
            if (blinkyPosition.isEmpty()) {
                this.chaseTarget = pacMan.getPosition();
            } else {
                this.targetPosition(pacMan, blinkyPosition);
            }
            this.findPath(this.chaseTarget);
            this.move(this.chaseTarget, 1);
        }
    }
}
