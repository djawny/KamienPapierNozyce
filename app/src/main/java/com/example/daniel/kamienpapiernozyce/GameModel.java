package com.example.daniel.kamienpapiernozyce;

import java.util.Random;

public class GameModel {
    private int gamerScore;
    private int computerScore;

    public GameModel() {
        resetWins();
    }

    private void resetWins() {
        gamerScore = 0;
        computerScore = 0;
    }

    public int getGamerScore() {
        return gamerScore;
    }

    public int getComputerScore() {
        return computerScore;
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

    public String checkWinningConditions(String gamerGesture, String computerGesture) {
        String winMsg = "Remis!";
        if (!gamerGesture.equals(computerGesture)) {
            if (gamerGesture.equals("paper") && computerGesture.equals("rock")
                    || gamerGesture.equals("scissors") && computerGesture.equals("paper")
                    || gamerGesture.equals("rock") && computerGesture.equals("scissors")) {
                winMsg = "Gracz wygrywa!";
                gamerScore += 1;
            } else {
                winMsg = "Computer wygrywa!";
                computerScore += 1;
            }
        }
        return winMsg;
    }
}
