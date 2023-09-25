import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    private JLabel playerWinsLabel;
    private JLabel computerWinsLabel;
    private JLabel tiesLabel;
    private JTextArea resultsTextArea;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose your move"));

        JButton rockButton = new JButton("Rock");
        JButton paperButton = new JButton("Paper");
        JButton scissorsButton = new JButton("Scissors");
        JButton quitButton = new JButton("Quit");

        // Add action listeners to buttons
        rockButton.addActionListener(new MoveListener("Rock"));
        paperButton.addActionListener(new MoveListener("Paper"));
        scissorsButton.addActionListener(new MoveListener("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);

        // Create a panel for statistics
        JPanel statsPanel = new JPanel(new GridLayout(3, 2));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));

        playerWinsLabel = new JLabel("Player Wins: 0");
        computerWinsLabel = new JLabel("Computer Wins: 0");
        tiesLabel = new JLabel("Ties: 0");

        statsPanel.add(playerWinsLabel);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(tiesLabel);

        // Create a panel for results
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));

        resultsTextArea = new JTextArea();
        resultsTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        resultsPanel.add(scrollPane);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private class MoveListener implements ActionListener {
        private String playerMove;

        public MoveListener(String move) {
            this.playerMove = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"Rock", "Paper", "Scissors"};
            String computerMove = options[new Random().nextInt(3)];

            String result = determineWinner(playerMove, computerMove);

            resultsTextArea.append(result + "\n");

            if (result.contains("Player Wins")) {
                playerWins++;
            } else if (result.contains("Computer Wins")) {
                computerWins++;
            } else {
                ties++;
            }

            updateStatistics();
        }
    }

    private String determineWinner(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "It's a tie!";
        } else if (
                (playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
                        (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
                        (playerMove.equals("Scissors") && computerMove.equals("Paper"))
        ) {
            return "Player Wins: " + playerMove + " beats " + computerMove;
        } else {
            return "Computer Wins: " + computerMove + " beats " + playerMove;
        }
    }

    private void updateStatistics() {
        playerWinsLabel.setText("Player Wins: " + playerWins);
        computerWinsLabel.setText("Computer Wins: " + computerWins);
        tiesLabel.setText("Ties: " + ties);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}