package com.example.daniel.kamienpapiernozyce;

import java.util.Random;

public class GameModel {
    private int gamerWinsNum;
    private int computerWinsNum;

    public GameModel() {
        gamerWinsNum = 0;
        computerWinsNum = 0;
    }

    public int getGamerWinsNum() {
        return gamerWinsNum;
    }

    public void setGamerWinsNum(int gamerWinsNum) {
        this.gamerWinsNum = gamerWinsNum;
    }

    public int getComputerWinsNum() {
        return computerWinsNum;
    }

    public void setComputerWinsNum(int computerWinsNum) {
        this.computerWinsNum = computerWinsNum;
    }

    public String createRandomGesture() {
        String result = null;
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                result = "paper";
                break;
            case 1:
                result = "scissors";
                break;
            case 2:
                result = "rock";
                break;
        }
        return result;
    }
}
