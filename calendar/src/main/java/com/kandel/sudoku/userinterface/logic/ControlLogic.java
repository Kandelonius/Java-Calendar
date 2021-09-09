package com.kandel.sudoku.userinterface.logic;

import com.kandel.sudoku.constants.GameState;
import com.kandel.sudoku.constants.Messages;
import com.kandel.sudoku.problemdomain.IStorage;
import com.kandel.sudoku.problemdomain.SudokuGame;
import com.kandel.sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;
    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try {
            SudokuGame gameData = storage.getGameData();
            int[][] newGridState = gameData.getCopyofGridState();
            newGridState[x][y] = input;

            gameData = new SudokuGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState
            );

            storage.updateGameData(gameData);

            view.updateSquare(x, y, input);

            if (gameData.getGameState() == GameState.COMPLETE) {
                view.showDialog(Messages.GAME_COMPLETE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    @Override
    public void onDialogClick() {
        try {
            storage.updateGameData(
                    GameLogic.getNewGame()
            );
        } catch (IOException e) {
            view.showError(Messages.ERROR);
        }
    }
}
