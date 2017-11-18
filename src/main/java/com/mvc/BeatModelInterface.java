package com.mvc;

public interface BeatModelInterface {
    void initialize();

    void on();
    void off();

    void setBPM(int bpm);
    int getBPM();
}
