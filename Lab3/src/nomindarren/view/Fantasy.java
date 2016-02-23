package nomindarren.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import nomindarren.controller.Controller;

public class Fantasy extends JFrame {
    public static final String DROPDOWN_NAME = "dropdown";
    public enum Position {
        GOALKEEPER, DEFENDER, MIDFIELDER, STRIKER, BENCH
    }

    private static final int[][] FORMATIONS = {{4, 4, 2}, {4, 3, 3}, {3, 5, 2}, {5, 3, 2}, {3, 4, 3}, {4, 5, 1}};

    private Controller controller;

    private Dictionary<Integer, PlayerPanel> playerPanels;

    private JComboBox<Formation> jcbFormationDropdown;
    private JPanel jpPlayersPanel;
    private JPanel jpGoalkeeperPanel, jpDefenderPanel, jpMidfielderPanel, jpStrikerPanel;
    private JPanel jpBenchPanel;

    public Fantasy(Controller c) {
        super("Fantasy Football");
        this.controller = c;
        this.playerPanels = new Hashtable<Integer, PlayerPanel>();

        this.setSize(800, 900);
        this.setLayout(new BorderLayout(20, 30));

        // Create dropdown:
        Formation[] formations = getFormations();
        jcbFormationDropdown = new JComboBox<Formation>(formations);
        // Hack to set initial selection:
        jcbFormationDropdown.setEditable(true);
        jcbFormationDropdown.setSelectedItem(new Formation() {
            @Override
            public String toString() {
                return "Select a formation:";
            }
        });
        jcbFormationDropdown.setEditable(false);
        jcbFormationDropdown.addActionListener(this.controller);
        jcbFormationDropdown.setName(DROPDOWN_NAME);
        this.add(jcbFormationDropdown, BorderLayout.NORTH);

        // Create players:
        jpPlayersPanel = new JPanel();
        jpPlayersPanel.setLayout(new GridLayout(0, 1));
        jpGoalkeeperPanel = new JPanel(); jpPlayersPanel.add(jpGoalkeeperPanel);
        jpDefenderPanel = new JPanel(); jpPlayersPanel.add(jpDefenderPanel);
        jpMidfielderPanel = new JPanel(); jpPlayersPanel.add(jpMidfielderPanel);
        jpStrikerPanel = new JPanel(); jpPlayersPanel.add(jpStrikerPanel);
        this.add(jpPlayersPanel, BorderLayout.CENTER);

        // Create bench:
        jpBenchPanel = new JPanel();
        this.add(jpBenchPanel, BorderLayout.SOUTH);
    }

    public static Formation[] getFormations() {
        Formation[] f = new Formation[6];
        for (int i = 0; i < FORMATIONS.length; i++) {
            f[i] = new Formation();
            f[i].formation = FORMATIONS[i];
        }
        return f;
    }

    public void addPlayer(String name, int id, String path, Position position) {
        PlayerPanel newPanel = new PlayerPanel(name, id, path, this.controller);
        playerPanels.put(id, newPanel);

        switch(position) {
        case GOALKEEPER:
            jpGoalkeeperPanel.add(newPanel);
            break;
        case DEFENDER:
            jpDefenderPanel.add(newPanel);
            break;
        case MIDFIELDER:
            jpMidfielderPanel.add(newPanel);
            break;
        case STRIKER:
            jpStrikerPanel.add(newPanel);
            break;
        case BENCH:
            jpBenchPanel.add(newPanel);
            break;
        }

        repaint(); revalidate();
    }

    public void updatePlayer(String name, int id, String path) {
        PlayerPanel playerPanel = playerPanels.get(id);
        playerPanel.setPlayerName(name);
        playerPanel.setPlayerPath(path);
    }

    public void clearPlayers() {
        jpGoalkeeperPanel.removeAll();
        jpDefenderPanel.removeAll();
        jpMidfielderPanel.removeAll();
        jpStrikerPanel.removeAll();
        jpBenchPanel.removeAll();
        repaint(); revalidate();
    }

    public static class Formation {
        public int[] formation;

        @Override
        public String toString() {
            String s = "";
            for (int i : formation) {
                s += i + "-";
            }
            return s.substring(0, s.length() - 1);
        }

        public int goalkeepers() {
            return 1;
        }
        public int defenders() {
            return formation[0];
        }
        public int midfielders() {
            return formation[1];
        }
        public int strikers() {
            return formation[2];
        }
    }
}
