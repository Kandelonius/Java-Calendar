package com.kandel.sudoku.buildlogic;

import com.kandel.sudoku.computationlogic.GameLogic;
import com.kandel.sudoku.persistence.LocalStorageImpl;
import com.kandel.sudoku.problemdomain.IStorage;
import com.kandel.sudoku.problemdomain.SudokuGame;
import com.kandel.sudoku.userinterface.IUserInterfaceContract;
import com.kandel.sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }
        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
