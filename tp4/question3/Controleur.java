package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(null /* null est à remplacer */);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);
        push.addActionListener(new ButtonListener());
        boutons.add(add);
        add.addActionListener(new ButtonListener());
        boutons.add(sub);
        sub.addActionListener(new ButtonListener());
        boutons.add(mul);
        mul.addActionListener(new ButtonListener());
        boutons.add(div);
        div.addActionListener(new ButtonListener());
        boutons.add(clear);
        clear.addActionListener(new ButtonListener());
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String button = e.getActionCommand();
            switch(button) {
                case "push":
                    try {
                        pile.empiler(operande());
                    } catch (NumberFormatException ex) {
                        System.out.println("Ceci n'est pas une entier!");
                    } catch (PilePleineException ex) {
                        System.out.println("La pile est pleine!");
                    }
                    break;
                case "+":
                    try {
                        pile.empiler(pile.depiler() + pile.depiler());
                    } catch (PilePleineException ex) {
                        System.out.println("La pile est pleine!");
                    } catch (PileVideException ex) {
                        System.out.println("La pile est vide!");
                    }
                    break;
                case "-":
                    try {
                        pile.empiler(pile.depiler() - pile.depiler());
                    } catch (PilePleineException ex) {
                        System.out.println("La pile est pleine!");
                    } catch (PileVideException ex) {
                        System.out.println("La pile est vide!");
                    }
                    break;
                case "*":
                    try {
                        pile.empiler(pile.depiler() * pile.depiler());
                    } catch (PilePleineException ex) {
                        System.out.println("La pile est pleine!");
                    } catch (PileVideException ex) {
                        System.out.println("La pile est vide!");
                    }
                    break;
                case "/":
                    try {
                        int numerateur = pile.depiler();
                        int denominateur = pile.depiler();
                        if (denominateur == 0) {
                            pile.empiler(denominateur);
                            pile.empiler(numerateur);
                            System.out.println("Division par z�ro n'est pas possible!");
                        } else {
                            pile.empiler(numerateur / denominateur);
                        }
                    } catch (PilePleineException ex) {
                        System.out.println("La pile est pleine!");
                    } catch (PileVideException ex) {
                        System.out.println("La pile est vide!");
                    }
                    break;
                case "[]":
                    while (!pile.estVide()) {
                        try {
                            pile.depiler();
                        } catch (PileVideException ex) {
                        }
                    }
                    break;
            }
        }
    }
}