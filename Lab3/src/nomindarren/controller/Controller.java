package nomindarren.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import nomindarren.model.Player;
import nomindarren.model.Squad;
import nomindarren.view.Fantasy;

public class Controller implements ActionListener {
    public static final String NULL_PATH = "None";

    private Squad squad;
    private Fantasy fantasy;

    public Controller() {
        squad = new Squad();
        fantasy = new Fantasy(this);

        fantasy.setVisible(true);
        fantasy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupFormation(Fantasy.Formation formation) {
        fantasy.clearPlayers();

        // Add starting players:
        Player[] goalkeepers = squad.getGoalkeepers();
        for (int i = 0; i < formation.goalkeepers(); i++) {
            fantasy.addPlayer(goalkeepers[i].getName(), goalkeepers[i].getId(), goalkeepers[i].getPath(), Fantasy.Position.GOALKEEPER);
        }
        Player[] defenders = squad.getDefenders();
        for (int i = 0; i < formation.defenders(); i++) {
            fantasy.addPlayer(defenders[i].getName(), defenders[i].getId(), defenders[i].getPath(), Fantasy.Position.DEFENDER);
        }
        Player[] midfielders = squad.getMidfielders();
        for (int i = 0; i < formation.midfielders(); i++) {
            fantasy.addPlayer(midfielders[i].getName(), midfielders[i].getId(), midfielders[i].getPath(), Fantasy.Position.MIDFIELDER);
        }
        Player[] strikers = squad.getStrikers();
        for (int i = 0; i < formation.strikers(); i++) {
            fantasy.addPlayer(strikers[i].getName(), strikers[i].getId(), strikers[i].getPath(), Fantasy.Position.STRIKER);
        }

        // Add benched players:
        for (int i = formation.goalkeepers(); i < Squad.NUM_GOALKEEPERS; i++) {
            fantasy.addPlayer(goalkeepers[i].getName(), goalkeepers[i].getId(), goalkeepers[i].getPath(), Fantasy.Position.BENCH);
        }
        for (int i = formation.defenders(); i < Squad.NUM_DEFENDERS; i++) {
            fantasy.addPlayer(defenders[i].getName(), defenders[i].getId(), defenders[i].getPath(), Fantasy.Position.BENCH);
        }
        for (int i = formation.midfielders(); i < Squad.NUM_MIDFIELDERS; i++) {
            fantasy.addPlayer(midfielders[i].getName(), midfielders[i].getId(), midfielders[i].getPath(), Fantasy.Position.BENCH);
        }
        for (int i = formation.strikers(); i < Squad.NUM_STRIKERS; i++) {
            fantasy.addPlayer(strikers[i].getName(), strikers[i].getId(), strikers[i].getPath(), Fantasy.Position.BENCH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = (Component) e.getSource();
        if (component.getName() == Fantasy.DROPDOWN_NAME) {
            JComboBox<Fantasy.Formation> dropdown = (JComboBox<Fantasy.Formation>) component;
            Fantasy.Formation formation = (Fantasy.Formation) dropdown.getSelectedItem();
            setupFormation(formation);
            return;
        }
    }

    public static void main(String[] args) {
        new Controller();
    }
}
