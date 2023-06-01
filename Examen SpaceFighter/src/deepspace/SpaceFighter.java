package deepspace;

public interface SpaceFighter {
    float fire();

    float protection();

    ShotResult receiveShot(float shot);

}