package Graphic;

import Model.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel implements ActionListener {
    JButton newGame = new JButton();
    JButton loadGame = new JButton();
    JButton scoreBoard = new JButton() ;
    JButton exit = new JButton();
    ArrayList<Listener> listeners = new ArrayList<Listener>();

    public MainPanel() {
        this.setPreferredSize( new Dimension(300 , 500));
        this.setLayout(null);

        newGame.setFont(new Font("Courier" , Font.PLAIN  , 20) );
        newGame.setText("new Game");
        newGame.setBounds(50 , 20 , 200 ,100 );
        newGame.addActionListener(this);
        newGame.setFocusable(false);

        loadGame.setFont(new Font("Courier" , Font.PLAIN  , 20) );
        loadGame.setText("load Game");
        loadGame.setBounds(50 , 140 , 200 , 100);
        loadGame.addActionListener(this);
        loadGame.setFocusable(false);

        scoreBoard.setFont(new Font("Courier" , Font.PLAIN  , 20) );
        scoreBoard.setText("score board");
        scoreBoard.setBounds(50 , 260 , 200 , 100);
        scoreBoard.addActionListener(this);
        scoreBoard.setFocusable(false);

        exit.setFont(new Font("Courier" , Font.PLAIN  , 20) );
        exit.setText("exit");
        exit.setBounds(50 , 380 , 200 , 100);
        exit.addActionListener(this);
        exit.setFocusable(false);

        this.add(newGame);
        this.add(loadGame);
        this.add(scoreBoard);
        this.add(exit);


    }
    public void addToListeners (Listener listen){
        this.listeners.add(listen);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame){
            for (Listener listen : listeners) {
                listen.listen("new game");
            }
        }
        if (e.getSource() == loadGame){
            for (Listener listen : listeners) {
                listen.listen("load game");
            }
        }
        if (e.getSource() == scoreBoard){
            for (Listener listen : listeners) {
                listen.listen("score board");
            }
        }
        if (e.getSource() == exit){
            for (Listener listen : listeners) {
                listen.listen("exit");
            }
        }

    }
}
