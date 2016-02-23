package nomindarren.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;

import nomindarren.model.Player;
import nomindarren.model.Squad;
import nomindarren.view.Fantasy;
import nomindarren.view.PlayerPanel;

public class Controller implements ActionListener {
    public static final String NULL_PATH = "None";

    private Squad squad;
    private Fantasy fantasy;

    private final JFileChooser fc = new JFileChooser();

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

    private void updateName(int id, String name) {
        Player player = squad.getPlayerById(id);
        player.setName(name);

        fantasy.updatePlayer(player.getName(), player.getId(), player.getPath());
    }

    private void updatePath(int id, String path) {
        Player player = squad.getPlayerById(id);
        player.setPath(path);

        fantasy.updatePlayer(player.getName(), player.getId(), player.getPath());
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

        String[] names = component.getName().split(" ");

        if (names[0].equals(PlayerPanel.NAME_CHANGE)) {
            int id = Integer.parseInt(names[1]);
            JTextField textfield = (JTextField) component;
            String name = textfield.getText();

            updateName(id, name);
            return;
        }

        if (names[0].equals(PlayerPanel.IMAGE_CHANGE)) {
            int id = Integer.parseInt(names[1]);
            int result = fc.showOpenDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }
            updatePath(id, fc.getSelectedFile().getAbsolutePath());
            return;
        }
    }

    public static void main(String[] args) {
        new Controller();
    }
}
